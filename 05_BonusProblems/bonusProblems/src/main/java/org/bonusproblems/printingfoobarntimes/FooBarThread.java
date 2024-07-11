package org.bonusproblems.printingfoobarntimes;

public class FooBarThread extends Thread{

    FooBar fooBar;
    String method;

    public FooBarThread(FooBar fooBar,String method){
        this.fooBar = fooBar;
        this.method = method;
    }

    public void run(){
        if("foo".equals(method)){
            try {
                fooBar.foo();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else if("bar".equals(method)){
            try {
                fooBar.bar();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
