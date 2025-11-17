# microStay
Commands for docker run:
  1) cd {directory of the project}
  2) Run all: docker compose up --build
  2.1) Selective run postgres and keycloak only example: docker compose up postgres keycloak

Details for the local ports and passwords are in the following file:
docker-compose.yml:
  keycloak: 8081
  postgres: 5433
  springboot: 8080
