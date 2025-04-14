SELECT w.*
FROM weapons AS w
JOIN units AS u
ON w.unit_id = u.id
WHERE u.id = :unitId;

