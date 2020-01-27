create table users
(
    user_name varchar(50)                                       not null,
    password  varchar(50) default 'password'::character varying not null,
    constraint users_pk
        primary key (user_name)
);

alter table users
    owner to postgres;

create table datasets
(
    dataset_id          serial      not null,
    user_name           varchar(50) not null,
    task_name           varchar(50) not null,
    dataset_name        varchar(50) not null,
    timestamp           timestamp   not null,
    dataset_description text,
    url                 text,
    constraint datasets_pk
        primary key (dataset_id),
    constraint datasets_users_user_name_fk
        foreign key (user_name) references users,
    constraint datasets_unique_names
	unique (dataset_name, task_name)
);

alter table datasets
    owner to postgres;

create table models
(
    model_id          serial      not null,
    dataset_id        serial      not null,
    user_name         varchar(50) not null,
    model_name        varchar(50) not null,
    hash              varchar(64) not null,
    timestamp         timestamp   not null,
    language          varchar(10) not null,
    target            varchar(50),
    model_description text,
    constraint models_pk
        primary key (model_id),
    constraint models_datasets_dataset_id_fk
        foreign key (dataset_id) references datasets,
    constraint models_users_user_name_fk
        foreign key (user_name) references users
);

alter table models
    owner to postgres;

create table tags
(
    model_id serial      not null,
    tag_name varchar(50) not null,
    constraint tags_pk
        primary key (model_id, tag_name),
    constraint tags_models_model_id_fk
        foreign key (model_id) references models
);

alter table tags
    owner to postgres;

create table audits
(
    model_id    serial           not null,
    dataset_id  serial           not null,
    measure     varchar(50)      not null,
    value       double precision not null,
    timestamp   timestamp   	 not null,
    constraint audits_pk
        primary key (model_id, dataset_id, measure),
    constraint audits_models_model_id_fk
        foreign key (model_id) references models,
    constraint audits_datasets_dataset_id_fk
        foreign key (dataset_id) references datasets
);

alter table audits
    owner to postgres;
