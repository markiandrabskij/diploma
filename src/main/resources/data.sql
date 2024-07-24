INSERT INTO stone_type VALUES (default,'Diamond','2'),(default,'Ruby','3'),(default,'Iolite','4')

INSERT INTO necklace VALUES (default, 'newNecklace', '0'), (default, 'necklace2', '5')

--(id, name, pos_in_necklace, price_per_carat, transparency, weight, necklace_id, type_id)
INSERT INTO stone VALUES (default, 'SomeDiamond',   '1', '12', '0.2', '10', '2', '1')
INSERT INTO stone VALUES (default, 'SomeRuby',      '2', '11', '0.3', '10', '2', '2')
INSERT INTO stone VALUES (default, 'SomeIolite',    '3', '9',  '0.1', '10', '2', '3')
INSERT INTO stone VALUES (default, 'SecondDiamond', '4', '13', '0.7', '10', '2', '1')
INSERT INTO stone VALUES (default, 'testIolite',    '1', '13', '0.7', '10', '1', '1')