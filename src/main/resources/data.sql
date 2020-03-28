insert into users values (1,current_date ,true ,'anercan@mail.com','12345678',5,'anercan');
insert into users values (2,current_date ,true ,'test1@mail.com','12345678',5,'test1');
insert into users values (3,current_date ,true ,'test2@mail.com','12345678',5,'test2');

insert into questions values (1,make_date(2020,2,25) ,'Consectetur adipiscing elit ? ','Lorem ipsum dolor sit amet, consectetur adipiscing elit',1);
insert into questions values (2,make_date(2020,2,26) ,'Consectetur adipiscing elit ? ','Lorem ipsum dolor sit amet, consectetur adipiscing elit',1);
insert into questions values (3,make_date(2020,2,27) ,'Consectetur adipiscing elit ? ','Lorem ipsum dolor sit amet, consectetur adipiscing elit',2);
insert into questions values (4,make_date(2020,1,19) ,'Consectetur adipiscing elit ? ','Lorem ipsum dolor sit amet, consectetur adipiscing elit',3);
insert into questions values (5,make_date(2020,3,20) ,'Consectetur adipiscing elit ? ','Lorem ipsum dolor sit amet, consectetur adipiscing elit',3);
insert into questions values (6,make_date(2020,3,22) ,'Consectetur adipiscing elit ? ','Lorem ipsum dolor sit amet, consectetur adipiscing elit',3);
insert into questions values (7,make_date(2020,3,22) ,'Consectetur adipiscing elit ? ','Lorem ipsum dolor sit amet, consectetur adipiscing elit',3);
#saat eklencek


insert into answers values (1,make_date(2020,3,21),'Lorem ipsum dolor sit amet, consectetur adipiscing elit ',10,15,false,1,1);
insert into answers values (2,make_date(2020,3,20),'Lorem ipsum dolor sit amet, consectetur adipiscing elit ',12,1,true,1,1);
insert into answers values (3,make_date(2020,2,23),'Lorem ipsum dolor sit amet, consectetur adipiscing elit ',10,15,false,1,2);
insert into answers values (4,make_date(2020,3,22),'Lorem ipsum dolor sit amet, consectetur adipiscing elit ',152,15,false,2,1);
insert into answers values (5,make_date(2020,2,25),'Lorem ipsum dolor sit amet, consectetur adipiscing elit ',10,8,false,2,3);

insert into categories values (1,'Müzik');
insert into categories values (2,'Sağlık');
insert into categories values (3,'Spor');
insert into categories values (4,'Siyaset');

insert into category_relations values (1,2);
insert into category_relations values (2,1);
insert into category_relations values (3,1);
insert into category_relations values (4,2);
insert into category_relations values (5,2);

insert into question_follower values (1,1);
insert into question_follower values (1,2);
insert into question_follower values (1,3);
insert into question_follower values (1,4);
insert into question_follower values (2,1);
insert into question_follower values (3,1);
insert into question_follower values (3,2);


insert into properties values ('app.login.fail.text.password',make_date(2019,12,20),'Kullanıcı Adı veya Şifre Yanlış.');

insert into properties values ('app.user.default.question.right',make_date(2019,12,20),'5');
