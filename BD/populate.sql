use bd;

call sp_inserirTipoUtilizador('Staff');
call sp_inserirTipoUtilizador('User');
call sp_inserirTipoUtilizador('Speaker');
call sp_inserirTipoUtilizador('VIP');
call sp_inserirTipoUtilizador('Admin');


call sp_inserirUtilizador('xXxmarizexXx','123456','mariajose@gmail.com',str_to_date('1985.11.27','%Y.%m.%d'), 'Speaker');
call sp_inserirUtilizador('tyler1','ahhhh','masters@gmail.com',str_to_date('1990.01.30','%Y.%m.%d'), 'Speaker');
call sp_inserirUtilizador('dghtrOfIvan','clint','alsoded@yahoo.com',str_to_date('1988.02.21','%Y.%m.%d'), 'Speaker');
call sp_inserirUtilizador('negritoclaro','aura','oznofficial@gmail.com',str_to_date('1990.05.27','%Y.%m.%d'), 'Speaker');

call sp_inserirUtilizador('jorgecalado','forcao98','jpc2013@yahoo.com',str_to_date('1998.01.22','%Y.%m.%d'), 'Staff');
call sp_inserirUtilizador('alexandre','ohze','chocapts@gmail.com',str_to_date('1985.04.02','%Y.%m.%d'), 'Staff');
call sp_inserirUtilizador('baki','mrs','guna@gmail.com',str_to_date('1997.09.08','%Y.%m.%d'), 'Staff');

call sp_inserirUtilizador('Jaques','xdxdxd','jaquelinaopas@yahoo.com',str_to_date('1997.01.01','%Y.%m.%d'), 'VIP');
call sp_inserirUtilizador('lexaa','clarke','iamded@yahoo.com',str_to_date('1991.06.22','%Y.%m.%d'), 'VIP');
call sp_inserirUtilizador('toytoy','todanoite','toyofficial@gmail.com',str_to_date('1980.11.27','%Y.%m.%d'), 'VIP');

call sp_inserirUtilizador('diogooui','bebe','calma@gmail.com',str_to_date('1999.08.06','%Y.%m.%d'), 'User');
call sp_inserirUtilizador('iamcat','updown','catrodrigues@hotmail.com',str_to_date('1996.03.20','%Y.%m.%d'), 'User');
call sp_inserirUtilizador('luisao','654321','luisaobrien@gmail.com',str_to_date('1997.09.11','%Y.%m.%d'), 'User');
call sp_inserirUtilizador('69frtn69','420xd420','noobmaster69@hotmail.com',str_to_date('1995.04.20','%Y.%m.%d'), 'User');
call sp_inserirUtilizador('lfv4ever','37','lfvbenfas@gmail.com',str_to_date('1980.10.02','%Y.%m.%d'), 'User');
call sp_inserirUtilizador('alexandre1','opas','alexsandro@hotmail.com',str_to_date('1999.12.24','%Y.%m.%d'), 'User');
call sp_inserirUtilizador('sopas','comecome','sopasefixe@hotmail.com',str_to_date('1998.07.11','%Y.%m.%d'), 'User');
call sp_inserirUtilizador('caladajuz','jus23cala','caladaJusefina23@gmail.com',str_to_date('1991.05.19','%Y.%m.%d'), 'User');

call sp_inserirUtilizador('deguzmanz','yesses','deguzmannoobens@hotmail.com',str_to_date('1998.01.04','%Y.%m.%d'), 'Admin');
call sp_inserirUtilizador('stvrogers','17000000','language@gmail.com',str_to_date('1918.12.09','%Y.%m.%d'), 'Admin');

call sp_inserirUtilizador('profCool','learningisgud','jorgee@yahoo.com',str_to_date('1980.11.18','%Y.%m.%d'), 'Speaker');
call sp_inserirUtilizador('agustinhocampos','trabalha','agusti@gmail.com',str_to_date('1980.05.03','%Y.%m.%d'), 'Speaker');
call sp_inserirUtilizador('baka','gymfanatc','30@hotmail.com',str_to_date('1995.10.09','%Y.%m.%d'), 'Staff');
call sp_inserirUtilizador('ze','chocap','base@hotmail.com',str_to_date('1999.02.01','%Y.%m.%d'), 'User');
call sp_inserirUtilizador('rdt','goat','rdt9official@gmail.com',str_to_date('1991.07.30','%Y.%m.%d'), 'User');
call sp_inserirUtilizador('sofsinha','abcdfasdasd','fonsinha@hotmail.com',str_to_date('1998.07.06','%Y.%m.%d'), 'User');


call sp_inserirEvento('Evento informático de Base de dados', '2019-07-15 09:30:00', '2019-07-17 18:30:00');
call sp_inserirEvento('Evento informático de Inteligência Artificial', '2019-07-18 09:30:00', '2019-07-20 18:30:00');
call sp_inserirEvento('Evento esport', '2019-11-01 10:00:00', '2019-11-03 18:00:00');
call sp_inserirEvento('Evento informático de Java', '2019-08-20 09:30:00', '2019-08-22 18:00:00');


call sp_inserirUtilizadorEvento('xXxmarizexXx', 1, 1);
call sp_inserirUtilizadorEvento('tyler1', 1, 2);
call sp_inserirUtilizadorEvento('dghtrOfIvan', 1, 3);
call sp_inserirUtilizadorEvento('negritoclaro', 1, 4);
call sp_inserirUtilizadorEvento('jorgecalado', 1, 5);
call sp_inserirUtilizadorEvento('alexandre', 1, 6);
call sp_inserirUtilizadorEvento('diogooui', 1, 7);
call sp_inserirUtilizadorEvento('iamcat', 1, 8);
call sp_inserirUtilizadorEvento('luisao', 1, 9);
call sp_inserirUtilizadorEvento('69frtn69', 1, 10);
call sp_inserirUtilizadorEvento('lfv4ever', 1, 11);
call sp_inserirUtilizadorEvento('alexandre1', 1, 12);
call sp_inserirUtilizadorEvento('deguzmanz', 1, 13);

