SELECT me.*
FROM military_equipments AS me
JOIN military_equipment_types met
ON me.type_id = met.id
JOIN military_equipment_categories mec
ON met.category_id = mec.id
JOIN units AS u
ON me.unit_id = u.id
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
WHERE mec.title = :titleCategory AND a.id = :armyId;