public class Main {

    public static void main(String[] args) {

        Thread t1 = new Thread(new RunnableWorker());
        t1.setName("worker-Alpha");
        Thread t2 = new Thread(new RunnableWorker());
        t2.setName("worker-Beta");
        Thread t3 = new Thread(new RunnableWorker());
        t3.setName("worker-Gamma");
        t1.start();
        t2.start();
        t3.start();

    }
}
