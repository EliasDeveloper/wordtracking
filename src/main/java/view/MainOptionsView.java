package view;
import Global.SystemInfo;
import view.parent.View;

public class MainOptionsView extends View {

    private int MinOption = 0;
    private int MaxOption = 5;
    public void init(){
        CleanInputView();
        String data = "";
        try{
            data = LoadInputData();
        }catch (NumberFormatException exception){
            init();
        }
        CleanInputView();
        setChanged();
        notifyObservers(data);
    }
    private String LoadInputData() throws NumberFormatException{
        int RoutineOptionData = 0;
        String OptionData;
        do {
            showHeader();
            showOptions();
            OptionData = getInputData();
            RoutineOptionData = Integer.parseInt(OptionData);
        }while((RoutineOptionData <= MinOption ) || (RoutineOptionData > MaxOption));
        return OptionData;
    }

    private void showHeader(){
        StringBuilder header = new StringBuilder();
        header.append("Bienvenido A WordTracker " + SystemInfo.SystemVersion );
        System.out.println(header.toString());
    }
    private void  showOptions(){
        StringBuilder MyoptionsStrings = new StringBuilder();
        MyoptionsStrings.append(" 1) Mostrar las palabras de la base de datos. ");
        MyoptionsStrings.append("\n");
        MyoptionsStrings.append(" 2) Agregar una nueva palabra a la base de datos.");
        MyoptionsStrings.append("\n");
        MyoptionsStrings.append("3) Actualizar Una palabra de la base de datos. ");
        MyoptionsStrings.append("\n");
        MyoptionsStrings.append("4) Eliminar una palabra de la base de datos.");
        MyoptionsStrings.append("\n");
        MyoptionsStrings.append("5) Salir");
        MyoptionsStrings.append("\n");
        System.out.println(MyoptionsStrings.toString());
    }
}
