package Threads;


public class Test {
    public static void main(String[] args) {
        System.out.println("Debut programme principale");
        Traitement ta=new Traitement("A");
        Traitement tb=new Traitement("B");
        //ta.start();
        ta.start();
        tb.start();

        try {
            ta.join();
            tb.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Thread.sleep(2000);
        System.out.println("Fin programme principale");
    }
}
