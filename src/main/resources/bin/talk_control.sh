#!/usr/bin/env bash

#
# 服务启动和停止管理
#

APP=talk.jar

echo "Start..."
date "+%Y-%m-%d %H:%M:%S"

# 健康检查
function health() {
    wget --spider -q -o /dev/null --tries=1 -T 5 http://127.0.0.1/actuator/health
    if [[ $? -eq 0 ]]; then
        echo "${APP}: services in operation..."
    else
        echo "${APP}: service stop"
    fi
}

# 启动服务
function start() {
    if [[ `ps aux | grep ${APP} | grep -v grep | wc -l` -eq 1 ]]; then
        echo "${APP}: services in operation..."
    else
        echo "${APP}: starting the service..."
        java -version
        java -Xms1024m -Xmx1024m -jar ../lib/${APP}
    fi
}

# 停止服务
function stop() {
    if [[ `ps aux | grep ${APP} | grep -v grep | wc -l` -ge 1 ]]; then
        echo "${APP}: services in operation..."
        pid=`ps -ef | grep ${APP} | grep -v grep | awk '{print $2}'`
        kill -9 ${pid}
    else
        echo "${APP}: service stop"
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

date "+%Y-%m-%d %H:%M:%S"
echo "Done"