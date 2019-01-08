package ua.nure.balagura.practice5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Spam {

	static String[] messages = new String[] { "first", "second" };
	static int[] times = new int[] { 333, 222 };
	static List<Thread> threadList = new ArrayList<Thread>();

	public static void main(String[] args) throws IOException {
		startSpam();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		stopSpam();
	}

	private static void stopSpam() {
		for (Thread thread : threadList) {
			thread.interrupt();
		}		
	}

	private static void startSpam() {
		for (int i = 0; i < messages.length; i++) {
			Thread t = new Thread(new Spammer(messages[i], times[i]));
			threadList.add(t);
			t.start();
		}		
	}

	static private class Spammer implements Runnable {
		String msg;
		int pause;

		public Spammer(String msg, int pause) {
			this.msg = msg;
			this.pause = pause;
		}

		@Override
		public void run() {
			while (!(Thread.currentThread().isInterrupted())) {
				try {
					System.out.println(msg);
					Thread.sleep(pause);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	public Spam(String[] messages, int[] times) {
		if (messages.length != times.length) {
			throw new IllegalArgumentException("Arrays not agreed!~");
		}
		this.messages = messages;
		this.times = times;
	}
}
