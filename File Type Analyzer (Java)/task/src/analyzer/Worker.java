package analyzer;

class Worker extends Thread {
    String arg0;
    String arg1;
    String arg2;
    boolean result = false;

    public Worker(String a0, String a1, String a2){
        arg0=a0;
        arg1=a1;
        arg2=a2;
    }


    @Override
    public void run() {
        try {
            result=Main.checkFileType("--RK", arg0, arg1, arg2);
        } catch (Exception ignored) {
        }
    }
}
