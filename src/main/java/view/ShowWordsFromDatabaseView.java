package view;

import model.dto.Word;
import view.parent.View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class ShowWordsFromDatabaseView extends View {

    private List<Word> WordListFromDatabase;

    public ShowWordsFromDatabaseView(List<Word> WordListFromDatabase){
        if(Objects.isNull(WordListFromDatabase))
            throw new NullPointerException("The List coming from database can't be null. ");
        this.WordListFromDatabase = WordListFromDatabase;
    }

    public ShowWordsFromDatabaseView(){

    }


    @Override
    public void init() {
        CleanInputView();
        printWordList();
        loadUserInput();
        CleanInputView();
    }

    private void loadUserInput(){
        String UserExit;
        do{
            System.out.println("type EXIT to leave. \n");
            UserExit = getInputData();
        }while(!UserExit.equalsIgnoreCase("exit"));
        setChanged();
        notifyObservers(UserExit);
    }

    private void printWordList(){
        for (Word WordIteration: this.WordListFromDatabase) {
            StringBuilder WordlistLine = new StringBuilder();
            WordlistLine.append("ID: ").append(WordIteration.getIdWord()).append("  ")
                    .append("EnglishWord : ").append(WordIteration.getEnglishWord())
                    .append(" SpanishWord: ").append(WordIteration.getSpanishWord())
                    .append(" DateWord: ").append(DateFormatting(WordIteration.getDate())).append("\n");
            System.out.println(WordlistLine.toString());
        }
    }

    private String DateFormatting(LocalDate DateWord){
        if(Objects.isNull(DateWord))
            throw new NullPointerException("DateFormatting can be done with a null LocalDate.");
      return  DateWord.format(DateTimeFormatter.ofPattern("yyy-MM-dd"));
    }

    public void setWordListFromDatabase(List<Word> words){
        this.WordListFromDatabase = words;
    }
}
