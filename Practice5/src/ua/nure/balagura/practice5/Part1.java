package ua.nure.balagura.practice5;

public class Part1 {
	public static void main(String[] args) {
		
		try {
			presentate(new Part1().new MyThread());
			presentate(new Thread(new Part1().new MyRunnable()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public class MyThread extends Thread {
		public void run() {
			printName();
		}
	}
	public class MyRunnable implements Runnable {
		public void run() {
			printName();
		}
	}
	
	private void printName() {
		while (!(Thread.currentThread().isInterrupted())) {
			try {
				Thread.sleep(500);
				System.out.println(Thread.currentThread().getName());
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
	public static void presentate(Thread thread) throws InterruptedException {
		thread.start();
		Thread.sleep(2000);
		thread.interrupt();
		thread.join();
	}
}
