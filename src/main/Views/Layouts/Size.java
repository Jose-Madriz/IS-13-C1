package main.Views.Layouts;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Size {
    /* 
        Los atributos de position y width/height tienen valores relativoas a la pantalla
        Los valores van desde 0-100%
    */
    private float relativeWidth;
    private float relativeHeight;
    private float positionPercentX;
    private float positionPercentY;
    private int fatherWidth;
    private int fatherHeight;
    public Dimension dimensions;

    //Constructor
    public Size( float relWidth, float relHeight, float posPercentX, float posPercentY, Size fatherSize ){
        this.relativeWidth = relWidth;
        this.relativeHeight = relHeight;
        this.positionPercentX = posPercentX;
        this.positionPercentY = posPercentY;
        this.setFatherSizes(fatherSize);
        this.createDimension();
    }
    
    public Size( float relWidth, float relHeight, Size fatherSize ){
        this.relativeWidth = relWidth;
        this.relativeHeight = relHeight;
        this.positionPercentX = 0;
        this.positionPercentY = 0;
        this.setFatherSizes(fatherSize);
        this.createDimension();
    }
    
    // Getters
    public float getRelativeWidth(){
        return this.convertRelativeWidth();
    }
    public int getHeight(){
        return this.convertRelativeHeight();
    }
    public int getWidth(){
        return this.convertRelativeHeight();
    }
    public int getPositionX(){
        return this.convertRelativePositionX();
    }
    public int getPositionY(){
        return this.convertRelativePositionY();
    }
    public int getScreenWidth( ){
        return  Toolkit.getDefaultToolkit().getScreenSize().width; 
    }
    public int getScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height; 
    }
    public Dimension getDimension(){
        return this.dimensions;
    }
    public int getFatherHeight() {
        return this.fatherHeight ;
    }
    public int getFatherWidth() {
        return this.fatherWidth ;
    }
    
    // Seters
    public void setRelativeWidth( float relWidth ){
        this.relativeWidth = relWidth;
        this.validateRelatives();
    }
    public void setRelativeHeight( float relHeight ){
        this.relativeHeight = relHeight;
        this.validateRelatives();
    }
    public void setPositionPercentX( float posPercentX ){
        this.positionPercentX = posPercentX;
        convertRelativePositionX();
        this.validateRelatives();
    }
    public void setPositionPercentY( float posPercentY ){
        this.positionPercentY = posPercentY;
        convertRelativePositionY();
        this.validateRelatives();
    }
    private void setFatherSizes( Size father) {
        if( father == null ){
            this.fatherWidth = this.getScreenWidth();
            this.fatherHeight = this.getScreenHeight();
        }
        else{
            this.fatherWidth = father.getFatherWidth();
            this.fatherHeight = father.getFatherHeight();
        }
    }
    
    // Others
    private void validateRelatives(){
        this.relativeWidth = (this.relativeWidth > 100 || this.relativeWidth < 0) ? 30 : this.relativeWidth;
        this.relativeHeight = (this.relativeHeight > 100 || this.relativeHeight < 0) ? 30 : this.relativeHeight;
        this.positionPercentX = (this.positionPercentX > 100 || this.positionPercentX < 0) ? 0 : this.positionPercentX;
        this.positionPercentY = (this.positionPercentY > 100 || this.positionPercentY < 0) ? 0 : this.positionPercentY;
        this.dimensions.setSize( this.convertRelativeWidth(), this.convertRelativeHeight()  );
    }
    private int convertRelativeWidth( ){
        return (int) (fatherWidth * (this.relativeWidth / 100.0));
    }
    private int convertRelativeHeight( ){
        return (int) (fatherHeight * (this.relativeWidth / 100.0));
    }
    private int convertRelativePositionX( ){
        float fixPosition = java.lang.Math.abs(this.positionPercentX - this.relativeWidth/2);
        fixPosition = (fixPosition < 0) ? 0 : fixPosition;
        return (int) (this.fatherWidth * fixPosition / 100.0);
    }
    private int convertRelativePositionY( ){
        float fixPosition = java.lang.Math.abs(this.positionPercentY - this.relativeHeight/2);
        fixPosition = (fixPosition < 0) ? 0 : fixPosition;
        return (int) (this.fatherHeight * fixPosition / 100.0);
    }
    private void createDimension(  ){
        this.dimensions = new Dimension( this.convertRelativeWidth(), this.convertRelativeHeight() );
    }
}
