package com.quyennv.lms.adapter.jpa.repositories;

import com.quyennv.lms.adapter.jpa.entities.ConversationData;
import com.quyennv.lms.core.domain.entities.Conversation;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.conversation.ConversationRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ConversationRepositoryImpl implements ConversationRepository {
    private final JpaConversationRepository jpaConversationRepository;

    public ConversationRepositoryImpl(JpaConversationRepository jpaConversationRepository) {
        this.jpaConversationRepository = jpaConversationRepository;
    }

    @Override
    @Transactional
    public Conversation save(Conversation conversation) {
        return jpaConversationRepository.save(ConversationData.from(conversation)).fromThis();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Conversation> findById(Identity id) {
        return jpaConversationRepository.findById(id.getId()).map(ConversationData::fromThis);
    }
}
