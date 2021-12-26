-- auto-generated definition
create table HOMEWORK_CUSTOMER_TBL
(
    id   int auto_increment
        primary key,
    name varchar(100) null
);


-- auto-generated definition
create table HOMEWORK_SERVICE_SUBSCRIPTION_TBL
(
    id            int auto_increment
        primary key,
    customer_id   int      null,
    service_id    int      null,
    subscribed_at datetime null,
    constraint HOMEWORK_SERVICE_SUBSCRIPTION_TBL_id_uindex
        unique (id)
);


-- auto-generated definition
create table HOMEWORK_SERVICE_TBL
(
    id   int auto_increment
        primary key,
    code varchar(1000) null,
    constraint HOMEWORK_SERVICE_TBL_id_uindex
        unique (id)
);

