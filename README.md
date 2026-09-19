Rock Paper Scissors Game
A Java desktop game built with Swing as a 10-week university project.

Project Overview
This is a fully functional Rock Paper Scissors game where the player competes against the computer. The computer makes a random choice each round, the game determines the winner using the official rules, and a running scoreboard tracks wins, losses, and draws across multiple rounds.

How to Run
Clone or download this repository
Open the project in NetBeans IDE
Click Run → Run Project (or press F6)
The game window will open — click Rock, Paper, or Scissors to play
Requirements: Java JDK 8 or higher · NetBeans IDE

How to Play
Click Rock, Paper, or Scissors to make your choice
The computer instantly makes its own random choice
The result (WIN / LOSE / DRAW) appears with a flashing banner
The scoreboard at the top updates after every round
Click New Round to clear the board but keep your score
Click Reset Scores to start completely fresh
Rules
Player	Computer	Result
Rock	Scissors	WIN — Rock crushes Scissors
Scissors	Paper	WIN — Scissors cuts Paper
Paper	Rock	WIN — Paper covers Rock
Same	Same	DRAW
Any other combination		LOSE

Project Structure

src/
├── Main.java                  Entry point — launches the game window
├── controller/
│   └── Controller.java        Game logic: validates input, generates computer move, determines result, tracks score
├── model/
│   └── DataModel.java         Data storage: player choice, computer choice, result, win/lose/draw counters
├── ui/
│   ├── UserInterface.java     Main game window built with Java Swing
│   └── RoundedButton.java     Custom JButton with rounded corners and press animation
└── utils/
    ├── Validator.java         Input validation — checks choices before processing
    └── SoundPlayer.java       Generates win/lose/draw/click sounds using javax.sound

test/
├── controller/
│   └── ControllerTest.java    17 JUnit tests for game logic and score tracking
├── model/
│   └── DataModelTest.java     23 JUnit tests for data storage and reset behaviour
└── utils/
    └── ValidatorTest.java     13 JUnit tests for input validation

src/images/
├── rock.png
├── paper.png
└── scissors.png


Features
Random computer move generator using `java.util.Random`
Win / Lose / Draw detection covering all 9 possible combinations
Live scoreboard showing Wins, Losses, Draws, and Rounds played
Score shown in the window title bar
Custom rounded buttons with hover highlight and press animation
Rock / Paper / Scissors icons displayed for both player and computer
Flashing WIN / LOSE / DRAW result banner
Sound effects for every action (no external audio files — generated in code)
Gradient background and polished visual design
Two reset options: New Round (keeps score) and Reset Scores (clears all)
53 JUnit tests covering all core logic

Development Timeline
Week	Focus	Key Deliverable
1	Planning	Requirements, environment setup
2	System Design	Project structure, class diagrams
3	UI Development	Game window, Rock/Paper/Scissors buttons
4	User Interaction	Button actions wired to Controller
5	Computer Module	Random move generator
6	Core Game Logic	Win/Lose/Draw rules
7	Score Management	Scoreboard, reset options
8	Feature Enhancement	Animations, sounds, visual polish
9	Testing & Debugging	53 JUnit tests, 3 bug fixes
10	Finalization	Report, README, final submission

Bugs Fixed (Week 9)
Bug	Fix
Null pointer crash when no choice passed	Added explicit null guard in Controller
Double score increment on rapid clicking	Added isProcessing lock flag in Controller
Flash animation timer ran after window close	Added WindowListener to stop timer on close

Author
Student: Kabyobiswas07-sys  
Course: Software Development / Java Programming  
Repository: github.com/kabyobiswas07-sys/Rock-Paper-scissors-Game
