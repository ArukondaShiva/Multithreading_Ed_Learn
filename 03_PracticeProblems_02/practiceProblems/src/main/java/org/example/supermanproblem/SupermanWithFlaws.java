package org.example.supermanproblem;

public class SupermanWithFlaws {

    private static SupermanWithFlaws superman;

    private SupermanWithFlaws(){

    }

    public static SupermanWithFlaws getInstance(){
        if(superman==null){
            superman = new SupermanWithFlaws();
        }
        return superman;
    }

}
