package main.Views.Layouts;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;

public class Window {

    private static float DEFAULT_WIDTH = 40;
    private static float DEFAULT_HEIGHT = 50;
    protected Window previousWindow;
    protected JFrame frame;
    protected ArrayList<JComponent> children;
    protected String title;
    protected JPanel panel;
    protected Size size;
    public boolean visibility;

    // Constructors
    @SuppressWarnings("unchecked")
    public Window( float relHeight, float relWidth, float posPercentX, float posPercentY, boolean vis, Window prev ){
        this.size = new Size( relWidth, relHeight, posPercentX, posPercentY, null);
        this.children = new ArrayList<JComponent>();
        this.visibility = vis;
        this.previousWindow = prev;
        this.title = "DefaultTitle";
        this.frame = new JFrame();
    }
    @SuppressWarnings("unchecked")
    public Window( float relHeight, float relWidth, float posPercentX, float posPercentY, boolean vis ){
        this.size = new Size( relWidth, relHeight, posPercentX, posPercentY, null);
        this.children = new ArrayList<JComponent>();
        this.visibility = vis;
        this.previousWindow = null;
        this.title = "DefaultTitle";
        this.frame = new JFrame();
    }
    @SuppressWarnings("unchecked")
    public Window( float relHeight, float relWidth, Window prev ){
        this.size = new Size( relWidth, relHeight, 50, 50, null);
        this.children = new ArrayList<JComponent>();
        this.visibility = true;
        this.previousWindow = prev;
        this.title = "DefaultTitle";
        this.frame = new JFrame();
    }
    @SuppressWarnings("unchecked")
    public Window( float relHeight, float relWidth ){
        this.children = new ArrayList<JComponent>();
        this.previousWindow = null;
        this.visibility = true;
        this.title = "DefaultTitle";
        this.frame = new JFrame();
        this.size = new Size( relWidth, relHeight, 50, 50, null);
    }
    @SuppressWarnings("unchecked")
    public Window( ){
        this.size = new Size( DEFAULT_WIDTH, DEFAULT_HEIGHT, 50, 50, null);
        this.children = new ArrayList<JComponent>();
        this.visibility = true;
        this.previousWindow = null;
        this.title = "DefaultTitle";
        this.frame = new JFrame();
    }
    
    //Setters
    public void setInstance(){
        this.frame.setVisible(this.visibility);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setTitle(this.title);
        this.frame.pack();
        this.frame.setSize(this.size.getDimension());
        frame.setLocationRelativeTo(null);
    }
    public void setPanel( JPanel panel ){
        this.panel = panel;
        this.frame.add(this.panel);
        this.panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }
    public void setTitle( String title ){
        this.title = title;
    }
    public void setPreviousWindow( Window prev ){
        this.previousWindow = prev;
    }
    public void setVisible( boolean visibility ){
        this.visibility = visibility;
        this.frame.setVisible(visibility);
    }
    
    // Getters
    public void getDetails(){
        System.out.println("Visibility: " + this.visibility);
        System.out.println("Previous Window: " + this.previousWindow);
        System.out.println("Children: " + this.children);
        System.out.println("Frame: " + this.frame);
        System.out.println("Title: " + this.title);
        System.out.println("Dimensions: " + this.getDimension());
    }
    public JFrame getFrame(){
        return this.frame;
    }
    public Window getPreviousWindow(){
        return this.previousWindow;
    }
    public String getTitle(){
        return this.title;
    }
    public JPanel getPanel(){
        return this.panel;
    }
    public boolean getVisibility(){
        return this.visibility;
    }
    public Dimension getDimension(){
        return this.size.getDimension();
    }
    public Size getSize(){
        return this.size;
    }
    //Other Methods
    public void resize( float relWidth, float relHeight ){

        setInstance();
    }
    public void move( float posPercentX, float posPercentY ){

        setInstance();
    }

    // Add Childrens Methoods
    public <LayoutType> void pushChildren( JPanel child, LayoutType layout){
        this.children.add(child);
        this.panel.add(child);
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
