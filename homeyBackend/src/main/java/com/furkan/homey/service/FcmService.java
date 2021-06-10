package com.furkan.homey.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.furkan.homey.model.dto.PushNotificationRequest;
import com.furkan.homey.model.dto.PushNotificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class FcmService extends BaseService{

    private static final String FIREBASE_SERVER_KEY = "AAAADYXQoA4:APA91bH9pWBbYKPXn4tpu9DaQcl_hTi_dDEyPhE9sSdSTPnSWw21vhgNAApqrwgceXjqqp3Eqid4imsncs1YAJXHS0-UWg_bKYliwNsZnNablQNwMGCfvgErqt5Mejce-PHkGcVgGhr8";

    private final SmartDeviceService smartDeviceService;

    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public PushNotificationResponse fcmPush(String token, String title, String message) throws Exception {
        URL url = new URL(FIREBASE_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        String authKey = FIREBASE_SERVER_KEY;


        if (authKey == null) {
            throw new IllegalArgumentException(" Push notification failed. Auth key is null.");
        }

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + authKey);
        conn.setRequestProperty("Content-Type", "application/json");

        PushNotificationRequest request = new PushNotificationRequest();
        request.getNotification().setTitle(title);
        request.getNotification().setBody(message);
        request.setPriority("high");
        request.setTo(token.trim());

        try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
            wr.write(objectMapper.writeValueAsString(request));
            wr.flush();
        }

        String inputLine;
        StringBuilder response = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        PushNotificationResponse pushNotificationResponse = objectMapper.readValue(response.toString(), PushNotificationResponse.class);
        if (pushNotificationResponse.getSuccess() > 0) {
            //TODO: log duzelt
            log.info("Push notification success , device token: " + token);
        } else {
            //TODO: log duzelt

            log.error("User id  . Push notification failed! Response: " + pushNotificationResponse.toString());
            for (Object result : pushNotificationResponse.getResults()) {
                if (result instanceof LinkedHashMap) {
                    LinkedHashMap linkedHashMap = (LinkedHashMap) result;
                    if (linkedHashMap.containsValue("NotRegistered")
                            || linkedHashMap.containsValue("InvalidRegistration")
                            || linkedHashMap.containsValue("MismatchSenderId")) {
                        //TODO: log duzelt
                        log.info("Deleting user device , device token: " + token);
                        smartDeviceService.newTxInactiveFcmToken(token);
                    }
                }
            }
        }

        return pushNotificationResponse;
    }

}
