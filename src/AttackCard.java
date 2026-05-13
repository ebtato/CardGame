// should work correctly and be on theme(ish)



import java.util.ArrayList;

public abstract class AttackCard extends Card implements DealsDamage {

    private int attackDamage;

    public AttackCard() {

        // Attack card settings
        int minAttackDamage = 5;
        int maxAttackDamage = 8;
        this.attackDamage = Rand.randomInt(minAttackDamage, maxAttackDamage + 1);

        // has a point value of 0 because it can only damage
        super(0);
    }

    @Override
    public void play(Player currentPlayer, ArrayList<Player> allPlayers) {

        System.out.println(currentPlayer.getName() + " played " + this);

        doDamage(currentPlayer, currentPlayer);
    }

    @Override
    public void doDamage(Player currentPlayer, Player playerToDamage) {
        currentPlayer.removePoints(attackDamage);
        System.out.println("\n" + currentPlayer.getName() + " took " + attackDamage + " points damage.");
        System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getNumPoints() + " points.\n");
    }

    @Override
    public String toString() {
        return "Attack Card { point value: " + super.getPointValue() + ", damage: " + attackDamage + "}";
    }
}
