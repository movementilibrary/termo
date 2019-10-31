create table term_of_user
(
    id                int8        NOT NULL,
    current_date_term timestamp    NOT NULL,
    description_term  text         NULL,
    flag_atualizacao  boolean         NOT NULL,
    login_user        varchar(255) NOT NULL,
    status_term       varchar(255) NOT NULL,
    summary_term      text         NOT NULL,
    version_term      varchar(255) NOT NULL,
    CONSTRAINT term_of_user_pkey PRIMARY KEY (id)
)