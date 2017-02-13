# Netlight Summer Internship - Animal Poker
The task is to implement a simplified variant of a poker game in one of the available languages.

Read this document carefully before starting to make sure you have fully understood the assignment.

## Poker Rules
Each player should have 4 cards and the best hand will win. If two players or more are tied the player with the most valuable card should win. The deck of cards consists of 56 cards.

The most valuable card is _Tail of Unicorn_ and the least valuable is _2 of Pony_. The suits are valued in the following order: PONY (LOWEST), HORSE, RHINO, AND UNICORN (HIGHEST).

Each suit has the cards 2 to 14 and tail.

__Scoring__

| Hand            | Score |
|-----------------|-------|
| All Different   | 4     |
| Straight        | 3     |
| Two Pair        | 2     |
| High Card       | 1     |

_Tail should be treated as both 1 and 15 in straights._

## Assignment Rules
You are not allowed to use any external libraries/dependencies and you are free to choose **one** of the available languages.

The goal is to implement the rules of this game. Keep in mind that code quality will be evaluated along with making sure that the code fulfills the requirements and makes the tests pass.

#### Note
It is expected in all of the languages that a game with players should be created based on a list of player names and the cards that have been dealt. The cards that have been dealt are the list of cards based on their location in the deck and each player should get one card each from the list of cards until all players have 4 cards. This means that the first player would get `1, 3` and the second `2, 4` if list of cards is `1, 2, 3, 4, ...` and two players are playing.

__Example__

| ID | Card            |
|----|-----------------|
| 1  | 2 of Pony       |
| 14 | Tail of Pony    |
| 20 | 7 of Horse      |
| 38 | 11 of Rhino     |
| 50 | 9 of Unicorn    |

### Java
The code can be found in `/Java`. Open the project in your favorite IDE.

It is possible to open the project as a maven project or a java project including the lib directory to include the needed dependencies.

The tests can be found in `/Java/src/test/`

### JavaScript
The code can be found in `/JavaScript`. Begin with installing the dependencies for the project using `npm install` and after completion it will be possible to run the tests with `npm run test` and `npm run test:watch`.

An empty base have been provided as a starting point in `/JavaScript/src`. Use this as a start for the needed implementation.

The tests can be found in `/JavaScript/tests.js`

The answer will be tested against Node **4.5**.

### Python
The code can be found in `/Python`.

An empty base have been provided as a starting point in `/Python/src`. Use this as a start for the needed implementation.

Tests for your program can be found in `/Python/tests.py`.

The answer will be tested against Python **3.5.2**.

## Submit your solution
Submit back your solution via email in the language you selected.
