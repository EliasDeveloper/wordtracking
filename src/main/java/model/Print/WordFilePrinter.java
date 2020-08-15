package model.Print;

import model.dto.PrintObject;
import model.dto.Word;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

public class WordFilePrinter extends Observable {
	 private List<Word> wordlist = null;
	 private PrintObject pobj;

	public WordFilePrinter(List<Word> wordlist, PrintObject location){
		 if(Objects.isNull(wordlist)) throw new NullPointerException("The word list cannot be null. ");
		 this.wordlist = wordlist;
		 this.pobj = location;
	}

	public WordFilePrinter(PrintObject location){
		 this.pobj = location;
	}

	public void startPrinting()  {
		 try {
			  Writer writer;
			  writer = new BufferedWriter(new FileWriter(new File(getLocation(pobj.getLocation()))));
			  writer.write("#startid at "+ pobj.getIdInicial() + "\n#endid at "+ pobj.getIdFinal() + "\n");
			  writer.write("\t\tEnglish to spanish vocabulary  \n");
			  int  counter = 1;
			  for (Word word : wordlist) {
				   writer.write("("+ counter + ")=" + word.getEnglishWord() + "=" + word.getSpanishWord() + "\n");
				   counter = counter + 1;
			  }
			  writer.close();
			  setChanged();
			  notifyObservers(wordlist.size());
		 }catch (IOException error){
			  System.out.println(error.getMessage());
		 }
	}

	private String getLocation(String command){
		 switch (command){
			  case "desktop":
			  	 return getPropertywithloc() + "Desktop" + "\\" +pobj.getFilename() ;
			  case "document":
			  	 return  getPropertywithloc() + "Documents" + "\\"+ pobj.getFilename();
			  	 default:
			  	 	 return command;
		 }
	}
	private String getPropertywithloc(){
		 return System.getProperty("user.home") + "\\";
	}
	public void setWordlist(List<Word> wordlist){
		 if(Objects.isNull(wordlist)) throw  new NullPointerException("the word list cannot be null");
		 this.wordlist = wordlist;
	}


}
