package model.dao.daodefinition;

import model.dto.Word;

import java.util.List;

public interface IWordD {

    boolean addnewWord(Word word);
    boolean updateWord(Word wordtobeupdated);
    boolean deleteWord(int idword);
    List<Word> getAllWords();
    List<Word> getAllWord(int primero, int segundo );


}
