SELECT m.*
FROM militaries AS m
JOIN units AS u
ON m.unit_id = u.id
JOIN brigades_units AS bu
ON u.id = bu.unit_id
JOIN brigades AS b
ON bu.brigade_id = b.id
JOIN militaries_specialties AS ms
ON m.id = ms.military_id
JOIN specialties AS s
ON ms.specialty_id = s.id
WHERE b.id = :brigadeId AND s.title = :specialtyTitle
ORDER BY m.id;