SELECT w.*
FROM weapons AS w
JOIN units AS u
ON w.unit_id = u.id
JOIN corps_units AS cu
ON u.id = cu.unit_id
JOIN corps AS c
ON cu.corps_id = c.id
WHERE c.id = :corpsId;
