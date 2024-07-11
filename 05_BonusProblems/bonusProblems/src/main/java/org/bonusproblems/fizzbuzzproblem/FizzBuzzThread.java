package org.bonusproblems.fizzbuzzproblem;

public class FizzBuzzThread extends Thread{

    MultithreadedFizzBuzz obj;
    String method;


    public FizzBuzzThread(MultithreadedFizzBuzz obj, String method){
        this.obj = obj;
        this.method = method;
    }


    public void run() {

        if("Fizz".equals(method)){
            try {
                obj.fizz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else if("Buzz".equals(method)){
            try {
                obj.buzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else if("FizzBuzz".equals(method)){
            try {
                obj.fizzbuzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else if("Number".equals(method)){
            try {
                obj.number();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
