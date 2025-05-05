package ru.nsu.peretyatko.repository.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.nsu.peretyatko.model.infrastructure.*;

@Repository
@RequiredArgsConstructor
public class ArmyCustomRepository {

    private final EntityManager entityManager;

    public Army findArmyWithMostUnits() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Army> cq = cb.createQuery(Army.class);
        Root<Army> army = cq.from(Army.class);
        Subquery<Long> divisionCount = cq.subquery(Long.class);
        Root<ArmyDivision> ad = divisionCount.from(ArmyDivision.class);
        Join<ArmyDivision, Division> d = ad.join("division");
        Join<Division, DivisionUnit> du = d.join("divisionUnits");
        divisionCount.select(cb.countDistinct(du.get("unit")))
                .where(cb.equal(ad.get("army"), army));
        Subquery<Long> brigadeCount = cq.subquery(Long.class);
        Root<ArmyBrigade> ab = brigadeCount.from(ArmyBrigade.class);
        Join<ArmyBrigade, Brigade> b = ab.join("brigade");
        Join<Brigade, BrigadeUnit> bu = b.join("brigadeUnits");
        brigadeCount.select(cb.countDistinct(bu.get("unit")))
                .where(cb.equal(ab.get("army"), army));
        Subquery<Long> corpsCount = cq.subquery(Long.class);
        Root<ArmyCorps> ac = corpsCount.from(ArmyCorps.class);
        Join<ArmyCorps, Corps> c = ac.join("corps");
        Join<Corps, CorpsUnit> cu = c.join("corpsUnits");
        corpsCount.select(cb.countDistinct(cu.get("unit")))
                .where(cb.equal(ac.get("army"), army));
        Expression<Long> sumDivAndBrig = cb.sum(
                cb.coalesce(divisionCount, 0L),
                cb.coalesce(brigadeCount, 0L)
        );
        Expression<Long> totalUnits = cb.sum(
                sumDivAndBrig,
                cb.coalesce(corpsCount, 0L)
        );
        cq.select(army).orderBy(cb.desc(totalUnits));
        return entityManager.createQuery(cq)
                .setMaxResults(1)
                .getSingleResult();
    }

    public Army findArmyWithFewestUnits() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Army> cq = cb.createQuery(Army.class);
        Root<Army> army = cq.from(Army.class);
        Subquery<Long> divisionCount = cq.subquery(Long.class);
        Root<ArmyDivision> ad = divisionCount.from(ArmyDivision.class);
        Join<ArmyDivision, Division> d = ad.join("division");
        Join<Division, DivisionUnit> du = d.join("divisionUnits");
        divisionCount.select(cb.countDistinct(du.get("unit")))
                .where(cb.equal(ad.get("army"), army));
        Subquery<Long> brigadeCount = cq.subquery(Long.class);
        Root<ArmyBrigade> ab = brigadeCount.from(ArmyBrigade.class);
        Join<ArmyBrigade, Brigade> b = ab.join("brigade");
        Join<Brigade, BrigadeUnit> bu = b.join("brigadeUnits");
        brigadeCount.select(cb.countDistinct(bu.get("unit")))
                .where(cb.equal(ab.get("army"), army));
        Subquery<Long> corpsCount = cq.subquery(Long.class);
        Root<ArmyCorps> ac = corpsCount.from(ArmyCorps.class);
        Join<ArmyCorps, Corps> c = ac.join("corps");
        Join<Corps, CorpsUnit> cu = c.join("corpsUnits");
        corpsCount.select(cb.countDistinct(cu.get("unit")))
                .where(cb.equal(ac.get("army"), army));
        Expression<Long> sumDivAndBrig = cb.sum(
                cb.coalesce(divisionCount, 0L),
                cb.coalesce(brigadeCount, 0L)
        );
        Expression<Long> totalUnits = cb.sum(
                sumDivAndBrig,
                cb.coalesce(corpsCount, 0L)
        );
        cq.select(army).orderBy(cb.asc(totalUnits));
        return entityManager.createQuery(cq)
                .setMaxResults(1)
                .getSingleResult();
    }

}
