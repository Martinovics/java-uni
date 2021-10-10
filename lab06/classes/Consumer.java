package lab06.classes;




public class Consumer extends Thread {


    private volatile Fifo list;
    private String text;
    private int sleepTime;


    public Consumer(String s, Fifo f, int n) {
        this.text = s;
        this.list = f;
        this.sleepTime = n;
    }




    private void go() throws InterruptedException {

        while (true) {

            long time = System.currentTimeMillis() % 100000;
            System.out.println("consumed " + this.text + " " + this.list.get()  + " " + time);

            sleep(sleepTime);
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
