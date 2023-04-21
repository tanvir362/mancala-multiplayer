import com.codingame.gameengine.runner.MultiplayerGameRunner;

public class SkeletonMain {
    public static void main(String[] args) {

        // Uncomment this section and comment the other one to create a Solo Game
        /* Solo Game */
        // SoloGameRunner gameRunner = new SoloGameRunner();

        // Sets the player
        // gameRunner.setAgent(Player1.class);

        // Sets a test case
        // gameRunner.setTestCase("test1.json");

        /* Multiplayer Game */
        MultiplayerGameRunner gameRunner = new MultiplayerGameRunner();

//        gameRunner.addAgent(Agent1.class);
//        gameRunner.addAgent(Agent2.class);

        // Adds as many player as you need to test your game
        gameRunner.addAgent("python3 /home/tanvir/IdeaIC2020.3/IdeaProjects/agents/mancala_multiplayer/solution.py");
        gameRunner.addAgent("python3 /home/tanvir/IdeaIC2020.3/IdeaProjects/agents/mancala_multiplayer/solution.py");
//
        

        gameRunner.start();
    }
}
