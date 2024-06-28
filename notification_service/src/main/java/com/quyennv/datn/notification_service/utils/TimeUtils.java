package com.quyennv.datn.notification_service.utils;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class TimeUtils {

    public static Timestamp toTimeStampz(String time) {
        if (time == null) {
            return null;
        }
        return Timestamp.valueOf(ZonedDateTime.parse(time).toLocalDateTime());
    }
}
