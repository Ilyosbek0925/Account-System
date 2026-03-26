insert into users (
    id,
    first_name,
    last_name,
    phone_number,
    email,
    role,
    is_active,
    password
) values (
             'c7c6e1c4-6e6a-4c6c-9a33-8d63d0c8b1a2',
             'Super',
             'Admin',
             '+998900100000',
             'admin@gmail.com',
             'ADMIN',
             true,
             '$2a$10$pDo2ZIl7UToe5.8v/R4wWuF76YQChpPsgH9R4es3GlZa7FO1feYma'
         );

insert into admins (id)
values ('c7c6e1c4-6e6a-4c6c-9a33-8d63d0c8b1a2');