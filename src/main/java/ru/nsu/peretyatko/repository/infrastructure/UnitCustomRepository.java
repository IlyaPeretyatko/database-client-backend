package ru.nsu.peretyatko.repository.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.nsu.peretyatko.model.infrastructure.*;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UnitCustomRepository {

    private final EntityManager entityManager;

    public List<Unit> findUnitsByDivisionId(Integer divisionId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> criteriaQuery = criteriaBuilder.createQuery(Unit.class);
        Root<Unit> unitRoot = criteriaQuery.from(Unit.class);
        Join<Unit, DivisionUnit> divisionUnitJoin = unitRoot.join("divisionUnits");
        Join<DivisionUnit, Division> divisionJoin = divisionUnitJoin.join("division");
        criteriaQuery.select(unitRoot)
                .where(criteriaBuilder.equal(divisionJoin.get("id"), divisionId));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Unit> findUnitsByBrigadeId(Integer brigadeId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> criteriaQuery = criteriaBuilder.createQuery(Unit.class);
        Root<Unit> unitRoot = criteriaQuery.from(Unit.class);
        Join<Unit, BrigadeUnit> brigadeUnitJoin = unitRoot.join("brigadeUnits");
        Join<BrigadeUnit, Division> divisionJoin = brigadeUnitJoin.join("brigade");
        criteriaQuery.select(unitRoot)
                .where(criteriaBuilder.equal(divisionJoin.get("id"), brigadeId));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Unit> findUnitsByCorpsId(Integer corpsId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> criteriaQuery = criteriaBuilder.createQuery(Unit.class);
        Root<Unit> unitRoot = criteriaQuery.from(Unit.class);
        Join<Unit, CorpsUnit> corpsUnitJoin = unitRoot.join("corpsUnits");
        Join<CorpsUnit, Division> corpsJoin = corpsUnitJoin.join("corps");
        criteriaQuery.select(unitRoot)
                .where(criteriaBuilder.equal(corpsJoin.get("id"), corpsId));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Unit> findUnitsByArmyId(Integer armyId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> criteriaQuery = criteriaBuilder.createQuery(Unit.class);
        Root<Unit> unitRoot = criteriaQuery.from(Unit.class);
        Subquery<Unit> divisionSubquery = criteriaQuery.subquery(Unit.class);
        Root<Unit> divisionUnitRoot = divisionSubquery.from(Unit.class);
        Join<Unit, DivisionUnit> divisionUnitJoin = divisionUnitRoot.join("divisionUnits");
        Join<DivisionUnit, Division> divisionJoin = divisionUnitJoin.join("division");
        Join<Division, ArmyDivision> armyDivisionJoin = divisionJoin.join("armyDivisions");
        divisionSubquery.select(divisionUnitRoot)
                .where(criteriaBuilder.equal(armyDivisionJoin.get("army").get("id"), armyId));
        Subquery<Unit> brigadeSubquery = criteriaQuery.subquery(Unit.class);
        Root<Unit> brigadeUnitRoot = brigadeSubquery.from(Unit.class);
        Join<Unit, BrigadeUnit> brigadeUnitJoin = brigadeUnitRoot.join("brigadeUnits");
        Join<BrigadeUnit, Brigade> brigadeJoin = brigadeUnitJoin.join("brigade");
        Join<Brigade, ArmyBrigade> armyBrigadeJoin = brigadeJoin.join("armyBrigades");
        brigadeSubquery.select(brigadeUnitRoot)
                .where(criteriaBuilder.equal(armyBrigadeJoin.get("army").get("id"), armyId));
        Subquery<Unit> corpsSubquery = criteriaQuery.subquery(Unit.class);
        Root<Unit> corpsUnitRoot = corpsSubquery.from(Unit.class);
        Join<Unit, CorpsUnit> corpsUnitJoin = corpsUnitRoot.join("corpsUnits");
        Join<CorpsUnit, Corps> corpsJoin = corpsUnitJoin.join("corps");
        Join<Corps, ArmyCorps> armyCorpsJoin = corpsJoin.join("armyCorps");
        corpsSubquery.select(corpsUnitRoot)
                .where(criteriaBuilder.equal(armyCorpsJoin.get("army").get("id"), armyId));
        criteriaQuery.select(unitRoot)
                .where(criteriaBuilder.or(
                        criteriaBuilder.in(unitRoot).value(divisionSubquery),
                        criteriaBuilder.in(unitRoot).value(brigadeSubquery),
                        criteriaBuilder.in(unitRoot).value(corpsSubquery)
                ));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
