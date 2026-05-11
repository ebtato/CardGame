public class SabotageCard extends AttackCard{
    @Override
    public void doDamage(Player currentPlayer, Player playerToDamage) {
        System.out.println("An enemy nation sabotaged your work!");
        super.doDamage(currentPlayer, playerToDamage);
    }

    @Override
    public String toString() {
        return "Sabotage Card: " + super.toString();
    }


}
