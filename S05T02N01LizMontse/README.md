# Dice Game API

## Swagger

![swagger.PNG](captures%2Fswagger.PNG)
![swagger2.PNG](captures%2Fswagger2.PNG)

## Postman
### Environment:

![environtment.PNG](captures%2Fenvirontment.PNG)

## Fase 1 (MySQL)

### Request POST: 

- #### /players/add: crea un jugador/a

![add.PNG](captures%2Fadd.PNG)
![add2.PNG](captures%2Fadd2.PNG)
![add3.PNG](captures%2Fadd3.PNG)

- #### /players/{player_id}/game: un jugador/a específic realitza una tirada de daus

![addgame.PNG](captures%2Faddgame.PNG)
![addgame2.PNG](captures%2Faddgame2.PNG)

### Request PUT:

- #### /players/update/{player_id}: modifica el nom del jugador/a

![put.PNG](captures%2Fput.PNG)
![put2.PNG](captures%2Fput2.PNG)
![put3.PNG](captures%2Fput3.PNG)

### Request DELETE: 

- #### /players/{player_id}/games: elimina les tirades del jugador/a

![delete.PNG](captures%2Fdelete.PNG)
![delete2.PNG](captures%2Fdelete2.PNG)
![delete3.PNG](captures%2Fdelete3.PNG)

### Request GET: 

- #### /players/: retorna el llistat de tots els jugadors/es del sistema amb el seu percentatge mitjà d’èxits

![getall.PNG](captures%2Fgetall.PNG)
![getall2.PNG](captures%2Fgetall2.PNG)

- #### /players/{player_id}/games: retorna el llistat de jugades per un jugador/a

![getallgames.PNG](captures%2Fgetallgames.PNG)
![getallgames2.PNG](captures%2Fgetallgames2.PNG)
![getallgames3.PNG](captures%2Fgetallgames3.PNG)

- #### /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el percentatge mitjà d’èxits

![ranking.PNG](captures%2Franking.PNG)
![ranking2.PNG](captures%2Franking2.PNG)
![ranking3.PNG](captures%2Franking3.PNG)

- #### /players/ranking/loser: retorna el jugador/a amb pitjor percentatge d’èxit

![loser.PNG](captures%2Floser.PNG)
![loser2.PNG](captures%2Floser2.PNG)
![loser3.PNG](captures%2Floser3.PNG)

- #### /players/ranking/winner: retorna el jugador amb pitjor percentatge d’èxit

![winner.PNG](captures%2Fwinner.PNG)
![winner2.PNG](captures%2Fwinner2.PNG)
![winner3.PNG](captures%2Fwinner3.PNG)

### MySQL

![mysql.PNG](captures%2Fmysql.PNG)
![mysql2.PNG](captures%2Fmysql2.PNG)

## Fase 2 (MongoDB)

### Request POST:

- #### /players/add: crea un jugador/a

![addmongo.PNG](captures%2Faddmongo.PNG)
![addmongo2.PNG](captures%2Faddmongo2.PNG)
![addmongo3.PNG](captures%2Faddmongo3.PNG)

- #### /players/{id}/game: un jugador/a específic realitza una tirada de daus

![addgamemongo.PNG](captures%2Faddgamemongo.PNG)
![addgamemongo2.PNG](captures%2Faddgamemongo2.PNG)

### Request PUT:

- #### /players/update/{id}: modifica el nom del jugador/a

![putmongo.PNG](captures%2Fputmongo.PNG)
![putmongo2.PNG](captures%2Fputmongo2.PNG)
![putmongo3.PNG](captures%2Fputmongo3.PNG)

### Request DELETE:

- #### /players/{id}/games: elimina les tirades del jugador/a

![deletemongo.PNG](captures%2Fdeletemongo.PNG)
![deletemongo2.PNG](captures%2Fdeletemongo2.PNG)
![deletemongo3.PNG](captures%2Fdeletemongo3.PNG)

### Request GET:

- #### /players/: retorna el llistat de tots els jugadors/es del sistema amb el seu percentatge mitjà d’èxits

![getallmongo.PNG](captures%2Fgetallmongo.PNG)
![getallmongo2.PNG](captures%2Fgetallmongo2.PNG)

- #### /players/{id}/games: retorna el llistat de jugades per un jugador/a

![getallgamesmongo.PNG](captures%2Fgetallgamesmongo.PNG)
![getallgamesmongo2.PNG](captures%2Fgetallgamesmongo2.PNG)
![getallgamesmongo3.PNG](captures%2Fgetallgamesmongo3.PNG)

- #### /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el percentatge mitjà d’èxits

![rankingmongo.PNG](captures%2Frankingmongo.PNG)
![rankingmongo2.PNG](captures%2Frankingmongo2.PNG)
![rankingmongo3.PNG](captures%2Frankingmongo3.PNG)

- #### /players/ranking/loser: retorna el jugador/a amb pitjor percentatge d’èxit

![losermongo.PNG](captures%2Flosermongo.PNG)
![losermongo2.PNG](captures%2Flosermongo2.PNG)
![losermongo3.PNG](captures%2Flosermongo3.PNG)

- #### /players/ranking/winner: retorna el jugador amb pitjor percentatge d’èxit

![winnermongo.PNG](captures%2Fwinnermongo.PNG)
![winnermongo2.PNG](captures%2Fwinnermongo2.PNG)
![winnermongo3.PNG](captures%2Fwinnermongo3.PNG)

### MongoDB

![mongo.PNG](captures%2Fmongo.PNG)
