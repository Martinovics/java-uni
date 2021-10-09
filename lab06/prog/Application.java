package lab06.prog;

import lab06.classes.*;
import java.lang.Thread;




public class Application {

    public static void main(String[] args) {

        Producer producerOne = new Producer("elso");
        producerOne.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Producer producerTwo = new Producer("masodik");
        producerTwo.start();

    }
}
