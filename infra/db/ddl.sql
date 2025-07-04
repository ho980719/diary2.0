-- user
create table users
(
    id bigserial primary key,
    username varchar(50)  not null unique,
    password varchar(255) not null,
    email varchar(100) unique,
    enabled    boolean   default true,

    deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted_by BIGINT
);

INSERT INTO public.users (username, password, email, enabled, deleted, created_at, updated_at, deleted_at, created_by, updated_by, deleted_by) VALUES ('admin', '{noop}1234', 'admin@gmail.com', true, false, now(), now(), null, 0, 0, null);
INSERT INTO public.users (username, password, email, enabled, deleted, created_at, updated_at, deleted_at, created_by, updated_by, deleted_by) VALUES ('user', '{noop}1234', 'user@gmail.com', true, false, now(), now(), null, 0, 0, null);

create table roles
(
    id bigserial primary key,
    name varchar(50) not null unique
);

INSERT INTO public.roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO public.roles (name) VALUES ('ROLE_USER');

create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
);

INSERT INTO public.user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO public.user_roles (user_id, role_id) VALUES (2, 2);
-- user end

-- file
CREATE TABLE common_file (
    id BIGSERIAL PRIMARY KEY,
    file_key VARCHAR(50),
    origin_name VARCHAR(255),
    stored_name VARCHAR(255),
    file_path VARCHAR(512),
    file_size BIGINT,
    content_type VARCHAR(100),
    reference_id BIGINT NOT NULL,
    reference_type VARCHAR(10) NOT NULL,
    deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);
-- file end

-- feed
CREATE TABLE feed_posts (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    content TEXT,
    view_count BIGINT DEFAULT 0,

    deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP ,
    created_by BIGINT,
    updated_by BIGINT,
    deleted_by BIGINT
);

CREATE TABLE post_likes (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    post_id INT NOT NULL,

    deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP ,
    created_by BIGINT,
    updated_by BIGINT,
    deleted_by BIGINT,

    UNIQUE(user_id, post_id)
);

CREATE TABLE post_comments (
    id SERIAL PRIMARY KEY,
    post_id INT NOT NULL,
    user_id BIGINT NOT NULL,
    content TEXT NOT NULL,

    deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP ,
    created_by BIGINT,
    updated_by BIGINT,
    deleted_by BIGINT
);

CREATE TABLE hashtags (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE post_hashtags (
    post_id INT NOT NULL,
    hashtag_id INT NOT NULL,
    PRIMARY KEY (post_id, hashtag_id)
);
-- feed End

create table room
(
    id bigserial primary key,
    username varchar(50)  not null unique,
    password varchar(255) not null,
    email varchar(100) unique,
    enabled boolean   default true,

    deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted_by BIGINT
);