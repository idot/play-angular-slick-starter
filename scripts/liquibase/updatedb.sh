#!/bin/sh 
#updates development db (jenkins)
#liquibase --defaultsFile=liquibase.devel.properties dropAll
liquibase --defaultsFile=liquibase.psql.devel.properties updateSQL > psql.ddl.sql
liquibase --defaultsFile=liquibase.psql.devel.properties update
