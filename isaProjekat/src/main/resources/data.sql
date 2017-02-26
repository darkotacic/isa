insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (1,'16-10-15','darko.tacic@gmail.com','sifra','Darko','Tacic','GUEST');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (2,'16-10-15','marko.kljajic@gmail.com','sifra','Marko','Kljajic','GUEST');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (13,'16-10-15','test13.test@gmail.com','sifra','Test','Test','GUEST');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (14,'16-10-15','test14.test@gmail.com','sifra','Test','Test','GUEST');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (15,'16-10-15','test15.test@gmail.com','sifra','Test','Test','GUEST');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (16,'16-10-15','test16.test@gmail.com','sifra','Test','Test','GUEST');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (3,'16-10-15','milica.govedarica@gmail.com','sifra','Milca','Govedarica','WAITER');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (4,'16-10-15','sasa.momcilovic@gmail.com','sifra','Sasa','Momcilovic','WAITER');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (5,'16-10-15','mirko.odalovic@gmail.com','sifra','Mirko','Odalovic','COOK');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (6,'16-10-15','bakir.niksic@gmail.com','sifra','Baki','Niksic','COOK');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (7,'16-10-15','aleksandar.petrovic@gmail.com','sifra','Aca','Petrovic','SYSTEMMANAGER');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (8,'16-10-15','nikola.sajic@gmail.com','sifra','Nikola','Sajic','BARTENDER');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (9,'16-10-15','tamara.mrskic@gmail.com','sifra','Tamara','Mrksic','RESTAURANTMANAGER');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (10,'16-10-15','stefan.varajic@gmail.com','sifra','Stevan','Varaja','RESTAURANTMANAGER');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (11,'16-10-15','bider.bidic@gmail.com','sifra','Batko','Batkic','BIDDER');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (12,'16-10-15','bider2.bidic@gmail.com','sifra','Batko','Batkic','BIDDER');

insert into bidder(user_id) values(11);
insert into bidder(user_id) values(12);



insert into restaurant(res_id, res_desc, res_name) values(1,'Kineski restoran','Dva stapica');
insert into restaurant(res_id, res_desc, res_name) values(2,'Restoran domace kuhinje','Kod cice');


insert into worker(user_id,wrk_shirt,wrk_shoe, restaurant_res_id) values(3,'S',4,1);
insert into worker(user_id,wrk_shirt,wrk_shoe, restaurant_res_id) values(4,'L',5,1);
insert into worker(user_id,wrk_shirt,wrk_shoe, restaurant_res_id) values(5,'XL',6, 1);
insert into worker(user_id,wrk_shirt,wrk_shoe, restaurant_res_id) values(6,'XXL',7,1);
insert into worker(user_id,wrk_shirt,wrk_shoe, restaurant_res_id) values(8,'3XL',8,1);

insert into guest (user_id,status) values (1,'ACTIVE');
insert into guest (user_id,status) values (2,'ACTIVE');
insert into guest (user_id,status) values (13,'ACTIVE');
insert into guest (user_id,status) values (14,'ACTIVE');
insert into guest (user_id,status) values (15,'ACTIVE');
insert into guest (user_id,status) values (16,'ACTIVE');
insert into waiter (user_id) values (3);
insert into waiter (user_id) values (4);
insert into cook (user_id,cook_type) values (5,'SALAT');
insert into cook (user_id,cook_type) values (6,'FRIED');
insert into system_manager (user_id, predefined) values (7, true);
insert into bartender (user_id) values (8);

insert into friend(sender_id,reciever_id,status) values(1,2,false);
insert into friend(sender_id,reciever_id,status) values(1,14,false);
insert into friend(sender_id,reciever_id,status) values(1,15,false);
insert into friend(sender_id,reciever_id,status) values(1,16,false);
insert into friend(sender_id,reciever_id,status) values(1,13,true);
insert into friend(sender_id,reciever_id,status) values(13,2,true);
insert into friend(sender_id,reciever_id,status) values(13,14,true);
insert into friend(sender_id,reciever_id,status) values(13,15,false);
insert into friend(sender_id,reciever_id,status) values(13,16,false);

insert into restaurant_manager (user_id,restaurant_res_id) values (9,1);
insert into restaurant_manager (user_id,restaurant_res_id) values (10,1);

insert into product(pr_id, pr_des, pr_name, pr_price, product_type) values(1,'description','Coca cola',120.3,'DRINK');
insert into product(pr_id, pr_des, pr_name, pr_price, product_type) values(2,'description1','Karadjordjeva snicla',750.0,'FRIED');
insert into product(pr_id, pr_des, pr_name, pr_price, product_type) values(3,'description1','Ruska salata',150.0,'SALAT');
insert into product(pr_id, pr_des, pr_name, pr_price, product_type) values(4,'description3','Kineska supa',350.0,'BOILED');

insert into restaurant_products(pr_id, res_id) values (1, 1);
insert into restaurant_products(pr_id, res_id) values (2, 1);
insert into restaurant_products(pr_id, res_id) values (3, 2);
insert into restaurant_products(pr_id, res_id) values (2, 2);
insert into restaurant_products(pr_id, res_id) values (4, 1);

