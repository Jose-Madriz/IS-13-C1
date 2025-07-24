package main.Views.Layouts;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;



public class Panel{
    
    public boolean visibility;
    public ArrayList<Component> children;
    public JPanel panel;
    public Size size;
    public LayoutManager layout;
    public Size fatherSizes;

    //constructors
    public Panel( String type, float relWidth, float relHeight, float posPercentX, float posPercentY, boolean visibility, Size fatherSizes){
        this.fatherSizes = fatherSizes;
        this.size = new Size( relWidth, relHeight, 0, 0, fatherSizes);
        this.panel = new JPanel();
        this.children = new ArrayList<Component>();
        this.visibility = visibility;
        this.fatherSizes = fatherSizes;
        
        this.init();
    }
    public Panel( String type, float relWidth, float relHeight, float posPercentX, float posPercentY, Size fatherSizes){
        this.fatherSizes = fatherSizes;
        this.size = new Size( relWidth, relHeight, 0, 0, fatherSizes);
        this.panel = new JPanel();
        this.children = new ArrayList<Component>();
        this.visibility = true;
        this.fatherSizes = fatherSizes;
        
        this.init();
    }
    public Panel( String type, float relWidth, float relHeight, Size fatherSizes){
        this.fatherSizes = fatherSizes;
        this.size = new Size( relWidth, relHeight, 0, 0, fatherSizes);
        this.panel = new JPanel();
        this.children = new ArrayList<Component>();
        this.visibility = true;
        this.fatherSizes = fatherSizes;
        
        this.init(type);
    }
    public Panel(  float relWidth, float relHeight, Size fatherSizes ){
        this.fatherSizes = fatherSizes;
        this.size = new Size(relWidth, relHeight, 0, 0, fatherSizes);
        this.panel = new JPanel();
        this.children = new ArrayList<Component>();
        this.visibility = true;
        this.fatherSizes = fatherSizes;
        
        this.init();
    }
    
    // Getters
    public boolean isVisible(){
        return this.visibility;
    }
    public ArrayList<Component> getChildrens(){
        return this.children;
    }
    public JPanel getPanel(){
        return this.panel;
    }
    public Size getSize(){
        return this.size;
    }
    
    // Seters
    public void setSize(){
        this.panel.setPreferredSize(this.size.getDimension());
    }
    public void setRelativeWidth( float relWidth ){
        this.size.setRelativeWidth( relWidth );
    }
    public void setRelativeHeight( float relHeight ){
        this.size.setRelativeHeight( relHeight );
    }
    public void setPositionPercentX( float posPercentX ){
        this.size.setPositionPercentX( posPercentX );
    }
    public void setPositionPercentY( float posPercentY ){
        this.size.setPositionPercentY(posPercentY);
    }
    public void setVisibility( boolean visibility){
        this.visibility = visibility;
        this.panel.setVisible(visibility);
    }
    public void setPanel( JPanel panel ){
        this.panel = panel;
    }
    public void setLayout( LayoutManager layout ){
        this.panel.setLayout(layout);
    }

    // Other Methods
    private void pushChildren( Component child ){
        this.children.add(child);
    }
    public void init( String type ){
        switch(type){
            case "grid":
                this.panel.setLayout( new GridLayout(2,2) );
                break;
            case "border":
                this.panel.setLayout( new BorderLayout() );
                break;
            default:
                this.panel.setLayout( new FlowLayout() );
                break;
        }
        this.panel.setVisible(this.visibility);
        this.panel.setPreferredSize(this.size.getDimension());
        this.panel.setSize(this.size.getDimension());
        this.panel.setLocation(this.size.getPositionX(), this.size.getPositionY());
    }
    public void init( ){
        this.panel.setLayout( new FlowLayout() );   
        this.panel.setVisible(this.visibility);
        this.panel.setPreferredSize(this.size.getDimension());
        this.panel.setSize(this.size.getDimension());
        this.panel.setLocation(this.size.getPositionX(), this.size.getPositionY());
    }
    public void addChildPanel( Panel child, Object layoutConstrains ){
        this.panel.add(child.getPanel(), layoutConstrains);
        this.pushChildren(panel);
    }
}
