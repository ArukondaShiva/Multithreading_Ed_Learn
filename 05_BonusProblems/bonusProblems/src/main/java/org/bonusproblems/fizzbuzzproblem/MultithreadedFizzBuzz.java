package org.bonusproblems.fizzbuzzproblem;

public class MultithreadedFizzBuzz {

    private int n;
    private int num = 1;


    public MultithreadedFizzBuzz(int n){
        this.n = n;
    }

    public synchronized void fizz() throws InterruptedException {

        while(num <= n){

            if(num%3==0 && num%5!=0){
                System.out.println("fizz");
                num++;
                notifyAll();
            }else{
                wait();
            }

        }
    }


    public synchronized void buzz() throws InterruptedException {
         while (num<=n){
             if(num%3 !=0 && num%5==0){
                 System.out.println("buzz");
                 num++;
                 notifyAll();
             }else{
                 wait();
             }
         }
    }


    //A multiple of 15 is divisible by 3 and 5 both so num is checked for its divisibility by 15.
    public synchronized void fizzbuzz() throws InterruptedException {

        while (num <= n){
            if(num % 15 == 0){
                System.out.println("fizzbuzz");
                num++;
                notifyAll();
            }else{
                wait();
            }
        }

    }


    public synchronized void number() throws InterruptedException {

        while (num<=n){
            if(num%3!=0 && num%5!=0){
                System.out.println(num);
                num++;
                notifyAll();
            }else{
                wait();
            }
        }

    }


}
