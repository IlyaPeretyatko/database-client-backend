package ru.nsu.peretyatko.repository.buildings;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.nsu.peretyatko.model.buildings.Building;
import ru.nsu.peretyatko.model.infrastructure.Company;
import ru.nsu.peretyatko.model.infrastructure.Unit;
import ru.nsu.peretyatko.model.weapons.Weapon;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BuildingCustomRepository {

    private final EntityManager entityManager;

    public Page<Building> findBuildingsUnit(int unitId, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Building> cq = cb.createQuery(Building.class);
        Root<Building> building = cq.from(Building.class);
        Join<Building, Unit> unit = building.join("unit");
        cq.select(building)
                .where(cb.equal(unit.get("id"), unitId));
        List<Building> resultList = entityManager.createQuery(cq).getResultList();
        List<Building> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Building> findBuildingsOfSeparation(Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Building> cq = cb.createQuery(Building.class);
        Root<Building> building = cq.from(Building.class);
        Join<Building, Unit> unit = building.join("unit");
        Join<Unit, Company> company = unit.join("companies");
        cq.select(building)
                .groupBy(building.get("id"));
        List<Building> resultList = entityManager.createQuery(cq).getResultList();
        List<Building> paginatedList = resultList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(
                paginatedList,
                pageable,
                resultList.size()
        );
    }

    public Page<Building> findBuildingsOfNoSeparation(Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Building> cq = cb.createQuery(Building.class);
        Root<Building> building = cq.from(Building.class);
        Subquery<Long> unitSubquery = cq.subquery(Long.class);
        Root<Unit> unit = unitSubquery.from(Unit.class);
        Join<Unit, Company> company = unit.join("companies");
        unitSubquery.select(unit.get("id"))
                .groupBy(unit.get("id"));
        cq.select(building)
                .where(cb.not(cb.in(building.get("unit").get("id")).value(unitSubquery)));
        List<Building> resultList = entityManager.createQuery(cq).getResultList();
        List<Building> paginatedList = resultList.stream()
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
