package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class RegistroTest {
    
@Test
void CedulaRegistradaTest(){
    //Se debe declarar el objeto y poder acceder al campo de la contraseña introducida
    //Y si es posible añadir tambien la variable o el objeto con la clave almacenada en el archivo
    //De la cual se va a comparar

    Assertions.assertEquals(/*CI ingresada*/, /*CI almacenada */);
}

@Test
void CIconLetrasTest2(){
//Se debe declarar el objeto y poder acceder al campo de la contraseña introducida
    //Y si es posible añadir tambien la variable o el objeto con la clave almacenada en el archivo
    //De la cual se va a comparar
    String civalue=new String(/* Obtener valor de la cedula*/);
    Boolean encontrado=true;

    String errorvalues="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ;:,.{}()%$#@!`=+-_|\\/?<>";

    for(int i=0;i<civalue.length();i++){
        if(errorvalues.indexOf(civalue.charAt(i))!=-1){
            encontrado=false;
        }
    }
    Assertions.assertTrue(encontrado);
    //Para que verificar este mensaje en VScode debes seleccionar el apartado de 
        //Consola de Depuración
         System.out.println("CI con letras o Caracteres especiales");

}

@Test
void LongContrasenaRegistroTest(){
//Se debe declarar el objeto y poder acceder al campo de la contraseña introducida
    //Y si es posible añadir tambien la variable o el objeto con la clave almacenada en el archivo
    //De la cual se va a comparar
    assertTrue(/*contraseña value*/.length() > 8, "La cedula debe tener más de 8 caracteres");


}

@Test 
void CIvaciaTest2(){
 //Se debe declarar el objeto y poder acceder al campo de la contraseña introducida
    //Y si es posible añadir tambien la variable o el objeto con la clave almacenada en el archivo
    //De la cual se va a comparar

    Assertions.assertEquals(/*Cedula value*/,null);

}

@Test
void ContraseñavaciaTest2(){
//Se debe declarar el objeto y poder acceder al campo de la contraseña introducida
    //Y si es posible añadir tambien la variable o el objeto con la clave almacenada en el archivo
    //De la cual se va a comparar

    Assertions.assertEquals(/*Contraseña value*/,null);



}

@Test
void Camposvacios(){

     boolean vacio=true;
         //Aquí se inicializaran los objetos para poder realizar la prueba unitaria
        //Objeto---->
        

        ///En el primer campo antes de la coma, va implementado el objeto haciendo
        //Colocar en la condicion los campos del objeto asociado a CV y CF
        if(/*CI value */==null&&/*Contraseña value */==null){
            vacio=false;
        }

        Assertions.assertTrue(vacio);
         //Para que verificar este mensaje en VScode debes seleccionar el apartado de 
        //Consola de Depuración
         System.out.println("Ambos campos estan vacios");



}


}
