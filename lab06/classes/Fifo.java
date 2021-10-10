package lab06.classes;

import java.util.LinkedList;




public class Fifo extends Thread {


    private volatile LinkedList<String> list;
    private int MAX_SIZE;

    public Fifo() {
        this.list = new LinkedList<String>();
        this.MAX_SIZE = 10;
    }




    public void put(String element) throws InterruptedException {

        synchronized (this.list) {

            while (this.list.size() == this.MAX_SIZE) {
                this.list.wait();
            }

            this.list.add(element);
            this.list.notify();
        }

    }




    public String get() throws InterruptedException {

        synchronized (this.list) {

            while (this.list.size() == 0) {
                this.list.wait();
            }

            String element = this.list.remove(0);
            this.list.notify();

            return element;
        }
    }


}
