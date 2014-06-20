#/bin/sh
#drops the databases for initial development of shema 
#
liquibase --defaultsFile=liquibase.psql.devel.properties dropAll
liquibase --defaultsFile=liquibase.H2.devel.properties dropAll

