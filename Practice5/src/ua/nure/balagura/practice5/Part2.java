package ua.nure.balagura.practice5;

import java.io.IOException;
import java.io.InputStream;

public class Part2 {

	public static void main(String[] args) throws InterruptedException {
		System.setIn(new Part2().new MyInputStream(2000));

		Thread t = new Thread() {
			public void run() {
				try {
					Spam.main(null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
		t.join();
		System.setIn(System.in);
	}

	class MyInputStream extends InputStream {
		long millis;
		public MyInputStream(long millis) {
			this.millis = millis;
		}
		@Override
		public int read() throws IOException {
			try {
				Thread.sleep(millis);
				return -1;
			} catch (InterruptedException e) {
				return -1;
			}
		}
	}
}
