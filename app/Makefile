build:
	./gradlew clean
	./gradlew build

run:
	./gradlew run

test:
	./gradlew test

lint:
	./gradlew checkstyleMain

run-dist:
	./build/install/app/bin/app

report:
	./gradlew test
	./gradlew jacocoTestReport

.PHONY: build
.PHONY: test
.PHONY: jacocoTestReport