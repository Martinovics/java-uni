package lab06.prog;

import lab06.classes.*;
import java.util.Random;



public class Application {

    public static void main(String[] args) {

        Random rand = new Random();

        Fifo list = new Fifo();

        Producer producer1 = new Producer("p1", list, rand.nextInt(1000) + 1000);
        Producer producer2 = new Producer("p2", list, rand.nextInt(1000) + 1000);
        Producer producer3 = new Producer("p2", list, rand.nextInt(1000) + 1000);

        Consumer consumer1 = new Consumer("c1", list, rand.nextInt(500) + 100);
        Consumer consumer2 = new Consumer("c2", list, rand.nextInt(500) + 100);
        Consumer consumer3 = new Consumer("c3", list, rand.nextInt(500) + 100);
        Consumer consumer4 = new Consumer("c4", list, rand.nextInt(500) + 100);

        Thread p1 = new Thread(producer1);
        p1.start();
        Thread p2 = new Thread(producer2);
        p2.start();
        Thread p3 = new Thread(producer3);
        p3.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();

    }
}
