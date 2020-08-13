package view;

import view.parent.View;

public class DeletingWordView extends View {
	 @Override
	 public void init() {
	 	 CleanInputView();
	 	 showheader();
	 	 String input = getInputData();
	 	 while(true){
	 	 	 if(isDigit(input))
	 	 	 	 break;
	 	 	 if(input.equalsIgnoreCase("cancel"))
				break;
			  System.out.println("You typed an incorrect input. id should be a number and positive. number>0");
			  input = getInputData();
		 }
		 setChanged();
	 	 notifyObservers(input);
	 }
	 private void showheader(){
		  System.out.println("Type the ID of the word you want to delete from the database, type cancel if you want to quit from this view");
	 }
	 private boolean isDigit(String wordinput){
	 	 try{
	 	 	 int id = Integer.parseInt(wordinput);
	 	 	 return id > 0;
		 }catch (NumberFormatException exception){
	 	 	 return false;
		 }
	 }

}
