package com.example.BookingSystem.implementation.repositoryImpl;

import com.example.BookingSystem.entity.Booking;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    public List<Booking> findByCriteria(String bookingUser, int page, int size, String orderBy, String direction) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Booking> criteriaQuery = builder.createQuery(Booking.class);
        Root<Booking> root = criteriaQuery.from(Booking.class);

        Predicate predicate = filterCriteriaBuilder(bookingUser, builder, root);

        criteriaQuery.select(root);
        criteriaQuery.where(predicate);
        orderByBuilder(criteriaQuery, root, orderBy, direction);
        TypedQuery<Booking> query = em.createQuery(criteriaQuery)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size);
        return query.getResultList();
    }

    public long countByCriteria(String bookingUser) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Booking> root = criteriaQuery.from(Booking.class);

        Predicate predicate = filterCriteriaBuilder(bookingUser, builder, root);

        criteriaQuery.select(builder.count(root));
        criteriaQuery.where(predicate);
        return em.createQuery(criteriaQuery).getSingleResult();

    }

    private Predicate filterCriteriaBuilder(String bookingUser, CriteriaBuilder builder, Root<Booking> root) {
        Predicate predicate = builder.conjunction();

        if(bookingUser != null && !bookingUser.isEmpty()) {
            Predicate predFilter = builder.equal(root.get("user").get("username"), bookingUser);
            predicate = builder.and(predicate, predFilter);
        }

        return predicate;
    }

    private void orderByBuilder(CriteriaQuery<Booking> criteriaQuery, Root<Booking> root, String orderBy, String direction) {
        if (orderBy != null && !orderBy.isEmpty()) {
            if (direction.equals("asc")) {
                criteriaQuery.orderBy(em.getCriteriaBuilder().asc(root.get(orderBy)));
            } else {
                criteriaQuery.orderBy(em.getCriteriaBuilder().desc(root.get(orderBy)));
            }
        }
    }
}
