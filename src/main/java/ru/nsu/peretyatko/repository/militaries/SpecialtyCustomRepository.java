package ru.nsu.peretyatko.repository.militaries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.nsu.peretyatko.model.infrastructure.*;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.model.militaries.MilitarySpecialty;
import ru.nsu.peretyatko.model.militaries.Specialty;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpecialtyCustomRepository {

    private final EntityManager entityManager;

    public List<Specialty> findSpecialtiesInUnit(int unitId, int minCount) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Specialty> cq = cb.createQuery(Specialty.class);
        Root<Specialty> specialty = cq.from(Specialty.class);
        Join<Specialty, MilitarySpecialty> militarySpecialty = specialty.join("militarySpecialties");
        Join<MilitarySpecialty, Military> military = militarySpecialty.join("military");
        Join<Military, Unit> unit = military.join("unit");
        Predicate whereCondition = cb.equal(unit.get("id"), unitId);
        cq.groupBy(specialty.get("id"));
        Expression<Long> count = cb.count(military.get("id"));
        Predicate havingCondition = cb.gt(count, minCount);
        cq.select(specialty)
                .where(whereCondition)
                .having(havingCondition);
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Specialty> findNoSpecialtiesInUnit(int unitId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Specialty> cq = cb.createQuery(Specialty.class);
        Root<Specialty> specialty = cq.from(Specialty.class);
        Subquery<Long> subquery = cq.subquery(Long.class);
        Root<Specialty> subSpecialty = subquery.from(Specialty.class);
        Join<Specialty, MilitarySpecialty> subMilitarySpecialty = subSpecialty.join("militarySpecialties");
        Join<MilitarySpecialty, Military> subMilitary = subMilitarySpecialty.join("military");
        Join<Military, Unit> subUnit = subMilitary.join("unit");
        subquery.select(subSpecialty.get("id"))
                .where(cb.equal(subUnit.get("id"), unitId))
                .groupBy(subSpecialty.get("id"))
                .having(cb.notEqual(cb.count(subMilitary.get("id")), 0L));
        cq.select(specialty)
                .where(cb.not(cb.in(specialty.get("id")).value(subquery)));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Specialty> findSpecialtiesInDivision(int divisionId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Specialty> cq = cb.createQuery(Specialty.class);
        Root<Specialty> specialty = cq.from(Specialty.class);
        Join<Specialty, MilitarySpecialty> militarySpecialty = specialty.join("militarySpecialties");
        Join<MilitarySpecialty, Military> military = militarySpecialty.join("military");
        Join<Military, Unit> unit = military.join("unit");
        Join<Unit, DivisionUnit> divisionUnit = unit.join("divisionUnits");
        Join<DivisionUnit, Division> division = divisionUnit.join("division");
        Predicate divisionCondition = cb.equal(division.get("id"), divisionId);
        cq.groupBy(specialty.get("id"));
        Expression<Long> militaryCount = cb.count(military.get("id"));
        Predicate havingCondition = cb.gt(militaryCount, 5L);
        cq.select(specialty)
                .where(divisionCondition)
                .having(havingCondition);
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Specialty> findNoSpecialtiesInDivision(int divisionId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Specialty> cq = cb.createQuery(Specialty.class);
        Root<Specialty> specialty = cq.from(Specialty.class);
        Subquery<Long> subquery = cq.subquery(Long.class);
        Root<Specialty> subSpecialty = subquery.from(Specialty.class);
        Join<Specialty, MilitarySpecialty> subMilitarySpecialty = subSpecialty.join("militarySpecialties");
        Join<MilitarySpecialty, Military> subMilitary = subMilitarySpecialty.join("military");
        Join<Military, Unit> subUnit = subMilitary.join("unit");
        Join<Unit, DivisionUnit> subDivisionUnit = subUnit.join("divisionUnits");
        Join<DivisionUnit, Division> subDivision = subDivisionUnit.join("division");
        subquery.select(subSpecialty.get("id"))
                .where(cb.equal(subDivision.get("id"), divisionId))
                .groupBy(subSpecialty.get("id"))
                .having(cb.notEqual(cb.count(subMilitary.get("id")), 0L));
        cq.select(specialty)
                .where(cb.not(cb.in(specialty.get("id")).value(subquery)));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Specialty> findSpecialtiesInBrigade(int brigadeId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Specialty> cq = cb.createQuery(Specialty.class);
        Root<Specialty> specialty = cq.from(Specialty.class);
        Join<Specialty, MilitarySpecialty> militarySpecialty = specialty.join("militarySpecialties");
        Join<MilitarySpecialty, Military> military = militarySpecialty.join("military");
        Join<Military, Unit> unit = military.join("unit");
        Join<Unit, BrigadeUnit> brigadeUnit = unit.join("brigadeUnits");
        Join<BrigadeUnit, Brigade> brigade = brigadeUnit.join("brigade");
        Predicate brigadeCondition = cb.equal(brigade.get("id"), brigadeId);
        cq.groupBy(specialty.get("id"));
        Expression<Long> militaryCount = cb.count(military.get("id"));
        Predicate havingCondition = cb.gt(militaryCount, 5L);
        cq.select(specialty)
                .where(brigadeCondition)
                .having(havingCondition);
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Specialty> findNoSpecialtiesInBrigade(int brigadeId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Specialty> cq = cb.createQuery(Specialty.class);
        Root<Specialty> specialty = cq.from(Specialty.class);
        Subquery<Long> subquery = cq.subquery(Long.class);
        Root<Specialty> subSpecialty = subquery.from(Specialty.class);
        Join<Specialty, MilitarySpecialty> subMilitarySpecialty = subSpecialty.join("militarySpecialties");
        Join<MilitarySpecialty, Military> subMilitary = subMilitarySpecialty.join("military");
        Join<Military, Unit> subUnit = subMilitary.join("unit");
        Join<Unit, BrigadeUnit> subBrigadeUnit = subUnit.join("brigadeUnits");
        Join<BrigadeUnit, Brigade> subBrigade = subBrigadeUnit.join("brigade");
        subquery.select(subSpecialty.get("id"))
                .where(cb.equal(subBrigade.get("id"), brigadeId))
                .groupBy(subSpecialty.get("id"))
                .having(cb.notEqual(cb.count(subMilitary.get("id")), 0L));
        cq.select(specialty)
                .where(cb.not(cb.in(specialty.get("id")).value(subquery)));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Specialty> findSpecialtiesInCorps(int corpsId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Specialty> cq = cb.createQuery(Specialty.class);
        Root<Specialty> specialty = cq.from(Specialty.class);
        Join<Specialty, MilitarySpecialty> militarySpecialty = specialty.join("militarySpecialties");
        Join<MilitarySpecialty, Military> military = militarySpecialty.join("military");
        Join<Military, Unit> unit = military.join("unit");
        Join<Unit, CorpsUnit> corpsUnit = unit.join("corpsUnits");
        Join<CorpsUnit, Corps> corps = corpsUnit.join("corps");
        Predicate corpsCondition = cb.equal(corps.get("id"), corpsId);
        cq.groupBy(specialty.get("id"));
        Expression<Long> militaryCount = cb.count(military.get("id"));
        Predicate havingCondition = cb.gt(militaryCount, 5L);
        cq.select(specialty)
                .where(corpsCondition)
                .having(havingCondition);
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Specialty> findNoSpecialtiesInCorps(int corpsId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Specialty> cq = cb.createQuery(Specialty.class);
        Root<Specialty> specialty = cq.from(Specialty.class);
        Subquery<Long> subquery = cq.subquery(Long.class);
        Root<Specialty> subSpecialty = subquery.from(Specialty.class);
        Join<Specialty, MilitarySpecialty> subMilitarySpecialty = subSpecialty.join("militarySpecialties");
        Join<MilitarySpecialty, Military> subMilitary = subMilitarySpecialty.join("military");
        Join<Military, Unit> subUnit = subMilitary.join("unit");
        Join<Unit, CorpsUnit> subCorpsUnit = subUnit.join("corpsUnits");
        Join<CorpsUnit, Corps> subCorps = subCorpsUnit.join("corps");
        subquery.select(subSpecialty.get("id"))
                .where(cb.equal(subCorps.get("id"), corpsId))
                .groupBy(subSpecialty.get("id"))
                .having(cb.notEqual(cb.count(subMilitary.get("id")), 0L));
        cq.select(specialty)
                .where(cb.not(cb.in(specialty.get("id")).value(subquery)));
        return entityManager.createQuery(cq).getResultList();
    }
}
