SELECT m.*
FROM militaries AS m
JOIN ranks AS r
ON m.rank_id = r.id
JOIN rank_categories AS rc
ON rc.id = r.rank_category_id
JOIN units AS u
ON u.id = m.unit_id
WHERE rc.title = 'Сержантский состав' AND r.title = :titleRank AND u.id = :unitId;