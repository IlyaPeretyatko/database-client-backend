SELECT m.*
FROM militaries AS m
JOIN units AS u
ON m.unit_id = u.id
JOIN militaries_specialties AS ms
ON m.id = ms.military_id
JOIN specialties AS s
ON ms.specialty_id = s.id
WHERE u.id = :unitId  AND s.title = :specialtyTitle
ORDER BY m.id;