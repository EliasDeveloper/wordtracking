package view;

import model.Util.TimeUtil;
import model.dto.Word;
import view.parent.View;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InsertingWordFromUserView extends View {

    @Override
    public void init() {
      List<Word> wordlist = LoadInput();
        if(!Objects.isNull(wordlist))
            this.setChanged();
            this.notifyObservers(wordlist);
    }

    private List<Word> LoadInput(){
        List<Word> wordList = new ArrayList<>();
        boolean centinela = false;
        showHeader();
        while(!centinela){
            String inputdata = getInputData();
            if(inputdata.equalsIgnoreCase("stop")) {
                centinela = true;
                continue;
            }
            Word word = gettingWord(inputdata);
            if(Objects.isNull(word)){
                System.out.println("There was an error trying to parse the input you gave. Please try again following the given format.");
                continue;
            }
            wordList.add(word);
        }
        return wordList;
    }

    private void showHeader(){
        System.out.println("Format for word Englishword=SpanishWord#Date(yyy-MM-dd) or 'now' to get the current date of your computer. ");
        System.out.println("Type 'stop' to stop the Insertign and turn back to the main. ");
    }
    private Word gettingWord(String inputData){
        Word word = null;
        try{
            word = ConvertStringToWord(inputData);
        }catch (NoSuchElementException exception){
            return null;
        }
        return word;
    }

    private Word ConvertStringToWord(String WordInput) throws  NoSuchElementException{
        StringTokenizer tokenizer = new StringTokenizer(WordInput, "#");
        String TokenLeftSide = getTokenLeftSide(tokenizer);
        LocalDate TokenRightSide = getTokenRightSide(tokenizer);
        return getWord(TokenLeftSide, TokenRightSide);
    }

    private Word getWord(String lefttoken, LocalDate Date) throws NoSuchElementException{
        StringTokenizer stringTokenizer = new StringTokenizer(lefttoken, "=");
        String englishWord = stringTokenizer.nextToken();
        String spanishWord = stringTokenizer.nextToken();
        Word word = new Word();
        word.setSpanishWord(spanishWord);
        word.setEnglishWord(englishWord);
        word.setDate(Date);
        return word;
    }

    private String getTokenLeftSide(StringTokenizer stringTokenizer) throws NoSuchElementException{
        return stringTokenizer.nextToken();
    }

    private LocalDate getTokenRightSide(StringTokenizer stringTokenizer) throws NoSuchElementException {
         return ValidateDate(stringTokenizer.nextToken());
    }

    private LocalDate ValidateDate(String StringUserTokenInput){
        if(StringUserTokenInput.equalsIgnoreCase("now")){
            return TimeUtil.getCurrentTime();
        }else{
            return TimeUtil.parseTimeFromInput(StringUserTokenInput, "yyy-MM-dd");
        }
    }


}
