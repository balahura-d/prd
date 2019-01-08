package ua.nure.balagura.practice5;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Part5 implements Runnable {

	public static final int K = 10; // strings number
	public static final int N = 20; // number of chars in string

	public static final String FILE_NAME = "part5.txt";
	static File file;
	static RandomAccessFile raFile;

	private int n = 0; // number of print chars
	private int k = 0; // number of thread's string

	public static void main(String[] args) throws IOException {
		file = new File(FILE_NAME);
		if (file.exists())
			file.delete();
		raFile = new RandomAccessFile(file, "rw");

		List<Thread> tList = new ArrayList<>(K);
		for (int i = 0; i < K; i++) {
			tList.add(new Thread(new Part5(i)));
			tList.get(i).start();
		}

		for (int i = 0; i < tList.size(); i++) {
			try {
				tList.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(readRandAccFile(raFile));
	}

	private static String readRandAccFile(RandomAccessFile raFile) {
		StringBuilder sb = new StringBuilder();
		try {
			raFile.seek(0);
			String str = raFile.readLine();
			while (str != null) {
				sb.append(str).append(System.lineSeparator());
				str = raFile.readLine();
			}
		} catch (IOException e) {
		}
		return sb.toString();
	}

	Part5(int k) {
		this.k = k;
	}

	@Override
	public void run() {
		for (n = 0; n <= N; n++) {
			synchronized (raFile) {
				long pos = (N * k + n);
				pos += (System.lineSeparator().getBytes().length) * k;
				try {
					raFile.seek(pos);
					Thread.currentThread().sleep(1);
					if (n == N) {
						raFile.write(System.lineSeparator().getBytes());
					} else {
						raFile.write('0' + k);
					}
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}