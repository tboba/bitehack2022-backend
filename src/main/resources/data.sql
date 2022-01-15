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
    ('Chleb pszenny', 'Swieze pieczywko specjalnie dla Ciebie!', '2022-01-15T12:23:45', '2022-01-17T18:34:23', 'https://i.imgur.com/bBApToy.jpg', 1, 1, 1),
    ('Serek homogenizowany Danio', 'Oddam pyszne danio', '2022-01-15T15:21:45', '2022-01-24T08:15:42', 'https://i.imgur.com/ceTywVz.png', 2, 2, 2),
    ('Papryka czerwona', 'Miala isc na bigos, zostala.', '2022-01-15T17:18:11', '2022-01-18T07:18:22', 'https://i.imgur.com/uxleAuS.jpg', 3, 3, 3),
    ('Zupka chińska Vifon', 'W sumie nie chce jej jesc', '2022-01-15T02:55:32', '2023-01-28T16:23:34', 'https://i.imgur.com/pMkoqNG.jpg', 4, 4, 4),
    ('Pepsi 2L', 'Wole coca cole', '2022-01-15T13:22:54', '2022-08-21T18:22:11', 'https://i.imgur.com/ORKRGIi.jpg', 1, 4, 5);
