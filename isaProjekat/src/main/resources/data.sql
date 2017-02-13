insert into user (user_date,user_email,user_pass,user_name,user_surname) values ('16-10-15','darko.tacic@gmail.com','sifra','Darko','Tacic');
insert into user (user_date,user_email,user_pass,user_name,user_surname) values ('16-10-15','marko.kljajic@gmail.com','sifra','Marko','Kljajic');
insert into user (user_date,user_email,user_pass,user_name,user_surname) values ('16-10-15','milica.govedarica@gmail.com','sifra','Milca','Govedarica');
insert into user (user_date,user_email,user_pass,user_name,user_surname) values ('16-10-15','sasa.momcilovic@gmail.com','sifra','Sasa','Momcilovic');
insert into user (user_date,user_email,user_pass,user_name,user_surname) values ('16-10-15','mirko.odalovic@gmail.com','sifra','Mirko','Odalovic');
insert into user (user_date,user_email,user_pass,user_name,user_surname) values ('16-10-15','bakir.niksic@gmail.com','sifra','Baki','Niksic');
insert into user (user_date,user_email,user_pass,user_name,user_surname) values ('16-10-15','aleksandar.petrovic@gmail.com','sifra','Aca','Petrovic');
insert into user (user_date,user_email,user_pass,user_name,user_surname) values ('16-10-15','nikola.sajic@gmail.com','sifra','Nikola','Sajic');
insert into user (user_date,user_email,user_pass,user_name,user_surname) values ('16-10-15','tamara.mrskic@gmail.com','sifra','Tamara','Mrksic');
insert into user (user_date,user_email,user_pass,user_name,user_surname) values ('16-10-15','stefan.varajic@gmail.com','sifra','Stevan','Varaja');
insert into user (user_date,user_email,user_pass,user_name,user_surname) values ('16-10-15','stefan.ceranic@gmail.com','sifra','Stefan','Ceranic');
insert into user (user_date,user_email,user_pass,user_name,user_surname) values ('16-10-15','milos.nisic@gmail.com','sifra','Milos','Nisic');

insert into bidder(user_email) values('stefan.ceranic@gmail.com');
insert into bidder(user_email) values('milos.nisic@gmail.com');

insert into worker(user_email,wrk_shirt,wrk_shoe) values('milica.govedarica@gmail.com','S',4);
insert into worker(user_email,wrk_shirt,wrk_shoe) values('sasa.momcilovic@gmail.com','L',5);
insert into worker(user_email,wrk_shirt,wrk_shoe) values('mirko.odalovic@gmail.com','XL',6);
insert into worker(user_email,wrk_shirt,wrk_shoe) values('bakir.niksic@gmail.com','XXL',7);
insert into worker(user_email,wrk_shirt,wrk_shoe) values('nikola.sajic@gmail.com','3XL',8);


insert into guest (user_email) values ('darko.tacic@gmail.com');
insert into guest (user_email) values ('marko.kljajic@gmail.com');
insert into waiter (user_email) values ('milica.govedarica@gmail.com');
insert into waiter (user_email) values ('sasa.momcilovic@gmail.com');
insert into cook (user_email,cook_type) values ('mirko.odalovic@gmail.com','SALAT');
insert into cook (user_email,cook_type) values ('bakir.niksic@gmail.com','FRIED');
insert into system_manager (user_email) values ('aleksandar.petrovic@gmail.com');
insert into bartender (user_email) values ('nikola.sajic@gmail.com');

insert into restaurant(res_desc, res_name) values('Kineski restoran','Dva stapica');
insert into restaurant(res_desc, res_name) values('Restoran domace kuhinje','Kod cice');

insert into restaurant_manager (user_email,restaurant_res_name) values ('tamara.mrskic@gmail.com','Dva stapica');
insert into restaurant_manager (user_email,restaurant_res_name) values ('stefan.varajic@gmail.com','Dva stapica');

