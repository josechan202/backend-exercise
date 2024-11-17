#!/bin/bash

restart-db(){
  echo 'Restarting mariadb'
  brew services restart mariadb
}