call sp_inserirUtilizadorEvento('xXxmarizexXx', 2, 14);
call sp_inserirUtilizadorEvento('tyler1', 2, 15);
call sp_inserirUtilizadorEvento('dghtrOfIvan', 2, 16);
call sp_inserirUtilizadorEvento('negritoclaro', 2, 17);
call sp_inserirUtilizadorEvento('baki', 2, 18);
call sp_inserirUtilizadorEvento('alexandre', 2, 19);
call sp_inserirUtilizadorEvento('sofsinha', 2, 20);
call sp_inserirUtilizadorEvento('iamcat', 2, 21);
call sp_inserirUtilizadorEvento('lexaa', 2, 22);
call sp_inserirUtilizadorEvento('caladajuz', 2, 23);
call sp_inserirUtilizadorEvento('lfv4ever', 2, 24);
call sp_inserirUtilizadorEvento('alexandre1', 2, 25);
call sp_inserirUtilizadorEvento('deguzmanz', 2, 26);

call sp_inserirUtilizadorEvento('profCool', 3, 27);
call sp_inserirUtilizadorEvento('agustinhocampos', 3, 28);
call sp_inserirUtilizadorEvento('baka', 3, 29);
call sp_inserirUtilizadorEvento('baki', 3, 30);
call sp_inserirUtilizadorEvento('rdt', 3, 31);
call sp_inserirUtilizadorEvento('ze', 3, 32);
call sp_inserirUtilizadorEvento('sopas', 3, 33);
call sp_inserirUtilizadorEvento('Jaques', 3, 34);
call sp_inserirUtilizadorEvento('toytoy', 3, 35);
call sp_inserirUtilizadorEvento('alexandre1', 3, 36);
call sp_inserirUtilizadorEvento('stvrogers', 3, 37);

call sp_inserirUtilizadorEvento('xXxmarizexXx', 4, 38);
call sp_inserirUtilizadorEvento('tyler1', 4, 39);
call sp_inserirUtilizadorEvento('dghtrOfIvan', 4, 40);
call sp_inserirUtilizadorEvento('negritoclaro', 4, 41);
call sp_inserirUtilizadorEvento('baki', 4, 42);
call sp_inserirUtilizadorEvento('alexandre', 4, 43);
call sp_inserirUtilizadorEvento('sopas', 4, 44);
call sp_inserirUtilizadorEvento('iamcat', 4, 45);
call sp_inserirUtilizadorEvento('luisao', 4, 46);
call sp_inserirUtilizadorEvento('caladajuz', 4, 47);
call sp_inserirUtilizadorEvento('sofsinha', 4, 48);
call sp_inserirUtilizadorEvento('alexandre1', 4, 49);
call sp_inserirUtilizadorEvento('deguzmanz', 4, 50);


call sp_inserirSessao('Introdução Programação', '2019-07-15 09:30:00', '2019-07-15 12:30:00', 3, 1);
call sp_inserirSessao('Introduçao a BD', '2019-07-15 14:00:00', '2019-07-15 16:30:00', 2.5, 1);
call sp_inserirSessao('Interação e Perguntas', '2019-07-15 16:30:00', '2019-07-15 18:30:00', 2, 1);
call sp_inserirSessao('BD', '2019-07-16 09:30:00', '2019-07-16 12:30:00', 3, 1);
call sp_inserirSessao('CBD', '2019-07-16 14:00:00', '2019-07-16 18:30:00', 4.5, 1);
call sp_inserirSessao('SQL', '2019-07-17 09:30:00', '2019-07-17 12:30:00', 3, 1);
call sp_inserirSessao('BD Object-oriented', '2019-07-17 14:00:00', '2019-07-17 16:30:00', 2.5, 1);
call sp_inserirSessao('Conclusão', '2019-07-17 16:30:00', '2019-07-17 18:30:00', 2, 1);

call sp_inserirSessao('Introdução Programação', '2019-07-18 09:30:00', '2019-07-18 11:30:00', 2, 2);
call sp_inserirSessao('AI Introduçao', '2019-07-18 11:30:00', '2019-07-18 12:30:00', 1, 2);
call sp_inserirSessao('Aplicações da AI', '2019-07-18 14:00:00', '2019-07-18 17:00:00', 3, 2);
call sp_inserirSessao('Interação e Perguntas', '2019-07-18 17:00:00', '2019-07-18 18:30:00', 1.5, 2);
call sp_inserirSessao('AI', '2019-07-19 09:30:00', '2019-07-19 12:30:00', 3, 2);
call sp_inserirSessao('AI Avançada', '2019-07-19 14:00:00', '2019-07-19 18:30:00', 4.5, 2);
call sp_inserirSessao('Startups', '2019-07-20 09:30:00', '2019-07-20 12:30:00', 3, 2);
call sp_inserirSessao('Inovação', '2019-07-20 14:00:00', '2019-07-20 16:30:00', 2.5, 2);
call sp_inserirSessao('Conclusão', '2019-07-20 16:30:00', '2019-07-20 18:30:00', 2, 2);

call sp_inserirSessao('Introdução', '2019-11-01 10:00:00', '2019-11-01 12:00:00', 2, 3);
call sp_inserirSessao('Esports', '2019-11-01 14:00:00', '2019-11-01 18:00:00', 4, 3);
call sp_inserirSessao('Competiçoes', '2019-11-02 10:00:00', '2019-11-02 12:00:00', 2, 3);
call sp_inserirSessao('Sport vs esport', '2019-11-02 14:00:00', '2019-11-02 18:00:00', 4, 3);
call sp_inserirSessao('esports pelo mundo', '2019-11-03 10:00:00', '2019-11-03 12:00:00', 2, 3);
call sp_inserirSessao('Conclusão', '2019-11-03 14:00:00', '2019-11-03 18:00:00', 4, 3);

call sp_inserirSessao('Introdução Programação', '2019-08-20 09:30:00', '2019-08-20 13:00:00', 3.5, 4);
call sp_inserirSessao('Introduçao a Java', '2019-08-20 14:00:00', '2019-08-20 16:00:00', 2, 4);
call sp_inserirSessao('Interação e Perguntas', '2019-08-20 16:00:00', '2019-08-20 18:00:00', 2, 4);
call sp_inserirSessao('POO', '2019-08-21 09:30:00', '2019-08-21 13:00:00', 3, 4);
call sp_inserirSessao('PA', '2019-08-21 14:00:00', '2019-08-21 18:00:00', 4, 4);
call sp_inserirSessao('Startups', '2019-08-22 09:30:00', '2019-08-22 13:00:00', 3.5, 4);
call sp_inserirSessao('Aplicações de Java', '2019-08-22 14:00:00', '2019-08-22 16:00:00', 2, 4);
call sp_inserirSessao('Conclusão', '2019-08-22 16:00:00', '2019-08-22 18:00:00', 2, 4);


