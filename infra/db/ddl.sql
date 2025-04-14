-- user
create table users
(
    id bigserial primary key,
    username varchar(50)  not null unique,
    password varchar(255) not null,
    email varchar(100) unique,
    enabled    boolean   default true,

    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted_by BIGINT
);

create table roles
(
    id bigserial primary key,
    name varchar(50) not null unique
);

create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
);
-- user end

-- feed
CREATE TABLE feed_posts (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    content TEXT,
    image_urls TEXT[] DEFAULT '{}',
    view_count BIGINT DEFAULT 0,

    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted_by BIGINT
);
CREATE TABLE post_likes (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    post_id INT NOT NULL,

    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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

    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted_by BIGINT
);
CREATE TABLE hashtags (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,

    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted_by BIGINT
);
CREATE TABLE post_hashtags (
    post_id INT NOT NULL,
    hashtag_id INT NOT NULL,

    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted_by BIGINT,

    PRIMARY KEY (post_id, hashtag_id)
);
-- feed End