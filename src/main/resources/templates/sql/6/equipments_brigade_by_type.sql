SELECT me.*
FROM military_equipments AS me
JOIN military_equipment_types AS met
ON me.type_id = met.id
JOIN units AS u
ON me.unit_id = u.id
JOIN brigades_units AS bu
ON u.id = bu.unit_id
JOIN brigades AS b
ON bu.brigade_id = b.id
WHERE met.title = :titleType AND b.id = :brigadeId;
