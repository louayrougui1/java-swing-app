package Threads;

public class Test {
    public static void main(String[] args) {
        System.out.println("Debut prog principal");
        Traitement ta = new Traitement("A");
        Traitement tb = new Traitement("B");
        ta.start();
        tb.start();
        try {
            ta.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Fin de prog principal");
    }
}
