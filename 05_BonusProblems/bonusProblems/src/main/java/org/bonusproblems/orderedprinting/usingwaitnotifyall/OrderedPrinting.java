package org.bonusproblems.orderedprinting.usingwaitnotifyall;

public class OrderedPrinting {

    int count;

    public OrderedPrinting(){
        count = 1;
    }

    public void printFirst() throws InterruptedException{

        synchronized (this){
            System.out.println("First");
            count++;
            this.notifyAll();
        }

    }


    public void printSecond() throws InterruptedException {

        synchronized (this){

            while (count!=2){
                this.wait();
            }

            System.out.println("Second");
            count++;
            this.notifyAll();
        }

    }



    public void printThird() throws InterruptedException {

        synchronized (this){

            while (count!=3){
                this.wait();
            }

            System.out.println("Third");
        }

    }


}
