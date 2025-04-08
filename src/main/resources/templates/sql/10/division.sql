SELECT s.*
FROM divisions AS d
JOIN divisions_units AS du
ON d.id = du.division_id
JOIN units AS u
ON u.id = du.unit_id
JOIN militaries AS m
ON u.id = m.unit_id
JOIN militaries_specialties AS ms
ON m.id = ms.military_id
JOIN specialties AS s
ON ms.specialty_id = s.id
WHERE d.id = :divisionId AND s.title = :specialtyTitle
GROUP BY s.id
HAVING COUNT(*) > 5;
