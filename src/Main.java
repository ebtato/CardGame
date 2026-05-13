public class Main {
    public static void main(String[] args) {
        Player p1 = new Player("USSR");
        Player p2 = new Player("USA");
        Player p3 = new Player("SpongeBob");
    
        Game game = new Game();
        game.registerPlayer(p1);
        game.registerPlayer(p2);
        game.registerPlayer(p3);

        game.run();
    }
}
