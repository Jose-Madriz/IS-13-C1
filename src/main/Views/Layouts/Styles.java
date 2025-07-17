package main.Views.Layouts;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedHashMap;
import java.util.Map;

public class Styles {
    Map<String, Color> palette;
    protected Font font;
    protected int fontSize;

    Styles( Font font, int fontSize ){
        this.font = font;
        this.fontSize = fontSize;
        this.initializeColors();
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
