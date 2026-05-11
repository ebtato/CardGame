public class FailureCard extends AttackCard{
    @Override
    public void doDamage(Player currentPlayer, Player playerToDamage) {
        System.out.println("You experienced a critical rocket failure!");
        super.doDamage(currentPlayer, playerToDamage);
    }

    @Override
    public String toString() {
        return "Failure Card: " + super.toString();
    }
}
