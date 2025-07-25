package main.Models.Login;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import main.Models.Usuario;

public class DatabaseManager {
    private static final String DB_PATH = "DataBase.json";
    private List<Usuario> usuarios;

    public DatabaseManager() {
        this.usuarios = new ArrayList<>();
        cargarUsuarios();
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios); // Devuelve una copia para evitar modificaciones externas
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
            return false; // Usuario ya existe
        }
        usuarios.add(nuevoUsuario);
        return guardarUsuarios();
    }

    public boolean actualizarUsuario(Usuario usuarioActualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCedula() == usuarioActualizado.getCedula()) {
                usuarios.set(i, usuarioActualizado);
                return guardarUsuarios();
            }
        }
        return false; // Usuario no encontrado
    }

    public boolean guardarUsuarios() {
        try (FileWriter writer = new FileWriter(DB_PATH)) {
            writer.write("[\n");
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario u = usuarios.get(i);
                writer.write("  {\n");
                writer.write(String.format("    \"nombre\": \"%s\",\n", u.getNombre()));
                writer.write(String.format("    \"nombre2\": \"%s\",\n", u.getNombre2()));
                writer.write(String.format("    \"apellido\": \"%s\",\n", u.getApellido()));
                writer.write(String.format("    \"apellido2\": \"%s\",\n", u.getApellido2()));
                writer.write(String.format("    \"cedula\": %d,\n", u.getCedula()));
                writer.write(String.format("    \"cargo\": \"%s\",\n", u.getCargo()));
                writer.write(String.format("    \"Pass\": \"%s\",\n", u.getPass()));
                writer.write(String.format("    \"saldo\": %.2f,\n", u.getSaldo()));
                writer.write(String.format("    \"User\": \"%s\"\n", u.getUser()));
                writer.write(i < usuarios.size() - 1 ? "  },\n" : "  }\n");
            }
            writer.write("]");
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al guardar la base de datos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void cargarUsuarios() {
        usuarios.clear();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_PATH))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonContent.append(line.trim());
            }

            String jsonString = jsonContent.toString();
            
            // Verificar si el archivo está vacío o no es un array JSON
            if (jsonString.isEmpty() || !jsonString.startsWith("[") || !jsonString.endsWith("]")) {
                return;
            }

            // Eliminar corchetes exteriores
            jsonString = jsonString.substring(1, jsonString.length() - 1).trim();
            
            // Si no hay usuarios, salir
            if (jsonString.isEmpty()) {
                return;
            }

            // Dividir los objetos de usuario
            String[] userObjects = jsonString.split("\\},\\s*\\{");

            for (String userObj : userObjects) {
                userObj = userObj.trim();
                
                // Limpiar llaves si existen
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
                    String[] parts = field.split(":", 2); // Dividir solo en el primer :
                    if (parts.length != 2) continue;

                    String key = parts[0].trim().replace("\"", "");
                    String value = parts[1].trim().replace("\"", "");

                    switch (key) {
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
                        case "Pass":
                            usuario.setPass(value);
                            break;
                        case "saldo":
                            try {
                                usuario.setSaldo(Double.parseDouble(value));
                            } catch (NumberFormatException e) {
                                usuario.setSaldo(0.0);
                            }
                            break;
                        case "User":
                            usuario.setUser(value);
                            break;
                    }
                }

                if (usuario.getCedula() != 0) {
                    usuarios.add(usuario);
                }
            }
        } catch (FileNotFoundException e) {
            // Archivo no existe, se creará al guardar el primer usuario
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error al leer la base de datos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}