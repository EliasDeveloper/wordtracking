package model.dto;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Word {
    private int IdWord;
    private String SpanishWord;
    private String EnglishWord;
    private LocalDate Date;
    private LocalDate Last_Update;

    public int getIdWord() {
        return IdWord;
    }

    public void setIdWord(int idWord) {
        IdWord = idWord;
    }

    public String getSpanishWord() {
        return SpanishWord;
    }

    public void setSpanishWord(String spanishWord) {
        SpanishWord = spanishWord;
    }

    public String getEnglishWord() {
        return EnglishWord;
    }

    public void setEnglishWord(String englishWord) {
        EnglishWord = englishWord;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }


    public LocalDate getLast_Update() {
        return Last_Update;
    }

    public void setLast_Update(LocalDate last_Update) {
        Last_Update = last_Update;
    }

    public String toString(){
        return EnglishWord + " = "+ SpanishWord + " # "+ Date.format(DateTimeFormatter.ofPattern("yyy-MM-dd"));
    }


}
