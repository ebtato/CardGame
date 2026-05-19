import java.util.ArrayList;

public class SabotageCard extends Card implements DealsDamage, AppliesFreeze {

    public SabotageCard() {
        // Sabotage card settings
        int pointValue = Rand.randomInt(1, 4); // costs 1 to 3 points

        super(pointValue);
    }

    @Override
    public void play(Player currentPlayer, ArrayList<Player> allPlayers) {

        doDamage(currentPlayer, currentPlayer);
        System.out.println(currentPlayer.getName() + " was forced to play " + this);
        System.out.println(currentPlayer.getName() + " was sabotaged and now has " + currentPlayer.getNumPoints() + " progress points.");
    }

    @Override
    public String toString() {
        return "Sabotage Card { point value: " + super.getPointValue() + "}";
    }

    @Override
    public void doDamage(Player currentPlayer, Player playerToDamage) {

        currentPlayer.removePoints(super.getPointValue());
        freeze(currentPlayer, currentPlayer);
    }

    @Override
    public void freeze(Player currentPlayer, Player playerToFreeze) {

        playerToFreeze.freeze();
        System.out.println("Player "+playerToFreeze.getName()+" was frozen by the sabotage!");
    }
}

