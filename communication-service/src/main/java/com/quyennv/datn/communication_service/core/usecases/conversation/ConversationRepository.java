package com.quyennv.datn.communication_service.core.usecases.conversation;

import com.quyennv.datn.communication_service.core.domain.entities.Conversation;
import com.quyennv.datn.communication_service.core.domain.entities.Identity;

import java.util.Optional;

public interface ConversationRepository {
    Conversation save(Conversation conversation);
    Optional<Conversation> findById(Identity id );
}
