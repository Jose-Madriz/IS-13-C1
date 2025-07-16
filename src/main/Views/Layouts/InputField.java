package main.Views.Layouts;

import javax.swing.*;

public class InputField <Field> {
    protected String name;
    public Field textField;
    private boolean visibility;
    private Window window;
    public Panel container;

    // Constructor
    public InputField( float relWidth, float relHeight, float posPercentX, float posPercentY, boolean visibility, String name, Window father ){
        this.name = name;
    }
    public InputField( float relWidth, float relHeight, String name, Window father ){
        this.name = name;
    }
    public InputField( float relWidth, float relHeight, Window father){
        this.name = "Defaultname";
    }
    public InputField( Window father ){
        this.name = "Defaultname";
    }

    // Setters
    public void setname( String name ){
        this.name = name;
    }
    public void setInstance( Field input ){
        this.textField = input;
    }
    public void setwindow( Window window ){
        this.window = window;
    }
    public void setContainer( Panel container ){
        this.container = container;
    }

    // Getters 
    public String getname(){
        return this.name;
    }
    public Field getField(){
        return this.textField;
    }
    public Window getwindow(){
        return this.window;
    }
    public void getDetails(){
        System.out.println("Visibility: " + this.visibility);
        System.out.println("name: " + this.name);
        System.out.println("window: " + this.window);
    }
    public Panel getContainer() {
        return this.container;
    }
}
