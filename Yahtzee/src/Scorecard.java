import java.util.Arrays;
import java.util.Scanner;


class Scorecard {
    private Die[] dice;
    private int[] numConcurrent;
    private int numSides;
    private int numDice;
    private int scoreCounter;
    private Scanner input;



    Scorecard(int numOfDice, int nSides){
        numDice = numOfDice;
        input = new Scanner(System.in);
        dice = new Die[numDice];
        numSides = nSides;
        createDice();
    }

    void scoreChecker(){
        findConcurrence(); //in scorecard fn?

        for(int i = 0; i < numDice; i++){
            if(numConcurrent[i] == 5) {
                yahtzee();
                return;
            }
        }

        aces();
        twos();
        threes();
        fours();
        fives();
        sixes();
        threeOfAKind();
        fourOfAKind();
        fullHouse();
        smallStraight();
        largeStraight();
        yahtzee();
        chance();

    }

    private void createDice(){
        for(int i = 0; i < numDice; i++){
            dice[i] = (new Die(numSides));
        }
    }

    void displayDice(){
        System.out.println("Your dice: ");
        for(int i = 0; i < numDice; i++){
            System.out.print(dice[i].getSideUp() + " ");
        }
        System.out.println();
        System.out.println();
    }

    void saveDice(){ //XXOOXO for example
        System.out.println("Which dice would you like to save? 'X' to save, 'O' to reroll");
        String diceSave = input.nextLine();

        diceSave = diceSave.toUpperCase();
        for(int i = 0; i < numDice; i++) { //Need to catch longer or shorter strings
            if(diceSave.charAt(i) == 'X')
                dice[i].setIs_kept(true);
            else{
                dice[i].setIs_kept(false);
            }
        }
    }

    void sortDiceArray() { //end turn if keep all
        Die[] diceCpy = new Die[numDice];
        for(int i = 0; i < numDice; i++){
            diceCpy[i] = (new Die(numSides));
        }

        int smallestNum;
        int smallestNumPlace;
        int j = 0;

        for (int i = 0; i < numDice; i++) {
            smallestNum = dice[0].getSideUp();
            smallestNumPlace = 0;
            while (j < numDice) {
                if (dice[j].getSideUp() <= smallestNum) {
                    smallestNum = dice[j].getSideUp();
                    smallestNumPlace = j;
                }
                j++;
            }
            diceCpy[i].setSideUp(smallestNum);
            dice[smallestNumPlace].setSideUp(numSides + 1);
            j = 0;
        }
        for(int r = 0; r < numDice; r++){
            dice[r].setSideUp(diceCpy[r].getSideUp());
        }
    }

    private void findConcurrence(){
        numConcurrent = new int[numSides];
        for(int i = 0; i < numDice; i++){
            numConcurrent[dice[i].getSideUp()-1]++; //Increments per die
        }
    }

    private void addDie(){
        scoreCounter = 0;
        for(int i = 0; i < numDice; i++){
            scoreCounter += dice[i].getSideUp();
        }
    }

    void rollDice(){
        for(int i = 0; i < numDice; i++){
                dice[i].roll();

        }
    }

    //Upper section of scorecard
    private void aces(){
        scoreCounter = 0;
        for(int i = 0; i < numDice; i++){
            if(dice[i].getSideUp() == 1){
                scoreCounter += 1;
            }
        }
        System.out.println("Your score for Aces: " + scoreCounter);
    }

    private void twos(){
        scoreCounter = 0;
        for(int i = 0; i < numDice; i++){
            if(dice[i].getSideUp() == 2){
                scoreCounter += 2;
            }
        }
        System.out.println("Your score for Twos: " + scoreCounter);
    }

    private void threes(){
        scoreCounter = 0;
        for(int i = 0; i < numDice; i++){
            if(dice[i].getSideUp() == 3){
                scoreCounter += 3;
            }
        }
        System.out.println("Your score for Threes: " + scoreCounter);
    }

    private void fours(){
        scoreCounter = 0;
        for(int i = 0; i < numDice; i++){
            if(dice[i].getSideUp() == 4){
                scoreCounter += 4;
            }
        }
        System.out.println("Your score for Fours: " + scoreCounter);
    }

    private void fives(){
        scoreCounter = 0;
        for(int i = 0; i < numDice; i++){
            if(dice[i].getSideUp() == 5){
                scoreCounter += 5;
            }
        }
        System.out.println("Your score for Fives: " + scoreCounter);
    }

    private void sixes(){
        scoreCounter = 0;
        for(int i = 0; i < numDice; i++){
            if(dice[i].getSideUp() == 6){
                scoreCounter += 6;
            }
        }
        System.out.println("Your score for Sixes: " + scoreCounter);
    }

    //Lower section of scorecard
    private void threeOfAKind(){
        scoreCounter = 0;
        for(int i = 0; i < numSides; i++){
            if(numConcurrent[i] >= 3){
                addDie();
            }
        }
        System.out.println("Your score for Three of a Kind: " + scoreCounter);
    }

    private void fourOfAKind(){
        scoreCounter = 0;
        for(int i = 0; i < numDice; i++){
            if(numConcurrent[i] >= 4){
                addDie();
            }
        }
        System.out.println("Your score for Four of a Kind: " + scoreCounter);
    }

    private void fullHouse(){
        scoreCounter = 0;
        for(int i = 0; i < numDice; i++){
            if(numConcurrent[i] == 3){
                for(int j = 0; j < numDice; j++){
                    if(numConcurrent[i] == 2){
                        scoreCounter += 25;
                    }
                }
            }
        }
        System.out.println("Your score for Full House: " + scoreCounter);
    }

    private void smallStraight(){
        int concurrentCounter = 0;
        scoreCounter = 0;
        for(int i = 0; i < numDice; i++){
            if(numConcurrent[i] > 0) {
                concurrentCounter++;
                if (concurrentCounter == 4) {
                    scoreCounter = 30;
                }
            }
            if(numConcurrent[i] == 0){
                concurrentCounter = 0;
            }
        }
        System.out.println("Your score for Small Straight: " + scoreCounter);
    }

    private void largeStraight(){
        scoreCounter = 40;
        for(int i = 0; i < numDice; i++){
            if(numConcurrent[i] != 1){
                scoreCounter = 0;
                break;
            }
        }
        System.out.println("Your score for Large Straight: " + scoreCounter);
    }

    private void yahtzee(){
        scoreCounter = 0;
        for(int i = 0; i < numDice; i++){
            if(numConcurrent[i] >= 5){
                scoreCounter += 50;
            }
        }
        System.out.println("Your score for Yahtzee: " + scoreCounter);
    }

    private void chance(){
        addDie();
        System.out.println("Your score for Chance: " + scoreCounter);
    }
}
