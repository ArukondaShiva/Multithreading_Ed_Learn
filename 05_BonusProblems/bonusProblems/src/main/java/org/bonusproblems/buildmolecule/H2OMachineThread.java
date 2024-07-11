package org.bonusproblems.buildmolecule;

public class H2OMachineThread extends Thread{

    H2OMachine molecule;
    String atom;


    public H2OMachineThread(H2OMachine molecule, String atom){
        this.atom = atom;
        this.molecule = molecule;
    }


    public void run(){

        if("H".equals(atom)){
            try {
                molecule.HydrogenAtom();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else if("O".equals(atom)){
            try {
                molecule.OxygenAtom();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
