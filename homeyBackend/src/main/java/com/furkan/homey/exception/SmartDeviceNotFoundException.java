package com.furkan.homey.exception;

import com.furkan.homey.utilities.exception.HomeyException;

public class SmartDeviceNotFoundException extends HomeyException implements IErrorCode {
    public SmartDeviceNotFoundException() {super(SMART_DEVICE_NOT_FOUND, null, null);}
}
