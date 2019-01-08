package ua.nure.balagura.practice6;

public class Word implements Comparable<Word> {
	String content;
	int frequency;

	public Word(String content, int frequency) {
		this.content = content;
		this.frequency = frequency;
	}
	
	@Override
	public int compareTo(Word o) {
		if (this.frequency == o.frequency) {
			return this.content.compareTo(o.content);
		} else {
			return o.frequency - this.frequency;
		}
	}
}
