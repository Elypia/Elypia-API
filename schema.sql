create table news
(
    news_id       int auto_increment
        primary key,
    title         varchar(128)                       not null,
    content       varchar(2048)                      not null,
    created_date  datetime default CURRENT_TIMESTAMP not null,
    modified_date datetime                           null on update CURRENT_TIMESTAMP,
    constraint news_news_content_uindex
        unique (content)
)
    comment 'New articles posted on Elypia.';

create table users
(
    user_id       int auto_increment
        primary key,
    email_address varchar(256)     not null,
    phone_number  varchar(16)      null,
    username      varchar(32)      not null,
    is_verified   bit default b'0' not null,
    is_admin      bit default b'0' null,
    constraint user_user_email_uindex
        unique (email_address)
)
    comment 'User entities under that belong to Elypia globally.';

create table comments
(
    comment_id        int auto_increment
        primary key,
    news_id           int                                not null,
    parent_comment_id int                                null comment 'The comment this comment is a response too if it''s a reply.',
    user_id           int                                null,
    content           varchar(2048)                      not null,
    created_date      datetime default CURRENT_TIMESTAMP null,
    constraint comments_comments_comment_id_fk
        foreign key (parent_comment_id) references comments (comment_id),
    constraint comments_news_news_id_fk
        foreign key (news_id) references news (news_id),
    constraint comments_users_user_id_fk
        foreign key (user_id) references users (user_id)
)
    comment 'Responses to news articles on the website.';

create table past_usernames
(
    user_id  int                                not null,
    username varchar(32)                        not null,
    end_date datetime default CURRENT_TIMESTAMP not null,
    constraint usernames_users_user_id_fk
        foreign key (user_id) references users (user_id)
)
    comment 'Table of all user usernames.';

create table upvotes
(
    comment_id int not null,
    user_id    int not null,
    constraint upvotes_comment_id_user_id_uindex
        unique (comment_id, user_id),
    constraint upvotes_comments_comment_id_fk
        foreign key (comment_id) references comments (comment_id),
    constraint upvotes_users_user_id_fk
        foreign key (user_id) references users (user_id)
);

