package com.quyennv.datn.assignment_service.core.domain.enums;

public enum NotificationTypeEnum {
    QUESTION_ANSWER_FEEDBACK("QUESTION_ANSWER_FEEDBACK"),
    CONVERSATION_COMMENT("CONVERSATION_COMMENT"),
    CONVERSATION_CREATED("CONVERSATION_CREATED"),
    COURSE_ENROLLED("COURSE_ENROLLED"),

    STUDENT_ENROLLED_COURSE("STUDENT_ENROLLED_COURSE");

    private final String value;

    NotificationTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
