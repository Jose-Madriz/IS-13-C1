package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import main.Views.MenuPrincipal.*;
import main.Models.ModeloMenuPrincipal.*;

public class MenuPrincipalTestTest{
Menu m1=new Menu();
Menu m2=new Menu();
Menu m3=new Menu();

//Pruebas Turno1
    @Test
    void testTurn1Menu1() {
        //Prueba Turno1
        MenuPrincipal _menu = new MenuPrincipal();
        _menu.CargarDatos(m1, m2,m3);
        //Verificar los turnos
        Assertions.assertEquals("Manana",m1.turno );
    }

    @Test
    void testTurn1Menu2() {
        //Prueba Turno1
        MenuPrincipal _menu = new MenuPrincipal();
        _menu.CargarDatos(m1, m2,m3);
        //Verificar los turno
        Assertions.assertEquals(m1.turno, "Tarde");
    }

     @Test
    void testTurn1Menu3() {
        //Prueba Turno1
        MenuPrincipal _menu = new MenuPrincipal();
        _menu.CargarDatos(m1, m2,m3);
        //Verificar los turno
        Assertions.assertEquals(m1.turno, "Noche");
    }


    //Pruebas Turno2

     @Test
    void testTurn2Menu1() {
        //Prueba Turno2
        MenuPrincipal _menu = new MenuPrincipal();
        _menu.CargarDatos(m1, m2,m3);
        //Verificar los turnos
        Assertions.assertEquals("Manana",m2.turno );
    }

    @Test
    void testTurn2Menu2() {
        //Prueba Turno2
        MenuPrincipal _menu = new MenuPrincipal();
        _menu.CargarDatos(m1, m2,m3);
        //Verificar los turno
        Assertions.assertEquals(m2.turno, "Tarde");
    }

     @Test
    void testTurn2Menu3() {
        //Prueba Turno2
        MenuPrincipal _menu = new MenuPrincipal();
        _menu.CargarDatos(m1, m2,m3);
        //Verificar los turno
        Assertions.assertEquals(m2.turno, "Noche");
    }

     //Pruebas Turno3

     @Test
    void testTurn3Menu1() {
        //Prueba Turno3
        MenuPrincipal _menu = new MenuPrincipal();
        _menu.CargarDatos(m1, m2,m3);
        //Verificar los turnos
        Assertions.assertEquals("Manana",m3.turno );
    }

    @Test
    void testTurn3Menu2() {
        //Prueba Turno3
        MenuPrincipal _menu = new MenuPrincipal();
        _menu.CargarDatos(m1, m2,m3);
        //Verificar los turno
        Assertions.assertEquals(m3.turno, "Tarde");
    }

     @Test
    void testTurn3Menu3() {
        //Prueba Turno3
        MenuPrincipal _menu = new MenuPrincipal();
        _menu.CargarDatos(m1, m2,m3);
        //Verificar los turno
        Assertions.assertEquals(m3.turno, "Noche");
    }



//Verificar no Disponibilidad
//turno1
    @Test
    void testTurno1(){
         String resultado="No Disponible";
        MenuPrincipal _menu = new MenuPrincipal();
        _menu.CargarDatos(m1, m2,m3);
        Assertions.assertEquals(m1.platillo, resultado);


    }
//turno2
     @Test
    void testTurno2(){
         String resultado="No Disponible";
        MenuPrincipal _menu = new MenuPrincipal();
        _menu.CargarDatos(m1, m2,m3);
        Assertions.assertEquals(m2.platillo, resultado);


    }
//turno3
     @Test
    void testTurno3(){
         String resultado="No Disponible";
        MenuPrincipal _menu = new MenuPrincipal();
        _menu.CargarDatos(m1, m2,m3);
        Assertions.assertEquals(m3.platillo, resultado);


    }

}
