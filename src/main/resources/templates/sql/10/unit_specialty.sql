SELECT s.*
FROM units AS u
JOIN militaries AS m
ON u.id = m.unit_id
JOIN militaries_specialties AS ms
ON m.id = ms.military_id
JOIN specialties AS s
ON ms.specialty_id = s.id
WHERE u.id = :unitId AND s.title = :specialtyTitle
GROUP BY s.id
HAVING COUNT(*) > 5;
