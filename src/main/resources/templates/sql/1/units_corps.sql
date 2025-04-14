SELECT u.*
FROM units AS u
JOIN corps_units AS cu
ON u.id = cu.unit_id
JOIN corps AS c
ON c.id = cu.corps_id
WHERE c.id = :corpsId;
