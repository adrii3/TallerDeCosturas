package com.company;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public  class Fabrica {
    public static void main(String[] args) throws InterruptedException {
        Cistell cistell_manigues = new Cistell(8);
        Cistell cistell_cossos = new Cistell(5);
        Cosidor cosidor_manigues = new Cosidor(cistell_manigues, "Maniga");
        Cosidor cosidor_manigues2 = new Cosidor(cistell_manigues, "Maniga2");
        Cosidor cosidor_cossos = new Cosidor(cistell_cossos, "Cos");
        Ensamblador ensamblador = new Ensamblador(cistell_manigues,cistell_cossos,"Peça");

        cosidor_manigues.start();
        cosidor_manigues2.start();
        cosidor_cossos.start();
        ensamblador.start();
    }
}

