package org.example.nonblockingstack.cassolution;

public class SimulatedCompareAndSwap<T>{

    private T value;


    // constructor to initialize the value
    public SimulatedCompareAndSwap(T intValue){
        value = intValue;
    }

    synchronized T getValue(){
        return value;
    }


    synchronized T compareAndSwap(T expectedValue,T newValue){

        if(value==null){
            if(expectedValue==null){
                value = newValue;
            }
            return null;
        }

        if(value.equals(expectedValue)){
            value = newValue;
            return expectedValue;
        }

        return value;
    }



    // This method uses the compareAndSwap() method to indicate if the CAS
    // instruction completed successfully or not.
    synchronized boolean compareAndSet(T expectedValue,T newValue){

        T returnVal = compareAndSwap(expectedValue, newValue);

        if(returnVal == null && expectedValue==null){
            return true;
        }else if(returnVal==null && expectedValue!=null){
            return false;
        }else{
            return returnVal.equals(expectedValue);
        }

    }



}
