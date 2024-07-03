package org.example.nonblockingstack.synchronizedsolution;

public class SynchronizedStack<T>{

    StackNode<T> top;

    public synchronized void push(T item){
        if(top==null){
            top = new StackNode<>(item);
        }
        else{
            StackNode<T> oldHead = top;
            top = new StackNode<>(item);
            top.setNext(oldHead);
        }
    }


    public synchronized T pop(){

        if(top == null){
            return null;
        }

        StackNode<T> oldHead = top;
        top = oldHead.getNext();
        return oldHead.getItem();
    }


}
