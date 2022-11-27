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
- pull the docker image from: dipu11/dice-game
- Or, 
- 


### APIs: Please check the below controller:
- http://localhost:8080/dice-game-service/swagger-ui/index.html#/game-controller
- addPlayer: `/dice-game-service/api/game/player`
- Start the game: `/dice-game-service/api/game/start`
- score: `/dice-game-service/api/game/score`
- Stop the game engine: `/dice-game-service/api/game/stop` [additional]