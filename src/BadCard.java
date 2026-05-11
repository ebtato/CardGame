import java.util.ArrayList;

public abstract class BadCard extends Card implements DealsDamage{
    private int damage;

    public BadCard(int pointValue) {
        int minDamage = 5;
        int maxDamage = 8;
        damage = Rand.randomInt(minDamage, maxDamage + 1);
        super(pointValue);
    }

    @Override
    public void play(Player currentPlayer, ArrayList<Player> allPlayers){

    }

    @Override
    public void doDamage(Player currentPlayer, Player playerToDamage){

    }

}
