import java.util.ArrayList;

public class SpyCard extends Card {

    public SpyCard() {
        // Thief card settings
        int pointValue = -1; // costs 1 point to use

        super(pointValue);
    }

    @Override
    public void play(Player currentPlayer, ArrayList<Player> allPlayers) {
        currentPlayer.addPoints(super.getPointValue());

        System.out.println(currentPlayer.getName() + " played " + this);
        System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getNumPoints() + " progress points.");

        // move a card from a target player to a new player

        // 1. choose a target player (and not oneself)
        if (allPlayers.size() < 2) {
            System.out.println("Error: No other players for the Spy Card to steal from.");
            return;
        }

        boolean selectedAnotherPlayer = false;
        Player otherPlayer = null;

        while (!selectedAnotherPlayer) {
            int randomPlayerIndex = Rand.randomInt(0, allPlayers.size());
            otherPlayer = allPlayers.get(randomPlayerIndex);
            if (otherPlayer != currentPlayer) {
                selectedAnotherPlayer = true;
            }
        }

        // 2. remove a random card from that player
        Card removedCard = otherPlayer.removeRandomCard();
        if (removedCard == null) {
            System.out.println("Cannot steal from " + otherPlayer.getName() + " because they have no cards!");
        }

        // 3. add the removed card to the current player
        else {
            currentPlayer.addCardToHand(removedCard);
            System.out.println(currentPlayer.getName() + " stole " + removedCard + " from " + otherPlayer.getName() + ".");
        }
    }

    @Override
    public String toString() {
        return "SPY CARD { point value: " + super.getPointValue() + "}";
    }
}
