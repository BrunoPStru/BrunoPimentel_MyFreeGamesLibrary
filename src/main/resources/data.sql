INSERT INTO Genre(genre) VALUES('Shooter');
INSERT INTO Genre(genre) VALUES('Strategy');

INSERT INTO Game(title, thumbnail, short_description, game_url, genre_id, platform, publisher, developer, release_date, freetogame_profile_url)
    VALUES('PUBG: BATTLEGROUNDS', 'https://www.freetogame.com/g/516/thumbnail.jpg',
        'Get into the action in one of the longest running battle royale games PUBG Battlegrounds.',
        'https://www.freetogame.com/open/pubg', 1, 'PC (Windows)', 'KRAFTON, Inc.', 'KRAFTON, Inc.', '2022-01-12',
        'https://www.freetogame.com/pubg');

INSERT INTO Game(title, thumbnail, short_description, game_url, genre_id, platform, publisher, developer, release_date, freetogame_profile_url)
    VALUES('Game Of Thrones Winter Is Coming', 'https://www.freetogame.com/g/340/thumbnail.jpg',
        'A free-to-play browser-based RTS based on the George R.R. Martin novels and popular HBO series.',
        'https://www.freetogame.com/open/game-of-thrones-winter-is-coming', 2, 'Web Browser', 'GTArcade', 'YOOZOO Games', '2019-11-14',
        'https://www.freetogame.com/game-of-thrones-winter-is-coming');
