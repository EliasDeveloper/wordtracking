package model.dao.daoimplementation;

import model.connection.MysqlConnection;
import model.dao.daodefinition.IWordD;
import model.dto.Word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WordDaoImp implements IWordD {

    public WordDaoImp(){}

    @Override
    public boolean addnewWord(Word word) {

        if(Objects.isNull(word))
            throw new NullPointerException("The word to be added shouldn't be null, at " +
                    new Exception().getStackTrace()[0].getMethodName()+"();");

        try(Connection conn = MysqlConnection.getInstance().getConnectionAcces()){
            String sql = "insert into words(spanishword, englishword, date_word) value (?, ?, ?);";
            PreparedStatement preparedStatement =  conn.prepareStatement(sql);
            preparedStatement.setString(1, word.getSpanishWord());
            preparedStatement.setString(2, word.getEnglishWord());
            preparedStatement.setObject(3, word.getDate());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace()[0].getMethodName());
            System.out.println(e.getStackTrace()[0].getLineNumber());
        }
        return false;
    }

    @Override
    public boolean updateWord(Word wordtobeupdated) {

        if(Objects.isNull(wordtobeupdated))
            throw new NullPointerException("The word to be added shouldn't be null, at " + new Exception().getStackTrace()[0].getMethodName()+"();");

        try(Connection conn = MysqlConnection.getInstance().getConnectionAcces()){
            String sql = "update words set spanishword = ?, englishword = ?, last_update = ? where idword = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, wordtobeupdated.getSpanishWord());
            preparedStatement.setString(2, wordtobeupdated.getEnglishWord());
            preparedStatement.setObject(3, wordtobeupdated.getLast_Update());
            preparedStatement.setInt(4, wordtobeupdated.getIdWord());
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace()[0].getMethodName());
            System.out.println(e.getStackTrace()[0].getLineNumber());
        }
        return false;
    }

    @Override
    public boolean deleteWord(int idword) {
        if(!(idword>0)) throw new IllegalArgumentException("the argument cannot be a negative number. ");
        try(Connection conn = MysqlConnection.getInstance().getConnectionAcces()){
            String sql = "delete from words where idword = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, idword);
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace()[0].getMethodName());
            System.out.println(e.getStackTrace()[0].getLineNumber());
        }
        return false;
    }

    @Override
    public List<Word> getAllWords() {
        List<Word> words = new ArrayList<>();
        try(Connection conn = MysqlConnection.getInstance().getConnectionAcces()){
                String sql = "select * from words limit 1000; ";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                words = LoadWordList(preparedStatement.executeQuery());
                return words;
            }catch (SQLException e){
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace()[0].getMethodName());
                System.out.println(e.getStackTrace()[0].getLineNumber());
            }
        return words;
    }

    @Override
    public List<Word> getAllWord(int primero, int segundo) {

        List<Word> worlist = new ArrayList<>();

        try(Connection conn = MysqlConnection.getInstance().getConnectionAcces()){
            String sql = "select * from words limit ?, ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, primero);
            preparedStatement.setInt(2, segundo);
            return LoadWordList(preparedStatement.executeQuery());
        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace()[0].getMethodName());
            System.out.println(e.getStackTrace()[0].getLineNumber());
        }

        return worlist;
    }


    private List<Word> LoadWordList(ResultSet resultSet) throws SQLException {
        if(Objects.isNull(resultSet)) throw new NullPointerException("Resulset cannot be null. " );
        List<Word> Wordlist = new ArrayList<>();
        while(resultSet.next()){
            Word word = new Word();
            word.setIdWord(resultSet.getInt("idword"));
            word.setSpanishWord(resultSet.getString("spanishword"));
            word.setEnglishWord(resultSet.getString("englishword"));
            word.setDate(resultSet.getObject("date_word", LocalDate.class));
            Wordlist.add(word);
        }
        return Wordlist;
    }
}
