SELECT s.*
FROM corps AS c
JOIN corps_units AS cu
ON c.id = cu.corps_id
JOIN units AS u
ON u.id = cu.unit_id
JOIN militaries AS m
ON u.id = m.unit_id
JOIN militaries_specialties AS ms
ON m.id = ms.military_id
JOIN specialties AS s
ON ms.specialty_id = s.id
WHERE c.id = :corpsId AND s.title = :specialtyTitle
GROUP BY s.id
HAVING COUNT(*) > 5;