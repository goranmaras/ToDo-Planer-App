# ToDo-Planer-App

To Do jednostavna aplikacija koja omogućava kreiranje naloga, dodavanje projekata i svakom projektu određene zadatke.
Aplikacija je na lokaciji [To-Do-Planer-App](https://todo-planer-goranm.herokuapp.com/). 
![SLIKA](https://github.com/goranmaras/ToDo-Paner-App-FrontEnd/blob/master/Izrezak.PNG)

## BackEnd

Ovaj folder sadrži BackEnd dadoteke aplikacije.
Za FrontEnd datoteke pogledati [To-Do-Planer-App--FrontEnd](https://github.com/goranmaras/ToDo-Paner-App-FrontEnd). 

## Sažetak 

U Spring okruženju napravljen BackEnd aplikacije sa svim Rest api kontrolama. Zatim u React-Redux JS library-u postavljen
FrontEnd. Za završetak App je zaštićen JWT-em. Aplikacija ima mogućnost Registracije i Prijave. Nakon objave backenda na Heroku,
odraditi u frontend okruženju naredbu

```
npm run build
```
i datoteke iz build foldera prebaciti u Static folder na backendu. Nakon Pusha na GitHub i Deploya na Heroku aplikacija je spremna.


## Testiranje

RESTfull api i Security testirani preko PostmanCanary aplikacije.

## Dodatci

Aplikacija koristi free plan za DB server što dovodi do čestog overloadnja servera. 

## Razvoj

* [SpringBoot 2 framework](https://spring.io/projects/spring-boot) - SpringBoot 2 framework za backend dio koda. 
* [React-redux](https://reactjs.org/) - React i redux za frontend.
* [ClearDB Mysql](https://www.cleardb.com/dashboard?source=MEUxQjUwMkQzMUIwRDk4RTZFQkVENjBBRkVCMDczQzg=&nav-data=eyJhcHBuYW1lIjoidG9kby1wbGFuZXItZ29yYW5tIiwiYWRkb24iOiJDbGVhckRCIE15U1FMIElnbml0ZSIsImFkZG9ucyI6W3siY3VycmVudCI6dHJ1ZSwiaWNvbiI6Imh0dHBzOi8vYWRkb25zLmhlcm9rdS5jb20vcHJvdmlkZXIvYWRkb25zL2NsZWFyZGIvaWNvbnMvbWVudS9wcm9jZXNzZWQucG5nIiwic2x1ZyI6ImNsZWFyZGI6aWduaXRlIiwibmFtZSI6IkNsZWFyREIgTXlTUUwifV19) - Baza podataka sa Heroku platforme
* [Server za App](https://dashboard.heroku.com/login) - Server Aplikacije

## Autor

* **Goran Maras** - - [Goran Maras](https://github.com/goranmaras)


## Priznanja

* [AgileIntelligence](https://github.com/AgileIntelligence)


