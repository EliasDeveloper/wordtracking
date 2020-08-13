package view;

import model.Util.TimeUtil;
import model.dto.Word;
import view.parent.View;

import java.util.Objects;
import java.util.StringTokenizer;

public class UpdatingWordView extends View {
	 @Override
	 public void init() {
		 CleanInputView();
		 showheader();
		 Word updatingword = initloop();
		 if(Objects.isNull(updatingword)){
		 	 updatingword = new Word();
		 	 updatingword.setEnglishWord("no");
		 }
		 setChanged();
		 notifyObservers(updatingword);
	 }
	 private void showheader(){
		  System.out.println("type the following format to update the word in the database, EnglishWord=SpanishWord=ID, type cancel to quit from this view.");
	 }
	 private Word initloop(){
	 	 Word theword =  null;
	 	 showheader();
	 	 while (true){
	 	 	 String input = getInputData();
	 	 	 if(input.equalsIgnoreCase("cancel")) break;
	 	 	 theword = gettingwordwithvalidation(input);
	 	 	 if(Objects.isNull(theword))
				  System.out.println("There was an error, type the input in the given format to be accepted. ;)");
			 else
			 	 break;
		 }
		 return theword;
	 }

	 private Word getWordFromToken(String token) throws NumberFormatException{
	 	 StringTokenizer stringTokenizer = new StringTokenizer(token, "=");
	 	 String englishword = stringTokenizer.nextToken();
	 	 String spanishWord = stringTokenizer.nextToken();
	 	 int id = isdigit(stringTokenizer.nextToken());
	 	 Word updatedWord = new Word();
	 	 updatedWord.setEnglishWord(englishword);
	 	 updatedWord.setSpanishWord(spanishWord);
	 	 updatedWord.setIdWord(id);
	 	 updatedWord.setLast_Update(TimeUtil.getCurrentTime());
	 	 return updatedWord;
	 }

	 //if it is another type... Automatically it is going to be cast.
	 private int isdigit(String str)throws NumberFormatException{
		  int digit = Integer.parseInt(str);
		  if(digit>0)
		  	 return digit;
		  else
		  	 throw new NumberFormatException("The number cannot be negative ");
	 }

	 private Word gettingwordwithvalidation(String input){
		  try{
			   return getWordFromToken(input);
		  }catch (NumberFormatException e){
			   return  null;
		  }
	 }


}
