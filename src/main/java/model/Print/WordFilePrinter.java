package model.Print;

import model.dto.Word;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

public class WordFilePrinter extends Observable {
	 private List<Word> wordlist = null;
	 private String location;


	public WordFilePrinter(List<Word> wordlist, String location){
		 if(Objects.isNull(wordlist)) throw new NullPointerException("The word list cannot be null. ");
		 this.wordlist = wordlist;
		 this.location = location;
	}

	public WordFilePrinter(String location){
		 this.location = location;
	}

	public void startPrinting() throws IOException {
		 Writer FileWriter = null;
		 FileWriter = new BufferedWriter(new FileWriter(new File(this.location)));
		 printingfileloop(FileWriter);
	}


	private void printingfileloop(Writer writer) throws IOException{
		 writer.write("English to spanish dictionary \n");
		 for(Word word: wordlist){
		 	 writer.write(word.getEnglishWord() + "=" + word.getSpanishWord() + "\n");
		 }
		 writer.close();
		 setChanged();
		 notifyObservers(wordlist.size());
	}


	public void setWordlist(List<Word> wordlist){
		 if(Objects.isNull(wordlist)) throw  new NullPointerException("the word list cannot be null");
		 this.wordlist = wordlist;
	}


}
