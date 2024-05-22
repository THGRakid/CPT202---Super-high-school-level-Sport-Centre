CREATE DATABASE  IF NOT EXISTS `shslsc` ;
USE `shslsc`;

create table admin
(
    admin_id        int auto_increment
        primary key,
    admin_name      varchar(10)  null,
    dep_name        varchar(10)  null,
    password        varchar(100) null,
    phone           varchar(20)  null,
    email           varchar(20)  null,
    create_time     datetime     null,
    expiration_time datetime     null,
    power           int          null,
    constraint admin_id
        unique (admin_id)
);

create table stadium
(
    sta_id       int auto_increment
        primary key,
    sta_name     varchar(30)  not null,
    place_number int          null,
    open_time    varchar(10)  null,
    close_time   varchar(10)  null,
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

create table timeslots
(
    slot_id    int auto_increment
        primary key,
    place_id   int        not null,
    start_time char(30)   null,
    end_time   char(30)   null,
    is_booked  tinyint(1) null,
    constraint timeslots_place_place_id_fk
        foreign key (place_id) references place (place_id)
);

create table user
(
    user_id     int auto_increment
        primary key,
    openid      varchar(45)   null,
    user_name   varchar(15)   not null,
    phone       varchar(20)   null,
    email       varchar(30)   null,
    password    varchar(100)  null,
    sex         int default 1 null,
    avatar      varchar(255)  null,
    background  varchar(255)  null,
    create_time datetime      null,
    constraint user_user_id_uindex
        unique (user_id),
    constraint user_user_name_uindex
        unique (user_name)
);

create table reservation_record
(
    res_id         int auto_increment
        primary key,
    sta_id         int           null,
    place_id       int           not null,
    user_id        int           not null,
    user_name      varchar(20)   null,
    booking_date   datetime      null,
    start_time     char(30)      null,
    leave_time     char(30)      null,
    booking_status varchar(20)   null,
    people         int default 1 null,
    amount         int           null,
    constraint reservation_record_res_id_uindex
        unique (res_id),
    constraint reservation_record_place_info_place_id_fk
        foreign key (place_id) references place (place_id),
    constraint reservation_record_user_info_user_id_fk
        foreign key (user_id) references user (user_id)
)
    comment '预约记录';

create index reservation_record_stadium_sta_id_fk
    on reservation_record (slot_id);

