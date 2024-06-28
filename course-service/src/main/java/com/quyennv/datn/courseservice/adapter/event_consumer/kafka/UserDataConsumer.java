package com.quyennv.datn.courseservice.adapter.event_consumer.kafka;

import com.quyennv.datn.courseservice.adapter.event_consumer.kafka.cdc.MessageCDC;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.entities.User;
import com.quyennv.datn.courseservice.adapter.event_consumer.kafka.cdc.user.UserAfter;
import com.quyennv.datn.courseservice.adapter.event_consumer.kafka.cdc.user.UserRepository;
import com.quyennv.datn.courseservice.presenter.utils.DateHelper;
import com.quyennv.datn.courseservice.presenter.utils.JsonHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class UserDataConsumer {
    private final UserRepository userRepository;

    public UserDataConsumer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener (
            topics = "users_course.public.users",
            groupId = "users_course-group",
            containerFactory = "userKafkaListenerContainerFactory"
    )
    public void userDataListener(MessageCDC message) {
        if (message.getOp().equals("c") || message.getOp().equals("u")) {
            UserAfter newUser = message.getAfter();
            User user = new User();
            user.setId(Identity.fromString(newUser.getId()));
            user.setAvatar(newUser.getAvatar());
            user.setEmail(newUser.getEmail());
            user.setGender(newUser.getGender());
            user.setDateOfBirth(DateHelper.toLocalDateTime(newUser.getDob()));
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPhoneNumber(newUser.getPhoneNumber());
            user.setRole(newUser.getRole());
            user.setUsername(newUser.getUsername());
            user.setIsVerified(JsonHelper.toBoolean(newUser.getVerified()));
            user.setCreatedAt(DateHelper.toLocalDateTime(newUser.getCreatedAt()));
            user.setUpdatedAt(DateHelper.toLocalDateTime(newUser.getUpdatedAt()));
            user.setDeletedAt(DateHelper.toLocalDateTime(newUser.getDeletedAt()));

            userRepository.persist(user);

        } else if (message.getOp().equals("d")) {
            userRepository.deleteById(UUID.fromString(message.getBefore().getId()));
        }
    }
}
