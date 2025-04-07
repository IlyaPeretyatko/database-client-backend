package ru.nsu.peretyatko.repository.weapons;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.weapons.WeaponType;

public interface WeaponTypeRepository extends JpaRepository<WeaponType, Integer> {
}