call sp_inserirUtilizadorSessao('xXxmarizexXx', 1, 1);
call sp_inserirUtilizadorSessao('tyler1', 1, 2);
call sp_inserirUtilizadorSessao('dghtrOfIvan', 1, 3);
call sp_inserirUtilizadorSessao('negritoclaro', 1, 4);
call sp_inserirUtilizadorSessao('jorgecalado', 1, 5);
call sp_inserirUtilizadorSessao('alexandre', 1, 6);
call sp_inserirUtilizadorSessao('diogooui', 1, 7);
call sp_inserirUtilizadorSessao('iamcat', 1, 8);
call sp_inserirUtilizadorSessao('luisao', 1, 9);
call sp_inserirUtilizadorSessao('69frtn69', 1, 10);
call sp_inserirUtilizadorSessao('lfv4ever', 1, 11);
call sp_inserirUtilizadorSessao('alexandre1', 1, 12);
call sp_inserirUtilizadorSessao('deguzmanz', 1, 13);
call sp_inserirUtilizadorSessao('xXxmarizexXx', 2, 1);
call sp_inserirUtilizadorSessao('tyler1', 2, 2);
call sp_inserirUtilizadorSessao('jorgecalado', 2, 5);
call sp_inserirUtilizadorSessao('diogooui', 2, 7);
call sp_inserirUtilizadorSessao('iamcat', 2, 8);
call sp_inserirUtilizadorSessao('luisao', 2, 9);
call sp_inserirUtilizadorSessao('xXxmarizexXx', 3, 1);
call sp_inserirUtilizadorSessao('tyler1', 3, 2);
call sp_inserirUtilizadorSessao('dghtrOfIvan', 3, 3);
call sp_inserirUtilizadorSessao('jorgecalado', 3, 5);
call sp_inserirUtilizadorSessao('diogooui', 3, 7);
call sp_inserirUtilizadorSessao('luisao', 3, 9);
call sp_inserirUtilizadorSessao('69frtn69', 3, 10);
call sp_inserirUtilizadorSessao('lfv4ever', 3, 11);
call sp_inserirUtilizadorSessao('alexandre1', 3, 12);
call sp_inserirUtilizadorSessao('tyler1', 4, 2);
call sp_inserirUtilizadorSessao('negritoclaro', 4, 4);
call sp_inserirUtilizadorSessao('alexandre', 4, 6);
call sp_inserirUtilizadorSessao('diogooui', 4, 7);
call sp_inserirUtilizadorSessao('iamcat', 4, 8);
call sp_inserirUtilizadorSessao('lfv4ever', 4, 11);
call sp_inserirUtilizadorSessao('alexandre1', 4, 12);
call sp_inserirUtilizadorSessao('dghtrOfIvan', 5, 3);
call sp_inserirUtilizadorSessao('negritoclaro', 5, 4);
call sp_inserirUtilizadorSessao('jorgecalado', 5, 5);
call sp_inserirUtilizadorSessao('alexandre', 5, 6);
call sp_inserirUtilizadorSessao('diogooui', 5, 7);
call sp_inserirUtilizadorSessao('iamcat', 5, 8);
call sp_inserirUtilizadorSessao('luisao', 5, 9);
call sp_inserirUtilizadorSessao('69frtn69', 5, 10);
call sp_inserirUtilizadorSessao('xXxmarizexXx', 6, 1);
call sp_inserirUtilizadorSessao('dghtrOfIvan', 6, 3);
call sp_inserirUtilizadorSessao('iamcat', 6, 8);
call sp_inserirUtilizadorSessao('luisao', 6, 9);
call sp_inserirUtilizadorSessao('69frtn69', 6, 10);
call sp_inserirUtilizadorSessao('dghtrOfIvan', 7, 3);
call sp_inserirUtilizadorSessao('jorgecalado', 7, 5);
call sp_inserirUtilizadorSessao('69frtn69', 7, 10);
call sp_inserirUtilizadorSessao('lfv4ever', 7, 11);
call sp_inserirUtilizadorSessao('alexandre1', 7, 12);
call sp_inserirUtilizadorSessao('deguzmanz', 7, 13);
call sp_inserirUtilizadorSessao('xXxmarizexXx', 8, 1);
call sp_inserirUtilizadorSessao('dghtrOfIvan', 8, 3);
call sp_inserirUtilizadorSessao('alexandre', 8, 6);
call sp_inserirUtilizadorSessao('iamcat', 8, 8);
call sp_inserirUtilizadorSessao('69frtn69', 8, 10);
call sp_inserirUtilizadorSessao('deguzmanz', 8, 13);


