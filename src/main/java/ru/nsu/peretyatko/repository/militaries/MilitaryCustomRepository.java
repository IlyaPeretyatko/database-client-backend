package ru.nsu.peretyatko.repository.militaries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.nsu.peretyatko.model.infrastructure.*;
import ru.nsu.peretyatko.model.militaries.Military;
import ru.nsu.peretyatko.model.militaries.Rank;
import ru.nsu.peretyatko.model.militaries.RankCategory;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MilitaryCustomRepository {

    private final EntityManager entityManager;

    public List<Military> findMilitariesByRankCategory(String rankCategoryTitle) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> query = cb.createQuery(Military.class);
        Root<Military> military = query.from(Military.class);
        Join<Military, Rank> rankJoin = military.join("rank");
        Join<Rank, RankCategory> rankCategoryJoin = rankJoin.join("rankCategory");
        query.select(military)
                .where(cb.equal(rankCategoryJoin.get("title"), rankCategoryTitle));
        return entityManager.createQuery(query).getResultList();
    }

    public List<Military> findMilitariesByRankCategoryAndRank(String rankCategoryTitle, String rankTitle) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> query = cb.createQuery(Military.class);
        Root<Military> military = query.from(Military.class);
        Join<Military, Rank> rankJoin = military.join("rank");
        Join<Rank, RankCategory> rankCategoryJoin = rankJoin.join("rankCategory");
        query.select(military)
                .where(cb.and(
                        cb.equal(rankCategoryJoin.get("title"), rankCategoryTitle),
                        cb.equal(rankJoin.get("title"), rankTitle)
                ));
        return entityManager.createQuery(query).getResultList();
    }

    public List<Military> findMilitariesByRankCategoryAndRankUnit(String rankCategoryTitle, String rankTitle, int unitId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> query = cb.createQuery(Military.class);
        Root<Military> military = query.from(Military.class);
        Join<Military, Rank> rankJoin = military.join("rank");
        Join<Rank, RankCategory> rankCategoryJoin = rankJoin.join("rankCategory");
        Join<Military, Unit> unitJoin = military.join("unit");
        query.select(military)
                .where(cb.and(
                        cb.equal(rankCategoryJoin.get("title"), rankCategoryTitle),
                        cb.equal(rankJoin.get("title"), rankTitle),
                        cb.equal(unitJoin.get("id"), unitId)
                ));
        return entityManager.createQuery(query).getResultList();
    }

    public List<Military> findMilitariesByRankCategoryAndRankDivision(String rankCategoryTitle, String rankTitle, int divisionId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> query = cb.createQuery(Military.class);
        Root<Military> military = query.from(Military.class);
        Join<Military, Rank> rankJoin = military.join("rank");
        Join<Rank, RankCategory> rankCategoryJoin = rankJoin.join("rankCategory");
        Join<Military, Unit> unitJoin = military.join("unit");
        Join<Unit, DivisionUnit> divisionsUnitsJoin = unitJoin.join("divisionUnits");
        Join<DivisionUnit, Division> divisionJoin = divisionsUnitsJoin.join("division");
        query.select(military)
                .where(cb.and(
                        cb.equal(rankCategoryJoin.get("title"), rankCategoryTitle),
                        cb.equal(rankJoin.get("title"), rankTitle),
                        cb.equal(divisionJoin.get("id"), divisionId)
                ));
        return entityManager.createQuery(query).getResultList();
    }

    public List<Military> findMilitariesByRankCategoryAndRankBrigade(String rankCategoryTitle, String rankTitle, int brigadeId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> query = cb.createQuery(Military.class);
        Root<Military> military = query.from(Military.class);
        Join<Military, Rank> rankJoin = military.join("rank");
        Join<Rank, RankCategory> rankCategoryJoin = rankJoin.join("rankCategory");
        Join<Military, Unit> unitJoin = military.join("unit");
        Join<Unit, BrigadeUnit> brigadesUnitsJoin = unitJoin.join("brigadeUnits");
        Join<BrigadeUnit, Brigade> brigadeJoin = brigadesUnitsJoin.join("brigade");
        query.select(military)
                .where(cb.and(
                        cb.equal(rankCategoryJoin.get("title"), rankCategoryTitle),
                        cb.equal(rankJoin.get("title"), rankTitle),
                        cb.equal(brigadeJoin.get("id"), brigadeId)
                ));
        return entityManager.createQuery(query).getResultList();
    }

    public List<Military> findMilitariesByRankCategoryAndRankCorps(String rankCategoryTitle, String rankTitle, int corpsId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> query = cb.createQuery(Military.class);
        Root<Military> military = query.from(Military.class);
        Join<Military, Rank> rankJoin = military.join("rank");
        Join<Rank, RankCategory> rankCategoryJoin = rankJoin.join("rankCategory");
        Join<Military, Unit> unitJoin = military.join("unit");
        Join<Unit, CorpsUnit> corpsUnitsJoin = unitJoin.join("corpsUnits");
        Join<CorpsUnit, Brigade> corpsJoin = corpsUnitsJoin.join("corps");
        query.select(military)
                .where(cb.and(
                        cb.equal(rankCategoryJoin.get("title"), rankCategoryTitle),
                        cb.equal(rankJoin.get("title"), rankTitle),
                        cb.equal(corpsJoin.get("id"), corpsId)
                ));
        return entityManager.createQuery(query).getResultList();
    }

    public List<Military> findMilitariesByRankCategoryAndRankArmy(String rankCategoryTitle, String rankTitle, int armyId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Military> criteriaQuery = criteriaBuilder.createQuery(Military.class);
        Root<Military> militaryRoot = criteriaQuery.from(Military.class);
        Subquery<Military> divisionSubquery = criteriaQuery.subquery(Military.class);
        Root<Military> divisionMilitaryRoot = divisionSubquery.from(Military.class);
        Join<Military, Rank> divisionRankJoin = divisionMilitaryRoot.join("rank");
        Join<Rank, RankCategory> divisionRankCategoryJoin = divisionRankJoin.join("rankCategory");
        Join<Military, Unit> divisionUnitJoin = divisionMilitaryRoot.join("unit");
        Join<Unit, DivisionUnit> divisionUnitJoin2 = divisionUnitJoin.join("divisionUnits");
        Join<DivisionUnit, Division> divisionJoin = divisionUnitJoin2.join("division");
        Join<Division, ArmyDivision> armyDivisionJoin = divisionJoin.join("armyDivisions");
        divisionSubquery.select(divisionMilitaryRoot)
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(divisionRankCategoryJoin.get("title"), rankCategoryTitle),
                        criteriaBuilder.equal(divisionRankJoin.get("title"), rankTitle),
                        criteriaBuilder.equal(armyDivisionJoin.get("army").get("id"), armyId)
                ));
        Subquery<Military> brigadeSubquery = criteriaQuery.subquery(Military.class);
        Root<Military> brigadeMilitaryRoot = brigadeSubquery.from(Military.class);
        Join<Military, Rank> brigadeRankJoin = brigadeMilitaryRoot.join("rank");
        Join<Rank, RankCategory> brigadeRankCategoryJoin = brigadeRankJoin.join("rankCategory");
        Join<Military, Unit> brigadeUnitJoin = brigadeMilitaryRoot.join("unit");
        Join<Unit, BrigadeUnit> brigadeUnitJoin2 = brigadeUnitJoin.join("brigadeUnits");
        Join<BrigadeUnit, Brigade> brigadeJoin = brigadeUnitJoin2.join("brigade");
        Join<Brigade, ArmyBrigade> armyBrigadeJoin = brigadeJoin.join("armyBrigades");
        brigadeSubquery.select(brigadeMilitaryRoot)
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(brigadeRankCategoryJoin.get("title"), rankCategoryTitle),
                        criteriaBuilder.equal(brigadeRankJoin.get("title"), rankTitle),
                        criteriaBuilder.equal(armyBrigadeJoin.get("army").get("id"), armyId)
                ));
        Subquery<Military> corpsSubquery = criteriaQuery.subquery(Military.class);
        Root<Military> corpsMilitaryRoot = corpsSubquery.from(Military.class);
        Join<Military, Rank> corpsRankJoin = corpsMilitaryRoot.join("rank");
        Join<Rank, RankCategory> corpsRankCategoryJoin = corpsRankJoin.join("rankCategory");
        Join<Military, Unit> corpsUnitJoin = corpsMilitaryRoot.join("unit");
        Join<Unit, CorpsUnit> corpsUnitJoin2 = corpsUnitJoin.join("corpsUnits");
        Join<CorpsUnit, Corps> corpsJoin = corpsUnitJoin2.join("corps");
        Join<Corps, ArmyCorps> armyCorpsJoin = corpsJoin.join("armyCorps");
        corpsSubquery.select(corpsMilitaryRoot)
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(corpsRankCategoryJoin.get("title"), rankCategoryTitle),
                        criteriaBuilder.equal(corpsRankJoin.get("title"), rankTitle),
                        criteriaBuilder.equal(armyCorpsJoin.get("army").get("id"), armyId)
                ));
        criteriaQuery.select(militaryRoot)
                .where(criteriaBuilder.or(
                        criteriaBuilder.in(militaryRoot).value(divisionSubquery),
                        criteriaBuilder.in(militaryRoot).value(brigadeSubquery),
                        criteriaBuilder.in(militaryRoot).value(corpsSubquery)
                ));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }


}
