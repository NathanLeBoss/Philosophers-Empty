package diningphilosophers;

import java.util.Random;


public class Philosopher
        extends Thread {

    private static int seed = 1;
    // Un générateur aléatoire pour tirer au sort les durées
    private final Random myRandom = new Random(System.currentTimeMillis() + seed++);
    private final static int DELAY = 1000;
    private final ChopStick myLeftStick;
    private final ChopStick myRightStick;
    private boolean running = true;

    public Philosopher(String name, ChopStick left, ChopStick right) {
        super(name);
        myLeftStick = left;
        myRightStick = right;
    }

    @Override
    public void run() {
        // tant qu'il ne quitte pas la table
        while(running){
            try {
                think();
                // si réussi à prendre baguette gauche
                if(myLeftStick.take()){
                    // si réussi à prendre baguette droite
                    if(myRightStick.take()){
                        // mange puis relache les baguettes
                        eat();
                        myLeftStick.release();
                        myRightStick.release();
                    } else {
                        // sinon relache la baguette gauche
                        myLeftStick.release();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Permet d'interrompre le philosophe "proprement" :
    // Il doit relâcher ses baguettes avant de s'arrêter
    public void leaveTable() {
        running = false;
    }

    private void think() throws InterruptedException {
        int delay = myRandom.nextInt(2000 + DELAY);
        System.out.println(this.getName() + " Starts Thinking for: " + delay + " ms");
        sleep(delay); // Le thread peut être interrompu ici
        System.out.println(this.getName() + " Stops Thinking");
    }

    private void eat() throws InterruptedException {
        int delay = myRandom.nextInt(2000 + DELAY);
        System.out.println(this.getName() + " Starts Eating for:" + delay + " ms");
        sleep(delay); // Le thread peut être interrompu ici
        System.out.println(this.getName() + " Stops Eating");
    }
}
