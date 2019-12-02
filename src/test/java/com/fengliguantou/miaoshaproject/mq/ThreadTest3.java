package com.fengliguantou.miaoshaproject.mq;

public class ThreadTest3 {
    public static void main(String[] args) {
        Counter counter = new Counter();
        new Thread(new PrintOdd(counter)).start();
        new Thread(new PrintEven(counter)).start();
    }
}
class Counter {
    public int value = 1;
    public boolean odd = true;
}

class PrintOdd implements Runnable {
    public Counter counter;
    public PrintOdd(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (counter.value <= 100) {
            synchronized(counter) {
                if (counter.odd) {
                    System.out.println(counter.value);
                    counter.value++;
                    counter.odd = !counter.odd;
                    //很重要，要去唤醒打印偶数的线程
                    counter.notify();
                }
                try {
                    counter.wait();
                } catch (InterruptedException e) {}
            }
        }
    }
}
class PrintEven implements Runnable {
    public Counter counter;
    public PrintEven(Counter counter) {
        this.counter = counter;
    }
    @Override
    public void run() {
        while (counter.value <= 100) {
            synchronized (counter) {
                if (!counter.odd) {
                    System.out.println(counter.value);
                    counter.value++;
                    counter.odd = !counter.odd;
                    counter.notify();
                }
                try {
                    counter.wait();
                } catch (InterruptedException e) {}
            }
        }
    }
}
