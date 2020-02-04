class Yahtzee {

    private int turnNum;
    Scorecard scorecard;
    private int numTurns = 3;


    Yahtzee(int numDice, int numSides) {
        scorecard = new Scorecard(numDice, numSides);
        turnNum = 0;
        turn();
    }

    private void turn() {
        while (turnNum < numTurns-1) {
            scorecard.rollDice();
            scorecard.displayDice();
            scorecard.scoreChecker();
            scorecard.saveDice();
            turnNum++;
        }
        scorecard.rollDice();
        scorecard.sortDiceArray();
        scorecard.displayDice();
        scorecard.scoreChecker();
    }
}

