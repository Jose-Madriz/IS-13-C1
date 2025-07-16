package main.Views.Layouts;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class App {
    public static void main(String[] args){
        Window testWindow = new Window( 50, 50 );
        
        FlowLayout mainLayout = new FlowLayout(1, 10, 10);
        Panel mainPanel = new Panel( 100.0f, 100.0f, testWindow.size );
        mainPanel.setLayout(mainLayout);
        mainPanel.getPanel().setBorder( BorderFactory.createEmptyBorder(20,20,20,20) );
        mainPanel.getPanel().setBackground(new Color(353535));

        testWindow.setPanel( mainPanel.getPanel() );
        testWindow.setInstance();
        
        Size buttonSize = new Size( 60.0f, 6.0f, testWindow.size );
        
        Panel buttonPanel = new Panel(  30.0f, 30.0f, testWindow.size );
        FlowLayout buttonLayout = new FlowLayout(3, 30, 10);
        buttonPanel.setLayout(buttonLayout);

        buttonPanel.getPanel().setBackground(new Color(000000));

        JButton button = new JButton("Boton1");
        System.out.println( buttonSize.getDimension() );
        button.setSize( buttonSize.getDimension() );
        button.setVisible( true );
        
        JButton button2 = new JButton("Boton2");
        button2.setVisible( true );
        
        JButton button3 = new JButton("Boton3");
        button3.setVisible( true );
        
        JButton button4 = new JButton("Boton3");
        button3.setVisible( true );

        buttonPanel.getPanel().add( button );
        buttonPanel.getPanel().add( button2 );
        buttonPanel.getPanel().add( button3 );
        buttonPanel.getPanel().add( button4 );
        

        mainPanel.addChildPanel(buttonPanel, FlowLayout.CENTER);

        testWindow.getDetails();
    }

}