SELECT *
FROM buildings AS b
WHERE b.unit_id NOT IN (
    SELECT u.id
    FROM units AS u
    JOIN companies AS c
    ON c.unit_id = u.id
    GROUP BY u.id
);
