@echo off
call build.bat

if %errorlevel% neq 0 (
    exit /b %errorlevel%
)

call run.bat