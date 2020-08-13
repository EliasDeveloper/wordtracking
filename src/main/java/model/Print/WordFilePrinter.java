package model.Print;

import Global.SystemInfo;
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
		 FileWriter = new BufferedWriter(new FileWriter(new File(getLocation(location))));
		 printingfileloop(FileWriter);
	}

	private String getLocation(String command){
		 switch (command){
			  case "desktop":
			  	 return getPropertywithloc() + "Desktop" + File.pathSeparator + SystemInfo.DefaultNamePrinting;
			  case "document":
			  	 return  getPropertywithloc() + "Documents" + File.pathSeparator + SystemInfo.DefaultNamePrinting;
			  	 default:
			  	 	 return command;
		 }
	}
	private String getPropertywithloc(){
		 return System.getProperty("user.home") + File.pathSeparator;
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
