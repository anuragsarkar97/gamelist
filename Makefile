build:
	./gradlew clean build
test:
	./gradlew clean test
run:
	./gradlew bootrun
docker: build test 
	docker build -t anuragsarkar250/gamelist:latest
docker-run:
	docker run -p 8080:8080 anuragsarkar250/gamelist:latest
compose:
	docker compose up 