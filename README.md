# Drone Logfile API #
This sample API allows users to save and retrieve drone logfiles, and also get information about how data changed between the start and end of a drone flight.

## Local Setup ##
Setup MySQL, or use a Docker container. 

```
MySQL version: 8.0.18
username: root
password: strongpassword
port: 3306
schema: skyward
```

In the project directory, run the following command to create the tables needed.

```
./gradlew runScripts
```
Then you can start the project. It will run on port 8080.

```$xslt
./gradlew bootRun
```
