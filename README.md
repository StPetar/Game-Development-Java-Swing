# Mobile-Development-Java-Swing
## A Java Swing recreation of the famous Super Mario game
### 3-Person Team project

Everything happens in a JPanel, using the paintComponent method. 
In a seperate thread, a repaint method is called. this repaint method simply calls the paintComponent method continuously in an infinite loop.

##The Model-View-Controller approach was used to create our game. 
### Models
This is where we handle the behaviour of our game objects/models3.

### View
This is where we handle level generation.

### Controller
this is where we handled, spawn points for enemies, collision detections, user controls, etc



## The final game consists of 3 Levels
The difficulty and complexity of the levels increases as they do
---
<img src="https://imgur.com/KAWUO5C.png">
---
* **Level 1**
  * Has the basic enemies - Turtules and Mushrooms
  * Obstacles - Pipes and Bricks, Flooded area
  * Enemy behaviour is simple - they move in a given direction until they collide with other object at which point their direction is reversed
  * Collectables - Coins of three different values [yellow, green and purple coin]
  * Shooting requires 0 points
  ---
  <img src="https://imgur.com/eOQ8AxE.png">
  ---
  
* **Level 2**
  * Introduces a new enemy - A zig-zagging bird which can only be shot, not stomped
  * Obstacles remain the same in this level, simply in a more complex layout, requiering a bit better control to traverse
  * Enemy behaviour remains the same
  * Collectables remain the same
  * Shooting requires 1 point
  ---
  <img src="https://imgur.com/oZpKChz.png">
  ---
  
* **Level 3**
  * Enemies - New winged version of Turtles and Mushrooms only, can not be stomped and must be shot
  * Obstacles - Introduces moving platforms, every pipe shoots a fireball in random intervals
  * Enemy behaviour exanded - they can now spawn on flying platforms and detect the edge of blocks, detect incoming bullets and try to dodge them
  * Collectables remain the same
  * Shooting requires 2 points
  ---
  <img src="https://imgur.com/evba9II.png">
  ---

#### For the 3rd level we decided to completely change the in-game models as the difficulty and complexity of the level was meant to be completely new to the overall feel of the game and the level itself - to be a challenge to our classmates when trying the game
