package ru.nsu.peretyatko.repository.weapons;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.nsu.peretyatko.model.weapons.Weapon;
import ru.nsu.peretyatko.model.weapons.WeaponCategory;
import ru.nsu.peretyatko.model.weapons.WeaponType;
import ru.nsu.peretyatko.model.infrastructure.*;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WeaponCustomRepository {

    private final EntityManager entityManager;

    public List<Weapon> findWeaponsByType(String titleType) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Weapon> criteriaQuery = criteriaBuilder.createQuery(Weapon.class);
        Root<Weapon> militaryWeaponRoot = criteriaQuery.from(Weapon.class);
        Join<Weapon, WeaponType> weaponTypeJoin = militaryWeaponRoot.join("type");
        criteriaQuery.select(militaryWeaponRoot)
                .where(criteriaBuilder.equal(weaponTypeJoin.get("title"), titleType));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Weapon> findWeaponsByCategory(String titleCategory) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Weapon> criteriaQuery = criteriaBuilder.createQuery(Weapon.class);
        Root<Weapon> weaponRoot = criteriaQuery.from(Weapon.class);
        Join<Weapon, WeaponType> weaponTypeJoin = weaponRoot.join("type");
        Join<WeaponType, WeaponCategory> weaponCategoryJoin = weaponTypeJoin.join("category");
        criteriaQuery.select(weaponRoot)
                .where(criteriaBuilder.equal(weaponCategoryJoin.get("title"), titleCategory));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Weapon> findWeaponsByTypeUnit(String titleType, int unitId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Weapon> cq = cb.createQuery(Weapon.class);
        Root<Weapon> weapon = cq.from(Weapon.class);
        Join<Weapon, WeaponType> typeJoin = weapon.join("type");
        Join<Weapon, Unit> unitJoin = weapon.join("unit");
        cq.select(weapon)
                .where(cb.and(
                        cb.equal(unitJoin.get("id"), unitId),
                        cb.equal(typeJoin.get("title"), titleType)
                ));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Weapon> findWeaponsByCategoryUnit(String titleCategory, int unitId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Weapon> cq = cb.createQuery(Weapon.class);
        Root<Weapon> weapon = cq.from(Weapon.class);
        Join<Weapon, WeaponType> typeJoin = weapon.join("type");
        Join<WeaponType, WeaponCategory> categoryJoin = typeJoin.join("category");
        Join<Weapon, Unit> unitJoin = weapon.join("unit");
        cq.select(weapon)
                .where(cb.and(
                        cb.equal(unitJoin.get("id"), unitId),
                        cb.equal(categoryJoin.get("title"), titleCategory)
                ));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Weapon> findWeaponsByTypeDivision(String titleType, int divisionId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Weapon> cq = cb.createQuery(Weapon.class);
        Root<Weapon> weapon = cq.from(Weapon.class);
        Join<Weapon, WeaponType> type = weapon.join("type");
        Join<Weapon, Unit> unit = weapon.join("unit");
        Join<Unit, DivisionUnit> divisionUnit = unit.join("divisionUnits");
        Join<DivisionUnit, Division> division = divisionUnit.join("division");
        cq.select(weapon)
                .where(cb.and(
                        cb.equal(type.get("title"), titleType),
                        cb.equal(division.get("id"), divisionId)
                ));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Weapon> findWeaponsByCategoryDivision(String titleCategory, int divisionId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Weapon> cq = cb.createQuery(Weapon.class);
        Root<Weapon> weapon = cq.from(Weapon.class);
        Join<Weapon, WeaponType> type = weapon.join("type");
        Join<WeaponType, WeaponCategory> category = type.join("category");
        Join<Weapon, Unit> unit = weapon.join("unit");
        Join<Unit, DivisionUnit> divisionUnit = unit.join("divisionUnits");
        Join<DivisionUnit, Division> division = divisionUnit.join("division");
        cq.select(weapon)
                .where(cb.and(
                        cb.equal(category.get("title"), titleCategory),
                        cb.equal(division.get("id"), divisionId)
                ));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Weapon> findWeaponsByTypeBrigade(String titleType, int brigadeId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Weapon> cq = cb.createQuery(Weapon.class);
        Root<Weapon> weapon = cq.from(Weapon.class);
        Join<Weapon, WeaponType> type = weapon.join("type");
        Join<Weapon, Unit> unit = weapon.join("unit");
        Join<Unit, BrigadeUnit> brigadeUnit = unit.join("brigadeUnits");
        Join<BrigadeUnit, Brigade> brigade = brigadeUnit.join("brigade");
        cq.select(weapon)
                .where(cb.and(
                        cb.equal(type.get("title"), titleType),
                        cb.equal(brigade.get("id"), brigadeId)
                ));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Weapon> findWeaponsByCategoryBrigade(String titleCategory, int brigadeId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Weapon> cq = cb.createQuery(Weapon.class);
        Root<Weapon> weapon = cq.from(Weapon.class);
        Join<Weapon, WeaponType> type = weapon.join("type");
        Join<WeaponType, WeaponCategory> category = type.join("category");
        Join<Weapon, Unit> unit = weapon.join("unit");
        Join<Unit, BrigadeUnit> brigadeUnit = unit.join("brigadeUnits");
        Join<BrigadeUnit, Brigade> brigade = brigadeUnit.join("brigade");
        cq.select(weapon)
                .where(cb.and(
                        cb.equal(category.get("title"), titleCategory),
                        cb.equal(brigade.get("id"), brigadeId)
                ));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Weapon> findWeaponsByTypeCorps(String titleType, int corpsId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Weapon> cq = cb.createQuery(Weapon.class);
        Root<Weapon> weapon = cq.from(Weapon.class);
        Join<Weapon, WeaponType> type = weapon.join("type");
        Join<Weapon, Unit> unit = weapon.join("unit");
        Join<Unit, CorpsUnit> corpsUnit = unit.join("corpsUnits");
        Join<CorpsUnit, Corps> corps = corpsUnit.join("corps");
        cq.select(weapon)
                .where(cb.and(
                        cb.equal(type.get("title"), titleType),
                        cb.equal(corps.get("id"), corpsId)
                ));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Weapon> findWeaponsByCategoryCorps(String titleCategory, int corpsId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Weapon> cq = cb.createQuery(Weapon.class);
        Root<Weapon> weapon = cq.from(Weapon.class);
        Join<Weapon, WeaponType> type = weapon.join("type");
        Join<WeaponType, WeaponCategory> category = type.join("category");
        Join<Weapon, Unit> unit = weapon.join("unit");
        Join<Unit, CorpsUnit> corpsUnit = unit.join("corpsUnits");
        Join<CorpsUnit, Corps> corps = corpsUnit.join("corps");
        cq.select(weapon)
                .where(cb.and(
                        cb.equal(category.get("title"), titleCategory),
                        cb.equal(corps.get("id"), corpsId)
                ));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Weapon> findWeaponsByTypeArmy(String titleType, int armyId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Weapon> cq = cb.createQuery(Weapon.class);
        Subquery<Weapon> divisionSubquery = cq.subquery(Weapon.class);
        Root<Weapon> divisionWeapon = divisionSubquery.from(Weapon.class);
        Join<Weapon, WeaponType> divisionType = divisionWeapon.join("type");
        Join<Weapon, Unit> divisionUnit = divisionWeapon.join("unit");
        Join<Unit, DivisionUnit> divisionUnitJoin = divisionUnit.join("divisionUnits");
        Join<DivisionUnit, Division> division = divisionUnitJoin.join("division");
        Join<Division, ArmyDivision> armyDivision = division.join("armyDivisions");
        divisionSubquery.select(divisionWeapon)
                .where(cb.and(
                        cb.equal(divisionType.get("title"), titleType),
                        cb.equal(armyDivision.get("army").get("id"), armyId)
                ));
        Subquery<Weapon> brigadeSubquery = cq.subquery(Weapon.class);
        Root<Weapon> brigadeWeapon = brigadeSubquery.from(Weapon.class);
        Join<Weapon, WeaponType> brigadeType = brigadeWeapon.join("type");
        Join<Weapon, Unit> brigadeUnit = brigadeWeapon.join("unit");
        Join<Unit, BrigadeUnit> brigadeUnitJoin = brigadeUnit.join("brigadeUnits");
        Join<BrigadeUnit, Brigade> brigade = brigadeUnitJoin.join("brigade");
        Join<Brigade, ArmyBrigade> armyBrigade = brigade.join("armyBrigades");
        brigadeSubquery.select(brigadeWeapon)
                .where(cb.and(
                        cb.equal(brigadeType.get("title"), titleType),
                        cb.equal(armyBrigade.get("army").get("id"), armyId)
                ));
        Subquery<Weapon> corpsSubquery = cq.subquery(Weapon.class);
        Root<Weapon> corpsWeapon = corpsSubquery.from(Weapon.class);
        Join<Weapon, WeaponType> corpsType = corpsWeapon.join("type");
        Join<Weapon, Unit> corpsUnit = corpsWeapon.join("unit");
        Join<Unit, CorpsUnit> corpsUnitJoin = corpsUnit.join("corpsUnits");
        Join<CorpsUnit, Corps> corps = corpsUnitJoin.join("corps");
        Join<Corps, ArmyCorps> armyCorps = corps.join("armyCorps");
        corpsSubquery.select(corpsWeapon)
                .where(cb.and(
                        cb.equal(corpsType.get("title"), titleType),
                        cb.equal(armyCorps.get("army").get("id"), armyId)
                ));
        cq.select(cq.from(Weapon.class))
                .where(cb.or(
                        cb.in(cq.from(Weapon.class)).value(divisionSubquery),
                        cb.in(cq.from(Weapon.class)).value(brigadeSubquery),
                        cb.in(cq.from(Weapon.class)).value(corpsSubquery)
                ));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Weapon> findWeaponsByCategoryArmy(String titleCategory, int armyId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Weapon> cq = cb.createQuery(Weapon.class);
        Subquery<Weapon> divisionSubquery = cq.subquery(Weapon.class);
        Root<Weapon> divisionWeapon = divisionSubquery.from(Weapon.class);
        Join<Weapon, WeaponType> divisionType = divisionWeapon.join("type");
        Join<WeaponType, WeaponCategory> divisionCategory = divisionType.join("category");
        Join<Weapon, Unit> divisionUnit = divisionWeapon.join("unit");
        Join<Unit, DivisionUnit> divisionUnitJoin = divisionUnit.join("divisionUnits");
        Join<DivisionUnit, Division> division = divisionUnitJoin.join("division");
        Join<Division, ArmyDivision> armyDivision = division.join("armyDivisions");
        divisionSubquery.select(divisionWeapon)
                .where(cb.and(
                        cb.equal(divisionCategory.get("title"), titleCategory),
                        cb.equal(armyDivision.get("army").get("id"), armyId)
                ));
        Subquery<Weapon> brigadeSubquery = cq.subquery(Weapon.class);
        Root<Weapon> brigadeWeapon = brigadeSubquery.from(Weapon.class);
        Join<Weapon, WeaponType> brigadeType = brigadeWeapon.join("type");
        Join<WeaponType, WeaponCategory> brigadeCategory = brigadeType.join("category");
        Join<Weapon, Unit> brigadeUnit = brigadeWeapon.join("unit");
        Join<Unit, BrigadeUnit> brigadeUnitJoin = brigadeUnit.join("brigadeUnits");
        Join<BrigadeUnit, Brigade> brigade = brigadeUnitJoin.join("brigade");
        Join<Brigade, ArmyBrigade> armyBrigade = brigade.join("armyBrigades");
        brigadeSubquery.select(brigadeWeapon)
                .where(cb.and(
                        cb.equal(brigadeCategory.get("title"), titleCategory),
                        cb.equal(armyBrigade.get("army").get("id"), armyId)
                ));
        Subquery<Weapon> corpsSubquery = cq.subquery(Weapon.class);
        Root<Weapon> corpsWeapon = corpsSubquery.from(Weapon.class);
        Join<Weapon, WeaponType> corpsType = corpsWeapon.join("type");
        Join<WeaponType, WeaponCategory> corpsCategory = corpsType.join("category");
        Join<Weapon, Unit> corpsUnit = corpsWeapon.join("unit");
        Join<Unit, CorpsUnit> corpsUnitJoin = corpsUnit.join("corpsUnits");
        Join<CorpsUnit, Corps> corps = corpsUnitJoin.join("corps");
        Join<Corps, ArmyCorps> armyCorps = corps.join("armyCorps");
        corpsSubquery.select(corpsWeapon)
                .where(cb.and(
                        cb.equal(corpsCategory.get("title"), titleCategory),
                        cb.equal(armyCorps.get("army").get("id"), armyId)
                ));
        cq.select(cq.from(Weapon.class))
                .where(cb.or(
                        cb.in(cq.from(Weapon.class)).value(divisionSubquery),
                        cb.in(cq.from(Weapon.class)).value(brigadeSubquery),
                        cb.in(cq.from(Weapon.class)).value(corpsSubquery)
                ));
        return entityManager.createQuery(cq).getResultList();
    }
}
