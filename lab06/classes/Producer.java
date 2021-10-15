package lab06.classes;




public class Producer implements Runnable {


    private String text;
    private volatile Fifo list;
    private int sleepTime;


    public Producer(String text, Fifo list, int sleepTime) {
        this.text = text;
        this.list = list;
        this.sleepTime = sleepTime;
    }




    private void go() throws InterruptedException {

        int c = 0;
        while (true) {

            long time = System.currentTimeMillis() % 100000;

            this.list.put(this.text + " " + time);
            System.out.println("produced " + this.text + " " + c  + " " + time);

            Thread.sleep(this.sleepTime);
            c += 1;
        }
    }




    public void run() {
        try {
            go();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}