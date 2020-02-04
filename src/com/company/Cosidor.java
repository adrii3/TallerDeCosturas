package com.company;

public class Cosidor implements Runnable {
    private String Nom;
    private Fabrica fa;

    public Cosidor(Fabrica f, String nom) {
        fa=f;
        Nom = nom;
    }

    public Cosidor() {
        super();
    }

    public Cosidor(String nom) {
        super();
        Nom = nom;
    }


    @Override
    public void run() {
        fa.OmplePotManigues(1);
        System.out.println("Sóc " + Nom + " manigues que afegeixo = " + fa.getManigues());
    }
}
