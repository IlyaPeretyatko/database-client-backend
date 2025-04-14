SELECT m.*
FROM militaries AS m
JOIN ranks AS r
ON m.rank_id = r.id
JOIN rank_categories AS rc
ON rc.id = r.rank_category_id
JOIN units AS u
ON u.id = m.unit_id
JOIN divisions_units AS du
ON u.id = du.unit_id
JOIN divisions AS d
ON du.division_id = d.id
JOIN brigades_units AS bu
ON u.id = bu.unit_id
JOIN brigades AS b
ON bu.brigade_id = b.id
JOIN corps_units AS cu
ON u.id = cu.unit_id
JOIN corps AS c
ON cu.corps_id = c.id
JOIN armies_brigades AS ab
ON ab.brigade_id = b.id
JOIN armies_divisions AS ad
ON ad.division_id = d.id
JOIN armies_corps AS ac
ON ac.army_id = c.id
JOIN armies AS a
ON a.id = ab.army_id OR a.id = ad.army_id OR a.id = ac.army_id
WHERE rc.title = 'Рядовой состав' AND r.title = :titleRank AND a.id = :armyId;