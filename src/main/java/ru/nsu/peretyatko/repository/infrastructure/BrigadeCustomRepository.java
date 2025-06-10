package ru.nsu.peretyatko.repository.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.nsu.peretyatko.model.infrastructure.Brigade;
import ru.nsu.peretyatko.model.infrastructure.BrigadeUnit;

@Repository
@RequiredArgsConstructor
public class BrigadeCustomRepository {

    private final EntityManager entityManager;

    public Brigade findBrigadeWithMostUnits() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Brigade> cq = cb.createQuery(Brigade.class);
        Subquery<Long> unitCountSubquery = cq.subquery(Long.class);
        Root<BrigadeUnit> brigadeUnit = unitCountSubquery.from(BrigadeUnit.class);
        unitCountSubquery.select(cb.count(brigadeUnit.get("unit").get("id")))
                .groupBy(brigadeUnit.get("brigade").get("id"));
        Root<Brigade> brigade = cq.from(Brigade.class);
        Join<Brigade, BrigadeUnit> brigadeUnitJoin = brigade.join("brigadeUnits");
        cq.select(brigade)
                .groupBy(brigade.get("id"))
                .orderBy(cb.desc(cb.count(brigadeUnitJoin.get("unit").get("id"))));
        return entityManager.createQuery(cq)
                .setMaxResults(1)
                .getSingleResult();
    }

    public Brigade findBrigadeWithFewestUnits() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Brigade> cq = cb.createQuery(Brigade.class);
        Subquery<Long> unitCountSubquery = cq.subquery(Long.class);
        Root<BrigadeUnit> brigadeUnit = unitCountSubquery.from(BrigadeUnit.class);
        unitCountSubquery.select(cb.count(brigadeUnit.get("unit").get("id")))
                .groupBy(brigadeUnit.get("brigade").get("id"));
        Root<Brigade> brigade = cq.from(Brigade.class);
        Join<Brigade, BrigadeUnit> brigadeUnitJoin = brigade.join("brigadeUnits");
        cq.select(brigade)
                .groupBy(brigade.get("id"))
                .orderBy(cb.asc(cb.count(brigadeUnitJoin.get("unit").get("id"))));
        return entityManager.createQuery(cq)
                .setMaxResults(1)
                .getSingleResult();
    }
    
}
