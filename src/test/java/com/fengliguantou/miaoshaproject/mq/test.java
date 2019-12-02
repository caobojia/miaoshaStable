package com.fengliguantou.miaoshaproject.mq;

public class  test implements Runnable {
	private int i = 1;

	public void run() {
		while (true) {
			synchronized (this) {
				notify();
				if (i <= 100) {
					System.out.println(Thread.currentThread().getName() + ":" + i);
					i++;
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) {

		test test1 = new test();
		new Thread(test1, "新线程1").start();
		new Thread(test1, "新线程2").start();

	}
}