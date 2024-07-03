package org.example.supermanproblem;

public class SupermanNaiveButCorrect {


    private static SupermanNaiveButCorrect superman = new SupermanNaiveButCorrect();


    private SupermanNaiveButCorrect(){

    }

    public static SupermanNaiveButCorrect getInstance(){
        return superman;
    }

    public void fly(){
        System.out.println("I am Superman & I can fly !");
    }

}