insert into product(pr_des, pr_name, pr_price, product_type) values('description','Coca cola',120.3,'DRINK');
insert into product(pr_des, pr_name, pr_price, product_type) values('description1','Karadjordjeva snicla',750.0,'FRIED');
insert into product(pr_des, pr_name, pr_price, product_type) values('description1','Ruska salata',150.0,'SALAT');
insert into product(pr_des, pr_name, pr_price, product_type) values('description3','Kineska supa',350.0,'BOILED');

insert into restaurant_products(pr_name, res_name) values ('Coca cola', 'Dva stapica');

insert into request_offer(id, expiration_date, start_date, status, restaurant_manager_user_email)  values (1, '12-12-15', '10-12-15', true, 'tamara.mrskic@gmail.com');
insert into request_offer(id, expiration_date, start_date, status, restaurant_manager_user_email)  values (2, '12-12-15', '10-12-15', true, 'tamara.mrskic@gmail.com');


insert into offered_products(ro_id, pr_name) values (1, 'Coca cola');

insert into bidder_offer(id, bo_dod, bo_garanty, bo_price, bidder_user_email, request_offer_id) values (1, '13-12-15', 'Return money', 300.3, 'stefan.ceranic@gmail.com', 1);
insert into bidder_offer(id, bo_dod, bo_garanty, bo_price, bidder_user_email, request_offer_id) values (2, '13-12-15', 'Return money', 300.3, 'stefan.ceranic@gmail.com', 2);

insert into segment(id, sgm_pos, sgm_smoking, restaurant_res_name) values(1,'basta',false,'Dva stapica');
insert into segment(id, sgm_pos, restaurant_res_name) values(2,'unutra','Dva stapica');
insert into segment(id, sgm_pos, restaurant_res_name) values(3,'unutra','Dva stapica');

insert into restaurant_table(id, res_free, res_chair, segment_id) values(1,true,4,1);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(2,true,2,1);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(3,true,6,1);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(4,true,4,2);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(5,true,2,2);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(6,true,4,2);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(7,true,4,3);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(8,true,8,3);

insert into work_schedule(work_sch_id, worker_user_email, work_sch_date, work_sch_start, work_sch_end, segment_id, shift_user_email) values(1,'milica.govedarica@gmail.com','16-10-15',8.0,15.0,1,'sasa.momcilovic@gmail.com');
insert into work_schedule(work_sch_id, worker_user_email, work_sch_date, work_sch_start, work_sch_end, segment_id, shift_user_email) values(2,'sasa.momcilovic@gmail.com','16-10-15',15.0,22.0,2,null);
insert into work_schedule(work_sch_id, worker_user_email, work_sch_date, work_sch_start, work_sch_end, segment_id, shift_user_email) values(3,'milica.govedarica@gmail.com','16-10-16',7.0,14.0,2,'sasa.momcilovic@gmail.com');
insert into work_schedule(work_sch_id, worker_user_email, work_sch_date, work_sch_start, work_sch_end, segment_id, shift_user_email) values(4,'sasa.momcilovic@gmail.com','16-10-16',14.0,21.0,3,null);

insert into res_ord(res_ord_id, res_ord_date, table_id, waiter_user_email) values(1,'16-10-15',1,'milica.govedarica@gmail.com');
insert into res_ord(res_ord_id, res_ord_date, table_id, waiter_user_email) values(2,'16-10-16',3,'sasa.momcilovic@gmail.com');

insert into ord_it(ord_it_id, ord_it_qua, bartender_user_email, cook_user_email, order_res_ord_id, product_pr_name) values(1,2,'nikola.sajic@gmail.com',null,1,'Coca cola');
insert into ord_it(ord_it_id, ord_it_qua, bartender_user_email, cook_user_email, order_res_ord_id, product_pr_name) values(2,1,null,'bakir.niksic@gmail.com',1,'Karadjordjeva snicla');
insert into ord_it(ord_it_id, ord_it_qua, bartender_user_email, cook_user_email, order_res_ord_id, product_pr_name) values(3,1,null,'bakir.niksic@gmail.com',2,'Karadjordjeva snicla');
insert into ord_it(ord_it_id, ord_it_qua, bartender_user_email, cook_user_email, order_res_ord_id, product_pr_name) values(4,1,null,'mirko.odalovic@gmail.com',2,'Ruska salata');