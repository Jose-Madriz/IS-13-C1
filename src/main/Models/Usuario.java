package main.Models;

public class Usuario {
    // Atributos (privados para encapsulación)
    private String nombre;
    private String nombre2;
    private String apellido;
    private String apellido2;
    private int cedula;
    private String cargo;
    private String pass;
    private double saldo;
    private String user;

    // ========== CONSTRUCTORES ==========
    // Constructor vacío (útil para Jackson/Gson)
    public Usuario() {}

    // Constructor completo
    public Usuario(String nombre, String nombre2, String apellido, String apellido2, 
                  int cedula, String cargo, String  pass, double saldo, String user) {
        this.nombre = nombre;
        this.nombre2 = nombre2;
        this.apellido = apellido;
        this.apellido2 = apellido2;
        this.cedula = cedula;
        this.cargo = cargo;
        this.pass = pass;
        this.saldo = saldo;
        this.user = user;
    }

    // ========== GETTERS ==========
    public String getNombre() {
        return nombre;
    }

    public String getNombre2() {
        return nombre2;
    }

    public String getApellido() {
        return apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public int getCedula() {
        return cedula;
    }

    public String getCargo() {
        return cargo;
    }

    public String getPass() {
        return pass;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getUser() {
        return user;
    }

    // ========== SETTERS ==========
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setUser(String user) {
        this.user = user;
    }
}