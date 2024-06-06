package com.quyennv.datn.communication_service.presenter.utils;

import org.springframework.stereotype.Component;

@Component
public class JsonHelper {
    public static Boolean toBoolean(String value) {
        if (value == null) {
            return false;
        }
        if (value.equals("true")) {
            return true;
        }
        if (value.equals("True")) {
            return true;
        }
        return false;
    }
}
