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
JOIN divisions_units AS du
ON du.unit_id = u.id
JOIN divisions AS d
ON d.id = du.division_id
WHERE d.id = :divisionId
GROUP BY s.id
HAVING COUNT(m.id) != 0;