package main.Views.Layouts;

public class App {

    public static void main(String[] args){
        Window testWindow = new Window( 50, 50 );
        testWindow.setInstance( );
        
        Button testButton = new Button(20, 7, 40, 70, true, "Boton", testWindow);
        
        Paragraph testPara = new Paragraph(50, 60, 15, 20, true, "Mucho texto y asi", testWindow);
        
        Title testTitle = new Title(30, 30, 30, 10, true, "Titulo Arrecho", testWindow);
        
        TextField testTextField = new TextField(40, 5, 50, 20, true, "Texfield para decir que eras una locotrona, gafa",testWindow);
        
        testButton.setInstance( );
        testPara.setInstance();
        testTitle.setInstance();
        testTextField.setInstance();
        
        testWindow.getDetails( );
        testButton.getDetails( );
        testPara.getDetails();
        testTitle.getDetails();
        testTextField.getDetails();
    }
} 