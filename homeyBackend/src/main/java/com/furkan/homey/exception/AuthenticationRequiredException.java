package com.furkan.homey.exception;

import com.furkan.homey.utilities.exception.HomeyException;

public class AuthenticationRequiredException extends HomeyException implements IErrorCode {

    public AuthenticationRequiredException() {super(AUTH_REQUIRED, null, null);}

}
