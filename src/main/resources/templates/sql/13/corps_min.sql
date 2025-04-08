SELECT c.*
FROM corps c
         JOIN (
    SELECT corps_id, COUNT(unit_id) AS unit_count
    FROM corps_units
    GROUP BY corps_id
) AS cu
              ON c.id = cu.corps_id
ORDER BY cu.unit_count ASC
LIMIT 1;
