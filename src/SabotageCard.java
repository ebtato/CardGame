import java.util.ArrayList;

public class SabotageCard extends Card {

    public SabotageCard() {
        // Sabotage card settings
        int pointValue = -Rand.randomInt(1, 4); // costs 1 to 3 points

        super(pointValue);
    }

    @Override
    public void play(Player currentPlayer, ArrayList<Player> allPlayers) {
        currentPlayer.addPoints(super.getPointValue());

        System.out.println(currentPlayer.getName() + " was forced to play " + this);
        System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getNumPoints() + " progress points.");

    }

    @Override
    public String toString() {
        return "Sabotage Card { point value: " + super.getPointValue() + "}";
    }
}

