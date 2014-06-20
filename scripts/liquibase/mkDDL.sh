#!/bin/sh
#created the DDL for the different database systems
liquibase --defaultsFile=liquibase.psql.devel.properties updateSQL > psql.ddl.sql
liquibase --defaultsFile=liquibase.H2.devel.properties updateSQL > h2.ddl.sql
