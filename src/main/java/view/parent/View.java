package view.parent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Scanner;

public abstract class View extends Observable {

    protected String getInputData(){
        return new Scanner(System.in).next();
    }

    protected void CleanInputView(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void init();

}
