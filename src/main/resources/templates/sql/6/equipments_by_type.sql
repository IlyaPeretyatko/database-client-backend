SELECT me.*
FROM military_equipments AS me
JOIN military_equipment_types AS met
ON me.type_id = met.id
WHERE met.title = :titleType;