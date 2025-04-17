SELECT me.*
FROM military_equipments AS me
JOIN military_equipment_types AS met
ON me.type_id = met.id
JOIN units AS u
ON me.unit_id = u.id
WHERE u.id = :unitId AND met.title = :titleType;