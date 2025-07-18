
package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest{

@Test
void ContraseñaIncorrectaTest(){

    //Se debe declarar el objeto y poder acceder al campo de la contraseña introducida
    //Y si es posible añadir tambien la variable o el objeto con la clave almacenada en el archivo
    //De la cual se va a comparar


    //Ej:

    Assertions.assertEquals(/*Contraseña introducida por el usuario*/,/*Valor a comparar*/);


}

@Test
void CedulaIncorrectaTest(){


    //Se debe declarar el objeto y poder acceder al campo de la contraseña introducida
    //Y si es posible añadir tambien la variable o el objeto con la clave almacenada en el archivo
    //De la cual se va a comparar


    //Ej:

    Assertions.assertEquals(/*Cedula introducida por el usuario*/,/*Valor a comparar*/);

}

@Test 
void CamposIncorrectosTest(){
Boolean verificar=false;


//Se debe declarar el objeto y poder acceder al campo de la contraseña introducida
    //Y si es posible añadir tambien la variable o el objeto con la clave almacenada en el archivo
    //De la cual se va a comparar

    if(/*Contraseña ingresada */!=/*Contra almacenada*/&&/*Cedula ingresada */!=/*Cedula almacenada*/){
        verificar=true;
    }

Assertions.assertFalse(verificar);
//Para que verificar este mensaje en VScode debes seleccionar el apartado de 
        //Consola de Depuración
         System.out.println("CI o Contraseña invalidos");

}

@Test
void CIconLetrasTest(){
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
void LongContrasenaTest(){
//Se debe declarar el objeto y poder acceder al campo de la contraseña introducida
    //Y si es posible añadir tambien la variable o el objeto con la clave almacenada en el archivo
    //De la cual se va a comparar
    assertTrue(/*contraseña value*/.length() > 8, "La cedula debe tener más de 8 caracteres");


}

@Test
void LongContrasenaTest2(){

    //Se debe declarar el objeto y poder acceder al campo de la contraseña introducida
    //Y si es posible añadir tambien la variable o el objeto con la clave almacenada en el archivo
    //De la cual se va a comparar

    Assertions.assertEquals(/*contraseña value*/.length(),8);
}

@Test 
void CIvaciaTest(){
 //Se debe declarar el objeto y poder acceder al campo de la contraseña introducida
    //Y si es posible añadir tambien la variable o el objeto con la clave almacenada en el archivo
    //De la cual se va a comparar

    Assertions.assertEquals(/*Cedula value*/,null);

}

@Test
void ContraseñavaciaTest(){
//Se debe declarar el objeto y poder acceder al campo de la contraseña introducida
    //Y si es posible añadir tambien la variable o el objeto con la clave almacenada en el archivo
    //De la cual se va a comparar

    Assertions.assertEquals(/*Contraseña value*/,null);



}

}