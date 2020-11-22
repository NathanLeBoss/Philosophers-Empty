package diningphilosophers;

public class ChopStick {
    // Le nombre total de baguettes
    private static int stickCount = 0;
    // Le numéro de chaque baguette
    private final int myNumber;
    // Est-ce que ma baguette est libre ?
    private boolean iAmFree = true;

    public ChopStick() {
        // Chaque baguette est numérotée 
        myNumber = ++stickCount;
    }

    synchronized boolean take() throws InterruptedException{
        // si la baguette n'est pas libre
        if(!iAmFree){
            // alors pas réussi à la prendre
            return false;
        }
        // sinon baguette prise donc plus libre
        iAmFree = false;
        return true;
    }

    synchronized void release() throws InterruptedException{
        this.iAmFree = true;
        // on indique à tout le monde que la baguette est relachée
        notifyAll();
    }
    
    @Override
    public String toString() {
        return "Stick#" + myNumber;
    }
}
