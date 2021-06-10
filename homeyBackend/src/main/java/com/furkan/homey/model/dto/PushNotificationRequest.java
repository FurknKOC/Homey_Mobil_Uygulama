package com.furkan.homey.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushNotificationRequest {
    private String to;
    private String priority;
    private PushNotificationData notification = new PushNotificationData();

    private int time_to_live = 172800;
}
