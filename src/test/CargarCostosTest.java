package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.lang.Math;




public class CargarCostosTest {


    @Test
    void valoresPositivosTest(){
        //Aquí se inicializaran los objetos para poder realizar la prueba unitaria
        //Objeto---->

        //Esta variable almacenara el resultado de la operación
        float resultado=2;

        //Cuando se establezca el objeto, se igualara la variable a la operación correspondiente 
        //resultado=

        assertTrue(resultado > 0, "El resultado debería ser un numero positivo");
    }

    @Test
    void MermaZeroTest(){
        //Aquí se inicializaran los objetos para poder realizar la prueba unitaria
        //Objeto---->
        

        //En el primer campo antes de la coma, va implementado el objeto haciendo
    //Referencia al campo de merma
        Assertions.assertEquals(,0);
        
    }

    @Test
    void BandejaZeroTest(){
        //Este booleano evaluara más adelante si lo que esta almacenado en NB
        //Es igual a cero, en caso de que lo sea, el programa fallará
        boolean zero=true;
         //Aquí se inicializaran los objetos para poder realizar la prueba unitaria
        //Objeto---->
        

        ///En el primer campo antes de la coma, va implementado el objeto haciendo
    //Referencia al campo de NB
    //OJO añadir el objeto .NB correspondiente a la condición
        if(==0){
            zero=false;
        }

        Assertions.assertTrue(zero);
    }

      @Test
    void CFyCVZeroTest(){
        
         //Aquí se inicializaran los objetos para poder realizar la prueba unitaria
        //Objeto---->
       int resultado;


        ///En el primer campo antes de la coma, va implementado el objeto haciendo
    //Referencia al campo de NB
    //OJO La operacion y almacenarla en resultado
        if(==0&&==0){
           Assertions.assertEquals(, 0);
        }

       
    }


    @Test
    void CFyCVNegativeTest(){
        //Este booleano evaluara más adelante si los datos de CF y CV son negativos
        //Si alguno de los dos lo es, el programa fallará
        boolean zero=true;
         //Aquí se inicializaran los objetos para poder realizar la prueba unitaria
        //Objeto---->
        

        ///En el primer campo antes de la coma, va implementado el objeto haciendo
    //Referencia al campo de NB
    //OJO añadir el objeto .NB correspondiente a la condición
        if(<0||<0){
            zero=false;
        }

        Assertions.assertTrue(zero);
    }
    

    @Test
    void MermaNegativeTest(){
         //Aquí se inicializaran los objetos para poder realizar la prueba unitaria
        //Objeto---->
        int resultado=1;
        
    assertTrue(resultado > 0, "La merma debe ser positiva");

    }

    @Test
    void RoundTest(){
        //Aquí se inicializaran los objetos para poder realizar la prueba unitaria
        //Objeto---->
        double num1=3.9;

        num1=Math.round(num1);
        
        Assertions.assertEquals(num1, Round(num1));

    }

   public  double Round(double _num){

        _num=Math.round(_num);
        return _num;
            
        }

}
