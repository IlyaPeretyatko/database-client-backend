package ru.nsu.peretyatko.repository.weapons;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.weapons.WeaponCategory;

public interface WeaponCategoryRepository extends JpaRepository<WeaponCategory, Integer> {
}
