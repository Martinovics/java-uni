package lab06.classes;

import java.util.LinkedList;




public class Fifo extends Thread {


    private volatile LinkedList<String> list;

    public Fifo() {
        this.list = new LinkedList<String>();
    }




    synchronized void put(String string) throws InterruptedException {

        if (this.list.size() < 10) {
            this.list.add(string);

            if (holdsLock(this)) {
                notify();
            }

        } else {
            if (holdsLock(this)) {
                wait();
            }
        }

    }




    synchronized String get() throws InterruptedException {

        String string = "";

        if (0 < this.list.size()) {
            string = this.list.remove(0);

            if (holdsLock(this)) {
                notify();
            }

            return string;

        } else {
            if (holdsLock(this)) {
                wait();
            }
        }

        return string;
    }


}
