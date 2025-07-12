package main.Views.Layouts;

import java.util.ArrayList;
import java.awt.Toolkit;
import javax.swing.*;

public class Window extends Element{
    Window previousWindow;
    JFrame frame;
    ArrayList<JComponent> children;
    public String title;
    public static float DEFAULT_WIDTH = 50;
    public static float DEFAULT_HEIGHT = 60;

    // Constructors
    public Window( float relWidth, float relHeight, float posPercentX, float posPercentY, boolean visibility, Window prev ){
        super( relWidth, relHeight, posPercentX, posPercentY, visibility );
        this.previousWindow = prev;
        this.children = new ArrayList<JComponent>();
    }
    public Window( Window prev ){
        super( DEFAULT_HEIGHT, DEFAULT_WIDTH );
        this.children = new ArrayList<JComponent>();
        this.previousWindow = prev;
    }

    //Setters
    protected ArrayList<JComponent> getChildrens(){
        return this.children;
    }
    public void setInstance(){
        this.frame = new JFrame();
        
        int screenWidth = getScreenWidth();
        int screenHeight = getScreenHeight();
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
    public void resize( float relWidth, float relHeight ){
        this.relativeWidth = relWidth;
        this.relativeHeight = relHeight;
    }
    public void move( float posPercentX, float posPercentY ){
        this.positionPercentX = posPercentX;
        this.positionPercentY = posPercentY;
    }

    // Add Childrens Methoods
    protected void pushChildren( JComponent child ){}
    
    // Delete Childrens Methods
    protected void deleteChildren( JComponent childrenReference ){}
    protected void popChildren(){}
}
