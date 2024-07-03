package org.example.nonblockingstack.atomicrefsolution;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class NonblockingStack<T>{

    private AtomicInteger count = new AtomicInteger(0);
    private AtomicReference<StackNode<T>> top = new AtomicReference<>();


    public int size(){
        return count.get();
    }


    /*
    The methods push() and pop() as a whole aren’t thread-safe, only the loops that update
     the top of the stack using compare-and-swap instructions
     */

    public void push(T newItem){

        StackNode<T> oldTop;
        StackNode<T> newTop;

        do{
            oldTop = top.get();
            newTop = new StackNode<>(newItem);
            newTop.setNext(oldTop);
        }while (!top.compareAndSet(oldTop,newTop));

        count.incrementAndGet();
    }


    public T pop(){

        StackNode<T> oldTop;
        StackNode<T> newTop;

        do{
            oldTop = top.get();

            if(oldTop==null){
                return null;
            }

            newTop = oldTop.getNext();
        }while (!top.compareAndSet(oldTop,newTop));

        count.decrementAndGet();
        return oldTop.getItem();
    }


}
