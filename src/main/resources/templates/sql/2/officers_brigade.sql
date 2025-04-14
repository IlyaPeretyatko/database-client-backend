SELECT m.*
FROM militaries AS m
JOIN ranks AS r
ON m.rank_id = r.id
JOIN rank_categories AS rc
ON rc.id = r.rank_category_id
JOIN units AS u
ON u.id = m.unit_id
JOIN brigades_units AS bu
ON u.id = bu.unit_id
JOIN brigades AS b
ON bu.brigade_id = b.id
WHERE rc.title = 'Офицерский состав' AND r.title = :titleRank AND b.id = :brigadeId;