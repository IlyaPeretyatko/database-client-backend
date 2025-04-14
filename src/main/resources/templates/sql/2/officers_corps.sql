SELECT m.*
FROM militaries AS m
JOIN ranks AS r
ON m.rank_id = r.id
JOIN rank_categories AS rc
ON rc.id = r.rank_category_id
JOIN units AS u
ON u.id = m.unit_id
JOIN corps_units AS cu
ON u.id = cu.unit_id
JOIN corps AS c
ON cu.corps_id = c.id
WHERE rc.title = 'Офицерский состав' AND r.title = :titleRank AND c.id = :corpsId;