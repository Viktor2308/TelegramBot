CREATE TABLE tasks
(
    id             BIGINT generated by default as identity primary key,
    message        TEXT      NOT NULL,
    chat_id        BIGINT    NOT NULL,
    task_date_time TIMESTAMP NOT NULL
);