call sp_inserirUtilizadorSessao('xXxmarizexXx', 9, 14);
call sp_inserirUtilizadorSessao('tyler1', 9, 15);
call sp_inserirUtilizadorSessao('negritoclaro', 9, 17);
call sp_inserirUtilizadorSessao('baki', 9, 18);
call sp_inserirUtilizadorSessao('alexandre', 9, 19);
call sp_inserirUtilizadorSessao('sofsinha', 9, 20);
call sp_inserirUtilizadorSessao('iamcat', 9, 21);
call sp_inserirUtilizadorSessao('lexaa', 9, 22);
call sp_inserirUtilizadorSessao('caladajuz', 9, 23);
call sp_inserirUtilizadorSessao('lfv4ever', 9, 24);
call sp_inserirUtilizadorSessao('alexandre1', 9, 25);
call sp_inserirUtilizadorSessao('deguzmanz', 9, 26);
call sp_inserirUtilizadorSessao('xXxmarizexXx', 10, 14);
call sp_inserirUtilizadorSessao('negritoclaro', 10, 17);
call sp_inserirUtilizadorSessao('baki', 10, 18);
call sp_inserirUtilizadorSessao('alexandre', 10, 19);
call sp_inserirUtilizadorSessao('sofsinha', 10, 20);
call sp_inserirUtilizadorSessao('iamcat', 10, 21);
call sp_inserirUtilizadorSessao('lfv4ever', 10, 24);
call sp_inserirUtilizadorSessao('alexandre1', 10, 25);
call sp_inserirUtilizadorSessao('xXxmarizexXx', 11, 14);
call sp_inserirUtilizadorSessao('dghtrOfIvan', 11, 16);
call sp_inserirUtilizadorSessao('sofsinha', 11, 20);
call sp_inserirUtilizadorSessao('iamcat', 11, 21);
call sp_inserirUtilizadorSessao('caladajuz', 11, 23);
call sp_inserirUtilizadorSessao('lfv4ever', 11, 24);
call sp_inserirUtilizadorSessao('alexandre1', 11, 25);
call sp_inserirUtilizadorSessao('tyler1', 12, 15);
call sp_inserirUtilizadorSessao('dghtrOfIvan', 12, 16);
call sp_inserirUtilizadorSessao('negritoclaro', 12, 17);
call sp_inserirUtilizadorSessao('baki', 12, 18);
call sp_inserirUtilizadorSessao('sofsinha', 12, 20);
call sp_inserirUtilizadorSessao('iamcat', 12, 21);
call sp_inserirUtilizadorSessao('alexandre1', 12, 25);
call sp_inserirUtilizadorSessao('deguzmanz', 12, 26);
call sp_inserirUtilizadorSessao('xXxmarizexXx', 13, 14);
call sp_inserirUtilizadorSessao('tyler1', 13, 15);
call sp_inserirUtilizadorSessao('dghtrOfIvan', 13, 16);
call sp_inserirUtilizadorSessao('negritoclaro', 13, 17);
call sp_inserirUtilizadorSessao('baki', 13, 18);
call sp_inserirUtilizadorSessao('alexandre', 13, 19);
call sp_inserirUtilizadorSessao('sofsinha', 13, 20);
call sp_inserirUtilizadorSessao('iamcat', 13, 21);
call sp_inserirUtilizadorSessao('lexaa', 13, 22);
call sp_inserirUtilizadorSessao('caladajuz', 13, 23);
call sp_inserirUtilizadorSessao('lfv4ever', 13, 24);
call sp_inserirUtilizadorSessao('alexandre1', 13, 25);
call sp_inserirUtilizadorSessao('deguzmanz', 13, 26);
call sp_inserirUtilizadorSessao('xXxmarizexXx', 14, 14);
call sp_inserirUtilizadorSessao('baki', 14, 18);
call sp_inserirUtilizadorSessao('xXxmarizexXx', 15, 14);
call sp_inserirUtilizadorSessao('tyler1', 15, 15);
call sp_inserirUtilizadorSessao('baki', 15, 18);
call sp_inserirUtilizadorSessao('alexandre', 15, 19);
call sp_inserirUtilizadorSessao('sofsinha', 15, 20);
call sp_inserirUtilizadorSessao('iamcat', 15, 21);
call sp_inserirUtilizadorSessao('dghtrOfIvan', 16, 16);
call sp_inserirUtilizadorSessao('negritoclaro', 16, 17);
call sp_inserirUtilizadorSessao('baki', 16, 18);
call sp_inserirUtilizadorSessao('alexandre1', 16, 25);
call sp_inserirUtilizadorSessao('deguzmanz', 16, 26);
call sp_inserirUtilizadorSessao('negritoclaro', 17, 17);
call sp_inserirUtilizadorSessao('baki', 17, 18);
call sp_inserirUtilizadorSessao('alexandre', 17, 19);
call sp_inserirUtilizadorSessao('alexandre1', 17, 25);

call sp_inserirUtilizadorSessao('profCool', 18, 27);
call sp_inserirUtilizadorSessao('agustinhocampos', 18, 28);
call sp_inserirUtilizadorSessao('baka', 18, 29);
call sp_inserirUtilizadorSessao('baki', 18, 30);
call sp_inserirUtilizadorSessao('rdt', 18, 31);
call sp_inserirUtilizadorSessao('ze', 18, 32);
call sp_inserirUtilizadorSessao('sopas', 18, 33);
call sp_inserirUtilizadorSessao('alexandre1', 18, 36);
call sp_inserirUtilizadorSessao('stvrogers', 18, 37);
call sp_inserirUtilizadorSessao('profCool', 19, 27);
call sp_inserirUtilizadorSessao('baki', 19, 30);
call sp_inserirUtilizadorSessao('rdt', 19, 31);
call sp_inserirUtilizadorSessao('ze', 19, 32);
call sp_inserirUtilizadorSessao('Jaques', 19, 34);
call sp_inserirUtilizadorSessao('toytoy', 19, 35);
call sp_inserirUtilizadorSessao('stvrogers', 19, 37);
call sp_inserirUtilizadorSessao('profCool', 20, 27);
call sp_inserirUtilizadorSessao('baka', 20, 29);
call sp_inserirUtilizadorSessao('rdt', 20, 31);
call sp_inserirUtilizadorSessao('ze', 20, 32);
call sp_inserirUtilizadorSessao('profCool', 21, 27);
call sp_inserirUtilizadorSessao('agustinhocampos', 21, 28);
call sp_inserirUtilizadorSessao('baka', 21, 29);
call sp_inserirUtilizadorSessao('baki', 21, 30);
call sp_inserirUtilizadorSessao('profCool', 22, 27);
call sp_inserirUtilizadorSessao('agustinhocampos', 3, 28);
call sp_inserirUtilizadorSessao('baki', 22, 30);
call sp_inserirUtilizadorSessao('stvrogers', 22, 37);
call sp_inserirUtilizadorSessao('profCool', 23, 27);
call sp_inserirUtilizadorSessao('baka', 23, 29);
call sp_inserirUtilizadorSessao('sopas', 23, 33);
call sp_inserirUtilizadorSessao('Jaques', 23, 34);
call sp_inserirUtilizadorSessao('toytoy', 23, 35);
call sp_inserirUtilizadorSessao('stvrogers', 23, 37);

