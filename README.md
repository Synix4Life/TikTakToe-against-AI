# TikTakToe against AI

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=black)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)

![License](https://img.shields.io/badge/License-MIT-%23ED8B00.svg?style=for-the-badge)
![Version](https://img.shields.io/badge/Version-1.2.0-%23ED8B00.svg?style=for-the-badge)
![Test](https://img.shields.io/badge/Test_passed-10_/_10-02303A.svg?style=for-the-badge)

---

An implementation of TikTakToe, where you can play against an AI.

It works completely in Java and uses the Gradle (Groovy) builder.

---

## Setup

You can change how the AI plays by manipulating the temperature in [Main.java](src/main/java/io/github/synix4life/games/tiktaktoe/Main.java)

The temperature determines how good the AI plays.

- A temperature of 0 means that the AI plays randomly.
- A temperature of 1 means that the AI plays perfectly (ruleset).

You can change it to any double value between 0 and 1, depending on the difficulty you want to choose.

---

## AI

The AI implies a Rule-Based strategy to determine its next move.

Thereby, it follows a 5-step plan, where if it finds a possible move, it will take it. If it finds none, it will continue to the next step.
If it finds multiple possibilities in the same rule, it takes one at random.

Below are the steps listed in their order:

1. Win: Checks if it can win
2. Block: Checks if it can block you if you're about to win
3. Center: Checks if it can acquire the center
4. Corners: Checks if it can acquire a corner
5. Edges: Checks if it can acquire an edge

If none is taken: Random move.

The temperature modifies this behaviour. Hereby, the temperature is a possibility, which controls how many of the choices actually pass.

As an example, if the temperature is 0.8: There is an 80% chance that if a move is found, it will be executed. With a 20% chance, it will ignore the result and continue to the next step.

Currently, the probability only applies to the steps. It doesn't take every possibility into consideration, if multiple moves are found in the same step.

---

### Win against AI with temperature = 1

Here is a quick guide on how to win against the AI with temperature = 1 (if you start), since the AI strictly follows the ruleset. 

Here, 'X' represents your turn, 'O' enemies:

1. 'X' : Take any corner
2. 'O' : Takes center
3. 'X' : Take adjacent corner
4. 'O' : Takes one of the remaining corners
5. 'X' : Take last corner → You now have a fork
6. 'O' : Blocks one possibility
7. 'X' : Win by the second possibility

---

### Future plans

- Improve AI to play truly perfect (i.e. notice a fork), for example by utilizing [this strategy](https://en.wikipedia.org/wiki/Tic-tac-toe#Strategy)

---

## Changelog

- $\textsf{\color{orange}Version 1.0.0}$
    - Initial Upload
    - $\textsf{\color{orange}Version 1.1.0}$
      - Outsourced ActionListener
      - Restructuring
      - Changed package structure to fit conventions
  - $\textsf{\color{orange}Version 1.2.0}$
      - Added ability to make bot start
