SELECT me.*
FROM military_equipments AS me
JOIN military_equipment_types AS met
ON me.type_id = met.id
JOIN military_equipment_categories AS mec
ON met.category_id = mec.id
WHERE mec.title = :titleCategory;