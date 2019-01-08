package ua.nure.balagura.practice5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Part4  {

	static int[][] matrix;
	
	final static String FILE_NAME = "part4.txt";
	
	public static void main(String[] args) {
		
		try {
			matrix = Part4Service.readMatrix(FILE_NAME);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long parallelStart = System.currentTimeMillis();		
		int max = parallelFindMaxInMatrix();
		System.out.println(max);
		System.out.println(System.currentTimeMillis() - parallelStart);
		
		long serialStart = System.currentTimeMillis();		
		max = serialFindMaxInMatrix();
		System.out.println(max);
		System.out.println(System.currentTimeMillis() - serialStart);
		
	}
	
	public static int serialFindMaxInMatrix() {
		int result = Integer.MIN_VALUE;
		for (int[] is : matrix) {
			int max = findMaxInRow(is);
			pause();
			if (max > result) 
				result = max;
		}
		return result;
	}

	public static int parallelFindMaxInMatrix() {
		int result = Integer.MIN_VALUE;
		int max = Integer.MIN_VALUE;
		ExecutorService threadPool = Executors.newFixedThreadPool(matrix.length);
		List<Future<Integer>> resultList = new ArrayList<Future<Integer>>();
		
		for (int i = 0; i < matrix.length; i++) {
			Callable<Integer> callable = new MyCallable(i);
			Future<Integer> future = threadPool.submit(callable);
			resultList.add(future);
		}
		for (Future<Integer> future : resultList) {
			try {
				max = future.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			if (result < max)
				result = max;
		}
		threadPool.shutdown();
		return result;
	}
	
	public static class MyCallable implements Callable<Integer> {
		int rowNumber;
		public MyCallable(int i) {
			rowNumber = i;
		}
		@Override
		public Integer call() throws Exception {
			return findMaxInRow(matrix[rowNumber]);
		}
	}


	private static int findMaxInRow(int[] is) {
		int result = Integer.MIN_VALUE;
		for (int i : is) {
			pause();
			if (result < i)
				result = i;
		}
		return result;
	}
	
	private static void pause() {
		try {
			Thread.currentThread().sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
}
