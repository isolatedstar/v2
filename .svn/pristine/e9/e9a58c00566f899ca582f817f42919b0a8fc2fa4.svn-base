@echo off
set JAVA_HOME=C:\Program Files (x86)\Java\jdk1.6.0_32
set JAVA_OPTIONS=-Xms512M -Xmx512M
setlocal enabledelayedexpansion 
set CLASSPATH=.;
 for %%j in (.\lib\*.jar) do (
   set CLASSPATH=!CLASSPATH!;%%j
 )

echo ===============================================================================   
echo   Startup Environment (环境变量)
echo   JAVA: %JAVA_HOME%   
echo   JAVA_OPTS: %JAVA_OPTIONS%   
echo   CLASSPATH: %CLASSPATH%   
echo ===============================================================================   
echo.   

java %JAVA_OPTIONS% -cp %CLASSPATH% cfca.test.APITest2
endlocal
pause