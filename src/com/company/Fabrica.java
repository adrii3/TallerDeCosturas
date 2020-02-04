package com.company;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public  class Fabrica {

    private int manigues;
    private int cossos;
    private boolean potManiguesOcupat, potCossosOcupat ,  HiHaManigues, HiHaCossos;

    public Fabrica() {
        manigues = 0;
        cossos = 0;
        potCossosOcupat = false;
        potManiguesOcupat =false;
        HiHaManigues = false;
        HiHaCossos = false;
    }
    public Fabrica(int manigues, int cossos) {
        this.manigues = manigues;
        potManiguesOcupat = false;
        HiHaManigues = true;

        this.cossos = cossos;
        potCossosOcupat = false;
        HiHaCossos = true;
    }

    public synchronized void OmplePotManigues(int n) {
        System.out.println("vull omplir manigues...");
        try {
            while(potManiguesOcupat || HiHaManigues) wait();
            potManiguesOcupat = true;
            this.manigues = n;
            System.out.println("Pot omplert amb "+n+" manigues");
            HiHaManigues = true;
            potManiguesOcupat = false;
            notifyAll();
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public synchronized void OmplePotCossos(int n){
        System.out.println("vull omplir cossos...");
        try {
            while(potCossosOcupat || HiHaCossos) wait();
            potCossosOcupat = true;
            this.cossos = n;
            System.out.println("Pot omplert amb "+n+" manigues");
            HiHaCossos = true;
            potCossosOcupat = false;
            notifyAll();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public synchronized int agafaManigues(int n) {
        System.out.println("vull agafar Manigues");
        try {
            while(potManiguesOcupat || HiHaManigues ) wait();
            potManiguesOcupat = true;
            this.manigues = this.manigues - n;
            System.out.println("Agafo "+n+" manigues");
            if(this.manigues <= 0) {
                HiHaManigues = false;
                potManiguesOcupat =false;
                notifyAll();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return manigues;
    }
    public synchronized int agafaCossos(int n) {
        System.out.println("vull agafar Cossos");
        try {
            while(potCossosOcupat || HiHaCossos ) wait();
            potCossosOcupat = true;
            this.cossos = this.cossos - n;
            System.out.println("Agafo "+n+" manigues");
            if(this.cossos <= 0) {
                HiHaCossos = false;
                potCossosOcupat =false;
                notifyAll();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return cossos;
    }

    public synchronized int getManigues() {
        return manigues;
    }
    public synchronized int getCossos(){
        return cossos;
    }

    public static void main(String[] args) throws InterruptedException {
        Fabrica Fam = new Fabrica(10,10);

        final ScheduledExecutorService schExService = Executors.newScheduledThreadPool(3);

        Ensamblador nen = new Ensamblador(Fam,"Flan");
        Treballador treballador = new Treballador(Fam, "Edgar");
        Cosidor cosidor = new Cosidor(Fam,"Adri");


        schExService.scheduleWithFixedDelay(nen, 2, 3, TimeUnit.SECONDS);
        schExService.scheduleWithFixedDelay(treballador, 4, 1, TimeUnit.SECONDS);
        schExService.scheduleWithFixedDelay(cosidor, 5, 3, TimeUnit.SECONDS);

        // Espera per acabar 35 segons
        schExService.awaitTermination(30, TimeUnit.SECONDS);

        // shutdown .
        schExService.shutdownNow();
        System.out.println("Completat");

    }

}
