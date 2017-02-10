insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname) values (1,'16-10-15','darko.tacic@gmail.com','sifra','Darko','Tacic');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname) values (2,'16-10-15','marko.kljajic@gmail.com','sifra','Marko','Kljajic');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname) values (3,'16-10-15','milica.govedarica@gmail.com','sifra','Milca','Govedarica');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname) values (4,'16-10-15','sasa.momcilovic@gmail.com','sifra','Sasa','Momcilovic');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname) values (5,'16-10-15','mirko.odalovic@gmail.com','sifra','Mirko','Odalovic');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname) values (6,'16-10-15','bakir.niksic@gmail.com','sifra','Baki','Niksic');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname) values (7,'16-10-15','aleksandar.petrovic@gmail.com','sifra','Aca','Petrovic');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname) values (8,'16-10-15','nikola.sajic@gmail.com','sifra','Nikola','Sajic');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname) values (9,'16-10-15','tamara.mrskic@gmail.com','sifra','Tamara','Mrksic');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname) values (10,'16-10-15','stefan.varajic@gmail.com','sifra','Stevan','Varaja');

insert into worker(user_id,wrk_shirt,wrk_shoe) values(3,'S',4);
insert into worker(user_id,wrk_shirt,wrk_shoe) values(4,'L',5);
insert into worker(user_id,wrk_shirt,wrk_shoe) values(5,'XL',6);
insert into worker(user_id,wrk_shirt,wrk_shoe) values(6,'XXL',7);
insert into worker(user_id,wrk_shirt,wrk_shoe) values(8,'3XL',8);

insert into guest (user_id) values (1);
insert into guest (user_id) values (2);
insert into waiter (user_id) values (3);
insert into waiter (user_id) values (4);
insert into cook (user_id,cook_type) values (5,'SALAT');
insert into cook (user_id,cook_type) values (6,'FRIED');
insert into system_manager (user_id) values (7);
insert into bartender (user_id) values (8);

insert into restaurant(res_id, res_desc, res_name) values(1,'Kineski restoran','Dva stapica');
insert into restaurant(res_id, res_desc, res_name) values(2,'Restoran domace kuhinje','Kod cice');

insert into restaurant_manager (user_id,restaurant_res_id) values (9,1);
insert into restaurant_manager (user_id,restaurant_res_id) values (10,1);


insert into product(pr_id, pr_des, pr_name, pr_price, product_type) values(1,'description','Coca cola',120.3,'DRINK');
insert into product(pr_id, pr_des, pr_name, pr_price, product_type) values(2,'description1','Karadjordjeva snicla',750.0,'FRIED');
insert into product(pr_id, pr_des, pr_name, pr_price, product_type) values(3,'description1','Ruska salata',150.0,'SALAT');
insert into product(pr_id, pr_des, pr_name, pr_price, product_type) values(4,'description3','Kineska supa',350.0,'BOILED');

insert into restaurant_products(pr_id, res_id) values (1, 1);


insert into segment(id, sgm_pos, sgm_rest, restaurant_res_id) values(1,'basta','pusacki',1);
insert into segment(id, sgm_pos, sgm_rest, restaurant_res_id) values(2,'unutra','pusacki',1);
insert into segment(id, sgm_pos, sgm_rest, restaurant_res_id) values(3,'unutra','nepusacki',1);

insert into restaurant_table(id, res_free, res_chair, segment_id) values(1,true,4,1);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(2,true,2,1);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(3,true,6,1);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(4,true,4,2);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(5,true,2,2);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(6,true,4,2);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(7,true,4,3);
insert into restaurant_table(id, res_free, res_chair, segment_id) values(8,true,8,3);

insert into work_schedule(work_sch_id, worker_user_id, work_sch_date, work_sch_start, work_sch_end, segment_id, shift_user_id) values(1,3,'16-10-15',8.0,15.0,1,4);
insert into work_schedule(work_sch_id, worker_user_id, work_sch_date, work_sch_start, work_sch_end, segment_id, shift_user_id) values(2,4,'16-10-15',15.0,22.0,2,null);
insert into work_schedule(work_sch_id, worker_user_id, work_sch_date, work_sch_start, work_sch_end, segment_id, shift_user_id) values(3,3,'16-10-16',7.0,14.0,2,4);
insert into work_schedule(work_sch_id, worker_user_id, work_sch_date, work_sch_start, work_sch_end, segment_id, shift_user_id) values(4,4,'16-10-16',14.0,21.0,3,null);

insert into res_ord(res_ord_id, res_ord_date, table_id, waiter_user_id) values(1,'16-10-15',1,3);
insert into res_ord(res_ord_id, res_ord_date, table_id, waiter_user_id) values(2,'16-10-16',3,4);

insert into ord_it(ord_it_id, ord_it_qua, bartender_user_id, cook_user_id, order_res_ord_id, product_pr_id) values(1,2,8,null,1,1);
insert into ord_it(ord_it_id, ord_it_qua, bartender_user_id, cook_user_id, order_res_ord_id, product_pr_id) values(2,1,null,6,1,2);
insert into ord_it(ord_it_id, ord_it_qua, bartender_user_id, cook_user_id, order_res_ord_id, product_pr_id) values(3,1,null,6,2,2);
insert into ord_it(ord_it_id, ord_it_qua, bartender_user_id, cook_user_id, order_res_ord_id, product_pr_id) values(4,1,null,5,2,3);