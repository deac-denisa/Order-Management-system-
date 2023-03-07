package start;

import presentation.Controller;
import presentation.StartGUI;

public class Main {

    public static  void main(String[] args){
        StartGUI startGUI = new StartGUI();
        startGUI.setVisible(true);

        new Controller(startGUI);

    }
}
