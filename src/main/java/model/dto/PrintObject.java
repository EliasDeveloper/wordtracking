package model.dto;

public class PrintObject {

	 private int IdInicial;
	 private int IdFinal;
	 private String location;
	 private String filename;

	 public int getIdInicial() {
		  return IdInicial;
	 }

	 public void setIdInicial(int idInicial) {
		  IdInicial = idInicial;
	 }

	 public int getIdFinal() {
		  return IdFinal;
	 }

	 public void setIdFinal(int idFinal) {
		  IdFinal = idFinal;
	 }

	 public String getLocation() {
		  return location;
	 }

	 public void setLocation(String location) {
		  this.location = location;
	 }

	 public String getFilename() {
		  return filename;
	 }

	 public void setFilename(String filename) {
		  this.filename = filename;
	 }
}
