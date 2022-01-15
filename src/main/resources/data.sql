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

INSERT INTO posts (title, description, creation_date, expiry_date, image_url, post_category_id, author_id) VALUES
    ('TestTitle1', 'TestDesc1', '2022-01-15', '2022-01-16', 'TestUrl1', 1, 1),
    ('TestTitle2', 'TestDesc2', '2022-01-15', '2022-01-16', 'TestUrl2', 2, 2),
    ('TestTitle3', 'TestDesc3', '2022-01-15', '2022-01-16', 'TestUrl3', 3, 3),
    ('TestTitle4', 'TestDesc4', '2022-01-15', '2022-01-16', 'TestUrl4', 4, 4),
    ('TestTitle5', 'TestDesc5', '2022-01-15', '2022-01-16', 'TestUrl5', 1, 4);
