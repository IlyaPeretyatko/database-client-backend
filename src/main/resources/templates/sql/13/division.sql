SELECT d.*
FROM divisions d
JOIN (
    SELECT division_id, COUNT(unit_id) AS unit_count
    FROM divisions_units
    GROUP BY division_id
) AS du
ON d.id = du.division_id
ORDER BY du.unit_count DESC
LIMIT 1;
