package ru.nsu.peretyatko.repository.weapons;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.weapons.WeaponProperty;

public interface WeaponPropertyRepository extends JpaRepository<WeaponProperty, Integer> {
}
