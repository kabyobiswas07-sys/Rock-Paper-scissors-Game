# 🪨📄✂️ Rock Paper Scissors Game

> 🖥️ A fully functional **Rock Paper Scissors** desktop game built in **Java Swing** as a university project.

```
🪨  Rock   →  crushes  →  ✂️  Scissors
✂️  Scissors →  cuts   →  📄  Paper
📄  Paper   →  covers  →  🪨  Rock
```

The player competes against the computer, which makes a random choice each round. The game tracks wins, losses, and draws with a live scoreboard, flashing animations, and sound effects.

---

## ⚡ Features

- 🪨📄✂️ Rock, Paper, Scissors choice buttons with icons
- 🤖 Random computer move generator
- 🧠 Win / Lose / Draw detection covering all 9 combinations
- 📊 Live scoreboard showing Wins, Losses, Draws, and Rounds
- 🪟 Score displayed in the window title bar
- 🎨 Custom rounded buttons with hover highlight and press animation
- 🌟 Flashing WIN / LOSE / DRAW result banner
- 🔊 Sound effects for every action (generated in code — no audio files)
- 🌈 Gradient background and polished visual design
- ⚔️ VS panel showing both player and computer icons side by side
- 🔄 Two reset options: New Round (keeps score) and Reset Scores (clears all)
- ✅ 53 JUnit automated tests covering all core logic

---

## 🧰 Technologies

- ☕ Java
- 🖼️ Java Swing
- 🎨 Java AWT
- 🔊 javax.sound.sampled
- 🎲 java.util.Random
- 🧪 JUnit 4
- 🐙 GitHub

---

## 📋 Requirements

- ☕ JDK 8 or newer
- 💻 Any Java IDE such as NetBeans, IntelliJ IDEA, or Eclipse

---

## 🚀 How to Run

1. Clone the repository:

```
git clone https://github.com/kabyobiswas07-sys/Rock-Paper-scissors-Game.git
```

2. 📂 Open the project in NetBeans (or any Java IDE)
3. 🔍 Locate `Main.java` inside the `src` folder
4. ▶️ Run the `main()` method
5. 🎮 The game window will open — click Rock, Paper, or Scissors to play

---

## 🕹️ How to Play

| Action | How |
|--------|-----|
| 🪨📄✂️ Make a choice | Click the Rock, Paper, or Scissors button |
| 🌟 See result | WIN / LOSE / DRAW banner flashes with colour and sound |
| 🔄 Start a new round | Click **New Round** — scores are kept |
| 🗑️ Reset everything | Click **Reset Scores** — all counters go back to 0 |

---

## 🗂️ Project Structure

```
src/
└── controller/
│   └── Controller.java
├── model/
│   └── DataModel.java
├── ui/
│   ├── UserInterface.java
│   └── RoundedButton.java
├── utils/
│   ├── Validator.java
│   └── SoundPlayer.java
├── images/
│   ├── rock.png
│   ├── paper.png
│   └── scissors.png
└── Main.java

test/
├── controller/
│   └── ControllerTest.java
├── model/
│   └── DataModelTest.java
└── utils/
    └── ValidatorTest.java
```

---

## 🔩 Main Classes

- 🚪 **Main.java** — Launches the game window
- 🧠 **Controller.java** — Handles all game logic: validates input, generates computer move, determines result, and updates scores
- 💾 **DataModel.java** — Stores all data: player choice, computer choice, result, and win/lose/draw counters
- 🖼️ **UserInterface.java** — Builds and displays the full game window using Java Swing
- 🔘 **RoundedButton.java** — Custom JButton with rounded corners, gradient background, shadow, and press animation
- ✔️ **Validator.java** — Checks that the player's input is one of the three valid choices before processing
- 🔊 **SoundPlayer.java** — Generates win, lose, draw, and click sounds using sine-wave math — no external audio files needed

---

## 🏆 Game Rules

| Player | Computer | Result | Reason |
|--------|----------|--------|--------|
| 🪨 Rock | ✂️ Scissors | WIN ✅ | Rock crushes Scissors |
| ✂️ Scissors | 📄 Paper | WIN ✅ | Scissors cuts Paper |
| 📄 Paper | 🪨 Rock | WIN ✅ | Paper covers Rock |
| 🪨 Rock | 📄 Paper | LOSE ❌ | Paper covers Rock |
| 📄 Paper | ✂️ Scissors | LOSE ❌ | Scissors cuts Paper |
| ✂️ Scissors | 🪨 Rock | LOSE ❌ | Rock crushes Scissors |
| 🪨 Rock | 🪨 Rock | DRAW 🤝 | Same choice |
| 📄 Paper | 📄 Paper | DRAW 🤝 | Same choice |
| ✂️ Scissors | ✂️ Scissors | DRAW 🤝 | Same choice |

