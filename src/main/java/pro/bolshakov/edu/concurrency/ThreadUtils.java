package pro.bolshakov.edu.concurrency;

public class ThreadUtils {

    public static void wait(int sec){
        try {
            System.out.println("Sleeping sec -> " + sec);
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
