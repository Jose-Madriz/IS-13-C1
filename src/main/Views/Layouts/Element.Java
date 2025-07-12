import java.util.ArrayList;

abstract class Element {
    protected float relativeWidth;
    protected float relativeHeight;
    protected float positionPercentX;
    protected float positionPercentY;
    protected boolean visibility; 

    // TODO implementar representacion de colores

    // Constructors
    public Element( float relHeight, float relWidth, float posPercentX, float posPercentY, boolean visibility ){
        this.relativeWidth = relHeight;
        this.relativeHeight = relWidth;
        this.positionPercentX = posPercentX;
        this.positionPercentY = posPercentY;
        this.visibility = visibility;
    }
    public Element( flaot relHeight, float relWidth ){
        this.relativeWidth = 10;
        this.relativeHeight = 10;
        this.positionPercentX = 0;
        this.positionPercentY = 0;
        this.visibility = true;
    }
    
    // Getters
    protected float getRelativeWidth(){
        return this.relativeWidth;
    }
    protected float getRelativeHeight(){
        return this.relativeHeight;
    }
    protected float getPositionPercentX(){
        return this.positionPercentX;
    }
    protected float getPositionPercentY(){
        return this.positionPercentY;
    }
    protected boolean isVisible(){
        return this.visibility;
    }
    
    // Seters
    protected void setRelativeWidth( float relWidth ){
        this.relativeWidth = relWidth;
    }
    protected void setRelativeHeight( float relHeight ){
        this.relativeHeight = relHeight;
    }
    protected void setPositionPercentX( float posPercentX ){
        this.positionPercentX = posPercentX;
    }
    protected void setPositionPercentY( float posPercentY ){
        this.positionPercentY = posPercentY;
    }
    protected void setVisibility( boolean visibility){
        this.visibility = visibility;
    }
    
    // Other Methods
    protected void resize( float relWidth, float relHeight ){}
    protected void move( float posPercentX, float posPercentY ){}

}