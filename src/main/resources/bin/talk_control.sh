#!/usr/bin/env bash

#
# 服务启动和停止管理
#

APP=talk.jar

# 健康检查
function health() {
    wget --spider -q -o /dev/null --tries=1 -T 5 http://127.0.0.1/actuator/health
    if [[ $? -eq 0 ]]; then
        echo "${APP} service is running..."
    else
        echo "${APP} service is stop"
    fi
}

# 启动服务
function start() {
    echo "服务启动中..."
    if [[ `ps aux | grep ${APP} | grep -v grep | wc -l` -eq 1 ]]; then
        echo "${APP} service is running..."
    else
        echo echo "${APP} is starting..."
        java -version
        java -Xms1024m -Xmx1024m -jar ../lib/${APP}
    fi
}

# 停止服务
function stop() {
    echo "服务正在停止..."
    if [[ `ps aux | grep ${APP} | grep -v grep | wc -l` -ge 1 ]]; then
        echo "${APP} service is running..."
        pid=`ps -ef | grep ${APP} | grep -v grep | awk '{print $2}'`
        kill -9 ${pid}
    else
        echo "${APP} is stop"
    fi
}

# 操作方式
case "$1" in
"start")
    start
    ;;
"stop")
    stop
    ;;
"restart")
    stop
    start
    ;;
"health")
    health
    ;;
*)
    echo "Usage: talk_control start|stop|restart|health"
    ;;
esac