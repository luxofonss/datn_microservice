package com.quyennv.lms.adapter.jpa.entities;

import com.quyennv.lms.core.domain.entities.Conversation;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.ConversationType;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@Entity(name="conversations")
@Table(name = "conversations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = {"user", "course", "lesson"})
@Slf4j
public class ConversationData extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private ConversationType type;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserData user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id")
    private CourseData course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lesson_id")
    private LessonData lesson;

    @OneToMany(mappedBy = "conversation", fetch = FetchType.LAZY)
    private List<CommentData> comments;

    public static ConversationData from(Conversation conversation) {
        ConversationData result = ConversationData
                .builder()
                .type(conversation.getType())
                .content(conversation.getContent())
                .build();

        if (Objects.nonNull(conversation.getId())) {
            result.setId(conversation.getId().getId());
        }

        if (Objects.nonNull(conversation.getUser())) {
            result.setUser(UserData.from(conversation.getUser()));
        }

        if (Objects.nonNull(conversation.getCourse())) {
            result.setCourse(CourseData.from(conversation.getCourse()));
        }

        if (Objects.nonNull(conversation.getLesson())) {
            result.setLesson(LessonData.from(conversation.getLesson()));
        }

        result.setCreatedAt(conversation.getCreatedAt());
        result.setUpdatedAt(conversation.getUpdatedAt());
        result.setDeletedAt(conversation.getDeletedAt());

        return result;
    }

    public Conversation fromThis() {
        Conversation result = Conversation
                .builder()
                .type(this.getType())
                .content(this.getContent())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();

        if (Objects.nonNull(this.getId())) {
            result.setId(Identity.from(this.getId()));
        }

        if (Objects.nonNull(this.getUser())) {
            result.setUser(this.getUser().fromThis());
        }

        if (Objects.nonNull(this.getComments())) {
            result.setComments(this.comments.stream().map(CommentData::fromThis).toList());
        }
        return result;
    }
}
