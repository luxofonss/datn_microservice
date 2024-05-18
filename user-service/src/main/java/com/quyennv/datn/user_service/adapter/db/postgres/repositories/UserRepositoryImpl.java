package com.quyennv.datn.user_service.adapter.db.postgres.repositories;

import com.quyennv.datn.user_service.adapter.db.postgres.entities.UserData;
import com.quyennv.datn.user_service.core.domain.entities.Identity;
import com.quyennv.datn.user_service.core.domain.entities.User;
import com.quyennv.datn.user_service.core.usecases.user.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;
    private final EntityManager em;
    private final CriteriaBuilder cb;
    public UserRepositoryImpl(JpaUserRepository jpaUserRepository, EntityManager em) {
        this.jpaUserRepository = jpaUserRepository;
        this.em = em;
        this.cb = em.getCriteriaBuilder();
    }


    @Override
    public Optional<User> getUserById(String userId) {
        return jpaUserRepository.findById(UUID.fromString(userId)).map(UserData::fromThis);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return jpaUserRepository.findByUsername(username).map(UserData::fromThis);
    }

    @Override
    public Optional<User> findByEmailOrUsernameOrPhoneNumber(String username, String email, String phoneNumber) {
        return jpaUserRepository.findByEmailOrUsernameOrPhoneNumber(username, email, phoneNumber).map(UserData::fromThis);
    }

    @Override
    public User persist(User user) {
        return jpaUserRepository.save(UserData.from(user)).fromThis();
    }

    @Override
    public List<User> getUsersWithConditions(
            List<String> emails,
            List<Identity> userIds,
            String keyword) {
        CriteriaQuery<UserData> criteriaQuery = cb.createQuery(UserData.class);
        Root<UserData> userRoot = criteriaQuery.from(UserData.class);

        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(emails) && !emails.isEmpty()) {
            List<Predicate> emailsPredicates = new ArrayList<>();
            emails.forEach(email -> {
                emailsPredicates.add(cb.equal(userRoot.get("email"), email));
            });
            predicates.add(cb.or(emailsPredicates.toArray(new Predicate[0])));
        }

        if (Objects.nonNull(userIds) && !userIds.isEmpty()) {
            List<Predicate> userIdsPredicates = new ArrayList<>();
            userIds.forEach(userId -> {
                userIdsPredicates.add(cb.equal(userRoot.get("id"), UUID.fromString(userId.getId().toString())));
            });
            predicates.add(cb.or(userIdsPredicates.toArray(new Predicate[0])));
        }

        if (Objects.nonNull(keyword)) {
            predicates.add(
                    cb.or(
                            cb.like(userRoot.get("username"), "%" + keyword + "%"),
                            cb.like(userRoot.get("firstName"), "%" + keyword + "%"),
                            cb.like(userRoot.get("lastName"), "%" + keyword + "%")
                    )
            );
        }

        Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[0]));
        criteriaQuery.where(finalPredicate);

        List<Order> orders = new ArrayList<>();
        orders.add(cb.desc(
                userRoot.get("updatedAt")
        ));

        criteriaQuery.orderBy(orders);
        TypedQuery<UserData> userQuery = em.createQuery(criteriaQuery);

        return userQuery.getResultList().stream().map(UserData::fromThis).toList();
    }
}
