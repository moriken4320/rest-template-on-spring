build:
	mvn clean package

run:
	@make build
	java -jar target/rest-template-on-spring-0.0.1-SNAPSHOT.jar