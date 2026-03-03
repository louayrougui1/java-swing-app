package Threads;

public class Traitement extends Thread{
    String nom;
    Traitement(String nom){
        this.nom=nom;
    }
    public void run(){
        int i=0;
        while(i<10){
            i++;
            System.out.println("Traitement num "+i+" du thread "+nom);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
