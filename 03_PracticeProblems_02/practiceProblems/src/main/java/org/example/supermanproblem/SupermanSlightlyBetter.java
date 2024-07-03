package org.example.supermanproblem;

public class SupermanSlightlyBetter {

    private static SupermanSlightlyBetter superman;

    private SupermanSlightlyBetter(){

    }

    public static SupermanSlightlyBetter getInstance(){

        if(superman==null){
            synchronized (SupermanSlightlyBetter.class){
                if(superman==null){
                    superman = new SupermanSlightlyBetter();
                }
            }
        }

        return superman;
    }


}
