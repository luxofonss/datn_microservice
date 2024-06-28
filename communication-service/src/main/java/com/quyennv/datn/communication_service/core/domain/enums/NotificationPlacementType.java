package com.quyennv.datn.communication_service.core.domain.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum NotificationPlacementType {
    LESSON("LESSON"),
    COURSE("COURSE");

    private final String value;

    NotificationPlacementType(String value) {
        this.value = value;
    }
}
