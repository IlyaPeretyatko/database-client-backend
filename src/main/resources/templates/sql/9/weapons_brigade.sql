SELECT w.*
FROM weapons AS w
JOIN units AS u
ON w.unit_id = u.id
JOIN brigades_units AS bu
ON u.id = bu.unit_id
JOIN brigades AS b
ON bu.brigade_id = b.id
WHERE b.id = :brigadeId;
