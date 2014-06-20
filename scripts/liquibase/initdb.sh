#!/bin/sh
#creates database on localhost  machine
createuser appdev -h localhost -U tamir 
createdb -h localhost -E UTF8 -O appdev appdev 'test database'
