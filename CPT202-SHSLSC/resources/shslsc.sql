CREATE DATABASE  IF NOT EXISTS `shslsc` ;
USE `shslsc`;

DROP TABLE IF EXISTS `admin`;

create table admin
(
    admin_id        int         null,
    admin_name      varchar(10) null,
    password        varchar(20) null,
    email           varchar(20) null,
    power           int         null,
    create_time     datetime    null,
    expiration_time datetime    null
);

DROP TABLE IF EXISTS `stadium`;

create table stadium
(
    sta_id       int auto_increment
        primary key,
    sta_name     varchar(10)  not null,
    place_number int          null,
    open_time    datetime     null,
    close_time   datetime     null,
    sta_info     varchar(255) null,
    sta_picture  varchar(255) null,
    `limit`      int          null,
    price        int          null,
    location     varchar(255) null,
    constraint stadium_info_sta_id_uindex
        unique (sta_id),
    constraint stadium_info_sta_name_uindex
        unique (sta_name)
);

DROP TABLE IF EXISTS `place`;

create table place
(
    place_id int auto_increment
        primary key,
    sta_id   int         not null,
    status   varchar(20) null,
    constraint place_info_place_id_uindex
        unique (place_id),
    constraint place_info_stadium_info_sta_id_fk
        foreign key (sta_id) references stadium (sta_id)
);

DROP TABLE IF EXISTS `user`;

create table user
(
    user_id      int auto_increment
        primary key,
    user_name    varchar(10)   not null,
    email        varchar(20)   null,
    password     varchar(20)   null,
    sex          int default 1 null,
    phone_number varchar(20)   null,
    avatar       varchar(255)  null,
    create_time  datetime      null,
    constraint user_user_id_uindex
        unique (user_id),
    constraint user_user_name_uindex
        unique (user_name)
);

DROP TABLE IF EXISTS `reservation_record`;

create table reservation_record
(
    res_id         int auto_increment
        primary key,
    place_id       int           not null,
    user_id        int           not null,
    user_name      varchar(20)   null,
    booking_date   datetime      null,
    start_time     datetime      null,
    leave_time     datetime      null,
    booking_status varchar(20)   null,
    people         int default 1 null,
    constraint reservation_record_res_id_uindex
        unique (res_id),
    constraint reservation_record_place_info_place_id_fk
        foreign key (place_id) references place (place_id),
    constraint reservation_record_user_info_user_id_fk
        foreign key (user_id) references user (user_id)
);

