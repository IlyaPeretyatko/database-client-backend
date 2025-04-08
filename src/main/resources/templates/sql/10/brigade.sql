SELECT s.*
FROM brigades AS b
JOIN brigades_units AS bu
ON b.id = bu.brigade_id
JOIN units AS u
ON u.id = bu.unit_id
JOIN militaries AS m
ON u.id = m.unit_id
JOIN militaries_specialties AS ms
ON m.id = ms.military_id
JOIN specialties AS s
ON ms.specialty_id = s.id
WHERE b.id = :brigadeId AND s.title = :specialtyTitle
GROUP BY s.id
HAVING COUNT(*) > 5;