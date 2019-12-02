package com.fengliguantou.miaoshaproject.mq;

public class LambadaDemo {
    public static void main(String[] args) {
        say((n, m) -> n + m);
        say(String::concat);
    }
    public static void say(myInterface myInterface){
        System.out.println(myInterface.sayHello("三少", "asd"));;
    }
    @FunctionalInterface
    public interface myInterface{
        String sayHello(String name, String message);
    }
}
