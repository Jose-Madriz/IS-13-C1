package main.Views.Layouts;

public abstract class Element {
    protected float relativeWidth;
    protected float relativeHeight;
    protected float positionPercentX;
    protected float positionPercentY;
    protected boolean visibility;
    public static float DEFAULT_WIDTH = 50;
    public static float DEFAULT_HEIGHT = 60;
    // TODO implementar representacion de colores

    //constructors
    public Element( float relHeight, float relWidth, float posPercentX, float posPercentY, boolean visibility ){
        this.relativeWidth = relHeight;
        this.relativeHeight = relWidth;
        this.positionPercentX = posPercentX;
        this.positionPercentY = posPercentY;
        this.visibility = visibility;
    }
    public Element( float relHeight, float relWidth ){
        this.relativeWidth = relHeight;
        this.relativeHeight = relWidth;
        this.positionPercentX = 0;
        this.positionPercentY = 0;
        this.visibility = true;
    }
    
    public Element(){
        this.relativeWidth = DEFAULT_HEIGHT;
        this.relativeHeight = DEFAULT_WIDTH;
        this.positionPercentX = 0;
        this.positionPercentY = 0;
        this.visibility = true;
    }

    // Getters
    public float getRelativeWidth(){
        return this.relativeWidth;
    }
    public float getRelativeHeight(){
        return this.relativeHeight;
    }
    public float getPositionPercentX(){
        return this.positionPercentX;
    }
    public float getPositionPercentY(){
        return this.positionPercentY;
    }
    public boolean isVisible(){
        return this.visibility;
    }
    
    // Seters
    public void setRelativeWidth( float relWidth ){
        this.relativeWidth = relWidth;
    }
    public void setRelativeHeight( float relHeight ){
        this.relativeHeight = relHeight;
    }
    public void setPositionPercentX( float posPercentX ){
        this.positionPercentX = posPercentX;
    }
    public void setPositionPercentY( float posPercentY ){
        this.positionPercentY = posPercentY;
    }
    public void setVisibility( boolean visibility){
        this.visibility = visibility;
    }
}