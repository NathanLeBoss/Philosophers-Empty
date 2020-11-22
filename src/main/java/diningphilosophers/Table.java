package diningphilosophers;

import java.io.IOException;

public class Table {

    public static void main(String[] args) {
        ChopStick s1 = new ChopStick();
        ChopStick s2 = new ChopStick();
        ChopStick s3 = new ChopStick();
        Philosopher rigaud = new Philosopher("Rigaud", s1, s2);
        Philosopher conchon = new Philosopher("Conchon", s2, s3);
        Philosopher bastide = new Philosopher("Bastide", s3, s1);
        rigaud.start();
        conchon.start();
        bastide.start();
        // attendre la frappe d’une touche dans la console
        System.out.println("Tapez <Enter> pour arrêter la table");
        try {
            System.in.read();
        } catch (IOException ex) {
        }
        rigaud.leaveTable();
        conchon.leaveTable();
        bastide.leaveTable();

    }
}
