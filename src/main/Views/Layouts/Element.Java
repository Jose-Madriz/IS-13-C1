package main.Views.Layouts;

public abstract class Element {
    /* 
        Los atributos de position y width/height tienen valores relativoas ala pantalla
        Los valores van desde 0-100%
    */
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
        
        // Validamos que los valores de cada valor relativo, en caso de que 
        this.validateRelatives();
    }
    public Element( float relHeight, float relWidth ){
        this.relativeWidth = relHeight;
        this.relativeHeight = relWidth;
        this.positionPercentX = 0;
        this.positionPercentY = 0;
        this.visibility = true;
        
        // Validamos que los valores de cada valor relativo, en caso de que 
        this.validateRelatives();
    }
    
    public Element(){
        this.relativeWidth = DEFAULT_HEIGHT;
        this.relativeHeight = DEFAULT_WIDTH;
        this.positionPercentX = 0;
        this.positionPercentY = 0;
        this.visibility = true;
        
       // Validamos que los valores de cada valor relativo, en caso de que 
       this.validateRelatives();
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
        // Validamos que los valores de cada valor relativo, en caso de que 
       this.validateRelatives();
    }
    public void setRelativeHeight( float relHeight ){
        this.relativeHeight = relHeight;
        // Validamos que los valores de cada valor relativo, en caso de que 
       this.validateRelatives();
    }
    public void setPositionPercentX( float posPercentX ){
        this.positionPercentX = posPercentX;
        // Validamos que los valores de cada valor relativo, en caso de que 
       this.validateRelatives();
    }
    public void setPositionPercentY( float posPercentY ){
        this.positionPercentY = posPercentY;
        // Validamos que los valores de cada valor relativo, en caso de que 
       this.validateRelatives();
    }
    public void setVisibility( boolean visibility){
        this.visibility = visibility;
        // Validamos que los valores de cada valor relativo, en caso de que 
       this.validateRelatives();
    }

    // Other Methods
    private void validateRelatives(){
        this.relativeWidth = (this.relativeWidth > 100 || this.relativeWidth < 0) ? DEFAULT_WIDTH : this.relativeWidth;
        this.relativeHeight = (this.relativeHeight > 100 || this.relativeHeight < 0) ? DEFAULT_HEIGHT : this.relativeHeight;
        this.positionPercentX = (this.positionPercentX > 100 || this.positionPercentX < 0) ? 0 : this.positionPercentX;
        this.positionPercentY = (this.positionPercentY > 100 || this.positionPercentY < 0) ? 0 : this.positionPercentY;
    }
}