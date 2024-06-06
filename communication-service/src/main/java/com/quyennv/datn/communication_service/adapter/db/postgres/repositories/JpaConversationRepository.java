package com.quyennv.datn.communication_service.adapter.db.postgres.repositories;

import com.quyennv.datn.communication_service.adapter.db.postgres.entities.ConversationData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaConversationRepository extends JpaRepository<ConversationData, UUID> {
    List<ConversationData> findByTargetPlacementId(UUID targetPlacementId);
}
