package ua.nure.balagura.practice6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WordContainer {

	private List<Word> listWord = new ArrayList<Word>();

	public static void main(String[] args) {
		
		WordContainer wc = new WordContainer();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			boolean stoped = false;
			while (!stoped) {
				String readedLine = br.readLine();
				String[] words = readedLine.split("(\\s)+");

				for (String word : words) {
					if ("stop".equals(word)) {
						stoped = true;
						break;
					}
					wc.add(word);
				}
			}
		} catch (Exception e) {
		}

		wc.sort(Word::compareTo);

		System.out.println(wc);
	}

	public void sort(Comparator<Word> c) {
		listWord.sort(c);
	}

	public void add(String string) {
		for (Word word : listWord) {
			if (string.equals(word.content)) {
				word.frequency++;
				return;
			}
		}
		listWord.add(new Word(string, 1));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Word word : listWord) {
			sb.append(word.content).append(" : ").append(word.frequency).append(System.lineSeparator());
		}
		return sb.toString();
	}
	
	
}