call sp_inserirUtilizadorSessao('xXxmarizexXx', 24, 38);
call sp_inserirUtilizadorSessao('dghtrOfIvan', 24, 40);
call sp_inserirUtilizadorSessao('baki', 24, 42);
call sp_inserirUtilizadorSessao('sopas', 24, 44);
call sp_inserirUtilizadorSessao('iamcat', 24, 45);
call sp_inserirUtilizadorSessao('luisao', 24, 46);
call sp_inserirUtilizadorSessao('caladajuz', 24, 47);
call sp_inserirUtilizadorSessao('deguzmanz', 24, 50);
call sp_inserirUtilizadorSessao('xXxmarizexXx', 25, 38);
call sp_inserirUtilizadorSessao('tyler1', 25, 39);
call sp_inserirUtilizadorSessao('dghtrOfIvan', 25, 40);
call sp_inserirUtilizadorSessao('baki', 25, 42);
call sp_inserirUtilizadorSessao('alexandre', 25, 43);
call sp_inserirUtilizadorSessao('sopas', 25, 44);
call sp_inserirUtilizadorSessao('iamcat', 25, 45);
call sp_inserirUtilizadorSessao('deguzmanz', 25, 50);
call sp_inserirUtilizadorSessao('xXxmarizexXx', 26, 38);
call sp_inserirUtilizadorSessao('baki', 26, 42);
call sp_inserirUtilizadorSessao('sofsinha', 26, 48);
call sp_inserirUtilizadorSessao('alexandre1', 26, 49);
call sp_inserirUtilizadorSessao('deguzmanz', 26, 50);
call sp_inserirUtilizadorSessao('tyler1', 27, 39);
call sp_inserirUtilizadorSessao('dghtrOfIvan', 27, 40);
call sp_inserirUtilizadorSessao('baki', 27, 42);
call sp_inserirUtilizadorSessao('iamcat', 27, 45);
call sp_inserirUtilizadorSessao('luisao', 27, 46);
call sp_inserirUtilizadorSessao('alexandre1', 27, 49);
call sp_inserirUtilizadorSessao('xXxmarizexXx', 28, 38);
call sp_inserirUtilizadorSessao('baki', 28, 42);
call sp_inserirUtilizadorSessao('iamcat', 28, 45);
call sp_inserirUtilizadorSessao('luisao', 28, 46);
call sp_inserirUtilizadorSessao('caladajuz', 28, 47);
call sp_inserirUtilizadorSessao('sofsinha', 28, 48);
call sp_inserirUtilizadorSessao('alexandre1', 28, 49);
call sp_inserirUtilizadorSessao('deguzmanz', 28, 50);
call sp_inserirUtilizadorSessao('xXxmarizexXx', 29, 38);
call sp_inserirUtilizadorSessao('tyler1', 29, 39);
call sp_inserirUtilizadorSessao('dghtrOfIvan', 29, 40);
call sp_inserirUtilizadorSessao('negritoclaro', 29, 41);
call sp_inserirUtilizadorSessao('baki', 29, 42);
call sp_inserirUtilizadorSessao('alexandre', 29, 43);
call sp_inserirUtilizadorSessao('sopas', 29, 44);
call sp_inserirUtilizadorSessao('iamcat', 29, 45);
call sp_inserirUtilizadorSessao('deguzmanz', 29, 50);
call sp_inserirUtilizadorSessao('xXxmarizexXx', 30, 38);
call sp_inserirUtilizadorSessao('dghtrOfIvan', 30, 40);
call sp_inserirUtilizadorSessao('negritoclaro', 30, 41);
call sp_inserirUtilizadorSessao('baki', 30, 42);
call sp_inserirUtilizadorSessao('sopas', 30, 44);
call sp_inserirUtilizadorSessao('sofsinha', 30, 48);
call sp_inserirUtilizadorSessao('alexandre1', 30, 49);
call sp_inserirUtilizadorSessao('deguzmanz', 30, 50);
call sp_inserirUtilizadorSessao('tyler1', 31, 39);
call sp_inserirUtilizadorSessao('dghtrOfIvan', 31, 40);
call sp_inserirUtilizadorSessao('negritoclaro', 31, 41);
call sp_inserirUtilizadorSessao('baki', 31, 42);
call sp_inserirUtilizadorSessao('deguzmanz', 31, 50);


call sp_inserirPack('NoCat','',0,0);
call sp_inserirPack('Basico','Café, Água e Bolachas ou bolos secos.',2,1);
call sp_inserirPack('Pro','Café, Água, Sumos de fruta e Miniaturas de bolos de pastelaria. ',5,1);
call sp_inserirPack('VIP','Café, Água, Sumos de fruta, Miniaturas de bolos de pastelaria, Folhados diversos (rissóis, croquetes, etc.), Tâmaras com presunto e Doces conventuais miniatura',10,1);


call sp_inserirSessaoPack(1, 'NoCat');
call sp_inserirSessaoPack(2, 'Basico');
call sp_inserirSessaoPack(3, 'Pro');
call sp_inserirSessaoPack(4, 'VIP');
call sp_inserirSessaoPack(5, 'Pro');
call sp_inserirSessaoPack(6, 'Basico');
call sp_inserirSessaoPack(7, 'Pro');
call sp_inserirSessaoPack(8, 'NoCat');

call sp_inserirSessaoPack(9, 'NoCat');
call sp_inserirSessaoPack(10, 'Basico');
call sp_inserirSessaoPack(11, 'Pro');
call sp_inserirSessaoPack(12, 'Basico');
call sp_inserirSessaoPack(13, 'Pro');
call sp_inserirSessaoPack(14, 'VIP');
call sp_inserirSessaoPack(15, 'Basico');
call sp_inserirSessaoPack(16, 'Pro');
call sp_inserirSessaoPack(17, 'NoCat');

call sp_inserirSessaoPack(18, 'NoCat');
call sp_inserirSessaoPack(19, 'Basico');
call sp_inserirSessaoPack(20, 'Pro');
call sp_inserirSessaoPack(21, 'Pro');
call sp_inserirSessaoPack(22, 'Pro');
call sp_inserirSessaoPack(23, 'NoCat');

call sp_inserirSessaoPack(24, 'NoCat');
call sp_inserirSessaoPack(25, 'Basico');
call sp_inserirSessaoPack(26, 'Basico');
call sp_inserirSessaoPack(27, 'Pro');
call sp_inserirSessaoPack(28, 'Pro');
call sp_inserirSessaoPack(29, 'Basico');
call sp_inserirSessaoPack(30, 'Basico');
call sp_inserirSessaoPack(31, 'NoCat');


call sp_inserirEquipamento(123456, 'Luz', 2.5, 4);
call sp_inserirEquipamento(111111, 'Escada', 10, 1);
call sp_inserirEquipamento(222222, 'Microfone', 5, 10);
call sp_inserirEquipamento(333333, 'Coluna', 5, 7);
call sp_inserirEquipamento(444444, 'Projetor', 40, 10);
call sp_inserirEquipamento(555555, 'Placar', 2, 3);
call sp_inserirEquipamento(666666, 'Comando', 5, 1);
call sp_inserirEquipamento(777777, 'Cadeira', 5, 5);
call sp_inserirEquipamento(888888, 'Adaptador', 3, 5);
call sp_inserirEquipamento(999999, 'Carregador', 4, 3);


call sp_inserirSala(123, 'Lazer', 35);
call sp_inserirSala(321, 'AI', 15);
call sp_inserirSala(1, 'Auditorio 1', 100);
call sp_inserirSala(9999, 'Nucleo', 50);
call sp_inserirSala(22, 'BD', 25);
call sp_inserirSala(2, 'Auditorio 2', 150);

