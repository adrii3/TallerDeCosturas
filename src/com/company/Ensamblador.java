package com.company;

public class Ensamblador implements Runnable  {
    private Fabrica fam;
    private String Nom;

    public Ensamblador(Fabrica f, String Nom) {
        fam = f;
        this.Nom = Nom;
    }

    @Override
    public void run() {
        if ((fam.getManigues() < 1) & fam.getCossos()< 1) {
            fam.agafaManigues(1);
            fam.agafaCossos(1);
            System.out.println("Sóc el cosidor " + Nom + " he agafat 1 maniga i 1 cos\n");
        }
    }
}
