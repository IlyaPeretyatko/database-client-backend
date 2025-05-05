package ru.nsu.peretyatko.repository.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.nsu.peretyatko.model.infrastructure.Corps;
import ru.nsu.peretyatko.model.infrastructure.CorpsUnit;

@Repository
@RequiredArgsConstructor
public class CorpsCustomRepository {

    private final EntityManager entityManager;

    public Corps findCorpsWithMostUnits() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Corps> cq = cb.createQuery(Corps.class);
        Subquery<Long> unitCountSubquery = cq.subquery(Long.class);
        Root<CorpsUnit> corpsUnit = unitCountSubquery.from(CorpsUnit.class);
        unitCountSubquery.select(cb.count(corpsUnit.get("unit").get("id")))
                .groupBy(corpsUnit.get("corps").get("id"));
        Root<Corps> corps = cq.from(Corps.class);
        Join<Corps, CorpsUnit> corpsUnitJoin = corps.join("corpsUnits");
        cq.select(corps)
                .groupBy(corps.get("id"))
                .orderBy(cb.desc(cb.count(corpsUnitJoin.get("unit").get("id"))));
        return entityManager.createQuery(cq)
                .setMaxResults(1)
                .getSingleResult();
    }

    public Corps findCorpsWithFewestUnits() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Corps> cq = cb.createQuery(Corps.class);
        Subquery<Long> unitCountSubquery = cq.subquery(Long.class);
        Root<CorpsUnit> corpsUnit = unitCountSubquery.from(CorpsUnit.class);
        unitCountSubquery.select(cb.count(corpsUnit.get("unit").get("id")))
                .groupBy(corpsUnit.get("corps").get("id"));
        Root<Corps> corps = cq.from(Corps.class);
        Join<Corps, CorpsUnit> corpsUnitJoin = corps.join("corpsUnits");
        cq.select(corps)
                .groupBy(corps.get("id"))
                .orderBy(cb.asc(cb.count(corpsUnitJoin.get("unit").get("id"))));
        return entityManager.createQuery(cq)
                .setMaxResults(1)
                .getSingleResult();
    }

}
