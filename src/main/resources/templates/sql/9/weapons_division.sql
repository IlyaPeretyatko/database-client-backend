SELECT w.*
FROM weapons AS w
JOIN units AS u
ON w.unit_id = u.id
JOIN divisions_units AS du
ON u.id = du.unit_id
JOIN divisions AS d
ON du.division_id = d.id
WHERE d.id = :divisionId;
