-- Users
INSERT INTO spotify_user (name, email, spotify_user_id, access_token)
VALUES ('Bobby Tables', 'bobbydrop@table.com', 'BobbyT_97', 'hakwiwjdjaow'),
       ('Mr Guy', 'guymr@gmail.com', 'MrGuy123', 'aborikjdrg'),
       ('Bill Mussix', 'billmussix@hotmail.com', 'MussixBill', 'oiuhgjkiuytg');

-- Artists
INSERT INTO artist (artist_id, artist_name, popularity, external_url, image_url)
VALUES ('jsjsjeaw', 'The BeStBaNd', '98', 'http://spotify.com/jsjsjeaw', 'http://spotify.com/jsjsjeaw/image'),
       ('kdksag', 'Underground_Band', '31', 'http://spotify.com/kdksag', 'http://spotify.com/kdksag/image'),
       ('iuhgnjuihj', 'DJ Khalid', '67', 'http://spotify.com/iuhgnjuihj', 'http://spotify.com/iuhgnjuihj/image');

-- Genres

INSERT INTO genre (genre_name)
VALUES ('Rock'),
       ('Shoegaze'),
       ('Pop'),
       ('Grindcore'),
       ('Musique Concréte');

-- User Artists
INSERT INTO spotify_user_artist (spotify_user_id, artist_id, artist_rank)
VALUES ('BobbyT_97', 'jsjsjeaw', 1),
       ('BobbyT_97', 'kdksag', 2),
       ('MrGuy123', 'kdksag', 1),
       ('MussixBill', 'iuhgnjuihj', 1),
       ('MussixBill', 'jsjsjeaw', 2),
       ('MussixBill', 'kdksag', 3);

-- Genre Artists
INSERT INTO genre_artists (genres_id, artists_artist_id)
VALUES (1, 'jsjsjeaw'),
       (1, 'kdksag'),
       (2, 'kdksag'),
       (3, 'iuhgnjuihj'),
       (3, 'jsjsjeaw'),
       (4, 'kdksag'),
       (5, 'iuhgnjuihj');