call sp_inserirSala(11, 'Sala 1', 50);
call sp_inserirSala(12, 'Sala 2', 50);
call sp_inserirSala(13, 'Sala 3', 50);
call sp_inserirSala(14, 'Sala 4', 50);

call sp_inserirSala(250, 'Sala de Programação', 30);
call sp_inserirSala(270, 'Sala 70', 30);
call sp_inserirSala(271, 'Auditorio 1', 75);
call sp_inserirSala(301, 'Sala 1', 30);
call sp_inserirSala(302, 'Sala 2', 30);
call sp_inserirSala(303, 'Sala 3', 45);


call sp_inserirSessaoSala(1,123);
call sp_inserirSessaoSala(2,1);
call sp_inserirSessaoSala(3,321);
call sp_inserirSessaoSala(4,2);
call sp_inserirSessaoSala(5,22);
call sp_inserirSessaoSala(6,22);
call sp_inserirSessaoSala(7,2);
call sp_inserirSessaoSala(8,123);

call sp_inserirSessaoSala(9,123);
call sp_inserirSessaoSala(10,1);
call sp_inserirSessaoSala(11,321);
call sp_inserirSessaoSala(12,2);
call sp_inserirSessaoSala(13,321);
call sp_inserirSessaoSala(14,321);
call sp_inserirSessaoSala(15,2);
call sp_inserirSessaoSala(16,123);
call sp_inserirSessaoSala(17,123);

call sp_inserirSessaoSala(18,11);
call sp_inserirSessaoSala(19,11);
call sp_inserirSessaoSala(20,12);
call sp_inserirSessaoSala(21,13);
call sp_inserirSessaoSala(22,13);
call sp_inserirSessaoSala(23,11);

call sp_inserirSessaoSala(24,270);
call sp_inserirSessaoSala(25,271);
call sp_inserirSessaoSala(26,271);
call sp_inserirSessaoSala(27,250);
call sp_inserirSessaoSala(28,250);
call sp_inserirSessaoSala(29,301);
call sp_inserirSessaoSala(30,303);
call sp_inserirSessaoSala(31,303);


call sp_inserirAvaliacao(1, 4.5, 'Very fixe', '2019-07-15 12:30:00');
call sp_inserirAvaliacao(2, 0, 'seca', '2019-07-15 16:30:00');
call sp_inserirAvaliacao(3, 3, 'meh', '2019-07-15 18:30:00');
call sp_inserirAvaliacao(4, 5, 'Fantastico', '2019-07-16 12:30:00');
call sp_inserirAvaliacao(5, 1, 'adormeci', '2019-07-16 18:30:00');
call sp_inserirAvaliacao(6, 4, 'cool', '2019-07-17 12:30:00');
call sp_inserirAvaliacao(7, 3, 'muito informativo', '2019-07-17 14:00:00');
call sp_inserirAvaliacao(8, 3, 'Aprendi muita coisa!', '2019-07-17 18:30:00');

call sp_inserirAvaliacao(9, 5, 'adorei, vou continuar a seguir esta informação', '2019-07-18 11:30:00');
call sp_inserirAvaliacao(10, 2, 'nao é do meu interesse', '2019-07-18 12:30:00');
call sp_inserirAvaliacao(11, 0, 'Não me interessou nada', '2019-07-18 17:00:00');
call sp_inserirAvaliacao(12, 3.5, 'boa forma de aprender', '2019-07-18 18:30:00');
call sp_inserirAvaliacao(13, 4, 'adorei este dia', '2019-07-19 12:30:00');
call sp_inserirAvaliacao(14, 3.5, 'gostei muito! estarei ca amanha de novo', '2019-07-19 18:30:00');
call sp_inserirAvaliacao(15, 1, 'Sessao muito longa...', '2019-07-20 12:30:00');
call sp_inserirAvaliacao(16, 3, 'Mediano pouca interação', '2019-07-20 16:30:00');
call sp_inserirAvaliacao(17, 5, 'retiro o que disse! estava na sessao errada!', '2019-07-20 18:30:00');

call sp_inserirAvaliacao(18, 2.5, 'Apesar de ser um topico interessante, nao é para mim.', '2019-11-01 12:00:00');
call sp_inserirAvaliacao(19, 5, 'recomendo a toda a gente', '2019-11-01 18:00:00');
call sp_inserirAvaliacao(20, 1, 'chato...', '2019-11-02 12:00:00');
call sp_inserirAvaliacao(21, 0, 'Não gostei da comida.', '2019-11-02 18:00:00');
call sp_inserirAvaliacao(22, 4.5, 'Estarei ca no proximo evento!', '2019-11-03 12:00:00');
call sp_inserirAvaliacao(23, 0.5, 'nope nope', '2019-11-03 18:00:00');

call sp_inserirAvaliacao(24, 4, 'Bom orador! passou a mensagem', '2019-08-20 13:00:00');
call sp_inserirAvaliacao(25, 3, 'Aprendi algo.', '2019-08-20 16:00:00');
call sp_inserirAvaliacao(26, 3, 'deu para passar o tempo.', '2019-08-20 18:00:00');
call sp_inserirAvaliacao(27, 3, 'Gostei', '2019-08-21 13:00:00');
call sp_inserirAvaliacao(28, 5, 'Realmente um tópico interessante, todos deviamos aprender mais sobre isto!', '2019-08-21 18:00:00');
call sp_inserirAvaliacao(29, 5, 'Adoro este tipo de informação, e da forma que é partilhado', '2019-08-22 13:00:00');
call sp_inserirAvaliacao(30, 2.5, 'não percebi nada', '2019-08-22 16:00:00');
call sp_inserirAvaliacao(31, 2, 'tive problemas em ouvir o orador', '2019-08-22 18:00:00');

call sp_inserirAvaliacao(32, 1, 'não foi muito bem organizado..', '2019-08-22 18:00:00');
call sp_inserirAvaliacao(33, 3, 'Recomendo!!', '2019-08-20 16:00:00');
call sp_inserirAvaliacao(34, 4.5, 'fantasticoooo', '2019-11-01 12:00:00');
call sp_inserirAvaliacao(35, 3, 'tarde muito especial!', '2019-07-20 16:30:00');
call sp_inserirAvaliacao(36, 3, 'Quero aprender mais no futuro.', '2019-07-17 14:00:00');
call sp_inserirAvaliacao(37, 4, 'Base de dados é muito fixe!', '2019-07-15 16:30:00');
call sp_inserirAvaliacao(38, 4, 'adoro programar', '2019-08-21 13:00:00');
call sp_inserirAvaliacao(39, 4.5, 'compreendi tudo, e achei muito fixe', '2019-11-03 12:00:00');
call sp_inserirAvaliacao(40, 0, 'sem comentários..', '2019-08-22 18:00:00');


