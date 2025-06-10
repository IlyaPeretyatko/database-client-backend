package ru.nsu.peretyatko.repository.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.nsu.peretyatko.model.infrastructure.Division;
import ru.nsu.peretyatko.model.infrastructure.DivisionUnit;

@Repository
@RequiredArgsConstructor
public class DivisionCustomRepository {

    private final EntityManager entityManager;

    public Division findDivisionWithMostUnits() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Division> cq = cb.createQuery(Division.class);
        Subquery<Long> unitCountSubquery = cq.subquery(Long.class);
        Root<DivisionUnit> divisionUnit = unitCountSubquery.from(DivisionUnit.class);
        unitCountSubquery.select(cb.count(divisionUnit.get("unit").get("id")))
                .groupBy(divisionUnit.get("division").get("id"));
        Root<Division> division = cq.from(Division.class);
        Join<Division, DivisionUnit> divisionUnitJoin = division.join("divisionUnits");
        cq.select(division)
                .groupBy(division.get("id"))
                .orderBy(cb.desc(cb.count(divisionUnitJoin.get("unit").get("id"))));
        return entityManager.createQuery(cq)
                .setMaxResults(1)
                .getSingleResult();
    }

    public Division findDivisionWithFewestUnits() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Division> cq = cb.createQuery(Division.class);
        Subquery<Long> unitCountSubquery = cq.subquery(Long.class);
        Root<DivisionUnit> divisionUnit = unitCountSubquery.from(DivisionUnit.class);
        unitCountSubquery.select(cb.count(divisionUnit.get("unit").get("id")))
                .groupBy(divisionUnit.get("division").get("id"));
        Root<Division> division = cq.from(Division.class);
        Join<Division, DivisionUnit> divisionUnitJoin = division.join("divisionUnits");
        cq.select(division)
                .groupBy(division.get("id"))
                .orderBy(cb.asc(cb.count(divisionUnitJoin.get("unit").get("id"))));
        return entityManager.createQuery(cq)
                .setMaxResults(1)
                .getSingleResult();
    }
}
