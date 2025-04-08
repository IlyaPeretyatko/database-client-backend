SELECT b.*
FROM brigades b
         JOIN (
    SELECT brigade_id, COUNT(unit_id) AS unit_count
    FROM brigades_units
    GROUP BY brigade_id
) AS bu
ON b.id = bu.brigade_id
ORDER BY bu.unit_count DESC
LIMIT 1;
