package main.Views.Layouts;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.Toolkit;

public abstract class Element {
    /* 
        Los atributos de position y width/height tienen valores relativoas a la pantalla
        Los valores van desde 0-100%
    */
    protected float relativeWidth;
    protected float relativeHeight;
    protected float positionPercentX;
    protected float positionPercentY;
    protected boolean visibility;
    protected static float DEFAULT_WIDTH = 50;
    protected static float DEFAULT_HEIGHT = 60;
    Map<String, Color> palette;
    protected Font font;
    protected int fontSize;

    //constructors
    public Element( float relHeight, float relWidth, float posPercentX, float posPercentY, boolean visibility ){
        this.relativeWidth = relHeight;
        this.relativeHeight = relHeight;
        this.positionPercentX = posPercentX;
        this.positionPercentY = posPercentY;
        this.visibility = visibility;
        
        // Validamos que los valores de cada valor relativo, en caso de que 
        this.validateRelatives();
        this.initializeColors();
    }
    public Element( float relHeight, float relWidth ){
        this.relativeWidth = relWidth;
        this.relativeHeight = relHeight;
        this.positionPercentX = 0;
        this.positionPercentY = 0;
        this.visibility = true;
        
        // Validamos que los valores de cada valor relativo, en caso de que 
        this.validateRelatives();
        this.initializeColors();
    }
    
    public Element(){
        this.relativeWidth = DEFAULT_HEIGHT;
        this.relativeHeight = DEFAULT_WIDTH;
        this.positionPercentX = 0;
        this.positionPercentY = 0;
        this.visibility = true;
        
        // Validamos que los valores de cada valor relativo, en caso de que 
        this.validateRelatives();
        this.initializeColors();
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
    public Map<String, Color> getPalette(){
        return this.palette;
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
    public void setfont(String font){
        this.font = new Font(font, Font.PLAIN, this.fontSize);
    }
    public void setFontSize(int fontSize){
        this.fontSize = fontSize;
    }

    // Other Methods
    private void validateRelatives(){
        this.relativeWidth = (this.relativeWidth > 100 || this.relativeWidth < 0) ? DEFAULT_WIDTH : this.relativeWidth;
        this.relativeHeight = (this.relativeHeight > 100 || this.relativeHeight < 0) ? DEFAULT_HEIGHT : this.relativeHeight;
        this.positionPercentX = (this.positionPercentX > 100 || this.positionPercentX < 0) ? 0 : this.positionPercentX;
        this.positionPercentY = (this.positionPercentY > 100 || this.positionPercentY < 0) ? 0 : this.positionPercentY;
    }
    public int getScreenWidth(){
        return  Toolkit.getDefaultToolkit().getScreenSize().width; 
    }
    public int getScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height; 
    }
    public Font getFont(){
        return this.font;
    }
    public int getFonSize(){
        return this.fontSize;
    }
    public int convertRelativeWidth( int fatherWidth ){
        return (int) (fatherWidth * (this.relativeWidth / 100.0));
    }
    public int convertRelativeHeight( int fatherHeight ){
        return (int) (fatherHeight * (this.relativeWidth / 100.0));
    }
    public int convertRelativePositionX( int fatherWidth ){
        float fixPosition = java.lang.Math.abs(this.positionPercentX - this.relativeWidth/2);
        fixPosition = (fixPosition < 0) ? 0 : fixPosition;
        return (int) (fatherWidth * fixPosition / 100.0);
    }
    public int convertRelativePositionY( int fatherHeight ){
        float fixPosition = java.lang.Math.abs(this.positionPercentY - this.relativeHeight/2);
        fixPosition = (fixPosition < 0) ? 0 : fixPosition;
        return (int) (fatherHeight * fixPosition / 100.0);
    }
    
    private void initializeColors(){
        this.palette = new LinkedHashMap<>();

        // Agregamos los colores usando los códigos hexadecimales como clave
        palette.put("Color1", Color.decode("#7c5a69"));
        palette.put("Color2", Color.decode("#b69d75"));
        palette.put("Color3", Color.decode("#d3d3d2"));
        palette.put("Color4", Color.decode("#1f2224"));
        palette.put("Color5", Color.decode("#2c3d73"));
    }
}