---

## 💥 Game Logic

Win/Lose/Draw detection is handled in `Controller.java` using the `determineResult()` method:

```java
if (player.equals(computer)) {
    return DataModel.DRAW;
}
if ((player.equals(DataModel.ROCK)     && computer.equals(DataModel.SCISSORS)) ||
    (player.equals(DataModel.SCISSORS) && computer.equals(DataModel.PAPER))    ||
    (player.equals(DataModel.PAPER)    && computer.equals(DataModel.ROCK))) {
    return DataModel.WIN;
}
return DataModel.LOSE;
```

This covers all 9 possible combinations every round.

---

## 📅 Weekly Development

### 📌 Week 1 — Planning & Requirement Gathering
Selected the project topic, studied Rock Paper Scissors rules, identified required features, and set up Java, NetBeans, and the GitHub repository.

### 📐 Week 2 — System Design
Created the overall project structure, prepared flowcharts and class diagrams, and defined the MVC architecture with separate Controller, Model, and UI layers.

### 🖼️ Week 3 — User Interface Development
Built the game window using Java Swing. Added Rock, Paper, and Scissors buttons with icons. Created the custom `RoundedButton` class with rounded corners and hover effects.

### 🖱️ Week 4 — User Interaction
Wired button actions to the Controller. Implemented `Validator.java` to check input before processing. `DataModel` now stores the player's choice.

### 🤖 Week 5 — Computer Decision Module
Developed the random move generator in `Controller.java` using `java.util.Random`. The computer's icon is displayed in the VS panel after every pick.

### ⚙️ Week 6 — Core Game Logic
Implemented `determineResult()` covering all 9 combinations. Added WIN / LOSE / DRAW colour-coded result banner. The status bar shows the reason (e.g. "Rock crushes Scissors").

### 📊 Week 7 — Score Management
Added `winCount`, `loseCount`, and `drawCount` counters in `DataModel`. Built a live scoreboard at the top of the window. Added two reset options: New Round and Reset Scores.

### ✨ Week 8 — Feature Enhancement
Added gradient background, button press animation using `javax.swing.Timer`, flashing result banner, and sound effects generated in code using `javax.sound.sampled`. Score also appears in the window title bar.

### 🧪 Week 9 — Testing & Debugging
Wrote 53 JUnit 4 tests across three test classes. Found and fixed 3 bugs: null pointer crash, double score on rapid clicks, and animation timer leak on window close.

### 🎓 Week 10 — Finalization & Submission
Prepared the final report, updated README, added screenshots, completed the final GitHub upload, and prepared for project presentation.

---

## 🔬 Testing

53 JUnit tests were written across 3 test classes. All tests pass.

| Test Class | Tests | What Is Tested |
|------------|-------|----------------|
| DataModelTest.java | 23 | Default values, getters/setters, score counters, reset methods |
| ValidatorTest.java | 13 | Valid choices, null, empty string, wrong case, error messages |
| ControllerTest.java | 17 | Round flow, computer choice, result format, score totals, resets |
| **Total** | **53** | All core game logic |

---

## 🔧⚠️ Bugs Fixed

| # | File | Bug | Fix |
|---|------|-----|-----|
| 1 | Controller.java | Null pointer crash when null passed as choice | Added explicit null guard at top of `handlePlayerChoice()` |
| 2 | Controller.java | Rapid clicks doubled the score in one round | Added `isProcessing` boolean flag to block re-entry |
| 3 | UserInterface.java | Flash Timer kept running after window close | Added `WindowListener` calling `stopFlashAnimation()` on close |

---

## 🔭 Future Improvements

- 🎨 Add images/sprites instead of flat icons
- 👥 Multiplayer mode (Player vs Player)
- 💾 High score system saved to a file
- 📜 Game history log showing all previous rounds
- 🎯 Difficulty settings (Easy / Medium / Hard with weighted computer choices)
- 🏠 Main menu screen
- 🎬 Animated transitions between rounds

---

## 🪪 Project Information

**🎮 Project:** Rock Paper Scissors Game  
**☕ Language:** Java  
**🖼️ GUI:** Java Swing / AWT  
**🏫 Type:** University Project  
**🏁 Status:** ✅ Completed  
**👨‍💻 Student:** kabyobiswas07-sys  
**🔗 Repository:** [github.com/kabyobiswas07-sys/Rock-Paper-scissors-Game](https://github.com/kabyobiswas07-sys/Rock-Paper-scissors-Game)
