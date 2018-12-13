#!/bin/bash

#
# 打包应用程序
#

export LANG=en_US.UTF-8

APP=talk

# 1. 本地打包
mvn clean package -DskipTests=true

# 2. 打包为 talk.tar.gz 格式
rm -rf output
mkdir -p output
mkdir -p output/${APP}/bin
mkdir -p output/${APP}/lib

# lib 目录存放可执行 jar 应用程序
cp target/${APP}.jar output/${APP}/lib
# bin 目录存放启动管理脚本
cp src/main/resources/bin/* output/${APP}/bin
tar zcf output/${APP}.tar.gz -C output/${APP} .
rm -rf output/${APP}