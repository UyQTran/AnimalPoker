# Animal Poker - Poker with a twist

Each player is dealt 4 cards at random which totals to a hand. Then we compare each players hand so the player with the highest score wins. 

__Scoring__

| Hand            | Points |
|-----------------|--------|
| All Different   | 4      |
| Straight        | 3      |
| Two Pair        | 2      |
| High Card       | 1      |

The deck consists of 56 cards with four suits. Each suit has the cards 2 to 14 and tail. The suits value is orderes from lowest to highest: PONY (LOWEST), HORSE, RHINO, AND UNICORN (HIGHEST). _Tail should be treated as both 1 and 15 in straights._

__Example of a game__

| Player 1 hand   |
|-----------------|
| 4 of Pony       |
| Tail of Pony    |
| 7 of Horse      |
| 11 of Rhino     |


| Player 2 hand   |
|-----------------|
| 2 of Pony       |
| 4 of Horse      |
| 9 of Rhino      |
| Tail of Unicorn |

Player 2 wins because they get 4 points for having all different suits.
