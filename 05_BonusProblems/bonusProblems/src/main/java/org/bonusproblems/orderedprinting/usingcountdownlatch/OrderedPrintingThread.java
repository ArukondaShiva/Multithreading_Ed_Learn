package org.bonusproblems.orderedprinting.usingcountdownlatch;

public class OrderedPrintingThread extends Thread{

    private OrderedPrinting obj;
    private String method;


    public OrderedPrintingThread(OrderedPrinting obj,String method){
        this.method = method;
        this.obj = obj;
    }


    public void run(){

        if("first".equals(method)){
            try {
                obj.printFirst();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else if("second".equals(method)){
            try {
                obj.printSecond();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else if("third".equals(method)){
            try {
                obj.printThird();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }



}

