public class Main {

    public static void main(String[] args) {

        WorkerThread wt1 = new WorkerThread();
        wt1.setName("worker-Alpha");

        WorkerThread wt2 = new WorkerThread();
        wt2.setName("worker-Beta");
        wt1.start();
        wt2.start();
    }
}