from pathlib import Path

bat_content = """
@echo off
echo ===============================
echo 🚀 Running Native Spring Boot
echo ===============================

echo 🔧 Step 1: Compiling native image...
call .\\mvnw -Pnative native:compile

IF ERRORLEVEL 1 (
    echo ❌ Native image build failed.
    pause
    exit /b 1
)

echo ✅ Build done.
echo 🔥 Starting Native App...
.\\target\\BenchmarkNativeCompile.exe

echo.
pause
"""

# Save the .bat file in the root directory (where pom.xml is located)
bat_path = Path("/mnt/data/run-native.bat")
bat_path.write_text(bat_content.strip())

bat_path.name