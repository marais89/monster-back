DROP TABLE IF EXISTS individu;
DROP TABLE IF EXISTS users ;
DROP TABLE IF EXISTS authorities;

create table individu
(
    id             int not null primary key,
    nom            varchar(20)   null,
    prenom         varchar(20)   null,
    adresse        varchar(200)  null,
    email          varchar(100)  null,
    date_naissance date          null,
    numero_tel     varchar(255)  null,
    date_creation  date          null,
    niveau         int default 0 not null,
    statut         varchar(20)   null,
    user_image     mediumblob    null,
    date_ceation   date          null
);

INSERT INTO individu (
id,
nom,
prenom,
adresse,
email,
date_naissance,
numero_tel,
date_creation,
niveau,
statut,
user_image,
date_ceation) VALUES
  (1, 'Aliko', 'Dangote', '9 rue maurice berteaux', 'test.test@gmail.com', current_date, '0678787878',current_date, 2, 'active',null, null ),
  (2, 'Folrunsho', 'Alakija', '9 rue maurice berteaux', 'test2.test@gmail.com', current_date, '0678787878',current_date, 3, 'active',null, null);


create table users
(
    username varchar(50)       not null
        primary key,
    password varchar(100)      not null,
    enabled  tinyint default 1 not null,
    id       bigint            not null
);

insert  into users(username, password, enabled, id) VALUES
('test.test@gmail.com','$2a$10$8JqO51Q6SROyKrd.68fR2Oa4i.G/me1ro0FZQAP2c2M9dY7UJvO1C',true ,1),
('test2.test@gmail.com','$2a$10$8JqO51Q6SROyKrd.68fR2Oa4i.G/me1ro0FZQAP2c2M9dY7UJvO1C',true ,1);


create table authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    id        bigint      not null,
    constraint ix_auth_username
        unique (username, authority),
    constraint authorities_ibfk_1
        foreign key (username) references users (username)
);

insert into authorities(username, authority, id) VALUES
('test.test@gmail.com','USER', 1),
('test2.test@gmail.com','USER', 1);