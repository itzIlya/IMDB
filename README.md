# IMDB

Created by: Ilya Atashsoda
Created time: December 31, 2023 11:20 PM

# IMDB

This is a simplified design and terminal-based implementation of IMDB. It was done as a course assignment in order to help us learn the principles of object-oriented programming and design using Java. 

## The basic classes

### Person

As its name suggests, the Person class holds information about all people that have been registered in the system. These people **may or may not be registered users of the website**. As an example, an actor that acts in a movie, may have not signed up in the website, yet the actor can have a profile page that has been created by the website editors or system admins.

### Member

**Once a user signs up**, their data is saved in an instance of the Member class. That data consists of basic profile details such as name, username and password with some other data that changes as they use the system such as their favorite movies, watchlist and so on…

**Admins** and **Editors** are instances of the Member class that have `AccessLevel.Admin` or `AccessLevel.Editor` as their access level field in their profile details. Editors can suggest changes to profiles of movie casts and movie details for admin review. These **suggestions** are queued for admin review. If an admin approves a suggestion upon choosing the approve option when viewing that suggestion, the changes will immediately go into effect. Admins also have the ability to remove users, people and movies permanently from the system.

### UserManagement

This class handles the registration and login process for users. 

### DBLists

This class serves as a basic **database** for this design. As working with databases was not the purpose of this project, I have only simplified that behavior using Java Arraylists. Access to data such as members, movies, forums and violation reports that are all fundamental data entities of our system is provided through the static methods of this class. 

### Movie

Each movie has a title, a set of roles (people who have had a role in the making of this movie), a release date, a genre and more information dedicated to it. Users can review movies and the data for these reviews is kept in an Arraylist of reviews and ratings in that particular instance of the movie.

There are more than 40 other classes in this project that could’ve each been explained thoroughly, had I have had the will to write documentation for each of them. :/

## The design and implementation

This is a terminal based implementation. However, it is made so that **by simply replacing the Main method in the Main class, one can use all other classes of this design to create a graphical user interface based implementation of the website.** Error and exception handling has not been done through the terminal but by throwing exceptions through methods. These **exceptions have mostly to do with duplicate requests of users or with access restrictions.** With regards to error and exception handling, since the terminal based implementation was meant to be a simple way for people to interact with this system, there may have not been enough attention given to handling invalid user inputs through the terminal. As **handling such things was not the purpose of this project.** 

One can take everything that has nothing to do with the user interface currently implemented in this project and **build upon everything else that has been implemented and designed to make a functional use of the current code**.
