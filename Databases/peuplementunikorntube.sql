-- 20 commentaires
INSERT INTO `comments` (`comment`,`id_user`,`id_video`) VALUES ("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer",19,7),("Lorem ipsum dolor sit amet,",4,3),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit.",11,8),("Lorem",15,8),("Lorem ipsum dolor sit amet, consectetuer adipiscing",3,3),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu",12,3),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing",16,8),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu",4,6),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut",10,9),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et",3,2);
INSERT INTO `comments` (`comment`,`id_user`,`id_video`) VALUES ("Lorem ipsum dolor sit",18,6),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing",1,6),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur",8,7),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et",18,9),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit.",16,1),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam",6,5),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.",12,2),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna",19,1),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing",7,1),("Lorem",13,2);

-- 20 réponses aux 20 commentaires
INSERT INTO `comments` (`comment`,`id_user`,`id_video`,`id_response`) VALUES ("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer",9,5,5),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna",5,1,6),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et",16,4,7),("Lorem ipsum",15,7,18),("Lorem ipsum dolor sit amet, consectetuer",2,4,2),("Lorem ipsum",12,3,1),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing",9,6,2),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut",19,3,8),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit.",7,6,1),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus.",1,8,7);
INSERT INTO `comments` (`comment`,`id_user`,`id_video`,`id_response`) VALUES ("Lorem ipsum dolor sit amet,",18,5,4),("Lorem",14,7,19),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam",10,5,6),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.",5,1,12),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et",13,10,2),("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor.",17,1,16),("Lorem ipsum dolor sit",18,10,14),("Lorem",4,7,8),("Lorem",10,8,16),("Lorem ipsum",9,9,5);


-- 20 likes
INSERT INTO `likes` (`viewedtime`,`value`,`id_user`,`id_video`) VALUES ("2014-08-27 15:54:20",0,6,7),("2014-02-14 07:44:11",2,3,7),("2015-07-11 19:59:56",1,8,9),("2015-01-22 02:08:53",0,7,16),("2015-11-18 10:19:59",0,6,10),("2015-11-12 17:56:49",2,3,6),("2015-04-20 19:17:55",0,7,6),("2014-12-08 21:04:38",2,4,20),("2014-11-12 11:43:28",1,10,10),("2014-11-23 06:32:05",2,3,20);
INSERT INTO `likes` (`viewedtime`,`value`,`id_user`,`id_video`) VALUES ("2014-06-17 19:19:33",2,7,19),("2015-04-20 03:16:14",1,10,5),("2015-11-21 08:05:10",1,2,17),("2015-02-12 12:42:44",0,9,15),("2015-05-08 17:13:54",2,6,15),("2015-08-16 20:36:23",1,7,4),("2015-08-30 01:00:59",2,6,9),("2015-10-13 15:17:22",0,3,8),("2014-12-26 10:37:51",2,2,11),("2015-08-13 04:20:41",0,2,10);


-- 20 playlists
INSERT INTO `playlists` (`id_user`,`title`) VALUES (5,"dolor."),(5,"sapien, gravida non,"),(12,"nisi. Cum sociis natoque"),(5,"ante blandit viverra."),(10,"Sed dictum. Proin"),(17,"scelerisque"),(17,"semper auctor. Mauris vel"),(8,"Mauris eu"),(3,"et"),(3,"imperdiet ullamcorper. Duis");
INSERT INTO `playlists` (`id_user`,`title`) VALUES (14,"tortor nibh sit amet"),(13,"quam. Pellentesque habitant morbi"),(4,"ipsum"),(13,"semper egestas, urna justo"),(3,"bibendum. Donec felis orci, adipiscing"),(20,"tellus id nunc"),(7,"justo eu arcu. Morbi"),(3,"tellus,"),(4,"in felis. Nulla tempor augue"),(13,"Donec felis orci, adipiscing");


-- 20 playlist_video 
INSERT INTO `playlist_video` (`id_playlist`,`id_video`,`id_user`) VALUES (15,6,6),(17,20,2),(11,1,19),(4,15,17),(4,18,16),(11,2,10),(19,8,8),(16,19,15),(3,9,6),(13,9,6);
INSERT INTO `playlist_video` (`id_playlist`,`id_video`,`id_user`) VALUES (17,20,20),(2,19,14),(19,20,17),(15,2,10),(5,4,13),(6,2,1),(16,12,4),(12,18,1),(15,17,6),(11,14,3);


-- 20 tags
INSERT INTO `tags` (`tag`) VALUES ("nulla"),("urna"),("odio"),("In"),("euismod"),("pede."),("ac,"),("Phasellus"),("nibh"),("erat");
INSERT INTO `tags` (`tag`) VALUES ("auctor."),("ac"),("Sed"),("fringilla."),("augue"),("Morbi"),("ante"),("aliquam,"),("fringilla"),("In");


-- 20 tag_video
INSERT INTO `tag_video` (`id_video`,`id_tag`) VALUES (12,5),(7,8),(3,9),(5,14),(6,5),(4,13),(7,9),(10,9),(14,17),(12,4);
INSERT INTO `tag_video` (`id_video`,`id_tag`) VALUES (7,9),(2,2),(10,7),(8,4),(18,12),(11,8),(15,11),(9,20),(11,18),(14,1);


-- 20 users
INSERT INTO `users` (`username`,`password`,`email`) VALUES ("Léo","varius","leo.donny2@gmail.com"),("Guillaume","mollis.","guillaume.lecocq@gmx.fr"),("Idriss","libero","aarsenyx@gmail.com"),("Sandra","adipiscing","sandra.ladu@gmail.com"),("Damien","egestas.","damien.serin@gmail.com"),("Mickael","sem","mickael.goualard@gmail.com"),("Chandler","placerat,","molestie.sodales@acfeugiat.ca"),("Brynne","ut","Nunc.mauris.Morbi@lacusQuisquepurus.ca"),("Ignacia","Integer","varius@nequesedsem.org"),("Kermit","libero","purus.accumsan@nequeet.net");
INSERT INTO `users` (`username`,`password`,`email`) VALUES ("Ariel","ligula.","facilisis.Suspendisse.commodo@commodoauctor.net"),("Gareth","luctus","a.tortor.Nunc@dui.co.uk"),("Demetria","a","sodales@rutrum.com"),("Hayden","tempus","Phasellus@ipsumdolorsit.edu"),("Aiko","diam","ligula.Nullam@dapibusgravida.edu"),("Bevis","magna.","pretium@acfacilisis.co.uk"),("Jane","eros.","lorem@vitae.ca"),("Quentin","Nunc","penatibus@eu.edu"),("Nayda","ultricies","ullamcorper.Duis@lectusquismassa.edu"),("Lawrence","cubilia","eget.odio@ac.org");


-- 20 videos
INSERT INTO `videos` (`title`,`id_user`) VALUES ("purus sapien, gravida non,",7),("augue,",7),("ante blandit viverra. Donec tempus,",16),("turpis",7),("Fusce aliquam, enim",7),("magna. Suspendisse",18),("Class",15),("mauris.",20),("ullamcorper eu, euismod ac,",12),("dolor elit, pellentesque a,",13);
INSERT INTO `videos` (`title`,`id_user`) VALUES ("ipsum",3),("molestie tellus. Aenean",8),("ornare, elit",14),("vel",4),("per conubia nostra, per",20),("vel, convallis in,",17),("Fusce",2),("Donec egestas.",3),("Morbi non",2),("iaculis nec, eleifend",7);
