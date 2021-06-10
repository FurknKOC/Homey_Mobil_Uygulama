package com.furkan.homey.controller;

public abstract class BaseController {

    protected static final String LOGIN = "/login";
    protected static final String USER_INFORMATION = "/user";
    protected static final String PUBLIC_CREATE_USER = "/public/create/user";
    protected static final String CREATE_APART = "/apart";
    protected static final String GET_ALL_APART = "get/all/apart";
    protected static final String CREATE_DORMITORY = "/dormitory";
    protected static final String REGISTER_USER_HOUSE = "/userhouse";
    protected static final String CREATE_COMPLAINT = "/complaint";
    protected static final String GET_ALL_COMPLAINT = "/get/all/complaint";
    protected static final String GET_ALL_COMPLAINT_MANAGER = "/get/all/manager/complaint";
    protected static final String SEND_MAIL = "/sendmail";
    protected static final String SEND_UUID_FOR_APART = "/send/uuid/for/apart";
    protected static final String CREATE_TOOL = "/tool";
    protected static final String CREATE_RESERVATION = "/reservation";
    protected static final String GET_RESERVATIONS = "/get/reservations";
    protected static final String GET_TOOLS = "/get/tools";
    protected static final String SMART_DEVICE = "/smart/device";
    protected static final String COMPLAINT_TYPE_WORKING_ON = "/complaint/working/on";
    protected static final String COMPLAINT_TYPE_SOLVED = "/complaint/solved";
}
