package com.furkan.homey.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PushNotificationResponse {
    private String multicast_id;
    private int success;
    private int failure;
    private int canonical_ids;
    private List<Object> results;

    @Override
    public String toString() {
        return "PushNotificationResponse{" +
                "success=" + success +
                ", failure=" + failure +
                ", results=" + results +
                '}';
    }
}
