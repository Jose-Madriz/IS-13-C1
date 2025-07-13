package main.Views.Layouts;

import java.util.ArrayList;
import javax.swing.*;

public class Window extends Element{
    public Window previousWindow;
    public JFrame frame;
    public ArrayList<JComponent> children;

    public String title;
    //TODO implementar Layouts para las ventanas con JPanel y mejorar el posicionamiento
    // Constructors
    public Window( float relHeight, float relWidth, float posPercentX, float posPercentY, boolean vis, Window prev ){
        super( relHeight, relWidth, posPercentX, posPercentY, vis );
        this.children = new ArrayList<JComponent>();
        this.previousWindow = prev;
        this.title = "DefaultTitle";
    }
    public Window( float relHeight, float relWidth, float posPercentX, float posPercentY, boolean vis ){
        super( relHeight, relWidth, posPercentX, posPercentY, vis );
        this.children = new ArrayList<JComponent>();
        this.previousWindow = null;
        this.title = "DefaultTitle";
    }
    public Window( float relHeight, float relWidth, Window prev ){
        super( relHeight, relWidth );
        this.children = new ArrayList<JComponent>();
        this.previousWindow = prev;
        this.title = "DefaultTitle";
    }
    public Window( float relHeight, float relWidth ){
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

        // TODO: Arreglar problema al redimensionar Pantallas: Los componentes de la ventana se desbordan
        this.frame = new JFrame();

        int screenHeight = this.getScreenHeight();
        int screenWidth = this.getScreenWidth();
        
        this.frame.setSize(this.convertRelativeWidth(screenWidth), this.convertRelativeHeight(screenHeight));
        this.frame.setLocation(this.convertRelativePositionX(screenWidth), this.convertRelativePositionY(screenWidth));
        this.frame.setVisible(this.visibility);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        this.frame.setTitle(this.title);
    }
    
    // Getters
    protected ArrayList<JComponent> getChildrens(){
        return this.children;
    }
    public void getDetails(){
        System.out.println("Relative Width: " + this.relativeWidth + "%");
        System.out.println("Width px: " + this.frame.getWidth());
        System.out.println("Relative Height: " + this.relativeHeight + "%");
        System.out.println("Height px: " + this.frame.getHeight());
        System.out.println("Position X: " + this.positionPercentX + "%"); 
        System.out.println("Position Y: " + this.positionPercentY + "%"); 
        System.out.println("Visibility: " + this.visibility);
        System.out.println("Previous Window: " + this.previousWindow);
        System.out.println("Children: " + this.children);
        System.out.println("Frame: " + this.frame);
        System.out.println("Title: " + this.title);
    }

    //Other Methods
    public void resize( float relWidth, float relHeight ){
        this.relativeWidth = relWidth;
        this.relativeHeight = relHeight;
        this.convertRelativeHeight(getScreenHeight());
        this.convertRelativeWidth(getScreenWidth());
        setInstance();
    }
    public void move( float posPercentX, float posPercentY ){
        this.positionPercentX = posPercentX;
        this.positionPercentY = posPercentY;
        this.convertRelativePositionX(getScreenWidth());
        this.convertRelativePositionY(getScreenHeight());
        setInstance();
    }

    // Add Childrens Methoods
    public void pushChildren( JComponent child ){
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
