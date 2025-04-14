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
JOIN brigades_units AS bu
ON bu.unit_id = u.id
JOIN brigades AS b
ON b.id = bu.brigade_id
WHERE b.id = :brigadeId
GROUP BY s.id
HAVING COUNT(m.id) != 0;