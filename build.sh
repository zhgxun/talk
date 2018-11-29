#!/bin/bash

#
# 打包, 比原生调用 mvn 命令编译快速一些
#

export LANG=en_US.UTF-8

APP=talk

# check mvn
[[ -x mvnw ]] || chmod 755 mvnw
./mvnw --version >& /dev/null
if [[ $? -eq 0 ]]; then
    MAVEN_BIN=./mvnw
fi

if [[ -z "${MAVEN_BIN}" ]]; then
    echo "mvnw not found!"
    exit 1
fi

${MAVEN_BIN} -version 2>&1 | awk '/Apache Maven/ {
    split($3, vv, ".");
    if (vv[1] >= 3 && vv[2] >= 2) {exit 0} else {exit 1}
}' >& /dev/null
if [[ $? -ne 0 ]]; then
    echo "maven version must newer than 3.2.0"
    exit 1
fi

${MAVEN_BIN} -U clean install -DskipTests
if [[ $? -ne 0 ]]; then
    echo "compile error!"
    exit 2
fi

# packaging
rm -rf output
mkdir -p output
cp target/${APP}.jar output