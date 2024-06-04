package com.quyennv.lms.adapter.jpa.repositories;

import com.quyennv.lms.adapter.jpa.entities.ConversationData;
import com.quyennv.lms.core.domain.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaConversationRepository extends JpaRepository<ConversationData, UUID> {
}
