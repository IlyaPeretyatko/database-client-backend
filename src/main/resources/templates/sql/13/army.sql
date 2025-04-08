WITH unit_counts AS (
    SELECT
        a.id AS army_id,
        COUNT(DISTINCT du.unit_id) AS division_units_count,
        COUNT(DISTINCT bu.unit_id) AS brigade_units_count,
        COUNT(DISTINCT cu.unit_id) AS corps_units_count
    FROM armies AS a LEFT JOIN armies_divisions AS ad
    ON a.id = ad.army_id
    LEFT JOIN divisions AS d
    ON ad.division_id = d.id
    LEFT JOIN divisions_units AS du
    ON d.id = du.division_id
    LEFT JOIN armies_brigades AS ab
    ON a.id = ab.army_id
    LEFT JOIN brigades AS b
    ON ab.brigade_id = b.id
    LEFT JOIN brigades_units AS bu
    ON b.id = bu.brigade_id
    LEFT JOIN armies_corps AS ac
    ON a.id = ac.army_id
    LEFT JOIN corps AS c
    ON ac.corps_id = c.id
    LEFT JOIN corps_units AS cu
    ON c.id = cu.corps_id
    GROUP BY a.id
)
SELECT
    a.id AS id,
    a.title AS title,
    (COALESCE(uc.division_units_count, 0) +
     COALESCE(uc.brigade_units_count, 0) +
     COALESCE(uc.corps_units_count, 0)) AS total_units
FROM armies AS a LEFT JOIN unit_counts AS uc
ON a.id = uc.army_id
ORDER BY total_units DESC
LIMIT 1;