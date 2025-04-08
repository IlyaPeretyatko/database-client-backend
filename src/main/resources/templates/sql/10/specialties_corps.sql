SELECT s.*
FROM specialties AS s
JOIN militaries_specialties AS ms
ON s.id = ms.specialty_id
JOIN militaries AS m
ON ms.military_id = m.id
JOIN units AS u
ON m.unit_id = u.id
JOIN corps_units AS cu
ON cu.unit_id = u.id
JOIN corps AS c
ON c.id = cu.corps_id
WHERE c.id = :corpsId
GROUP BY s.id
HAVING COUNT(m.id) > 5;