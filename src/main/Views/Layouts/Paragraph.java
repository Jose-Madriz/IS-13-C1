package main.Views.Layouts;

import javax.swing.*;

public class Paragraph extends Element {
    private String content;
    JLabel component;

    public Paragraph( float relWidth, float relHeight, float posPercentX, float posPercentY, boolean visibility, String content ){
        super( relWidth, relHeight, posPercentX, posPercentY, visibility );
        this.content = content;
    }
    public Paragraph( float relWidth, float relHeight, String content ){
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
