package org.example.nonblockingstack.cassolution;

public class CASBasedStack<T>{

    private SimulatedCompareAndSwap<StackNode<T>> simulatedCAS;

    public CASBasedStack(){
        simulatedCAS = new SimulatedCompareAndSwap<>(null);
    }


    public void push(T item){

        StackNode<T> oldHead;
        StackNode<T> newHead;

        do{
            // retrieve the current value of top
            oldHead = simulatedCAS.getValue();

            // create a new StackNode for the passed-in item
            newHead = new StackNode<>(item);

            // Adjust the pointer
            newHead.setNext(oldHead);

        }while(!simulatedCAS.compareAndSet(oldHead,newHead));

    }



    public T pop(){

        StackNode<T> returnValue;
        StackNode<T> newHead;

        do{
            // get the current top of the stack
            returnValue = simulatedCAS.getValue();

            // if the top is null then simply return the null
            if(returnValue == null){
                return  null;
            }

            // compute the new top of stack
            newHead = returnValue.getNext();
        }while (!simulatedCAS.compareAndSet(returnValue,newHead));
        // attempt to update the new top of stack

        return returnValue.getItem();
    }



}
