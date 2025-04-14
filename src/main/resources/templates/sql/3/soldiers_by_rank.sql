SELECT m.*
FROM militaries AS m
JOIN ranks AS r
ON m.rank_id = r.id
JOIN rank_categories AS rc
ON rc.id = r.rank_category_id
WHERE rc.title = 'Рядовой состав' AND r.title = :titleRank;