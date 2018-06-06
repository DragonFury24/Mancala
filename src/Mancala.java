public class Mancala {
    public static void main(String[] args) {
        boolean running = true;

//        Thread game = new Thread(() -> {
//           MancalaPlayer mp = new MancalaPlayer();
//
//           mp.start();
//
//           while (true) {
//               if (!mp.isRunning()) {
//                   mp.stop();
//                   break;
//               }
//           }
//        });

        MancalaPlayer mp = new MancalaPlayer();

        mp.start();
    }
}
