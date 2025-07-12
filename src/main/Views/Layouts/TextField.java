package main.Views.Layouts;

import javax.swing.*;

public class TextField extends Element{
    private String content;
    public JTextField component;

    // Constructor
    public TextField( int relWidth, int relHeight, int posPercentX, int posPercentY, boolean visibility, String content ){
        super( relWidth, relHeight, posPercentX, posPercentY, visibility );
        this.content = "";
    }
    public TextField( int relWidth, int relHeight, String content){
        super( relWidth, relHeight );
        this.content = "";
    }

    // Setters
    public void setContent( String content ){
        this.content = content;
    }
    
    // Getters
    public String getContent(){
        return this.content;
    }
}
