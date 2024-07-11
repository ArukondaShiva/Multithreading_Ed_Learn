package org.bonusproblems.printingnumberseries;

import java.util.concurrent.Semaphore;

public class PrintNumberSeries {

    private int n;
    private Semaphore zeroSem, oddSem, evenSem;


    public PrintNumberSeries(int n){
        this.n = n;
        zeroSem = new Semaphore(1);
        oddSem = new Semaphore(0);
        evenSem = new Semaphore(0);
        //For oddSem and evenSem, all acquire() calls will be blocked initially as they are initialized with 0
        //For zeroSem, the first acquire() call will succeed as it is initialized with 1
    }

    public void printZero() throws InterruptedException {

        for(int i=0;i<n;i++){
            zeroSem.acquire();
            System.out.print("0");

            // release oddSem if 'i' is even else release evenSem if 'i' is odd.
            if ((i % 2 == 0)) {
                oddSem.release();
            } else {
                evenSem.release();
            }
        }

    }


    public void printOdd() throws InterruptedException {

        for(int i=1;i<=n;i+=2){
            oddSem.acquire();
            System.out.print(i);
            zeroSem.release();
        }

    }


    public void printEven() throws InterruptedException {

        for(int i=2;i<=n;i+=2){
            evenSem.acquire();
            System.out.print(i);
            zeroSem.release();
        }

    }


}
