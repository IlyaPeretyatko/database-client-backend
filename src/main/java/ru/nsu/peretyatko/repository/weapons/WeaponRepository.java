package ru.nsu.peretyatko.repository.weapons;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.peretyatko.model.weapons.Weapon;

public interface WeaponRepository extends JpaRepository<Weapon, Integer> {
}
