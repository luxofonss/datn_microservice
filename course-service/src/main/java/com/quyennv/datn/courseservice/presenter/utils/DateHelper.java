package com.quyennv.datn.courseservice.presenter.utils;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateHelper {
    private DateHelper() {
    }
    public static LocalDateTime toLocalDateTime(String date) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return LocalDateTime.parse(date, formatter);
    }

    public static LocalDateTime toLocalDateTime(Long time) {
        if (time == null) {
            return null;
        }
        Timestamp timeStamp = new Timestamp(time / 1_000_000);
        return timeStamp.toLocalDateTime().toLocalDate().atStartOfDay();
    }
}
