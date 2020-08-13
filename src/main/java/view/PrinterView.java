package view;

import model.dto.PrintObject;
import sun.security.krb5.PrincipalName;
import view.parent.View;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringTokenizer;

public class PrinterView  extends View {
	 @Override
	 public void init() {
		showheader();
		PrintObject printObject = initloop();
		setChanged();
		if(Objects.isNull(printObject)){
			 printObject = new PrintObject();
			 printObject.setIdInicial(-1);
			notifyObservers(printObject);
		}else{
			 notifyObservers(printObject);
		}
	 }

	 private void showheader(){
		  System.out.println("type the initial id and the final id to write on the file, following format IDInitial=IdFinal#ubicacion");
	 }

	 private PrintObject initloop(){
	 	 PrintObject printObject = null;
	 	 while(true){
			  String datauser = getInputData();
			  if(datauser.equalsIgnoreCase("stop")) break;
			   printObject = getObjectFromUser(datauser);
			  if(!Objects.isNull(printObject)) break;
			  System.out.println("there was an error pleas, write the format correctly. :)");
		 }
		return printObject;
	 }

	 private PrintObject getObjectFromUser(String userInput){
	 	 try{
			  StringTokenizer stringTokenizerAll = new StringTokenizer(userInput, "=");
			  StringTokenizer stringTokenizerleft = new StringTokenizer(stringTokenizerAll.nextToken(), "#");
			  String Ubicacion = stringTokenizerAll.nextToken();
			  int idINicial = Integer.parseInt(stringTokenizerleft.nextToken());
			  int idFinal = Integer.parseInt(stringTokenizerleft.nextToken());
			  PrintObject printObject = new PrintObject();
			  printObject.setLocation(Ubicacion);
			  printObject.setIdInicial(idINicial);
			  printObject.setIdFinal(idFinal);
			  return printObject;
		 }catch(NumberFormatException | NoSuchElementException e){
	 	 	 //if an excepcion we will know it.
	 	 	 return null;
		 }
	 }


}
