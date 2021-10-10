package lab06.prog;

import lab06.classes.*;




public class Application {

    public static void main(String[] args) {

        Fifo list = new Fifo();

        Producer producer = new Producer("producer_text", list, 2000);
        Consumer consumer = new Consumer("consumer_text", list, 500);
        producer.start();
        consumer.start();

    }
}
