SELECT m.*
FROM militaries AS m
JOIN units AS u
ON m.unit_id = u.id
JOIN divisions_units AS du
ON u.id = du.unit_id
JOIN divisions AS d
ON du.division_id = d.id
JOIN militaries_specialties AS ms
ON m.id = ms.military_id
JOIN specialties AS s
ON ms.specialty_id = s.id
WHERE d.id = :divisionId AND s.title = :specialtyTitle
ORDER BY m.id;