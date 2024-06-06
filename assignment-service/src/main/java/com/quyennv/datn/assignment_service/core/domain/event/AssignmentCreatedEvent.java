package com.quyennv.datn.assignment_service.core.domain.event;

public class AssignmentCreatedEvent {
    private final String assignmentId;
    private final String lessonId;
    private final String title;

    public AssignmentCreatedEvent(String assignmentId, String lessonId, String title) {
        this.assignmentId = assignmentId;
        this.lessonId = lessonId;
        this.title = title;
    }

    public String getAssignmentId() {
        return this.assignmentId;
    }

    public String getLessonId() {
        return this.lessonId;
    }

    public String getTitle() {
        return this.title;
    }
}
