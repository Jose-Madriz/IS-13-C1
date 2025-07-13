package main.Views.Layouts;

import java.util.ArrayList;
import java.awt.Toolkit;
import javax.swing.*;

public class Window extends Element{
    Window previousWindow;
    JFrame frame;
    ArrayList<JComponent> children;

    public String title;

    // Constructors
    public Window( int relHeight, int relWidth, int posPercentX, int posPercentY, boolean vis, Window prev ){
        super( relHeight, relWidth, posPercentX, posPercentY, vis );
        this.children = new ArrayList<JComponent>();
        this.previousWindow = prev;
        this.title = "DefaultTitle";
    }
    public Window( int relHeight, int relWidth, int posPercentX, int posPercentY, boolean vis ){
        super( relHeight, relWidth, posPercentX, posPercentY, vis );
        this.children = new ArrayList<JComponent>();
        this.previousWindow = null;
        this.title = "DefaultTitle";
    }
    public Window( int relHeight, int relWidth, Window prev ){
        super( relHeight, relWidth );
        this.children = new ArrayList<JComponent>();
        this.previousWindow = prev;
        this.title = "DefaultTitle";
    }
    public Window( int relHeight, int relWidth ){
        super( relHeight, relWidth );
        this.children = new ArrayList<JComponent>();
        this.previousWindow = null;
        this.title = "DefaultTitle";
    }
    public Window( ){
        super( );
        this.children = new ArrayList<JComponent>();
        this.previousWindow = null;
        this.title = "DefaultTitle";
    }

    //Setters
    public void setInstance(){
        this.frame = new JFrame();
        
        this.convertToRelative( this.getScreenWidth(), this.getScreenHeight() );
        
        this.frame.setSize(this.relativeWidth, this.relativeHeight);
        this.frame.setLocation(this.positionPercentX, this.positionPercentY);
        this.frame.setVisible(this.visibility);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Getters
    protected ArrayList<JComponent> getChildrens(){
        return this.children;
    }
    public void getDetails(){
        System.out.println("Relative Width: " + this.relativeWidth + "%");
        System.out.println("Relative Height: " + this.relativeHeight + "%");
        System.out.println("Position X: " + this.positionPercentX + "%"); 
        System.out.println("Position Y: " + this.positionPercentY + "%"); 
        System.out.println("Visibility: " + this.visibility);
        System.out.println("Previous Window: " + this.previousWindow);
        System.out.println("Children: " + this.children);
        System.out.println("Frame: " + this.frame);
        System.out.println("Title: " + this.title);
    }

    //Other Methods
    public void resize( int relWidth, int relHeight ){
        this.relativeWidth = relWidth;
        this.relativeHeight = relHeight;
        setInstance();
    }
    public void move( int posPercentX, int posPercentY ){
        this.positionPercentX = posPercentX;
        this.positionPercentY = posPercentY;
        setInstance();
    }

    // Add Childrens Methoods
    protected void pushChildren( JComponent child ){
        this.children.add(child);
        this.frame.add(child);
    }
    
    // Delete Childrens Methods
    protected void deleteChildren( JComponent childrenReference ){
        if(  this.children.contains(childrenReference) ){
            this.children.remove(childrenReference);
            this.frame.remove(childrenReference);
        }
    }
    protected void popChildren(){
        if( this.children.size() > 0 ){
            this.frame.remove(this.children.get(this.children.size() - 1));
            this.children.remove(this.children.size() - 1);
        }
    }
}
