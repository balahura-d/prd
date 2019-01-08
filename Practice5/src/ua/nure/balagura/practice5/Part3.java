package ua.nure.balagura.practice5;

public class Part3 {

	static Counter counter;
	static Thread t1;
	static Thread t2;

	public static void main(String[] args) throws InterruptedException {
		// not synch
		counter = new Part3().new Counter();
		t1 = new Part3().new MyThread();
		t2 = new Part3().new MyThread();
		
		t1.start();
		t2.start();
		Thread.sleep(100);
		t1.interrupt();
		t2.interrupt();
		// with synch
		System.out.println("With Synch!");
		counter = new Part3().new Counter();
		t1 = new Part3().new MySynchThread();
		t2 = new Part3().new MySynchThread();
		
		t1.start();
		t2.start();
		Thread.sleep(100);
		t1.interrupt();
		t2.interrupt();
		if (t1.isAlive()) t1.join();
		if (t2.isAlive()) t2.join();
	}

	class MyThread extends Thread {
		public void run() {
			while (!(Thread.currentThread().isInterrupted())) {
				System.out.println(counter.counter1 == counter.counter2);
				counter.counter1++;
				try {
					sleep(10);
				} catch (InterruptedException e) {
					return;
				}
				counter.counter2++;
			}
		}
	}
	class MySynchThread extends Thread {
		public void run() {
			while (!(Thread.currentThread().isInterrupted())) {
				synchronized (counter) {
					System.out.println(counter.counter1 == counter.counter2);
					counter.counter1++;
					try {
						sleep(10);
					} catch (InterruptedException e) {
						counter.counter2++;
						return;
					}
					counter.counter2++;
				}
			}
		}
	}

	class Counter {
		volatile int counter1 = 0;
		volatile int counter2 = 0;
	}
}
