package org.bonusproblems.printingfoobarntimes;

public class FooBar {

    private int n;
    private int flag = 0;


    public FooBar(int n){
        this.n = n;
    }


    public void foo() throws InterruptedException {

        for(int i=1;i<=n;i++){
            synchronized (this){
                while (flag==1){
                    this.wait();
                }
                System.out.println("Foo");
                flag = 1;
                this.notifyAll();
            }
        }

    }

    public void bar() throws InterruptedException {

        for(int i=1;i<=n;i++){
            synchronized (this){
                while (flag==0){
                    this.wait();
                }
                System.out.println("Bar");
                flag = 0;
                this.notifyAll();
            }
        }

    }


}
