package lab06.classes;




public class Producer extends Thread {

    private String text;

    public Producer(String text) {
        this.text = text;
    }




    public void go() {

        int c = 0;
        while (true) {

            System.out.println(this.text + " " + c  + " " + (System.currentTimeMillis() % 100000));

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            c += 1;
        }
    }




    public void run() {
        go();
    }

}