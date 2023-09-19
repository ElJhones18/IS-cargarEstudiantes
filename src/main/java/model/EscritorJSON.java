package model;

import com.google.gson.Gson;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EscritorJSON {

    private final File archivoJSON;

    public EscritorJSON(String ruta) {
        this.archivoJSON = new File(ruta);
    }

    /**
     * Crea el archivo .json a partir de una lista de estudiantes, se usa la dependencia gson
     * para pasar los objetos Estudiante a formato JSON
     * @param estudiantes lista de estudiantes
     */
    public void crearJSON(ArrayList<Estudiante> estudiantes) {
        Gson gson = new Gson();
        String texto = gson.toJson(estudiantes);

        try {

            PrintWriter escritor = new PrintWriter(archivoJSON);
            escritor.println(texto);
            escritor.close();
            System.out.println("Se ha creado el archivo " + archivoJSON.getAbsolutePath());

        } catch (Exception ex) {
            System.err.println("Error escribiendo el JSON");
        }

    }

}
