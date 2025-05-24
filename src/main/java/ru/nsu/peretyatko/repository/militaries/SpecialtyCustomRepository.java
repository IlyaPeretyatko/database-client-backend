package ru.nsu.peretyatko.repository.militaries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.nsu.peretyatko.model.infrastructure.*;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.model.militaries.MilitarySpecialty;
import ru.nsu.peretyatko.model.militaries.Specialty;
import ru.nsu.peretyatko.model.weapons.Weapon;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SpecialtyCustomRepository {

    private final EntityManager entityManager;

    public Page<Specialty> findSpecialtiesInUnit(int unitId, int minCount, Pageable pageable) {
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
        List<Specialty> resultList = entityManager.createQuery(cq).getResultList();
        List<Specialty> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Specialty> findNoSpecialtiesInUnit(int unitId, Pageable pageable) {
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
        List<Specialty> resultList = entityManager.createQuery(cq).getResultList();
        List<Specialty> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Specialty> findSpecialtiesInDivision(int divisionId, int minCount, Pageable pageable) {
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
        Predicate havingCondition = cb.gt(militaryCount, minCount);
        cq.select(specialty)
                .where(divisionCondition)
                .having(havingCondition);
        List<Specialty> resultList = entityManager.createQuery(cq).getResultList();
        List<Specialty> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Specialty> findNoSpecialtiesInDivision(int divisionId, Pageable pageable) {
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
        List<Specialty> resultList = entityManager.createQuery(cq).getResultList();
        List<Specialty> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Specialty> findSpecialtiesInBrigade(int brigadeId, int minCount, Pageable pageable) {
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
        Predicate havingCondition = cb.gt(militaryCount, minCount);
        cq.select(specialty)
                .where(brigadeCondition)
                .having(havingCondition);
        List<Specialty> resultList = entityManager.createQuery(cq).getResultList();
        List<Specialty> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Specialty> findNoSpecialtiesInBrigade(int brigadeId, Pageable pageable) {
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
        List<Specialty> resultList = entityManager.createQuery(cq).getResultList();
        List<Specialty> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Specialty> findSpecialtiesInCorps(int corpsId, int minCount, Pageable pageable) {
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
        Predicate havingCondition = cb.gt(militaryCount, minCount);
        cq.select(specialty)
                .where(corpsCondition)
                .having(havingCondition);
        List<Specialty> resultList = entityManager.createQuery(cq).getResultList();
        List<Specialty> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Specialty> findNoSpecialtiesInCorps(int corpsId, Pageable pageable) {
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
        List<Specialty> resultList = entityManager.createQuery(cq).getResultList();
        List<Specialty> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }
}
