# Quiz Game

### Description

This is a quiz game in which users have to answer 10 multiple-choice
questions from different domains. They can view their score at the end, knowing
that a wrong answer cancels out a correct one, but no question is scored negatively.

I implemented the logic using **PL/SQL** and **Oracle SQL Developer** for the database. I connected the database using **Java** and created a simple interface using **Java Swing**.

### Project Structure
In the src/main/java folder are the java files:
* Game.java - represents a game session with a given username and stores information about the current question, the answers given by the user, his score etc,
* Database.java - creates the connection with the database,
* QuestionFromDatabase.java - calls the function *urmatoarea_intrebare* to get the next question and sets the extracted information to the current game object,
* MainFrame.java - makes the connection between the start, question and final panels,
* StartPanel.java - the first panel the user sees, where enters a username to start the quiz,
* QuestionPanel.java - the panel that contains the questions, the answer choices and the *next* button,
* FinalPanel.java - the last panel, where the user's score is displayed,
* StyledButton.java - a custom button with gradient colors,
* Main.java - starts the quiz game.

The Quiz.sql file contains the script that creates the tables *intrebari*, *raspunsuri* and *teste*, where the first 2 tables are populated using [this script](https://docs.google.com/document/d/1Ps0-R_4NyDkMK04Jmk3bhZiy4bKg2LIjEq_G4Scojqk/edit), given by the lab teacher at DBMS course at FII, UAIC. The file also contains the definitions for:
* *creare_test* - creates a test with random questions for a given username, and
* *urmatoarea_intrebare* - returns the next question, and if the current one is the last one of the test then computes the score obtained by the user.