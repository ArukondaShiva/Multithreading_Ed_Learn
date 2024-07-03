package org.example.supermanproblem;

public class SupermanCorrectButSlow {

    private static SupermanCorrectButSlow superman;


    private SupermanCorrectButSlow(){

    }

    public static SupermanCorrectButSlow getInstance(){
        synchronized (SupermanCorrectButSlow.class){
            if(superman==null){
                superman = new SupermanCorrectButSlow();
            }
        }
        return superman;
    }

    public void fly(){
        System.out.println("I am Superman & I can fly !");
    }


}
