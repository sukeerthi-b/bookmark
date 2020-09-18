--liquibase formatted sql
--<ScriptOptions statementTerminator=";"/>
--DO NOT DELETE ABOVE LINES. IT IS NEEDED BY LIQUIBASE.

--changeset sukeerti.botu:001-bookmark-001 failOnError:true splitStatements:false logicalFilePath:bookmark-001.sql
create table t_groups (
	group_tecid NUMERIC(13),
	name varchar(100),
	description varchar(1000),
	created_by	VARCHAR (64) NOT NULL,
	created_on	timestamp (6) WITH TIME ZONE NOT NULL,
	constraint group_tecid_pk primary key(group_tecid)
);
create table t_bookmarks(
	bookmark_tecid NUMERIC(13) NOT NULL,
	actual_url varchar(2083) NOT NULL,
	favicon bytea,
	description varchar(1000),
	title varchar(100) NOT NULL,
	source varchar(50) ,
	short_url varchar(100),
	group_tecid NUMERIC(13) references t_groups(group_tecid),
	expired_date date,
	created_by	VARCHAR (64) NOT NULL,
	created_on	timestamp (6) WITH TIME ZONE NOT NULL,
	constraint bookmark_tecid_pk primary key(bookmark_tecid)
);

--changeset sukeerti.botu:002-bookmark-001 failOnError:true splitStatements:false logicalFilePath:bookmark-001.sql
CREATE SEQUENCE bookmark_seq
INCREMENT 1
START 100;