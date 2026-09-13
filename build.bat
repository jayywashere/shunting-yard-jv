@echo off
if exist out rmdir /s /q out
mkdir out

javac -d out src\app\*.java src\parser\*.java src\evaluator\*.java src\calculator\*.java

if %errorlevel% neq 0 (
    echo.
    echo Build failed.
    exit /b %errorlevel%
)

echo.
echo Build successful.