call sp_inserirSessaoAvaliacao(1,1);
call sp_inserirSessaoAvaliacao(2,2);
call sp_inserirSessaoAvaliacao(3,3);
call sp_inserirSessaoAvaliacao(4,4);
call sp_inserirSessaoAvaliacao(5,5);
call sp_inserirSessaoAvaliacao(6,6);
call sp_inserirSessaoAvaliacao(7,7);
call sp_inserirSessaoAvaliacao(8,8);
call sp_inserirSessaoAvaliacao(9,9);
call sp_inserirSessaoAvaliacao(10,10);
call sp_inserirSessaoAvaliacao(11,11);
call sp_inserirSessaoAvaliacao(12,12);
call sp_inserirSessaoAvaliacao(13,13);
call sp_inserirSessaoAvaliacao(14,14);
call sp_inserirSessaoAvaliacao(15,15);
call sp_inserirSessaoAvaliacao(16,16);
call sp_inserirSessaoAvaliacao(17,17);
call sp_inserirSessaoAvaliacao(18,18);
call sp_inserirSessaoAvaliacao(19,19);
call sp_inserirSessaoAvaliacao(20,20);
call sp_inserirSessaoAvaliacao(21,21);
call sp_inserirSessaoAvaliacao(22,22);
call sp_inserirSessaoAvaliacao(23,23);
call sp_inserirSessaoAvaliacao(24,24);
call sp_inserirSessaoAvaliacao(25,25);
call sp_inserirSessaoAvaliacao(26,26);
call sp_inserirSessaoAvaliacao(27,27);
call sp_inserirSessaoAvaliacao(28,28);
call sp_inserirSessaoAvaliacao(29,29);
call sp_inserirSessaoAvaliacao(30,30);
call sp_inserirSessaoAvaliacao(31,31);
call sp_inserirSessaoAvaliacao(31,32);
call sp_inserirSessaoAvaliacao(25,33);
call sp_inserirSessaoAvaliacao(18,34);
call sp_inserirSessaoAvaliacao(16,35);
call sp_inserirSessaoAvaliacao(7,36);
call sp_inserirSessaoAvaliacao(2,37);
call sp_inserirSessaoAvaliacao(27,38);
call sp_inserirSessaoAvaliacao(22,39);
call sp_inserirSessaoAvaliacao(31,40);


call sp_inserirUtilizadorAvaliacao('diogooui',1);
call sp_inserirUtilizadorAvaliacao('caladajuz', 2);
call sp_inserirUtilizadorAvaliacao('luisao',3);
call sp_inserirUtilizadorAvaliacao('lexaa', 4);
call sp_inserirUtilizadorAvaliacao('lfv4ever',5);
call sp_inserirUtilizadorAvaliacao('sopas',6);
call sp_inserirUtilizadorAvaliacao('toytoy', 7);
call sp_inserirUtilizadorAvaliacao('Jaques',8);
call sp_inserirUtilizadorAvaliacao('69frtn69', 9);
call sp_inserirUtilizadorAvaliacao('alexandre1',10);
call sp_inserirUtilizadorAvaliacao('sofsinha',11);
call sp_inserirUtilizadorAvaliacao('iamcat', 12);
call sp_inserirUtilizadorAvaliacao('luisao',13);
call sp_inserirUtilizadorAvaliacao('ze', 14);
call sp_inserirUtilizadorAvaliacao('lfv4ever',15);
call sp_inserirUtilizadorAvaliacao('diogooui',16);
call sp_inserirUtilizadorAvaliacao('iamcat', 17);
call sp_inserirUtilizadorAvaliacao('rdt',18);
call sp_inserirUtilizadorAvaliacao('69frtn69', 19);
call sp_inserirUtilizadorAvaliacao('toytoy',20);
call sp_inserirUtilizadorAvaliacao('diogooui',21);
call sp_inserirUtilizadorAvaliacao('sofsinha', 22);
call sp_inserirUtilizadorAvaliacao('luisao',23);
call sp_inserirUtilizadorAvaliacao('rdt', 24);
call sp_inserirUtilizadorAvaliacao('lexaa',25);
call sp_inserirUtilizadorAvaliacao('sofsinha',26);
call sp_inserirUtilizadorAvaliacao('iamcat', 27);
call sp_inserirUtilizadorAvaliacao('rdt',28);
call sp_inserirUtilizadorAvaliacao('Jaques', 29);
call sp_inserirUtilizadorAvaliacao('lfv4ever',30);
call sp_inserirUtilizadorAvaliacao('diogooui',31);
call sp_inserirUtilizadorAvaliacao('iamcat', 32);
call sp_inserirUtilizadorAvaliacao('lexaa',33);
call sp_inserirUtilizadorAvaliacao('Jaques', 34);
call sp_inserirUtilizadorAvaliacao('rdt',35);
call sp_inserirUtilizadorAvaliacao('rdt',36);
call sp_inserirUtilizadorAvaliacao('iamcat', 37);
call sp_inserirUtilizadorAvaliacao('luisao',38);
call sp_inserirUtilizadorAvaliacao('69frtn69', 39);
call sp_inserirUtilizadorAvaliacao('caladajuz',40);


call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);
call sp_inserirSuporteSessao(0);


call sp_inserirEquipamentoSessao(123456,1);
call sp_inserirEquipamentoSessao(111111,2);
call sp_inserirEquipamentoSessao(222222,3);
call sp_inserirEquipamentoSessao(444444,4);
call sp_inserirEquipamentoSessao(222222,5);
call sp_inserirEquipamentoSessao(123456,6);
call sp_inserirEquipamentoSessao(555555,7);
call sp_inserirEquipamentoSessao(222222,8);
call sp_inserirEquipamentoSessao(444444,9);
call sp_inserirEquipamentoSessao(222222,10);
call sp_inserirEquipamentoSessao(123456,11);
call sp_inserirEquipamentoSessao(123456,12);
call sp_inserirEquipamentoSessao(222222,13);
call sp_inserirEquipamentoSessao(444444,14);
call sp_inserirEquipamentoSessao(222222,15);
call sp_inserirEquipamentoSessao(123456,16);
call sp_inserirEquipamentoSessao(444444,17);
call sp_inserirEquipamentoSessao(222222,18);
call sp_inserirEquipamentoSessao(444444,19);
call sp_inserirEquipamentoSessao(222222,20);
call sp_inserirEquipamentoSessao(123456,21);
call sp_inserirEquipamentoSessao(555555,22);
call sp_inserirEquipamentoSessao(222222,23);
call sp_inserirEquipamentoSessao(555555,24);
call sp_inserirEquipamentoSessao(222222,25);
call sp_inserirEquipamentoSessao(123456,26);
call sp_inserirEquipamentoSessao(666666,27);
call sp_inserirEquipamentoSessao(333333,28);
call sp_inserirEquipamentoSessao(999999,29);
call sp_inserirEquipamentoSessao(333333,30);
call sp_inserirEquipamentoSessao(333333,31);


