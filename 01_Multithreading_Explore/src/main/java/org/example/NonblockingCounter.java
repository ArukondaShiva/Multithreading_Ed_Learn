package org.example;

public class NonblockingCounter {

    private SimulatedCAS count = new SimulatedCAS(0);

    public long get(){
        return count.getValue();
    }

    public void increment(){

        long currentCount;

        do{
            currentCount = count.getValue();
        }while (currentCount!=count.compareAndSwap(currentCount,currentCount+1));
    }

}
