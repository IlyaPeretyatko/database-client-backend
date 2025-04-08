SELECT m.*
FROM militaries AS m
JOIN units AS u
ON m.unit_id = u.id
JOIN corps_units AS cu
ON u.id = cu.unit_id
JOIN corps AS c
ON cu.corps_id = c.id
JOIN militaries_specialties AS ms
ON m.id = ms.military_id
JOIN specialties AS s
ON ms.specialty_id = s.id
WHERE c.id = :corpsId AND s.title = :specialtyTitle
ORDER BY m.id;