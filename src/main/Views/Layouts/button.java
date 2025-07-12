package main.Views.Layouts;

import javax.swing.*;

public class Button extends Element{
    private Window father;
    private String content;
    private JButton component;

    // Constructor
    public Button( float relWidth, float relHeight, float posPercentX, float posPercentY, boolean visibility, String content ){
        super( relWidth, relHeight, posPercentX, posPercentY, visibility );
        this.content = content;
    }
    public Button( float relWidth, float relHeight, String content ){
        super( relWidth, relHeight );
        this.content = content;
    }

    // Setters
    public void setContent( String content ){
        this.content = content;
    }
    public void setInstance( JButton component ){
        this.component = new JButton();

        this.component.setSize(0, 0);
        this.component.setLocation(0, 0);
        this.component.setVisible(this.visibility);
        this.component.setText(this.content);
    }
    // Getters 
    public String getContent(){
        return this.content;
    }

    // Other Methods

}