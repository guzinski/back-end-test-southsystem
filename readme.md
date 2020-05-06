# Southsystem backend test

## The Application
This App was build using Java as language at the 11 version, and Gradle (v6.1) was choice to be the build tool.

[Google Guice](https://github.com/google/guice/wiki/GettingStarted) was used to make dependencies injectable.

[Lombok](https://projectlombok.org/) was added to make easier to immutable objects.

[Mockito](https://site.mockito.org/) was used to power the unit tests.

## Env vars
The bellow env vars should be provided to a good run of this application.

- IN_DIR // Directory where the app will read the files
- OUT_DIR // The output directory for the processed files

Both vars should be a full path of Dirs.

----
## Build with Docker
A Dockerfile inside the project was made to make easier to build, even without any JDK installed.

    docker build -t back-end-test-southsystem ./
    docker run -i back-end-test-southsystem IN_DIR=read_path;OUT_DIR=output_path ./gradlew run  

----
## Build Manual
Having a JDK11 installed it's also easy to run this app.
On the root directory just run.

    IN_DIR=read_path;OUT_DIR=output_path ./gradlew run 

Note: please replace to values above with real paths name.

Once started, the system will be running and reading each new file added to `IN_DIR` path. 