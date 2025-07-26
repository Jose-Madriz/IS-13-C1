package main.Models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DatabaseManager {
    private static final String DB_PATH = "DataBase.json";
    private List<Usuario> usuarios;
    private static DatabaseManager instance;

    // Constructor privado para Singleton
    private DatabaseManager() {
        this.usuarios = new ArrayList<>();
        cargarUsuarios();
    }

    // Método estático para obtener la instancia única (Singleton)
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public boolean validarLogin(String ci, String password) {
        try {
            int cedula = Integer.parseInt(ci.trim());
            Usuario usuario = buscarPorCI(cedula);
            return usuario != null && usuario.getPass().equals(password);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Usuario buscarPorCI(int cedula) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCedula() == cedula) {
                return usuario;
            }
        }
        return null;
    }

    public boolean agregarUsuario(Usuario nuevoUsuario) {
        if (buscarPorCI(nuevoUsuario.getCedula()) != null) {
            JOptionPane.showMessageDialog(null,
                    "Ya existe un usuario con esta cédula",
                    "Error de registro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        usuarios.add(nuevoUsuario);
        boolean guardadoExitoso = guardarUsuarios();
        
        if (!guardadoExitoso) {
            usuarios.remove(nuevoUsuario); // Revertir si falla el guardado
            return false;
        }
        
        return true;
    }

    public boolean actualizarUsuario(Usuario usuarioActualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCedula() == usuarioActualizado.getCedula()) {
                Usuario anterior = usuarios.set(i, usuarioActualizado);
                if (!guardarUsuarios()) {
                    usuarios.set(i, anterior); // Revertir si falla el guardado
                    return false;
                }
                return true;
            }
        }
        JOptionPane.showMessageDialog(null,
                "Usuario no encontrado para actualizar",
                "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public synchronized boolean guardarUsuarios() {
        try (FileWriter writer = new FileWriter(DB_PATH)) {
            writer.write("[\n");
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario u = usuarios.get(i);
                writer.write("  {\n");
                writer.write(String.format("    \"nombre\": \"%s\",\n", escapeJson(u.getNombre())));
                writer.write(String.format("    \"nombre2\": \"%s\",\n", escapeJson(u.getNombre2())));
                writer.write(String.format("    \"apellido\": \"%s\",\n", escapeJson(u.getApellido())));
                writer.write(String.format("    \"apellido2\": \"%s\",\n", escapeJson(u.getApellido2())));
                writer.write(String.format("    \"cedula\": %d,\n", u.getCedula()));
                writer.write(String.format("    \"cargo\": \"%s\",\n", escapeJson(u.getCargo())));
                writer.write(String.format("    \"pass\": \"%s\",\n", escapeJson(u.getPass())));
                writer.write(String.format("    \"saldo\": %.2f,\n", u.getSaldo()));
                writer.write(String.format("    \"user\": \"%s\"\n", escapeJson(u.getUser())));
                writer.write(i < usuarios.size() - 1 ? "  },\n" : "  }\n");
            }
            writer.write("]");
            return true;
        } catch (IOException e) {
            mostrarError("Error al guardar la base de datos: " + e.getMessage());
            return false;
        }
    }

    private synchronized void cargarUsuarios() {
        usuarios.clear();
        File dbFile = new File(DB_PATH);
        
        if (!dbFile.exists()) {
            System.err.println("Archivo DataBase.json no encontrado, inicializando lista vacía");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(DB_PATH))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonContent.append(line.trim());
            }

            String jsonString = jsonContent.toString();
            
            if (jsonString.isEmpty() || !jsonString.startsWith("[") || !jsonString.endsWith("]")) {
                System.err.println("DataBase.json está vacío o malformado");
                return;
            }

            jsonString = jsonString.substring(1, jsonString.length() - 1).trim();
            
            if (jsonString.isEmpty()) {
                System.err.println("DataBase.json no contiene usuarios");
                return;
            }

            String[] userObjects = jsonString.split("\\},\\s*\\{");

            for (String userObj : userObjects) {
                userObj = userObj.trim();
                
                if (userObj.startsWith("{")) {
                    userObj = userObj.substring(1);
                }
                if (userObj.endsWith("}")) {
                    userObj = userObj.substring(0, userObj.length() - 1);
                }

                Usuario usuario = new Usuario();
                String[] fields = userObj.split(",");

                for (String field : fields) {
                    field = field.trim();
                    String[] parts = field.split(":", 2);
                    if (parts.length != 2) continue;

                    String key = parts[0].trim().replace("\"", "");
                    String value = parts[1].trim().replace("\"", "");

                    switch (key.toLowerCase()) {
                        case "nombre":
                            usuario.setNombre(value);
                            break;
                        case "nombre2":
                            usuario.setNombre2(value);
                            break;
                        case "apellido":
                            usuario.setApellido(value);
                            break;
                        case "apellido2":
                            usuario.setApellido2(value);
                            break;
                        case "cedula":
                            try {
                                usuario.setCedula(Integer.parseInt(value));
                            } catch (NumberFormatException e) {
                                usuario.setCedula(0);
                            }
                            break;
                        case "cargo":
                            usuario.setCargo(value);
                            break;
                        case "pass":
                            usuario.setPass(value);
                            break;
                        case "saldo":
                            try {
                                usuario.setSaldo(Double.parseDouble(value));
                            } catch (NumberFormatException e) {
                                usuario.setSaldo(0.0);
                            }
                            break;
                        case "user":
                            usuario.setUser(value);
                            break;
                    }
                }

                if (usuario.getCedula() != 0) {
                    usuarios.add(usuario);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al leer DataBase.json: " + e.getMessage());
            mostrarError("Error al leer la base de datos: " + e.getMessage());
        }
    }

    private String escapeJson(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\b", "\\b")
                   .replace("\f", "\\f")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null,
                mensaje,
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}