package main.Models;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class MasterBaseManager {

    private static final String MASTERBASE_PATH = "MasterBase.json";
    private Set<Integer> cedulasMasterBase;
    private static MasterBaseManager instance;

    private MasterBaseManager() {
        this.cedulasMasterBase = new HashSet<>();
        cargarMasterBase();
    }

    public static synchronized MasterBaseManager getInstance() {
        if (instance == null) {
            instance = new MasterBaseManager();
        }
        return instance;
    }

    private void cargarMasterBase() {
        File masterBaseFile = new File(MASTERBASE_PATH);

        if (!masterBaseFile.exists()) {
            // Crear archivo si no existe
            guardarMasterBase();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(MASTERBASE_PATH))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonContent.append(line.trim());
            }

            String jsonString = jsonContent.toString();

            if (jsonString.isEmpty() || !jsonString.startsWith("[") || !jsonString.endsWith("]")) {
                return;
            }

            jsonString = jsonString.substring(1, jsonString.length() - 1).trim();

            if (jsonString.isEmpty()) {
                return;
            }

            String[] cedulas = jsonString.split(",");
            for (String cedulaStr : cedulas) {
                try {
                    int cedula = Integer.parseInt(cedulaStr.trim());
                    cedulasMasterBase.add(cedula);
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar MasterBase: " + e.getMessage());
        }
    }

    public boolean removerDeMasterBase(int cedula) {
        if (cedulasMasterBase.remove(cedula)) {
            return guardarMasterBase();
        }
        return false;
    }

    public Set<Integer> getCedulas() {
        return new HashSet<>(cedulasMasterBase);
    }

    private boolean guardarMasterBase() {
        try (FileWriter writer = new FileWriter(MASTERBASE_PATH)) {
            writer.write("[\n");
            boolean first = true;
            for (Integer cedula : cedulasMasterBase) {
                if (!first) {
                    writer.write(",\n");
                }
                writer.write("  " + cedula);
                first = false;
            }
            writer.write("\n]");
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar MasterBase: " + e.getMessage());
            return false;
        }
    }

    public boolean existeEnMasterBase(int cedula) {
        return cedulasMasterBase.contains(cedula);
    }

    public boolean agregarAMasterBase(int cedula) {
        if (cedulasMasterBase.add(cedula)) {
            return guardarMasterBase();
        }
        return false;
    }
}
