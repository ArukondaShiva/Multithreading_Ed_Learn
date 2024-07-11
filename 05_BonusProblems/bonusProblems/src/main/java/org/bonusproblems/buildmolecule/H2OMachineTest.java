package org.bonusproblems.buildmolecule;

public class H2OMachineTest {

    public static void test(){


        H2OMachine molecule = new H2OMachine();


        Thread t1 = new H2OMachineThread(molecule,"H");
        Thread t2 = new H2OMachineThread(molecule,"H");
        Thread t3 = new H2OMachineThread(molecule,"O");
        Thread t4 = new H2OMachineThread(molecule,"O");


        t1.start();
        t2.start();
        t3.start();
        t4.start();


    }

}
