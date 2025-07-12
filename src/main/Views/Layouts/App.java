package main.Views.Layouts;

import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args){
        Window testWindow = new Window( 200, 300 );
        Button testButton = new Button(50, 20);
        
        testWindow.setInstance( );

        testButton.setContent("si");
        testButton.setFather(testWindow);
        testButton.setInstance(  );

        testWindow.pushChildren(testButton.getComponent());

        testWindow.getDetails( );
        testButton.getDetails( );
    
    }

} 