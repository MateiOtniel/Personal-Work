# GoQuiz App - Documentation

## This is a project made with JavaFX, Gradle, MySQL (for app logic) and SceneBuilder with CSS (for GUI) that allows users to simulate a simple Quiz App.

## App functionalities:
- login
  
  ![image](https://user-images.githubusercontent.com/97764522/230777006-1b2a1bdb-cf85-4222-b528-eaac38fead50.png)
- after logging in, each player can see:
  - its stats: number tokens, rank in game, name.
  - the list of available quizzes (which are not already solved by the player)
  ![image](https://user-images.githubusercontent.com/97764522/230777038-e1cbd401-0e7c-4bf9-8531-5a2b55eb22c8.png)

  - its badges: locked or unlocked
  ![image](https://user-images.githubusercontent.com/97764522/230777052-021de714-5f57-4067-bbda-0e8e1c4a0995.png)

  
- also the player can add new quizzes (if it has > 500 tokens, the price for a quiz)
  - a form pops up and the player can add all quiz details    
  ![image](https://user-images.githubusercontent.com/97764522/230777064-0165f752-ffc0-4c9d-b90e-1b4b5c3ef111.png)
- logout
> Inputs are tested and validated     
> Every crud action modifies the database.

## Class diagrams:
![image](https://user-images.githubusercontent.com/97764522/230777497-63b49f53-eb95-4708-aebb-165ee75802c0.png)
