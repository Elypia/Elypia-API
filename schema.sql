create table elypia.accounts
(
    account_id int auto_increment,
    email varchar(256) not null,
    passwd varchar(60) not null,
    phone_number varchar(32) null,
    is_verified bit default b'0' not null,
    is_admin bit default b'0' not null,
    constraint account_account_id_uindex
        unique (account_id),
    constraint account_email_uindex
        unique (email)
)
    comment 'The instance of an Elypia account, this is seperated from users to isolate sensitive information, this also contains information that may belong to multiple users if emails will be allowed to create multiple.';

alter table elypia.accounts
    add primary key (account_id);

create table elypia.articles
(
    article_id int auto_increment
        primary key,
    title varchar(128) not null,
    content varchar(2048) not null,
    created_date datetime default CURRENT_TIMESTAMP not null,
    modified_date datetime null on update CURRENT_TIMESTAMP,
    constraint news_news_content_uindex
        unique (content)
)
    comment 'New articles posted on Elypia.';

create table elypia.tags
(
    tag_id int auto_increment
        primary key,
    article_id int not null,
    tag varchar(32) not null,
    constraint article_tags_article_id_tag_uindex
        unique (article_id, tag),
    constraint article_tags_news_article_id_fk
        foreign key (article_id) references elypia.articles (article_id)
)
    comment 'The tags associated with an invidiual article.';

create table elypia.users
(
    user_id int auto_increment
        primary key,
    account_id int not null,
    constraint users_accounts_account_id_fk
        foreign key (account_id) references elypia.accounts (account_id)
)
    comment 'User entities under that belong to Elypia globally.';

create table elypia.comments
(
    comment_id int auto_increment
        primary key,
    article_id int not null,
    parent_comment_id int null comment 'The comment this comment is a response to if it''s a reply.',
    user_id int null,
    content varchar(2048) not null,
    created_date datetime default CURRENT_TIMESTAMP null,
    constraint comments_comments_comment_id_fk
        foreign key (parent_comment_id) references elypia.comments (comment_id),
    constraint comments_news_news_id_fk
        foreign key (article_id) references elypia.articles (article_id),
    constraint comments_users_user_id_fk
        foreign key (user_id) references elypia.users (user_id)
)
    comment 'Responses to news articles on the website.';

create table elypia.upvotes
(
    upvote_id bigint auto_increment
        primary key,
    comment_id int not null,
    user_id int not null,
    constraint upvotes_comment_id_user_id_uindex
        unique (comment_id, user_id),
    constraint upvotes_comments_comment_id_fk
        foreign key (comment_id) references elypia.comments (comment_id),
    constraint upvotes_users_user_id_fk
        foreign key (user_id) references elypia.users (user_id)
);

create table elypia.usernames
(
    username_id int auto_increment
        primary key,
    user_id int not null,
    username varchar(32) not null,
    set_date datetime default CURRENT_TIMESTAMP not null,
    constraint usernames_users_user_id_fk
        foreign key (user_id) references elypia.users (user_id)
)
    comment 'Table of all user usernames.';

create table elypia.verification
(
    token_id int auto_increment
        primary key,
    account_id int not null,
    token varchar(128) not null,
    expiry datetime not null,
    constraint verification_token_uindex
        unique (token),
    constraint verification_user_id_uindex
        unique (account_id)
)
    comment 'Verification tokens to ensure users are verified before they are able to signup and perform actions on their account.';

