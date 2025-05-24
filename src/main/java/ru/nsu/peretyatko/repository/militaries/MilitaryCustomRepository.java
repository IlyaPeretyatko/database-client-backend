package ru.nsu.peretyatko.repository.militaries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.nsu.peretyatko.model.infrastructure.*;
import ru.nsu.peretyatko.model.militaries.*;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MilitaryCustomRepository {

    private final EntityManager entityManager;

    public Page<Military> findMilitariesByRankCategory(String rankCategoryTitle, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> cq = cb.createQuery(Military.class);
        Root<Military> military = cq.from(Military.class);
        Join<Military, Rank> rankJoin = military.join("rank");
        Join<Rank, RankCategory> rankCategoryJoin = rankJoin.join("rankCategory");
        cq.select(military)
                .where(cb.equal(rankCategoryJoin.get("title"), rankCategoryTitle));
        List<Military> resultList = entityManager.createQuery(cq).getResultList();
        List<Military> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Military> findMilitariesByRankCategoryAndRank(String rankCategoryTitle, String rankTitle, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> cq = cb.createQuery(Military.class);
        Root<Military> military = cq.from(Military.class);
        Join<Military, Rank> rankJoin = military.join("rank");
        Join<Rank, RankCategory> rankCategoryJoin = rankJoin.join("rankCategory");
        cq.select(military)
                .where(cb.and(
                        cb.equal(rankCategoryJoin.get("title"), rankCategoryTitle),
                        cb.equal(rankJoin.get("title"), rankTitle)
                ));
        List<Military> resultList = entityManager.createQuery(cq).getResultList();
        List<Military> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Military> findMilitariesByRankCategoryAndRankUnit(String rankCategoryTitle, String rankTitle, int unitId, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> cq = cb.createQuery(Military.class);
        Root<Military> military = cq.from(Military.class);
        Join<Military, Rank> rankJoin = military.join("rank");
        Join<Rank, RankCategory> rankCategoryJoin = rankJoin.join("rankCategory");
        Join<Military, Unit> unitJoin = military.join("unit");
        cq.select(military)
                .where(cb.and(
                        cb.equal(rankCategoryJoin.get("title"), rankCategoryTitle),
                        cb.equal(rankJoin.get("title"), rankTitle),
                        cb.equal(unitJoin.get("id"), unitId)
                ));
        List<Military> resultList = entityManager.createQuery(cq).getResultList();
        List<Military> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Military> findMilitariesByRankCategoryAndRankDivision(String rankCategoryTitle, String rankTitle, int divisionId, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> cq = cb.createQuery(Military.class);
        Root<Military> military = cq.from(Military.class);
        Join<Military, Rank> rankJoin = military.join("rank");
        Join<Rank, RankCategory> rankCategoryJoin = rankJoin.join("rankCategory");
        Join<Military, Unit> unitJoin = military.join("unit");
        Join<Unit, DivisionUnit> divisionsUnitsJoin = unitJoin.join("divisionUnits");
        Join<DivisionUnit, Division> divisionJoin = divisionsUnitsJoin.join("division");
        cq.select(military)
                .where(cb.and(
                        cb.equal(rankCategoryJoin.get("title"), rankCategoryTitle),
                        cb.equal(rankJoin.get("title"), rankTitle),
                        cb.equal(divisionJoin.get("id"), divisionId)
                ));
        List<Military> resultList = entityManager.createQuery(cq).getResultList();
        List<Military> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Military> findMilitariesByRankCategoryAndRankBrigade(String rankCategoryTitle, String rankTitle, int brigadeId, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> cq = cb.createQuery(Military.class);
        Root<Military> military = cq.from(Military.class);
        Join<Military, Rank> rankJoin = military.join("rank");
        Join<Rank, RankCategory> rankCategoryJoin = rankJoin.join("rankCategory");
        Join<Military, Unit> unitJoin = military.join("unit");
        Join<Unit, BrigadeUnit> brigadesUnitsJoin = unitJoin.join("brigadeUnits");
        Join<BrigadeUnit, Brigade> brigadeJoin = brigadesUnitsJoin.join("brigade");
        cq.select(military)
                .where(cb.and(
                        cb.equal(rankCategoryJoin.get("title"), rankCategoryTitle),
                        cb.equal(rankJoin.get("title"), rankTitle),
                        cb.equal(brigadeJoin.get("id"), brigadeId)
                ));
        List<Military> resultList = entityManager.createQuery(cq).getResultList();
        List<Military> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Military> findMilitariesByRankCategoryAndRankCorps(String rankCategoryTitle, String rankTitle, int corpsId, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> cq = cb.createQuery(Military.class);
        Root<Military> military = cq.from(Military.class);
        Join<Military, Rank> rankJoin = military.join("rank");
        Join<Rank, RankCategory> rankCategoryJoin = rankJoin.join("rankCategory");
        Join<Military, Unit> unitJoin = military.join("unit");
        Join<Unit, CorpsUnit> corpsUnitsJoin = unitJoin.join("corpsUnits");
        Join<CorpsUnit, Brigade> corpsJoin = corpsUnitsJoin.join("corps");
        cq.select(military)
                .where(cb.and(
                        cb.equal(rankCategoryJoin.get("title"), rankCategoryTitle),
                        cb.equal(rankJoin.get("title"), rankTitle),
                        cb.equal(corpsJoin.get("id"), corpsId)
                ));
        List<Military> resultList = entityManager.createQuery(cq).getResultList();
        List<Military> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Military> findMilitariesByRankCategoryAndRankArmy(String rankCategoryTitle, String rankTitle, int armyId, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> cq = cb.createQuery(Military.class);
        Root<Military> militaryRoot = cq.from(Military.class);
        Subquery<Military> divisionSubquery = cq.subquery(Military.class);
        Root<Military> divisionMilitaryRoot = divisionSubquery.from(Military.class);
        Join<Military, Rank> divisionRankJoin = divisionMilitaryRoot.join("rank");
        Join<Rank, RankCategory> divisionRankCategoryJoin = divisionRankJoin.join("rankCategory");
        Join<Military, Unit> divisionUnitJoin = divisionMilitaryRoot.join("unit");
        Join<Unit, DivisionUnit> divisionUnitJoin2 = divisionUnitJoin.join("divisionUnits");
        Join<DivisionUnit, Division> divisionJoin = divisionUnitJoin2.join("division");
        Join<Division, ArmyDivision> armyDivisionJoin = divisionJoin.join("armyDivisions");
        divisionSubquery.select(divisionMilitaryRoot)
                .where(cb.and(
                        cb.equal(divisionRankCategoryJoin.get("title"), rankCategoryTitle),
                        cb.equal(divisionRankJoin.get("title"), rankTitle),
                        cb.equal(armyDivisionJoin.get("army").get("id"), armyId)
                ));
        Subquery<Military> brigadeSubquery = cq.subquery(Military.class);
        Root<Military> brigadeMilitaryRoot = brigadeSubquery.from(Military.class);
        Join<Military, Rank> brigadeRankJoin = brigadeMilitaryRoot.join("rank");
        Join<Rank, RankCategory> brigadeRankCategoryJoin = brigadeRankJoin.join("rankCategory");
        Join<Military, Unit> brigadeUnitJoin = brigadeMilitaryRoot.join("unit");
        Join<Unit, BrigadeUnit> brigadeUnitJoin2 = brigadeUnitJoin.join("brigadeUnits");
        Join<BrigadeUnit, Brigade> brigadeJoin = brigadeUnitJoin2.join("brigade");
        Join<Brigade, ArmyBrigade> armyBrigadeJoin = brigadeJoin.join("armyBrigades");
        brigadeSubquery.select(brigadeMilitaryRoot)
                .where(cb.and(
                        cb.equal(brigadeRankCategoryJoin.get("title"), rankCategoryTitle),
                        cb.equal(brigadeRankJoin.get("title"), rankTitle),
                        cb.equal(armyBrigadeJoin.get("army").get("id"), armyId)
                ));
        Subquery<Military> corpsSubquery = cq.subquery(Military.class);
        Root<Military> corpsMilitaryRoot = corpsSubquery.from(Military.class);
        Join<Military, Rank> corpsRankJoin = corpsMilitaryRoot.join("rank");
        Join<Rank, RankCategory> corpsRankCategoryJoin = corpsRankJoin.join("rankCategory");
        Join<Military, Unit> corpsUnitJoin = corpsMilitaryRoot.join("unit");
        Join<Unit, CorpsUnit> corpsUnitJoin2 = corpsUnitJoin.join("corpsUnits");
        Join<CorpsUnit, Corps> corpsJoin = corpsUnitJoin2.join("corps");
        Join<Corps, ArmyCorps> armyCorpsJoin = corpsJoin.join("armyCorps");
        corpsSubquery.select(corpsMilitaryRoot)
                .where(cb.and(
                        cb.equal(corpsRankCategoryJoin.get("title"), rankCategoryTitle),
                        cb.equal(corpsRankJoin.get("title"), rankTitle),
                        cb.equal(armyCorpsJoin.get("army").get("id"), armyId)
                ));
        cq.select(militaryRoot)
                .where(cb.or(
                        cb.in(militaryRoot).value(divisionSubquery),
                        cb.in(militaryRoot).value(brigadeSubquery),
                        cb.in(militaryRoot).value(corpsSubquery)
                ));
        List<Military> resultList = entityManager.createQuery(cq).getResultList();
        List<Military> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Military> findMilitariesBySpecialtyUnit(String titleSpecialty, int unitId, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> cq = cb.createQuery(Military.class);
        Root<Military> military = cq.from(Military.class);
        Join<Military, Unit> unit = military.join("unit");
        Join<Military, MilitarySpecialty> militarySpecialty = military.join("specialties");
        Join<MilitarySpecialty, Specialty> specialty = militarySpecialty.join("specialty");
        Predicate unitCondition = cb.equal(unit.get("id"), unitId);
        Predicate specialtyCondition = cb.equal(specialty.get("title"), titleSpecialty);
        cq.orderBy(cb.asc(military.get("id")));
        cq.select(military)
                .where(cb.and(unitCondition, specialtyCondition));
        List<Military> resultList = entityManager.createQuery(cq).getResultList();
        List<Military> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Military> findMilitariesBySpecialtyDivision(String titleSpecialty, int divisionId, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> cq = cb.createQuery(Military.class);
        Root<Military> military = cq.from(Military.class);
        Join<Military, Unit> unit = military.join("unit");
        Join<Unit, DivisionUnit> divisionUnit = unit.join("divisionUnits");
        Join<DivisionUnit, Division> division = divisionUnit.join("division");
        Join<Military, MilitarySpecialty> militarySpecialty = military.join("specialties");
        Join<MilitarySpecialty, Specialty> specialty = militarySpecialty.join("specialty");
        Predicate divisionCondition = cb.equal(division.get("id"), divisionId);
        Predicate specialtyCondition = cb.equal(specialty.get("title"), titleSpecialty);
        cq.orderBy(cb.asc(military.get("id")));
        cq.select(military)
                .where(cb.and(divisionCondition, specialtyCondition));
        List<Military> resultList = entityManager.createQuery(cq).getResultList();
        List<Military> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Military> findMilitariesBySpecialtyBrigade(String titleSpecialty, int brigadeId, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> cq = cb.createQuery(Military.class);
        Root<Military> military = cq.from(Military.class);
        Join<Military, Unit> unit = military.join("unit");
        Join<Unit, BrigadeUnit> brigadeUnit = unit.join("brigadeUnits");
        Join<BrigadeUnit, Brigade> brigade = brigadeUnit.join("brigade");
        Join<Military, MilitarySpecialty> militarySpecialty = military.join("specialties");
        Join<MilitarySpecialty, Specialty> specialty = militarySpecialty.join("specialty");
        Predicate brigadeCondition = cb.equal(brigade.get("id"), brigadeId);
        Predicate specialtyCondition = cb.equal(specialty.get("title"), titleSpecialty);
        cq.orderBy(cb.asc(military.get("id")));
        cq.select(military)
                .where(cb.and(brigadeCondition, specialtyCondition));
        List<Military> resultList = entityManager.createQuery(cq).getResultList();
        List<Military> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Military> findMilitariesBySpecialtyCorps(String titleSpecialty, int corpsId, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> cq = cb.createQuery(Military.class);
        Root<Military> military = cq.from(Military.class);
        Join<Military, Unit> unit = military.join("unit");
        Join<Unit, CorpsUnit> corpsUnit = unit.join("corpsUnits");
        Join<CorpsUnit, Corps> corps = corpsUnit.join("corps");
        Join<Military, MilitarySpecialty> militarySpecialty = military.join("specialties");
        Join<MilitarySpecialty, Specialty> specialty = militarySpecialty.join("specialty");
        Predicate corpsCondition = cb.equal(corps.get("id"), corpsId);
        Predicate specialtyCondition = cb.equal(specialty.get("title"), titleSpecialty);
        cq.orderBy(cb.asc(military.get("id")));
        cq.select(military)
                .where(cb.and(corpsCondition, specialtyCondition));
        List<Military> resultList = entityManager.createQuery(cq).getResultList();
        List<Military> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Military> findMilitariesBySpecialtyArmy(String titleSpecialty, int armyId, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> cq = cb.createQuery(Military.class);
        Root<Military> militaryRoot = cq.from(Military.class);
        Subquery<Military> divisionSubquery = cq.subquery(Military.class);
        Root<Military> divisionMilitaryRoot = divisionSubquery.from(Military.class);
        Join<Military, MilitarySpecialty> divisionMilitarySpecialty = divisionMilitaryRoot.join("specialties");
        Join<MilitarySpecialty, Specialty> divisionSpecialty = divisionMilitarySpecialty.join("specialty");
        Join<Military, Unit> divisionUnitJoin = divisionMilitaryRoot.join("unit");
        Join<Unit, DivisionUnit> divisionUnitJoin2 = divisionUnitJoin.join("divisionUnits");
        Join<DivisionUnit, Division> divisionJoin = divisionUnitJoin2.join("division");
        Join<Division, ArmyDivision> armyDivisionJoin = divisionJoin.join("armyDivisions");
        divisionSubquery.select(divisionMilitaryRoot)
                .where(cb.and(
                        cb.equal(divisionSpecialty.get("title"), titleSpecialty),
                        cb.equal(armyDivisionJoin.get("army").get("id"), armyId)
                ));
        Subquery<Military> brigadeSubquery = cq.subquery(Military.class);
        Root<Military> brigadeMilitaryRoot = brigadeSubquery.from(Military.class);
        Join<Military, MilitarySpecialty> brigadeMilitarySpecialty = brigadeMilitaryRoot.join("specialties");
        Join<MilitarySpecialty, Specialty> brigadeSpecialty = brigadeMilitarySpecialty.join("specialty");
        Join<Military, Unit> brigadeUnitJoin = brigadeMilitaryRoot.join("unit");
        Join<Unit, BrigadeUnit> brigadeUnitJoin2 = brigadeUnitJoin.join("brigadeUnits");
        Join<BrigadeUnit, Brigade> brigadeJoin = brigadeUnitJoin2.join("brigade");
        Join<Brigade, ArmyBrigade> armyBrigadeJoin = brigadeJoin.join("armyBrigades");
        brigadeSubquery.select(brigadeMilitaryRoot)
                .where(cb.and(
                        cb.equal(brigadeSpecialty.get("title"), titleSpecialty),
                        cb.equal(armyBrigadeJoin.get("army").get("id"), armyId)
                ));
        Subquery<Military> corpsSubquery = cq.subquery(Military.class);
        Root<Military> corpsMilitaryRoot = corpsSubquery.from(Military.class);
        Join<Military, MilitarySpecialty> corpsMilitarySpecialty = corpsMilitaryRoot.join("specialties");
        Join<MilitarySpecialty, Specialty> corpsSpecialty = corpsMilitarySpecialty.join("specialty");
        Join<Military, Unit> corpsUnitJoin = corpsMilitaryRoot.join("unit");
        Join<Unit, CorpsUnit> corpsUnitJoin2 = corpsUnitJoin.join("corpsUnits");
        Join<CorpsUnit, Corps> corpsJoin = corpsUnitJoin2.join("corps");
        Join<Corps, ArmyCorps> armyCorpsJoin = corpsJoin.join("armyCorps");
        corpsSubquery.select(corpsMilitaryRoot)
                .where(cb.and(
                        cb.equal(corpsSpecialty.get("title"), titleSpecialty),
                        cb.equal(armyCorpsJoin.get("army").get("id"), armyId)
                ));
        cq.select(militaryRoot)
                .where(cb.or(
                        cb.in(militaryRoot).value(divisionSubquery),
                        cb.in(militaryRoot).value(brigadeSubquery),
                        cb.in(militaryRoot).value(corpsSubquery)
                ));
        List<Military> resultList = entityManager.createQuery(cq).getResultList();
        List<Military> paginatedList = resultList.stream()
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
