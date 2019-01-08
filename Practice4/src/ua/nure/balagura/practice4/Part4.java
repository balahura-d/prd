package ua.nure.balagura.practice4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String> {

	static final String PATH_TO_FILE = "part4.txt";
	static final String ENCODING = "Cp1251";

	static String string = ""; 

	public static void main(String[] args) {
		string = FileInOut.read(PATH_TO_FILE, ENCODING);
		
		Part4.SentenseIterator itr = new Part4().new SentenseIterator();
		
		 while (itr.hasNext()) {
			System.out.print(itr.next().replace(System.lineSeparator(), "") + System.lineSeparator());
		}
	}

	public Iterator<String> iterator() {
		return new SentenseIterator();
	}

	public class SentenseIterator implements Iterator<String> {

		Pattern p = Pattern.compile("(?Us)(\\p{Upper}+)(.*?)([\\Q.!?\\E]{1,3}?(?=\\s+))"); 
		//(UPPERCASE CHARACTERS)(any symbols)(. or ! or ? from 1 to 3 in end of sentence)(wich have \s after it)
		Matcher m = p.matcher(string);

		protected int firsrNotMatchedChar = 0;

		@Override
		public boolean hasNext() {
			if (m.find(firsrNotMatchedChar))
				return true;
			return false;
		}

		@Override
		public String next() {
			if (m.find(firsrNotMatchedChar)) {
				firsrNotMatchedChar = m.end() + 1;
				return m.group();
			}
			throw new NoSuchElementException();
		}
	}
}
