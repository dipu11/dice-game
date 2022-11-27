# Dice Game
#### players needed: 
- min: 2
- max: 4


### server
- port:`8080`
- context path: `dice-game-service`

### Swagger-URL:
http://localhost:8080/dice-game-service/swagger-ui/index.html

### How to run and test
#### Tools
- java- 1.8
- maven-3.8.6
#### Github:
- clone/download this project from github: https://github.com/dipu11/dice-game
- Import into an IDE and run the project
- Or, run by maven:  `mvn spring-boot:run`

#### Docker
- pull the docker image from: dipu11/dice-game with latest tag
- command: `docker pull dipu11/dice-game:latest`
- To create local docker image: `docker build -t dice-game:latest .`
- Run image: `docker run -p 8080:8080 dipu11/dice-game:latest` [for remote image], to run the local image: replace image name by local image name

#### After running the app, check the console for program's output. delayed by 1 and .5 second for each player and each dice rolling respectively.

### APIs: Please check the below controller:
- http://localhost:8080/dice-game-service/swagger-ui/index.html#/game-controller
- addPlayer: `/dice-game-service/api/game/player`
- Start the game: `/dice-game-service/api/game/start`
- score: `/dice-game-service/api/game/score`
- Stop the game engine: `/dice-game-service/api/game/stop` [additional]