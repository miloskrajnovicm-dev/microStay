@echo off
REM Pokreni ovaj script iz root projekta
cd backend
if exist mvnw (
  call mvnw spring-boot:run
) else (
  mvn spring-boot:run
)
