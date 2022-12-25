
    create table order (
       id int identity not null,
        apporved int,
        date_order date,
        shipe_method varchar(255),
        user_id int,
        primary key (id)
    );

    create table orderdetail (
       id int identity not null,
        count int,
        order_id int,
        product_id int,
        primary key (id)
    );

    create table product (
       id int identity not null,
        count int,
        img varchar(255),
        name varchar(255),
        price double precision,
        primary key (id)
    );

    create table role (
       id int identity not null,
        name varchar(255),
        primary key (id)
    );

    create table user (
       id int identity not null,
        address varchar(255),
        password varchar(255),
        phone varchar(255),
        username varchar(255),
        primary key (id)
    );

    create table user_role (
       user_id int not null,
        role_id int not null,
        primary key (user_id, role_id)
    );

    alter table order 
       add constraint FKt7abetueht6dd1gs9jyl3o4t7 
       foreign key (user_id) 
       references user;

    alter table orderdetail 
       add constraint FKrfwyqi6rh48nqs526mpqee8f7 
       foreign key (order_id) 
       references order;

    alter table orderdetail 
       add constraint FKdubukg3j0fymgci0idnd72k0 
       foreign key (product_id) 
       references product;

    alter table user_role 
       add constraint FKa68196081fvovjhkek5m97n3y 
       foreign key (role_id) 
       references role;

    alter table user_role 
       add constraint FK859n2jvi8ivhui0rl0esws6o 
       foreign key (user_id) 
       references user;

    create table orderdetail (
       id int identity not null,
        count int,
        order_id int,
        product_id int,
        primary key (id)
    );

    create table orders (
       id int identity not null,
        apporved int,
        date_order date,
        shipe_method varchar(255),
        user_id int,
        primary key (id)
    );

    create table products (
       id int identity not null,
        count int,
        img varchar(255),
        name varchar(255),
        price double precision,
        primary key (id)
    );

    create table roles (
       id int identity not null,
        name varchar(255),
        primary key (id)
    );

    create table user_role (
       user_id int not null,
        role_id int not null,
        primary key (user_id, role_id)
    );

    create table users (
       id int identity not null,
        address varchar(255),
        password varchar(255),
        phone varchar(255),
        username varchar(255),
        primary key (id)
    );

    alter table orderdetail 
       add constraint FKfxkmvpbfxqect54pd7slj4ll9 
       foreign key (order_id) 
       references orders;

    alter table orderdetail 
       add constraint FK9iu7g1xs6c3u7n3ryo9yv2tyd 
       foreign key (product_id) 
       references products;

    alter table orders 
       add constraint FK32ql8ubntj5uh44ph9659tiih 
       foreign key (user_id) 
       references users;

    alter table user_role 
       add constraint FKt7e7djp752sqn6w22i6ocqy6q 
       foreign key (role_id) 
       references roles;

    alter table user_role 
       add constraint FKj345gk1bovqvfame88rcx7yyx 
       foreign key (user_id) 
       references users;
