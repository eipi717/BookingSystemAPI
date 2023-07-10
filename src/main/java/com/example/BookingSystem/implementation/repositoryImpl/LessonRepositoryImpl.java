package com.example.BookingSystem.implementation.repositoryImpl;

import com.example.BookingSystem.entity.BSUser;
import com.example.BookingSystem.entity.Lesson;
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

import java.util.List;
@Component
public class LessonRepositoryImpl {
    @PersistenceContext
    private EntityManager em;

    private final Logger logger = LoggerFactory.getLogger(LessonRepositoryImpl.class);

    public Lesson findByLessonName(String lessonName) {
        Lesson lesson = new Lesson();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Lesson> criteriaQuery = builder.createQuery(Lesson.class);
        Root<Lesson> root = criteriaQuery.from(Lesson.class);

        Predicate predicate = filterCriteriaBuilder(lessonName, builder, root);

        criteriaQuery.select(root);
        criteriaQuery.where(predicate);
        TypedQuery<Lesson> query = em.createQuery(criteriaQuery);

        try {
            lesson = query.getSingleResult();
            return lesson;
        } catch (Exception e) {
            logger.error("User not found!");
            return null;
        }
    }


    public List<Lesson> findByCriteria(int page, int size) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Lesson> criteriaQuery = builder.createQuery(Lesson.class);
        Root<Lesson> root = criteriaQuery.from(Lesson.class);

        Predicate predicate = filterCriteriaBuilder("", builder, root);

        criteriaQuery.select(root);
        criteriaQuery.where(predicate);
        TypedQuery<Lesson> query = em.createQuery(criteriaQuery)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size);

        return query.getResultList();
    }

    public long countByCriteria() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Lesson> root = criteriaQuery.from(Lesson.class);

        Predicate predicate = filterCriteriaBuilder("", builder, root);

        criteriaQuery.select(builder.count(root));
        criteriaQuery.where(predicate);
        return em.createQuery(criteriaQuery).getSingleResult();
    }

    private Predicate filterCriteriaBuilder(String lessonName, CriteriaBuilder builder, Root<Lesson> root) {
        Predicate predicate = builder.conjunction();

        if(lessonName != null && !lessonName.isEmpty()) {
            Predicate predFilter = builder.equal(root.get("lessonName"), lessonName);
            predicate = builder.and(predicate, predFilter);
        }

        return predicate;
    }
}
