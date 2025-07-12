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
    }
    public Window( int relHeight, int relWidth, int posPercentX, int posPercentY, boolean vis ){
        super( relHeight, relWidth, posPercentX, posPercentY, vis );
        this.children = new ArrayList<JComponent>();
        this.previousWindow = null;
    }
    public Window( int relHeight, int relWidth, Window prev ){
        super( relHeight, relWidth );
        this.children = new ArrayList<JComponent>();
        this.previousWindow = prev;
    }
    public Window( int relHeight, int relWidth ){
        super( relHeight, relWidth );
        this.children = new ArrayList<JComponent>();
        this.previousWindow = null;
    }
    public Window( ){
        super( );
        this.children = new ArrayList<JComponent>();
        this.previousWindow = null;
    }

    //Setters
    protected ArrayList<JComponent> getChildrens(){
        return this.children;
    }
    public void setInstance(){
        this.frame = new JFrame();
        
        int screenWidth = getScreenWidth();
        int screenHeight = getScreenHeight();

        // Calcula el valor en pixeles de los tamaños relativos(porcentajes)
        int frameWidth = (int) (screenWidth * (this.relativeWidth / 100.0));
        int frameHeight = (int) (screenHeight * (this.relativeHeight / 100.0));
        int frameX = (int) (screenWidth * (this.positionPercentX / 100.0));
        int frameY = (int) (screenHeight * (this.positionPercentY / 100.0));
        
        this.frame.setSize(frameWidth, frameHeight);
        this.frame.setLocation(frameX, frameY);
        this.frame.setVisible(this.visibility);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Getters
    public int getScreenWidth(){
        return  Toolkit.getDefaultToolkit().getScreenSize().width; 
    }
    public int getScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height; 
    }
    public void getDetails(){
        System.out.println("Relative Width: " + this.relativeWidth + "%");
        System.out.println("Relative Height: " + this.relativeHeight + "%");
        System.out.println("Position X: " + this.positionPercentX + "%"); 
        System.out.println("Position Y: " + this.positionPercentY + "%"); 
        System.out.println("Visibility: " + this.visibility); 
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
