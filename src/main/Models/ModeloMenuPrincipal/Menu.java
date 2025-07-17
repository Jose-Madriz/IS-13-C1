package main.Models.ModeloMenuPrincipal;





public class Menu {

public String turno;
public String platillo;
public String horario;
public double calorias;

public Menu(){
    
this.turno="";
this.platillo="";
this.horario="";
this.calorias=0;
}

public Menu(String _turno,String _platillo, String _horario, double _calorias){

this.turno=_turno;
this.platillo=_platillo;
this.horario=_horario;
this.calorias=_calorias;

}


    
}
