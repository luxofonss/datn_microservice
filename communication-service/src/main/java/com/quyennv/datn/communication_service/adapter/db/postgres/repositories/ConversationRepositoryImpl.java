package com.quyennv.datn.communication_service.adapter.db.postgres.repositories;

import com.quyennv.datn.communication_service.adapter.db.postgres.entities.ConversationData;
import com.quyennv.datn.communication_service.core.domain.entities.Conversation;
import com.quyennv.datn.communication_service.core.domain.entities.Identity;
import com.quyennv.datn.communication_service.core.usecases.conversation.ConversationRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Override
    @Transactional(readOnly = true)
    public List<Conversation> findByParentId(Identity parentId) {
        return jpaConversationRepository.findByTargetPlacementId(parentId.getId()).stream()
                .map(ConversationData::fromThis)
                .toList();
    }
}
