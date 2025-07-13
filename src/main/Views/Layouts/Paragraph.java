package main.Views.Layouts;

import javax.swing.*;

public class Paragraph extends Element {
    private Window father;
    private String content;
    JLabel component;

    // Constructor
    public Paragraph( float relWidth, float relHeight, float posPercentX, float posPercentY, boolean visibility, String content, Window father ){
        super( relWidth, relHeight, posPercentX, posPercentY, visibility );
        this.content = content;
        this.father = father;
    }
    public Paragraph( float relWidth, float relHeight, String content, Window father ){
        super( relWidth, relHeight );
        this.content = content;
        this.father = father;
    }
    public Paragraph( float relWidth, float relHeight, Window father){
        super( relWidth, relHeight );
        this.content = "DefaultContent";
        this.father = father;
    }
    public Paragraph( Window father ){
        super( );
        this.content = "DefaultContent";
        this.father = father;
    }
    
    // Setters
    public void setContent( String content ){
        this.content = content;
        this.setInstance();
    }
    public void setInstance( ){
        this.component = new JLabel();

        int fatherWidth = this.father.frame.getWidth();
        int fatherHeight = this.father.frame.getHeight();

        this.component.setSize( this.convertRelativeWidth(fatherWidth), this.convertRelativeHeight(fatherHeight) );
        this.component.setLocation( this.convertRelativePositionX(fatherWidth), this.convertRelativePositionY(fatherHeight) );
        this.component.setVisible( this.visibility );
        this.component.setText( this.content );
        this.component.setBackground( this.palette.get("Color1") );
        this.component.setText( content );
        this.father.pushChildren( this.component );
        
    }
    public void setFather( Window father ){
        this.father = father;
        this.father.pushChildren( this.component );
    }

    // Getters 
    public String getContent(){
        return this.content;
    }
    public JLabel getComponent(){
        return this.component;
    }
    public Window getFather(){
        return this.father;
    }
    public void getDetails(){
        System.out.println("Relative Width: " + this.relativeWidth + "%");
        System.out.println("Width px: " + this.component.getWidth());
        System.out.println("Relative Height: " + this.relativeHeight + "%");
        System.out.println("Height px: " + this.component.getHeight());
        System.out.println("Position X: " + this.positionPercentX + "%"); 
        System.out.println("Position Y: " + this.positionPercentY + "%"); 
        System.out.println("Visibility: " + this.visibility);
        System.out.println("Content: " + this.content);
        System.out.println("Father: " + this.father);
    }
}
