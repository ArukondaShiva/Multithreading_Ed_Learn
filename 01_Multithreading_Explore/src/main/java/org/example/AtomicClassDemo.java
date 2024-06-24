package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicClassDemo {

    public static void example(){

        AtomicInteger atomicFive = new AtomicInteger(5);
        AtomicInteger atomicAlsoFive = new AtomicInteger(5);

        System.out.println("atomicFive.equals(atomicAlsoFive) : "+atomicFive.equals(atomicAlsoFive));
        System.out.println("atomicFive.hashCode()==atomicAlsoFive.hashCode() : "+ (atomicFive.hashCode()==atomicAlsoFive.hashCode()));

        Integer integer1 = new Integer(23235);
        Integer integer2 = new Integer(23235);

        System.out.println("integer1.equals(integer2) : "+integer1.equals(integer2));
        System.out.println("integer1.hashCode()==integer2.hashCode() : "+(integer1.hashCode() == integer2.hashCode()));

    }

}
