package com.quyennv.datn.notification_service.utils;

import java.util.UUID;

public class UuidUtils {

    public static UUID fromString(String uuid) {
        if (uuid == null) {
            return null;
        }
        return UUID.fromString(uuid);
    }
}
