DO
$do$
    BEGIN
        IF
            NOT EXISTS(SELECT
                       FROM pg_roles
                       WHERE rolname = 'documents_user') THEN
            CREATE
                USER "documents_user" WITH PASSWORD 'secret' CREATEDB;
        END IF;
    END
$do$;
CREATE
    DATABASE "documents_db" WITH
    OWNER = "documents_user"
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

