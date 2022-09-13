insert into Genre(genre) values('Shooter');
insert into Genre(genre) values('ARPG');

insert into Game(title, thumbnail, short_description, game_url, genre_id, platform, publisher, developer, release_date, freetogame_profile_url)
    values('PUBG: BATTLEGROUNDS', 'https://www.freetogame.com/g/516/thumbnail.jpg',
        'Get into the action in one of the longest running battle royale games PUBG Battlegrounds.',
        'https://www.freetogame.com/open/pubg', 1, 'PC (Windows)', 'KRAFTON, Inc.', 'KRAFTON, Inc.', '2022-01-12',
        'https://www.freetogame.com/pubg');

insert into Game(title, thumbnail, short_description, game_url, genre_id, platform, publisher, developer, release_date, freetogame_profile_url)
    values('Lost Ark', 'https://www.freetogame.com/g/517/thumbnail.jpg',
        'Smilegate’s free-to-play multiplayer ARPG is a massive adventure filled with lands waiting to be explored, people waiting to be met, and an ancient evil waiting to be destroyed.',
        'https://www.freetogame.com/open/lost-ark', 2, 'PC (Windows)', 'Amazon Games', 'Smilegate RPG', '2022-02-11',
        'https://www.freetogame.com/lost-ark');