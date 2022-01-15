INSERT INTO user (name, type) VALUES
                                    ('TestUser1', 'PRIVATE'),
                                    ('TestUser2', 'PRIVATE'),
                                    ('TestUser3', 'PRIVATE'),
                                    ('TestUser3', 'RESTAURANT');

INSERT INTO post_categories (name) VALUES ('pieczywo'),
                                     ('przyprawy'),
                                     ('mięso'),
                                     ('dania na ciepło'),
                                     ('dania do odgrzania');

INSERT INTO location (latitude, longitude, city) VALUES (50.069490, 19.914944, 'Kraków'),
                                                        (50.072996, 19.911225, 'Kraków'),
                                                        (50.072996, 19.911225, 'Kraków'),
                                                        (50.064857, 19.925906, 'Kraków'),
                                                        (50.071209, 19.926891, 'Kraków');

INSERT INTO posts (title, description, creation_date, expiry_date, image_url, post_category_id, author_id, destination_id) VALUES
    ('Chleb pszenny', 'Kategoria: pieczywo | waga: 500g', '2022-01-15', '2022-01-17', 'https://i.imgur.com/bBApToy.jpg', 1, 1, 1),
    ('Serek homogenizowany Danio', 'Kategoria: nabiał | waga: 150g', '2022-01-15', '2022-01-24', 'https://i.imgur.com/ceTywVz.png', 2, 2, 2),
    ('Papryka czerwona', 'Kategoria: warzywa | waga: 200g', '2022-01-15', '2022-01-18', 'https://i.imgur.com/uxleAuS.jpg', 3, 3, 3),
    ('Zupka chińska Vifon', 'Kategoria: fast-food | waga: 80g', '2022-01-15', '2023-01-28', 'https://i.imgur.com/pMkoqNG.jpg', 4, 4, 4),
    ('Pepsi 2L', 'Kategoria: napoje gazowane | waga: 800g', '2022-01-15', '2022-08-21', 'https://i.imgur.com/ORKRGIi.jpg', 1, 4, 5);
