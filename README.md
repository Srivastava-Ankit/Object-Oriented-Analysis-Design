# CSCI-5448-Project-Mgm-Tool

Project Management Tool

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

```
Postgres 9.4.15
Gradle 4.2.1
Jetty 9.4.7
```

## Running the app

```
./gradlew appRun
```

Note: The app will not unless the env var 'DATABASE_URL' is set with the right database URL.
```
export DATABASE_URL="postgres://<username>:<password>@<server>:<port>/<dbName>"
```
Example :-
```bash
export DATABASE_URL="postgres://postgres:postgres@localhost:5432/prorg"
```