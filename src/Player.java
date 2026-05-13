import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int numPoints;
    private boolean isFrozen;

    private int totRocketTests;
    public int getTotRocketTests(){ return totRocketTests; }

    private float rocketTestFailChance;
    public float getRocketTestFailChance() { return rocketTestFailChance; }

    private int phase;
    public int getPhase() { return phase; }

    private boolean rocketTested;
    public boolean isRocketTested() { return rocketTested; }

    public void addCardToTestDeck(int i){
        switch (i){

            //add fail card
            case 0:
                rocketTestFailChance += 0.05f;
                break;

            //add success card
            case 1:
                rocketTestFailChance -= 0.05f;
                break;
            default:
                System.out.println("Error: card {"+i+"} does not exist.");
        }
    }

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<Card>();
        numPoints = 5;
        isFrozen = false;
        // a number between 0 and 1
        rocketTestFailChance = 0.5f;
    }

    public void playRandomCardFromHand(ArrayList<Player> players) {
        // select a random card from our hand to play
        int randomCardIndex = Rand.randomInt(0, hand.size());
        Card randomCard = hand.remove(randomCardIndex);
        randomCard.play(this, players);

        // pick a random player (but not oneself) to apply any additional actions to
        boolean selectedAnotherPlayer = false;
        Player otherPlayer = null;

        while (!selectedAnotherPlayer) {
            int randomPlayerIndex = Rand.randomInt(0, players.size());
            otherPlayer = players.get(randomPlayerIndex);
            if (otherPlayer != this) {
                selectedAnotherPlayer = true;
            }
        }

        /*
        // do possible additional damage action
        if (randomCard instanceof DealsDamage) {
            DealsDamage damageCard = (DealsDamage)randomCard;
            damageCard.doDamage(this, otherPlayer);
        }

        // do possible additional freeze action
        if (randomCard instanceof AppliesFreeze) {
            AppliesFreeze freezeCard = (AppliesFreeze)randomCard;
            freezeCard.freeze(this, otherPlayer);
        }

         */
    }

    public void updatePhase(float thresh1, float thresh2, float thresh3){

        int initPhase = phase;
        if(numPoints <= thresh1){
            phase = 1;
        }
        else if(numPoints > thresh1 && numPoints <= thresh2){
            phase = 2;
        }
        else if(numPoints > thresh2 && numPoints < thresh3){
            phase = 3;
        }
        else{
            // final launch phase
            phase = 4;
        }

        // if the phase increases then allow test to be performed
        // if pushed back one phase, nothing will happen until coming back up to test again
        if(initPhase < phase){
            rocketTested = false;
        }
    }

    public void testRocket(float thresh1, float thresh2){
        // "draw" test card from "draw pile"
        // not a real draw pile, but simply a probability of successful or failed test
        // that way certain in-game decisions can affect the chances of good/bad tests
        // simulates adding good/bad cards to the pile to change probabilities,
        // without actually adding cards to a pile and giving each player a separate draw pile.

        if(phase < 4){
            System.out.println("It's time for "+name+" to test their rocket!");
        }
        else{
            System.out.println("It's time for "+name+"'s final launch!");
        }

        float num = Rand.randomFloat(0f,1f);
        if(num < rocketTestFailChance){

            if(phase < 4){
                System.out.println("ROCKET TEST FAILED!");
            }
            else{
                System.out.println("ROCKET LAUNCH FAILED!");
            }
            setBackTestProgress(thresh1, thresh2);
        }
        else{

            if(phase < 4){
                System.out.println("Rocket Test Success!");
            }
            else{
                System.out.println("Rocket Launch Success!");
            }
        }

        rocketTested = true;
    }

    public void setBackTestProgress(float thresh1, float thresh2){

        switch(phase){

            case 2:
                numPoints = 0;
                break;
            case 3:
                numPoints = (int)thresh1 + 1;
                break;
            case 4:
                numPoints = (int)thresh2 + 1;
                break;
            default:
                break;
        }
    }

    public boolean hasCardsInHand() {
        return hand.size() > 0;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void freeze() {
        isFrozen = true;
    }

    public void unfreeze() {
        isFrozen = false;
    }

    public Card removeRandomCard() {
        if (hand.size() == 0) {
            return null; // returning null indicates there are no cards to remove
        }

        int randomCardIndex = Rand.randomInt(0, hand.size());
        return hand.remove(randomCardIndex); // ArrayList.remove both removes AND returns a reference to the object
    }

    public String getName() {
        return name;
    }

    public void addPoints(int pointsToAdd) {
        numPoints += pointsToAdd;
        if (numPoints < 0) {
            numPoints = 0;
        }
    }

    public void removePoints(int pointsToRemove) {
        addPoints(-pointsToRemove);
    }

    public int getNumPoints() {
        return numPoints;
    }

    public void displayStatus() {
        System.out.println(" | ----- " + name + " ----- ");
        System.out.println(" | Points: " + numPoints);
        if (isFrozen) {
            System.out.println(" | *REBUILDING*");
        }
        System.out.println(" | Cards in hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.print(" | " + (i+1) + ": ");
            System.out.println(hand.get(i));
        }
        System.out.println(" | ----- ----- ----- ");
    }
}
