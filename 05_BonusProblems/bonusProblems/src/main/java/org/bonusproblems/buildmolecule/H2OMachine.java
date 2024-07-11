package org.bonusproblems.buildmolecule;

import java.util.Arrays;
import java.util.Collections;

public class H2OMachine {

    Object sync;
    String[] molecule;
    int count;

    public H2OMachine(){
        molecule = new String[3];
        count = 0;
        sync = new Object();
    }



    public void HydrogenAtom() throws InterruptedException {

        synchronized (sync){

            // if 2 Hydrogen atoms already exist
            while (Collections.frequency(Arrays.asList(molecule),"H")==2){
                sync.wait();
            }

            molecule[count] = "H";
            count++;

            // when building molecule is complete
            if(count==3){
                for(String element : molecule){
                    System.out.print(element);
                }
                Arrays.fill(molecule,null);
                count = 0;
            }

            sync.notifyAll();
        }

    }


    public void OxygenAtom() throws InterruptedException {

        synchronized (sync){

            // when 1 Oxygen atom already exist.
            while (Collections.frequency(Arrays.asList(molecule),"O")==1){
                sync.wait();
            }

            molecule[count] = "O";
            count++;

            // when building a molecule completes.
            if(count==3){
                for(String element : molecule){
                    System.out.print(element);
                }
                Arrays.fill(molecule,null);
                count = 0;
            }

            sync.notifyAll();
        }

    }



}
