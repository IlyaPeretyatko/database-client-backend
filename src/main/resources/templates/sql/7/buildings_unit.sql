SELECT b.*
FROM buildings AS b
JOIN units AS u
ON b.unit_id = u.id
WHERE u.id = :unitId;