SELECT s.*
FROM armies AS a
LEFT JOIN armies_corps AS ac
ON a.id = ac.army_id
LEFT JOIN corps AS c
ON ac.corps_id = c.id
LEFT JOIN armies_divisions AS ad
ON a.id = ad.army_id
LEFT JOIN divisions AS d
ON ad.division_id = d.id
LEFT JOIN armies_brigades AS ab
ON a.id = ab.army_id
LEFT JOIN brigades AS b
ON ab.brigade_id = b.id
LEFT JOIN corps_units AS cu
ON cu.corps_id = c.id
LEFT JOIN divisions_units AS du
ON du.division_id = d.id
LEFT JOIN brigades_units AS bu
ON bu.brigade_id = b.id
LEFT JOIN units AS u
ON u.id = cu.unit_id OR u.id = du.unit_id OR u.id = bu.unit_id
LEFT JOIN militaries AS m
ON u.id = m.unit_id
LEFT JOIN militaries_specialties AS ms
ON m.id = ms.military_id
LEFT JOIN specialties AS s
ON ms.specialty_id = s.id
WHERE a.id = :armyId
GROUP BY s.id, m.id
HAVING COUNT(*) > 3;