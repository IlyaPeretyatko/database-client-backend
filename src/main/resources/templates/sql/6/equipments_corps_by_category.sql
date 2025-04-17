SELECT me.*
FROM military_equipments AS me
JOIN military_equipment_types AS met
ON me.type_id = met.id
JOIN military_equipment_categories AS mec
ON met.category_id = mec.id
JOIN units AS u
ON me.unit_id = u.id
JOIN corps_units AS cu
ON u.id = cu.unit_id
JOIN corps AS c
ON cu.corps_id = c.id
WHERE mec.title = :titleCategory AND c.id = :corpsId;
