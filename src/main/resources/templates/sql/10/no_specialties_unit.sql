SELECT *
FROM specialties
EXCEPT
SELECT s.*
FROM specialties AS s
JOIN militaries_specialties AS ms
ON s.id = ms.specialty_id
JOIN militaries AS m
ON ms.military_id = m.id
JOIN units AS u
ON m.unit_id = u.id
WHERE u.id = :unitId
GROUP BY s.id
HAVING COUNT(m.id) != 0;