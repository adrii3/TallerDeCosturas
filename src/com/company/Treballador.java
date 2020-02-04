package com.company;

public class Treballador implements Runnable {
    private String Nom;
    private Fabrica fa;

    public Treballador(Fabrica f, String nom) {
        fa=f;
        Nom = nom;
    }

    public Treballador() {
        super();
    }

    public Treballador(String nom) {
        super();
        Nom = nom;
    }

    @Override
    public void run() {
        fa.OmplePotCossos(1);
        System.out.println("Sóc " + Nom + " cossos que afegeixo = " + fa.getCossos());
    }
}
