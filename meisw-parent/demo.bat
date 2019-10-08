@echo off
set JAVA_HOME=H:\soft\jdk1.8.0_112
set SOFTPATH=H:\icdp\git\YUNMON-WEB\micro-agent\target
echo "正在启动,请稍后..."
set CLASSPATH=%SOFTPATH%\conf
title 'agent安装'
%JAVA_HOME%\bin\java -jar %SOFTPATH%\micro-agent-1.0.0.jar --spring.config.location=%CLASSPATH%\bootstrap-dev.yml

goto =--spring.config.location=%CLASSPATH%\bootstrap.yml -Dlogback.configurationFile=%CLASSPATH%\logback.xml > micro-agent.out 2>&1&