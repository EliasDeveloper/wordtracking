package controller;

import model.dao.daodefinition.IWordD;
import model.dao.daoimplementation.WordDaoImp;
import model.dto.Word;
import view.*;
import view.parent.View;

import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class MainOptionController {

    private MainOptionsView mainOptionsView;
    private ShowWordsFromDatabaseView showWordsFromDatabaseView;
    private InsertingWordFromUserView insertingWordFromUserView;
    private DeletingWordView deletingWordView;
    private UpdatingWordView updatingWordView;
    private String systemargs[];

    public MainOptionController(String... systemargs){
        this.systemargs = systemargs;
    }

    public void init(){
        loadviews();
        showMainOptionView();
    }

    private void showMainOptionView(){
        this.mainOptionsView.addObserver((observable, o) -> {
            String election = (String) o;
            resolveMainOptionResult(election);
        });
        this.mainOptionsView.init(); 
    }

    private void showListWordFromDatabaseView(){
        List<Word> WordListFromDatabase = getWordsFromDatabase();
        this.showWordsFromDatabaseView.addObserver((observable, obj) -> {
            String exitSucces = (String)obj;
            resolveShowWordsListViewResult(exitSucces);
        });
        this.showWordsFromDatabaseView.setWordListFromDatabase(WordListFromDatabase);
        this.showWordsFromDatabaseView.init();
    }

    private void showInsertingFromDatabaseView(){
        this.insertingWordFromUserView.addObserver((observable, o) -> {
            List<Word> WordCreated = (List<Word>)o;
            resolveInsertingWordCreatedView(WordCreated);
        });
        this.insertingWordFromUserView.init();
    }

    private void showDeletingWordView(){
        this.deletingWordView.addObserver((observable, object) -> {
                resolverDeletingWordResult(object);
        });
        this.deletingWordView.init();
    }

    private void showUpdateWordView(){
        this.updatingWordView.addObserver((observable, object) -> {
                resolveUpdatingWordResult(object);
        });
        this.updatingWordView.init();
    }

    private void loadviews(){
        this.showWordsFromDatabaseView = new ShowWordsFromDatabaseView();
        this.mainOptionsView = new MainOptionsView();
        this.insertingWordFromUserView = new InsertingWordFromUserView();
        this.updatingWordView = new UpdatingWordView();
        this.deletingWordView = new DeletingWordView();
    }

    private void resolveMainOptionResult(String result) {
        if(Objects.isNull(result))
            throw new NullPointerException("There was an Exception, The mainresultOption coming from the main option cannon be null.");
        selectedElectionInput(result);
    }

    private  void resolveShowWordsListViewResult(String result){
        this.mainOptionsView.init();
    }

    private void resolveInsertingWordCreatedView(List<Word> words){
        if(Objects.isNull(words))
            throw new NullPointerException("Error on adding the words, The words cannot be null. ");
        IWordD wordtreatment = new WordDaoImp();
        boolean succes = false;
        for(Word onlyWord : words){
            succes = wordtreatment.addnewWord(onlyWord);
        }
        showViewResulting(succes, "Se han añadido todas las palabras correctamente. ", "No se han podido añadir las palabras. ");
        this.mainOptionsView.init();
    }

    private void resolveUpdatingWordResult(Object resulting){
            if(Objects.isNull(resulting)) throw new NullPointerException("The updating word cannot be null. ");
            Word tobeupdated = (Word) resulting;
            if(tobeupdated.getEnglishWord().equalsIgnoreCase("no")) mainOptionsView.init();
            IWordD iWordD = new WordDaoImp();
            showViewResulting(iWordD.updateWord(tobeupdated), "Se ha Actualiza la palabra correctamente. ", "No se han podido actualizar la palabra.");
            mainOptionsView.init();
    }
    private void resolverDeletingWordResult(Object resulting){
        if(Objects.isNull(resulting)) throw new NullPointerException("The deleting word cannot be null. ");
        String resultingData = (String) resulting;
        if(resultingData.equalsIgnoreCase("cancel")) mainOptionsView.init();
        IWordD iWordD = new WordDaoImp();
        showViewResulting(iWordD.deleteWord(Integer.parseInt(resultingData)), "Se ha eliminado correctamente. ", "No se ha podido eliminar la palabra. ");
        mainOptionsView.init();
    }


    private void selectedElectionInput(String numbers){
        int numberelection = Integer.parseInt(numbers);
        switch (numberelection){
            case 1:
                showListWordFromDatabaseView();
                break;
            case 2:
                showInsertingFromDatabaseView();
                break;
            case 3:
                showUpdateWordView();
                break;
            case 4:
                showDeletingWordView();
                break;
            case 5:
                System.exit(0);
        }
    }


    private void showViewResulting(boolean bool, String Onsucces, String Onfailure){
        try {
            if (bool) {
                //we need to be consecuent with the view architecturre.
             new View() {
                    @Override
                    public void init() {
                        System.out.println(Onsucces);
                    }
                }.init();
                Thread.sleep(1000);
            }
           new View() {
               @Override
               public void init() {
                   System.out.println(Onfailure);
               }
           }.init();
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private List<Word> getWordsFromDatabase(){
        return new WordDaoImp().getAllWords();
    }

}
