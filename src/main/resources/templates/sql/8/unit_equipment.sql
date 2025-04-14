SELECT u.*
FROM units AS u
JOIN military_equipments AS me
ON u.id = me.unit_id
JOIN military_equipment_types AS met
ON me.type_id = met.id
WHERE met.title = :titleType
GROUP BY u.id
HAVING COUNT(me.id) > 5;