call sp_inserirLocalidade("Avenida Frei Heitor Pinto 12 Covilhã");
call sp_inserirLocalidade("Rua dos Rouxinóis Setúbal");
call sp_inserirLocalidade("Estrada Vale de Mulatas 14 Setúbal");
call sp_inserirLocalidade("Rua Cidade Liverpool 16 Lisboa");
call sp_inserirLocalidade("Estrada de Benfica Pares de 202 a 222 Lisboa");
call sp_inserirLocalidade("Rua Augusto Manuel de Freitas Lagoa");


call sp_inserirEventoLocalidade(1,2);
call sp_inserirEventoLocalidade(2,3);
call sp_inserirEventoLocalidade(3,3);
call sp_inserirEventoLocalidade(4,1);


call sp_inserirSpSSessao(1, 1, 'Introdução Programação', '2019-07-15 09:30:00', '2019-07-15 12:30:00', 3, 1);
call sp_inserirSpSSessao(2, 2, 'Introduçao a BD', '2019-07-15 14:00:00', '2019-07-15 16:30:00', 2.5, 1);
call sp_inserirSpSSessao(3, 3, 'Interação e Perguntas', '2019-07-15 16:30:00', '2019-07-15 18:30:00', 2, 1);
call sp_inserirSpSSessao(4, 4, 'BD', '2019-07-16 09:30:00', '2019-07-16 12:30:00', 3, 1);
call sp_inserirSpSSessao(5, 5, 'CBD', '2019-07-16 14:00:00', '2019-07-16 18:30:00', 4.5, 1);
call sp_inserirSpSSessao(6, 6, 'SQL', '2019-07-17 09:30:00', '2019-07-17 12:30:00', 3, 1);
call sp_inserirSpSSessao(7, 7, 'BD Object-oriented', '2019-07-17 14:00:00', '2019-07-17 16:30:00', 2.5, 1);
call sp_inserirSpSSessao(8, 8, 'Conclusão', '2019-07-17 16:30:00', '2019-07-17 18:30:00', 2, 1);

call sp_inserirSpSSessao(9, 9, 'Introdução Programação', '2019-07-18 09:30:00', '2019-07-18 11:30:00', 2, 2);
call sp_inserirSpSSessao(10, 10, 'AI Introduçao', '2019-07-18 11:30:00', '2019-07-18 12:30:00', 1, 2);
call sp_inserirSpSSessao(11, 11, 'Aplicações da AI', '2019-07-18 14:00:00', '2019-07-18 17:00:00', 3, 2);
call sp_inserirSpSSessao(12, 12, 'Interação e Perguntas', '2019-07-18 17:00:00', '2019-07-18 18:30:00', 1.5, 2);
call sp_inserirSpSSessao(13, 13, 'AI', '2019-07-19 09:30:00', '2019-07-19 12:30:00', 3, 2);
call sp_inserirSpSSessao(14, 14, 'AI Avançada', '2019-07-19 14:00:00', '2019-07-19 18:30:00', 4.5, 2);
call sp_inserirSpSSessao(15, 15, 'Startups', '2019-07-20 09:30:00', '2019-07-20 12:30:00', 3, 2);
call sp_inserirSpSSessao(16, 16, 'Inovação', '2019-07-20 14:00:00', '2019-07-20 16:30:00', 2, 2);
call sp_inserirSpSSessao(17, 17, 'Conclusão', '2019-07-20 16:30:00', '2019-07-20 18:30:00', 2, 2);

call sp_inserirSpSSessao(18, 18, 'Introdução', '2019-11-01 10:00:00', '2019-11-01 12:00:00', 2, 3);
call sp_inserirSpSSessao(19, 19, 'Esports', '2019-11-01 14:00:00', '2019-11-01 18:00:00', 4, 3);
call sp_inserirSpSSessao(20, 20, 'Competiçoes', '2019-11-02 10:00:00', '2019-11-02 12:00:00', 2, 3);
call sp_inserirSpSSessao(21, 21, 'Sport vs esport', '2019-11-02 14:00:00', '2019-11-02 18:00:00', 4, 3);
call sp_inserirSpSSessao(22, 22, 'esports pelo mundo', '2019-11-03 10:00:00', '2019-11-03 12:00:00', 2, 3);
call sp_inserirSpSSessao(23, 23, 'Conclusão', '2019-11-03 14:00:00', '2019-11-03 18:00:00', 4, 3);

call sp_inserirSpSSessao(24, 24, 'Introdução Programação', '2019-08-20 09:30:00', '2019-08-20 13:00:00', 3.5, 4);
call sp_inserirSpSSessao(25, 25, 'Introduçao a Java', '2019-08-20 14:00:00', '2019-08-20 16:00:00', 2, 4);
call sp_inserirSpSSessao(26, 26, 'Interação e Perguntas', '2019-08-20 16:00:00', '2019-08-20 18:00:00', 2, 4);
call sp_inserirSpSSessao(27, 27, 'POO', '2019-08-21 09:30:00', '2019-08-21 13:00:00', 3.5, 4);
call sp_inserirSpSSessao(28, 28, 'PA', '2019-08-21 14:00:00', '2019-08-21 18:00:00', 4, 4);
call sp_inserirSpSSessao(29, 29, 'Startups', '2019-08-22 09:30:00', '2019-08-22 13:00:00', 3.5, 4);
call sp_inserirSpSSessao(30, 30, 'Aplicações de Java', '2019-08-22 14:00:00', '2019-08-22 16:00:00', 2, 4);
call sp_inserirSpSSessao(31, 31, 'Conclusão', '2019-08-22 16:00:00', '2019-08-22 18:00:00', 2, 4);