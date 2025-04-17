SELECT me.*
FROM military_equipments AS me
JOIN military_equipment_types AS met
ON me.type_id = met.id
JOIN military_equipment_categories AS mec
ON met.category_id = mec.id
JOIN units AS u
ON u.id = me.unit_id
WHERE u.id = :unitId AND mec.title = :titleCategory;