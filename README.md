[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/RyiBKJgD)
# Portfolio project IDATG1003
This file uses Mark Down syntax. For more information see [here](https://www.markdownguide.org/basic-syntax/).

[//]: # (TODO: Fill inn your name and student ID)

STUDENT NAME = "Mathias Erik Nord"  
STUDENT ID = "129623"

## Project description

[//]: # (TODO: Write a short description of your project/product here.)
This project is a meal organization application. The application aims to help people reduce the waste of food. In the application you can save all you groceries too keep track of what you have, when they expire and much more. You can also create recipes and save them for later use. These recipes can be matched against the food you have, to give recommendations based on that.

## Project structure

[//]: # (TODO: Describe the structure of your project here. How have you used packages in your structure. Where are all sourcefiles stored. Where are all JUnit-test classes stored. etc.)

project-root/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── edu/
│   │   │       └── ntnu/
│   │   │           └── bidata/
│   │   │               ├── application/
│   │   │               │   ├── Main.java
│   │   │               │   ├── UserInputHandler.java
│   │   │               │   └── UserInterface.java
│   │   │               ├── entity/
│   │   │               │   └── Grocery.java
│   │   │               ├── menu/
│   │   │               │   ├── MainMenu.java
│   │   │               │   ├── StringMenu.java
│   │   │               │   └── cookbook/
│   │   │               │       ├── CookBookMenu.java
│   │   │               │       ├── CookBookMenuMutator.java
│   │   │               │       └── CookBookMenuPrinter.java
│   │   │               ├── menu/
│   │   │               │   └── grocery/
│   │   │               │       ├── GroceryMenu.java
│   │   │               │       └── GroceryMenuMutator.java
│   │   │               ├── recipe/
│   │   │               │   ├── CookBook.java
│   │   │               │   └── Recipe.java
│   │   │               ├── register/
│   │   │               │   └── FoodStorage.java
│   │   │               └── util/
│   │   │                   ├── CookBookFormatter.java
│   │   │                   └── GroceryFormatter.java
│   └── test/
│       └── java/
│           └── edu/
│               └── ntnu/
│                   └── bidata/
│                       ├── application/
│                       │   └── UserInputHandlerTest.java
│                       └── util/
│                           ├── CookBookFormatterTest.java
│                           └── GroceryFormatterTest.java

## Link to repository

[//]: # (TODO: Include a link to your GitHub repository here.)

## How to run the project

[//]: # (TODO: Describe how to run your project here. What is the main class? What is the main method?
What is the input and output of the program? What is the expected behaviour of the program?)

To run the program you have to make sure that you have JDK and Maven installed on your computer. You should then be able to clone the repository. Next you should run _mvn compile_ to compile the project. You should then be able to run the program from the class _Main.java_. This is where the _main_ method lives. This method will run the program.

## How to run the tests

[//]: # (TODO: Describe how to run the tests here.)
To run the tests you have to do _mvn clean_, then _mvn compile_ and finally _mvn test_.

## References

[//]: # (TODO: Include references here, if any. For example, if you have used code from the course book, include a reference to the chapter.
Or if you have used code from a website or other source, include a link to the source.)
