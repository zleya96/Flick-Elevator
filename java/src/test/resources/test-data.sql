BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user4','user4','ROLE_USER');

INSERT INTO accounts (user_id, liked_movies, disliked_movies, favorites, preferred_genre) VALUES (1, '1,2,3', '1,2,3', '1,2,3', 'action,adventure');
INSERT INTO accounts (user_id, liked_movies, disliked_movies, favorites, preferred_genre) VALUES (2, '4,5,6', '4,5,6', '4,5,6', 'romance,thriller');
INSERT INTO accounts (user_id, liked_movies, disliked_movies, favorites, preferred_genre) VALUES (3, '7,8,9', '7,8,9', '7,8,9', 'comedy,horror');

INSERT INTO movies (movie_id, title, release_date, genres_id, description, popularity, picture_path) VALUES (1, 'title1', '2020-01-01', 'action,comedy', 'overview1', 90.0, 'www.poster1.com');
INSERT INTO movies (movie_id, title, release_date, genres_id, description, popularity, picture_path) VALUES (2, 'title1', '2019-01-01', 'romance,disaster', 'overview2', 80.0, 'www.poster2.com');
INSERT INTO movies (movie_id, title, release_date, genres_id, description, popularity, picture_path) VALUES (3, 'title1', '2018-01-01', 'horror,thriller', 'overview1', 70.0, 'www.poster3.com');

COMMIT TRANSACTION;
