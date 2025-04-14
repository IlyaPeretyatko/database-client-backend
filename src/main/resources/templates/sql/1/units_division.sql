SELECT u.*
FROM units AS u
JOIN divisions_units AS du
ON u.id = du.unit_id
JOIN divisions AS d
ON d.id = du.division_id
WHERE d.id = :divisionId;
