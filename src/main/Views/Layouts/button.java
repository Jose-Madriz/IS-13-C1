package main.Views.Layouts;

import javax.swing.*;

public class Button extends Element{
    private Window father;
    private String content;
    private JButton component;

    // Constructor
    public Button( int relWidth, int relHeight, int posPercentX, int posPercentY, boolean visibility, String content ){
        super( relWidth, relHeight, posPercentX, posPercentY, visibility );
        this.content = content;
    }
    public Button( int relWidth, int relHeight, String content ){
        super( relWidth, relHeight );
        this.content = content;
    }
    public Button( int relWidth, int relHeight){
        super( relWidth, relHeight );
        this.content = "DefaultContent";
    }
    public Button( ){
        super( );
        this.content = "DefaultContent";
    }

    // Setters
    public void setContent( String content ){
        this.content = content;
        this.component.setText(content);
    }
    public void setInstance( ){
        this.component = new JButton();
        
        this.component.setSize( this.relativeWidth, this.relativeHeight );
        this.component.setLocation( this.positionPercentX, this.positionPercentY );
        this.component.setVisible(this.visibility);
        this.component.setText(this.content);
        this.component.setBackground( this.palette.get("color1") );
        this.component.setForeground( this.palette.get("color2") );
        this.component.setText(content);
    }
    public void setFather( Window father ){
        this.father = father;
        this.father.pushChildren(this.component);
    }

    // Getters 
    public String getContent(){
        return this.content;
    }
    public JButton getComponent(){
        return this.component;
    }
    public Window getFather(){
        return this.father;
    }
    public void getDetails(){
        System.out.println("Relative Width: " + this.relativeWidth + "%");
        System.out.println("Relative Height: " + this.relativeHeight + "%");
        System.out.println("Position X: " + this.positionPercentX + "%"); 
        System.out.println("Position Y: " + this.positionPercentY + "%"); 
        System.out.println("Visibility: " + this.visibility);
        System.out.println("Content: " + this.content);
        System.out.println("Father: " + this.father);
    }

    // Other Methods

}