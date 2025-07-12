package main.Views.Layouts;

import javax.swing.*;

public class Title extends Element{
    private String content;
    public JLabel component;
    
    public Title( int relWidth, int relHeight, int posPercentX, int posPercentY, boolean visibility, String content ){
        super( relWidth, relHeight, posPercentX, posPercentY, visibility );
        this.content = content;
    }
    public Title( int relWidth, int relHeight, String content ){
        super( relWidth, relHeight );
        this.content = content;
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
