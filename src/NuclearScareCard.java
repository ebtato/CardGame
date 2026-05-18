// TODO: Messages
//  Lines 27, 28, 45 have messages that may not fit with the theme
//  Change weights and possibly functionality:
//      At the moment, the card deals blanket damage to everyone and the player who deals the card gets points. This may
//      be too overpowered. Likely needs a reduction in either points damage, probability of being pulled, or a
//      combination of the two


import java.util.ArrayList;
import java.util.List;

public class NuclearScareCard extends Card implements DealsDamage {
    private int damage; // amount of points subtracted from everyone else

    public NuclearScareCard() {
        // Card settings (currently copied from FreezeCard, change values as you see fit
        int minDamage = 4;
        int maxDamage = 6;
        this.damage = Rand.randomInt(minDamage, maxDamage + 1);

        // Points gained from playing this card
        int pointValue = 0;

        super(pointValue);
    }

    @Override
    public void play(Player currentPlayer, ArrayList<Player> allPlayers) {
        currentPlayer.addPoints(super.getPointValue());

        System.out.println(currentPlayer.getName() + " played " + this);
        System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getNumPoints() + " points.");

        // choose a target player (and not the current player)
        if (allPlayers.size() < 2) {
            System.out.println("Error: No other players for the NuclearScareCard to inflict damage.");
            return;
        }

        // afflictedPlayers list, pretty suboptimal but for some reason this card attacks certain subjects twice
        List<Player> attackingPlayers = new ArrayList<>(allPlayers);
        //attackingPlayers.remove(currentPlayer);
        for (Player afflictedPlayer : attackingPlayers) {
            System.err.println("did damage, the problem is here");
            doDamage(currentPlayer, afflictedPlayer);
          }

    }

    @Override
    public void doDamage(Player currentPlayer, Player playerToDamage) {
        playerToDamage.removePoints(damage);
        System.err.println("called doDamage");
        System.out.println(currentPlayer.getName() + " did " + damage + " damage to " + playerToDamage.getName() + ". " + "(Points after: " + playerToDamage.getNumPoints() + ")");
//        System.out.println(playerToDamage.getName() + " now has " + playerToDamage.getNumPoints() + " points.\n");
    }

    @Override
    public String toString() {
        return "Nuclear Scare Card { point value: " + super.getPointValue() + ", damage: Variable}";
    }

}
