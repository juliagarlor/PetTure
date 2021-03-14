# Petture 

Welcome to the first social network made only for pets. Petture has been develop to fulfill the extreme neccesity of pet owners to upload cute pictures of their little buddies and, above all, to allow online communication for these furry friends.

Either if you are the owner or the pet, you will love this page, so let's get started!


## Definition

The petture project is divided in two parts: a front-end application developed in Angular, and a back-end application programed in java with some Mysql databases. The two parts are easily distinguished when you access to the repository, so let's talk about them a little bit:

- The front-end folder contains all the neccessary components, services and models to provide a nice interface experience to the user. Thanks to it you will be able to move and play with the webpage as much as you can. It is meant to be intuitive and responsive, so you can either use it on your computer or your phone.

- The back-end folder is divided in 5 spring-boot projects, each containing an essential microservice for the correct working of the page and the secure storing of data. They are completely isolated from one another, so you will be able to use the application under any circumstance.

### Down to work 🔧

These are the steps to get the project running:

1. Clone this project from the repository into your most convenient location.

2. Go into the eureka project and follow the path below to download the file with the sql queries necessary for using the databases. Then open the schema with a DB manager and create the schemas and tables:

```
'/src/main/resources/static/pettureDBAndTables.sql'
```

4. Go to this repository's application.properties file and replace these lines with your mySQL username and password. Please, make sure to do this in the projects of user-service, picture-service and post-service

```
'spring.datasource.username=username' and 'spring.datasource.password=password'
```

5. Now that the database and its settings are ready, we can open all the backend projects in Intellij and execute the following command on the console (eureka first, then the rest):

```
mvn spring-boot:run
```

6. Our back-end is up and ready, so now we can wake up the front-end part. Open the petture folder (inside the Frontend folder) and execute the following lines in the console:

```
ng add @angular/material
```

```
ng serve
```

May you get any error, try executing this:

```
npm i
```

7. Now we can start using Petture. Please, pick your favourite browser and look for the following url:

```
http://localhost:4200/
```



## Basic options ✒️

First of all, the login view will appear when oppening the web. You can, of course, register with your user and password, enter without registering, or try loging with one of these two profiles:

Username: PerryThePlatypus
Password: malditaSeaPerryElOrnitorrinco

Username: GarfieldLovesMondays
Password: lasaña

Once this has been decided, you will be carried to the home page:

![Home](prints/home.png)

and, if you are logged, you can add new posts and commentaries to other profiles' pictures:

![Home2](prints/profile.png)
![Home3](prints/search.png)

Do not forget to check your profile and search for friends to keep your page neat and cool, and not missing any event.

![Profile](prints/2.png)
![Search](prints/3.png)

---
developed with ❤️ by Julia García
