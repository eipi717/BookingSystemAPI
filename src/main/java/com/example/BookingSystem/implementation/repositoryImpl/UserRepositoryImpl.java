package com.example.BookingSystem.implementation.repositoryImpl;

import com.example.BookingSystem.entity.BSUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl {
    @PersistenceContext
    private EntityManager em;

    private final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    public BSUser findByUsername(String username) {
        BSUser bsUser = new BSUser();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BSUser> criteriaQuery = builder.createQuery(BSUser.class);
        Root<BSUser> root = criteriaQuery.from(BSUser.class);

        Predicate predicate = filterCriteriaBuilder(username, builder, root);

        criteriaQuery.select(root);
        criteriaQuery.where(predicate);
        TypedQuery<BSUser> query = em.createQuery(criteriaQuery);

        try {
            bsUser = query.getSingleResult();
            return bsUser;
        } catch (Exception e) {
            logger.error("User not found!");
            return null;
        }
    }

    private Predicate filterCriteriaBuilder(String username, CriteriaBuilder builder, Root<BSUser> root) {
        Predicate predicate = builder.conjunction();

        if(username != null && !username.isEmpty()) {
            Predicate predFilter = builder.equal(root.get("username"), username);
            predicate = builder.and(predicate, predFilter);
        }

        return predicate;
    }
}
