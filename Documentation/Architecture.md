#Software Architecture Document

This document delivers a brief explaination how this project is built up. Anyone interested into forking this project will find basic information to get an easier start into this project. 

Disclaimer: I DO NOT guarantee a fully updated documentation at all time. If you are still left with questions after reading this document, don't hesitate to contact me by PM.


## The Golden Rule: Strict seperation of model and view

Everything that is related to the GUI's / JavaFX' SceneGraph can be found within tictactoeFX.view.
Everything that stores data (called state or model) and has logic is found within tictactoeFX.presentationmodel. One minor exception thought: Within tictactoeFX.controller there is a pseudo-controller called NewGameCheckList (more information about it following later).

If you wonder why logic is within the presentationmodel and not in its own controller class, then consider this:
- It's my current knowledge horizon.
- There seems to be an issue with bindings when you setup bindings between 'view and presentationmodel' and 'view and controller' which affect the same property (or properties). I haven't fully understood my lecturers explanation yet, but if you are more experience, please don't hesitate to write me your take on it.
 
 
 Basically, it is a simplified version of a Model, View, Controller - Architecture.
 
 
 
## EventHandlers, Bindings and ValueChangeListeners
 
Since JavaFX' API offers these three powerful tools, they are heavily used in this project. They usually trigger logic within the presentation model or lead to an update of a GUI component. 



## View: The SceneGraph Structure

![alt text](https://github.com/AceVanCleef/tictactoeFX/blob/master/Documentation/UML/2)%20SceneGraph-structure.png?raw=true "view")

Note: Any class that holds a BooleanProperty isUpdated has/must have a bidirectional binding towards tictactoeFX.controller.NewGaemCheckList. This pseudocontroller checks whether all GUI components are refreshed when the end user wishes to start a new game (e.g. click on button triggers pm.newGame() ).



## Presentationmodel: Where Data and Logic lies

presentationmodel.RootPM is the entry point to all data and logic. That is why (almost) every class in tictactoeFX.view has a reference to it.

![alt text](https://github.com/AceVanCleef/tictactoeFX/blob/master/Documentation/UML/1)%20RootPM's-knowledge-space.png?raw=true "presentationmodel")

Note that a GameBoard consists of multiple fields. Therefore, there current state is represented by BoardFieldPMs stored within a List named allFields. Also, TicTacToe consists of at least two players. 
Since this project's purpose is to try out how it feels to play with more than just two players, it is possible to increase the amount of participants. Therefore, 
multiple PlayerPMs are stored within the List allPlayers.

### GameRules
Most games have well defined rules. In a later chapter, you will see how to add your own rules. But for now it is enough to know that RootPM chooses the correct GameRules according to factors such as the size 
of the GameBoard (= AMOUNT_OF_FIELDS). These criteria are defined and can be adjusted by the developer(s).

### GameStatePM
The GameStatePM is meant to represent whether the game has been won, is continuing or a draw concluded the match. Make sure there is always only one instance of it around since a singleton pattern hasn't been applied yet.
This class' instance is required to let RootPm know what to do when it has to update the currently running game.

### NewGame
There are also several components dealing with the case that the end user wants to start a new game (or game round). Note that 
.newGame() sets the flag "BooleanProperty setUpNewGame" which triggers a refresh of the affected views through their bindings to this flag. 
Each affected view instance will notify the NewGameCheckList whether it is done refreshing itself. The NewGameCheckList's job is to check whether 
all view instances are finished with refreshing and if this is the case, it resets the flag within RootPM.


## Adding more GameRules

As you can see in the following UML diagram, the GameRules  is implemented using the Factory Pattern: RootPM calls the static getGameRulesFor(RuleSet gametype, GameStatePM gameState).
Its parameter 'gametype' defines which subclass' instance is returned.

![alt text](https://github.com/AceVanCleef/tictactoeFX/blob/master/Documentation/UML/3)%20GameRules-Factory-Pattern.png?raw=true "gamerules")

When you want to add new rules you have to...  
 1) in GameRules: 
 - add a gametype name to the enum RuleSet (e.g. _3D_3x3x3)
 - add another else if - statement within the factory method getGameRulesFor() (compare with code)
 2) add your class which extends GameRules and have a look at Rules_2D_3x3 and...
 - create a constructor similar to Rules_2D_3x3's one.
 - Override updateGameState() the same way.
 - create your own win and draw condition checks within private helper methods
 - Override isWon() and call your win condition checks
 - Override isDraw() and call your draw condition checks
 - Override getCurrentState() the same way as in Rules_2D_3x3.
 - If you copy pasted a class: remove anything that doesn't make sense to keep for your own rules.
 3) update RootPM's .defineRules() switch statement (compare to existing code)
 

## 