insert into request_offer(id, expiration_date, start_date, status, restaurant_manager_user_id)  values (1, '12-12-17', '10-12-15', true, 9);
insert into request_offer(id, expiration_date, start_date, status, restaurant_manager_user_id)  values (2, '12-12-15', '10-12-15', true, 9);


insert into offered_products(ro_id, pr_id) values (1, 1);

insert into bidder_offer(id, bo_dod, bo_garanty, bo_price, bidder_user_id, request_offer_id) values (1, '13-12-15', 'Return money', 300.3, 11, 1);
insert into bidder_offer(id, bo_dod, bo_garanty, bo_price, bidder_user_id, request_offer_id) values (2, '13-12-15', 'Return money', 300.3, 11, 2);

insert into segment(id, sgm_pos, restaurant_res_id) values(1,'basta',1);
insert into segment(id, sgm_pos, sgm_smoking, restaurant_res_id) values(2,'unutra',false,1);
insert into segment(id, sgm_pos, restaurant_res_id) values(3,'unutra',1);

insert into restaurant_table(id, res_free, res_chair, segment_id) values(1,false,4,1);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(2,true,2,1);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(3,true,6,1);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(4,true,4,2);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(5,true,2,2);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(6,true,4,2);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(7,true,4,3);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(8,true,8,3);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(9,true,4,1);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(10,true,3,1);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(11,true,2,1);

insert into work_schedule(work_sch_id, work_sch_date, work_sch_second_date, work_sch_start, work_sch_end, segment_id, replacement_user_id,worker_user_id) values(1,'17-02-25','17-02-25',8.0,15.0,1,4,3);
insert into work_schedule(work_sch_id, work_sch_date, work_sch_second_date, work_sch_start, work_sch_end, segment_id, replacement_user_id,worker_user_id) values(2,'16-10-15','16-10-15',15.0,22.0,2,null,4);
insert into work_schedule(work_sch_id, work_sch_date, work_sch_second_date, work_sch_start, work_sch_end, segment_id, replacement_user_id,worker_user_id) values(3,'16-10-16','16-10-16',7.0,14.0,2,4,3);
insert into work_schedule(work_sch_id, work_sch_date, work_sch_second_date, work_sch_start, work_sch_end, segment_id, replacement_user_id,worker_user_id) values(4,'16-10-16','16-10-16',14.0,21.0,3,null,4);
insert into work_schedule(work_sch_id, work_sch_date, work_sch_second_date, work_sch_start, work_sch_end, segment_id, replacement_user_id,worker_user_id) values(5,'16-10-16','16-10-16',7.0,14.0,2,6,5);
insert into work_schedule(work_sch_id, work_sch_date, work_sch_second_date, work_sch_start, work_sch_end, segment_id, replacement_user_id,worker_user_id) values(6,'16-10-16','16-10-16',7.0,14.0,2,null,8);
insert into work_schedule(work_sch_id, work_sch_date, work_sch_second_date, work_sch_start, work_sch_end, segment_id, replacement_user_id,worker_user_id) values(7,'17-02-16','17-02-16',8.0,13.0,2,4,3);
insert into work_schedule(work_sch_id, work_sch_date, work_sch_second_date, work_sch_start, work_sch_end, segment_id, replacement_user_id,worker_user_id) values(8,'17-02-16','17-02-16',13.0,22.0,1,null,4);


insert into res_ord(res_ord_id, res_ord_date, order_time, table_id, waiter_user_id,price,order_status) values(1,'17-02-16',12.0,1,3,0,'NOTPAID');
insert into res_ord(res_ord_id, res_ord_date, order_time, table_id, waiter_user_id,price,order_status) values(2,'16-10-16',17.47,3,4,0,'NOTPAID');

insert into ord_it(ord_it_id, ord_it_qua, bartender_user_id, cook_user_id, order_res_ord_id, product_pr_id,status) values(1,2,8,null,1,1,'ONHOLD');
insert into ord_it(ord_it_id, ord_it_qua, bartender_user_id, cook_user_id, order_res_ord_id, product_pr_id,status) values(2,1,null,6,1,2,'ONHOLD');
insert into ord_it(ord_it_id, ord_it_qua, bartender_user_id, cook_user_id, order_res_ord_id, product_pr_id,status) values(3,1,null,6,2,2,'ONHOLD');
insert into ord_it(ord_it_id, ord_it_qua, bartender_user_id, cook_user_id, order_res_ord_id, product_pr_id,status) values(4,1,null,5,2,3,'ONHOLD');

insert into grade(grd_id, grd_meal, grd_res, grd_service, guest_user_id, order_res_ord_id, restaurant_res_id) values (1, 3, 5, 2, 1, 1, 1);
insert into grade(grd_id, grd_meal, grd_res, grd_service, guest_user_id, order_res_ord_id, restaurant_res_id) values (2, 4, 5, 8, 1, 1, 1);
insert into grade(grd_id, grd_meal, grd_res, grd_service, guest_user_id, order_res_ord_id, restaurant_res_id) values (3, 9, 5, 8, 1, 1, 1);