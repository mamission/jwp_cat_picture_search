INSERT INTO breed (id, name, temperament, origin)
VALUES ('ebur', 'European Burmese', 'Sweet, Affectionate, Loyal', 'Burma'),
       ('beng', 'Bengal', 'Alert, Agile, Energetic, Demanding, Intelligent', 'United States');


INSERT INTO cat_image (id, url, width, height)
VALUES ('DZzcGANt5', 'https://cdn2.thecatapi.com/images/DZzcGANt5.jpg', 2048, 1365),
       ('d8sbdRtLJ', 'https://cdn2.thecatapi.com/images/d8sbdRtLJ.jpg', 1050, 1126),
       ('5hmYjVhib', 'https://cdn2.thecatapi.com/images/5hmYjVhib.jpg', 935, 1000),
       ('oLtx9gsxx', 'https://cdn2.thecatapi.com/images/oLtx9gsxx.jpg', 4027, 2680),
       ('YOjBThApG', 'https://cdn2.thecatapi.com/images/YOjBThApG.jpg', 2838, 4518),
       ('daHxeTPgZ', 'https://cdn2.thecatapi.com/images/daHxeTPgZ.jpg', 800, 547),
       ('uvyjW5KIG', 'https://cdn2.thecatapi.com/images/uvyjW5KIG.jpg', 3008, 2000),
       ('ATYs2BetM', 'https://cdn2.thecatapi.com/images/ATYs2BetM.jpg', 1920, 1440),
       ('GI062lGTf', 'https://cdn2.thecatapi.com/images/GI062lGTf.png', 680, 656),
       ('pK_t-KYIi', 'https://cdn2.thecatapi.com/images/pK_t-KYIi.jpg', 637, 421),
       ('J2PmlIizw', 'https://cdn2.thecatapi.com/images/J2PmlIizw.jpg', 1080, 1350),
       ('LSaDk6OjY', 'https://cdn2.thecatapi.com/images/LSaDk6OjY.jpg', 1080, 1080),
       ('8pCFG7gCV', 'https://cdn2.thecatapi.com/images/8pCFG7gCV.jpg', 750, 937),
       ('VZ3qFLIe3', 'https://cdn2.thecatapi.com/images/VZ3qFLIe3.jpg', 750, 937),
       ('iWyIaja-G', 'https://cdn2.thecatapi.com/images/iWyIaja-G.jpg', 1080, 769),
       ('GAmy2bg8G', 'https://cdn2.thecatapi.com/images/GAmy2bg8G.jpg', 750, 750),
       ('Rl39SPjDO', 'https://cdn2.thecatapi.com/images/Rl39SPjDO.png', 1198, 1379),
       ('8RsP7Xt3h', 'https://cdn2.thecatapi.com/images/8RsP7Xt3h.jpg', 1024, 817),
       ('byQhFO7iV', 'https://cdn2.thecatapi.com/images/byQhFO7iV.jpg', 1795, 2397),
       ('4-5SzDNIL', 'https://cdn2.thecatapi.com/images/4-5SzDNIL.jpg', 880, 1100);

INSERT INTO cat_breed (breed_id, cat_image_id)
VALUES ('ebur', 'DZzcGANt5'),
       ('ebur', 'd8sbdRtLJ'),
       ('ebur', '5hmYjVhib'),
       ('ebur', 'oLtx9gsxx'),
       ('ebur', 'YOjBThApG'),
       ('ebur', 'daHxeTPgZ'),
       ('ebur', 'uvyjW5KIG'),
       ('ebur', 'ATYs2BetM'),
       ('ebur', 'GI062lGTf'),
       ('ebur', 'pK_t-KYIi'),
       ('beng', 'J2PmlIizw'),
       ('beng', 'LSaDk6OjY'),
       ('beng', '8pCFG7gCV'),
       ('beng', 'VZ3qFLIe3'),
       ('beng', 'iWyIaja-G'),
       ('beng', 'GAmy2bg8G'),
       ('beng', 'Rl39SPjDO'),
       ('beng', '8RsP7Xt3h'),
       ('beng', 'byQhFO7iV'),
       ('beng', '4-5SzDNIL');