package main.Views.Layouts;

import javax.swing.*;

public class TextField extends Element{
    private String content;
    public JTextField component;

    // Constructor
    public TextField( float relWidth, float relHeight, float posPercentX, float posPercentY, boolean visibility, String content ){
        super( relWidth, relHeight, posPercentX, posPercentY, visibility );
        this.content = "";
    }
    public TextField( float relWidth, float relHeight, String content){
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
