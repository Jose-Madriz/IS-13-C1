package main.Views.Layouts;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.Toolkit;

public abstract class Element {
    /* 
        Los atributos de position y width/height tienen valores relativoas ala pantalla
        Los valores van desde 0-100%
    */
    protected int relativeWidth;
    protected int relativeHeight;
    protected int positionPercentX;
    protected int positionPercentY;
    protected boolean visibility;
    public static int DEFAULT_WIDTH = 50;
    public static int DEFAULT_HEIGHT = 60;
    Map<String, Color> palette;

    //constructors
    public Element( int relHeight, int relWidth, int posPercentX, int posPercentY, boolean visibility ){
        this.relativeWidth = relHeight;
        this.relativeHeight = relWidth;
        this.positionPercentX = posPercentX;
        this.positionPercentY = posPercentY;
        this.visibility = visibility;
        
        // Validamos que los valores de cada valor relativo, en caso de que 
        this.validateRelatives();
        this.initializeColors();
    }
    public Element( int relHeight, int relWidth ){
        this.relativeWidth = relHeight;
        this.relativeHeight = relWidth;
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
    public int getRelativeWidth(){
        return this.relativeWidth;
    }
    public int getRelativeHeight(){
        return this.relativeHeight;
    }
    public int getPositionPercentX(){
        return this.positionPercentX;
    }
    public int getPositionPercentY(){
        return this.positionPercentY;
    }
    public Map<String, Color> getPalette(){
        return this.palette;
    }
    public boolean isVisible(){
        return this.visibility;
    }
    
    // Seters
    public void setRelativeWidth( int relWidth ){
        this.relativeWidth = relWidth;
        // Validamos que los valores de cada valor relativo, en caso de que 
       this.validateRelatives();
    }
    public void setRelativeHeight( int relHeight ){
        this.relativeHeight = relHeight;
        // Validamos que los valores de cada valor relativo, en caso de que 
       this.validateRelatives();
    }
    public void setPositionPercentX( int posPercentX ){
        this.positionPercentX = posPercentX;
        // Validamos que los valores de cada valor relativo, en caso de que 
       this.validateRelatives();
    }
    public void setPositionPercentY( int posPercentY ){
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
    public void convertToRelative( ){
        int screenWidth = getScreenWidth();
        int screenHeight = getScreenHeight();
        
        // Calcula el valor en pixeles de los tamaños relativos(porcentajes)
        int relWidth = (int) (screenWidth * (this.relativeWidth / 100.0));
        int relHeight = (int) (screenHeight * (this.relativeHeight / 100.0));
        int relX = (int) (screenWidth * (this.positionPercentX / 100.0));
        int relY = (int) (screenHeight * (this.positionPercentY / 100.0));

        this.relativeWidth = relWidth;
        this.relativeHeight = relHeight;
        this.positionPercentX = relX;
        this.positionPercentY = relY;
    }   
    public int getScreenWidth(){
        return  Toolkit.getDefaultToolkit().getScreenSize().width; 
    }
    public int getScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height; 
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