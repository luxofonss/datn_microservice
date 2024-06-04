package com.quyennv.lms.core.usecases.conversation;

import com.quyennv.lms.core.domain.entities.Conversation;
import com.quyennv.lms.core.domain.entities.Identity;

import java.util.Optional;

public interface ConversationRepository {
    Conversation save(Conversation conversation);
    Optional<Conversation> findById(Identity id );
}
