# CS5004_Final_Project
CS5004_Final_Project

Project name: 
Artillery game

Group member:
Jie Zhang
Changxu Ren


## Build Requirements:
* JDK version >= 17.0
* maven version >= 3.8

## How to do further development:

### Using IntelliJ:

Open 2DGameDemo as the project folder. And check the module settings and make sure src dir and JDK version are set
properly. 

## How to run locally:
```
git clone https://github.com/ChangxuRen-github/CS5004_Final_Project.git 
cd CS5004_Final_Project/2DGameDemo
mvn compile exec:java
```

## How to build a runnable jar:
```
mvn clean install assembly:single
```

The runnable jar will be at path:
```
./2DGameDemo/target/ArtilleryGame-1.0-SNAPSHOT-jar-with-dependencies.jar 
```


