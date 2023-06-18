create table if not exists users
(
    id          BIGINT NOT NULL,
    login       VARCHAR(512),
    password    VARCHAR(512),
    role        VARCHAR(512),
    primary key (id)
);

create sequence if not exists users_id_seq
    start 1
    increment 1
    NO MAXVALUE
    CACHE 1;