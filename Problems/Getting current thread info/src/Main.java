public static void printCurrentThreadInfo() {
    Thread t = Thread.currentThread();
    System.out.println("name: "+t.getName());
    System.out.println("priority: "+t.getPriority());
}