SELECT *
FROM units
EXCEPT
SELECT u.*
FROM units AS u JOIN weapons AS w
ON u.id = w.unit_id
JOIN weapons_types AS wt
ON w.type_id = wt.id
WHERE wt.title = :weaponTypeTitle
GROUP BY u.id
HAVING COUNT(w.id) != 0;