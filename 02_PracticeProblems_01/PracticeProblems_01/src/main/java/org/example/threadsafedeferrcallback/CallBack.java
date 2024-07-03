package org.example.threadsafedeferrcallback;

public class CallBack {

    long executeAt;
    String message;

    public CallBack(long executeAfter, String message){
        this.executeAt = System.currentTimeMillis()+(executeAfter*1000);
        this.message = message;
    }
}
