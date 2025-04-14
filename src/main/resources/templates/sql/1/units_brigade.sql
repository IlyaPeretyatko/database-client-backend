SELECT u.*
FROM units AS u
JOIN brigades_units AS bu
ON u.id = bu.unit_id
JOIN brigades AS b
ON b.id = bu.brigade_id
WHERE b.id = :brigadeId;
