SELECT me.*
FROM military_equipments AS me
JOIN military_equipment_types AS met
ON me.type_id = met.id
JOIN military_equipment_categories AS mec
ON met.category_id = mec.id
JOIN units AS u
ON me.unit_id = u.id
JOIN divisions_units AS du
ON u.id = du.unit_id
JOIN divisions AS d
ON du.division_id = d.id
WHERE mec.title = :titleCategory AND d.id = :divisionId;
