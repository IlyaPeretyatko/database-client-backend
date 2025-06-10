package ru.nsu.peretyatko.repository.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.nsu.peretyatko.model.equipments.Equipment;
import ru.nsu.peretyatko.model.equipments.EquipmentType;
import ru.nsu.peretyatko.model.infrastructure.*;
import ru.nsu.peretyatko.model.weapons.Weapon;
import ru.nsu.peretyatko.model.weapons.WeaponType;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UnitCustomRepository {

    private final EntityManager entityManager;

    public Page<Unit> findUnitsByDivisionId(Integer divisionId, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> cq = cb.createQuery(Unit.class);
        Root<Unit> unitRoot = cq.from(Unit.class);
        Join<Unit, DivisionUnit> divisionUnitJoin = unitRoot.join("divisionUnits");
        Join<DivisionUnit, Division> divisionJoin = divisionUnitJoin.join("division");
        cq.select(unitRoot)
                .where(cb.equal(divisionJoin.get("id"), divisionId));
        List<Unit> resultList = entityManager.createQuery(cq).getResultList();
        List<Unit> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Unit> findUnitsByBrigadeId(Integer brigadeId, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> cq = cb.createQuery(Unit.class);
        Root<Unit> unitRoot = cq.from(Unit.class);
        Join<Unit, BrigadeUnit> brigadeUnitJoin = unitRoot.join("brigadeUnits");
        Join<BrigadeUnit, Division> divisionJoin = brigadeUnitJoin.join("brigade");
        cq.select(unitRoot)
                .where(cb.equal(divisionJoin.get("id"), brigadeId));
        List<Unit> resultList = entityManager.createQuery(cq).getResultList();
        List<Unit> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Unit> findUnitsByCorpsId(Integer corpsId, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> cq = cb.createQuery(Unit.class);
        Root<Unit> unitRoot = cq.from(Unit.class);
        Join<Unit, CorpsUnit> corpsUnitJoin = unitRoot.join("corpsUnits");
        Join<CorpsUnit, Division> corpsJoin = corpsUnitJoin.join("corps");
        cq.select(unitRoot)
                .where(cb.equal(corpsJoin.get("id"), corpsId));
        List<Unit> resultList = entityManager.createQuery(cq).getResultList();
        List<Unit> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Unit> findUnitsByArmyId(Integer armyId, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> cq = cb.createQuery(Unit.class);
        Root<Unit> unitRoot = cq.from(Unit.class);
        Subquery<Unit> divisionSubquery = cq.subquery(Unit.class);
        Root<Unit> divisionUnitRoot = divisionSubquery.from(Unit.class);
        Join<Unit, DivisionUnit> divisionUnitJoin = divisionUnitRoot.join("divisionUnits");
        Join<DivisionUnit, Division> divisionJoin = divisionUnitJoin.join("division");
        Join<Division, ArmyDivision> armyDivisionJoin = divisionJoin.join("armyDivisions");
        divisionSubquery.select(divisionUnitRoot)
                .where(cb.equal(armyDivisionJoin.get("army").get("id"), armyId));
        Subquery<Unit> brigadeSubquery = cq.subquery(Unit.class);
        Root<Unit> brigadeUnitRoot = brigadeSubquery.from(Unit.class);
        Join<Unit, BrigadeUnit> brigadeUnitJoin = brigadeUnitRoot.join("brigadeUnits");
        Join<BrigadeUnit, Brigade> brigadeJoin = brigadeUnitJoin.join("brigade");
        Join<Brigade, ArmyBrigade> armyBrigadeJoin = brigadeJoin.join("armyBrigades");
        brigadeSubquery.select(brigadeUnitRoot)
                .where(cb.equal(armyBrigadeJoin.get("army").get("id"), armyId));
        Subquery<Unit> corpsSubquery = cq.subquery(Unit.class);
        Root<Unit> corpsUnitRoot = corpsSubquery.from(Unit.class);
        Join<Unit, CorpsUnit> corpsUnitJoin = corpsUnitRoot.join("corpsUnits");
        Join<CorpsUnit, Corps> corpsJoin = corpsUnitJoin.join("corps");
        Join<Corps, ArmyCorps> armyCorpsJoin = corpsJoin.join("armyCorps");
        corpsSubquery.select(corpsUnitRoot)
                .where(cb.equal(armyCorpsJoin.get("army").get("id"), armyId));
        cq.select(unitRoot)
                .where(cb.or(
                        cb.in(unitRoot).value(divisionSubquery),
                        cb.in(unitRoot).value(brigadeSubquery),
                        cb.in(unitRoot).value(corpsSubquery)
                ));
        List<Unit> resultList = entityManager.createQuery(cq).getResultList();
        List<Unit> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Unit> findUnitsWithEquipmentTypeCount(String titleType, int minCount, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> cq = cb.createQuery(Unit.class);
        Root<Unit> unit = cq.from(Unit.class);
        Join<Unit, Equipment> equipment = unit.join("equipments");
        Join<Equipment, EquipmentType> equipmentType = equipment.join("type");
        cq.groupBy(unit.get("id"));
        cq.select(unit)
                .where(cb.equal(equipmentType.get("title"), titleType))
                .having(cb.gt(cb.count(equipment.get("id")), minCount));
        List<Unit> resultList = entityManager.createQuery(cq).getResultList();
        List<Unit> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Unit> findUnitsWithoutEquipmentType(String titleType, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> cq = cb.createQuery(Unit.class);
        Root<Unit> unit = cq.from(Unit.class);
        Subquery<Long> subquery = cq.subquery(Long.class);
        Root<Unit> subUnit = subquery.from(Unit.class);
        Join<Unit, Equipment> subEquipment = subUnit.join("equipments");
        Join<Equipment, EquipmentType> subEquipmentType = subEquipment.join("type");
        subquery.select(subUnit.get("id"))
                .where(cb.equal(subEquipmentType.get("title"), titleType))
                .groupBy(subUnit.get("id"))
                .having(cb.gt(cb.count(subEquipment.get("id")), 0));
        cq.select(unit)
                .where(cb.not(cb.in(unit.get("id")).value(subquery)));
        List<Unit> resultList = entityManager.createQuery(cq).getResultList();
        List<Unit> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Unit> findUnitsWithWeaponTypeCount(String titleType, int minCount, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> cq = cb.createQuery(Unit.class);
        Root<Unit> unit = cq.from(Unit.class);
        Join<Unit, Weapon> weapon = unit.join("weapons");
        Join<Weapon, WeaponType> weaponType = weapon.join("type");
        cq.groupBy(unit.get("id"));
        cq.select(unit)
                .where(cb.equal(weaponType.get("title"), titleType))
                .having(cb.gt(cb.count(weapon.get("id")), minCount));
        List<Unit> resultList = entityManager.createQuery(cq).getResultList();
        List<Unit> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Unit> findUnitsWithoutWeaponType(String titleType, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> cq = cb.createQuery(Unit.class);
        Root<Unit> unit = cq.from(Unit.class);
        Subquery<Long> subquery = cq.subquery(Long.class);
        Root<Unit> subUnit = subquery.from(Unit.class);
        Join<Unit, Weapon> subWeapon = subUnit.join("weapons");
        Join<Weapon, WeaponType> subWeaponType = subWeapon.join("type");
        subquery.select(subUnit.get("id"))
                .where(cb.equal(subWeaponType.get("title"), titleType))
                .groupBy(subUnit.get("id"))
                .having(cb.gt(cb.count(subWeapon.get("id")), 0));
        cq.select(unit)
                .where(cb.not(cb.in(unit.get("id")).value(subquery)));
        List<Unit> resultList = entityManager.createQuery(cq).getResultList();
        List<Unit> paginatedList = resultList.stream()
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
