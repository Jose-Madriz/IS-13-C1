package main.Views.Layouts;

import javax.swing.*;

public class Paragraph extends Element {
    private String content;
    JLabel component;

    public Paragraph( int relWidth, int relHeight, int posPercentX, int posPercentY, boolean visibility, String content ){
        super( relWidth, relHeight, posPercentX, posPercentY, visibility );
        this.content = content;
    }
    public Paragraph( int relWidth, int relHeight, String content ){
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
