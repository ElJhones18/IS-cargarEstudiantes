package model;

import java.io.BufferedReader;
import java.io.File;

public class LectorCSV {

    private final File archivoCSV;

    public LectorCSV(String ruta) {
        this.archivoCSV = new File(ruta);
    }

    /**
     * Verifica que el archivo ingresado exista, sea un archivo y que su extension sea csv
     * @return verdadero si la verificación es exitosa
     */
    public boolean verificarArchivo() {

        String extension;
        String ruta = archivoCSV.getAbsolutePath();
        int index = ruta.lastIndexOf(".");
        if (index != -1) {
            extension = ruta.substring(index + 1);
            return archivoCSV.exists() && archivoCSV.isFile() && extension.equals("csv");
        } else {
            return false;
        }

    }

    /**
     * Lee el archivo .csv y obtiene el texto dentro de él
     * @return el texto obtenido del archivo .csv
     */
    public String leerCSV() {

        String filePath = this.archivoCSV.getAbsolutePath();
        String text = "";

        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(filePath));
            StringBuilder temp = new StringBuilder();
            String bfRead;
            while ((bfRead = br.readLine()) != null) {
                temp.append(bfRead).append("\n");
            }
            text = temp.toString();
        } catch (Exception e) {
            System.err.println("error");
        }
        return text;
    }

    public File getArchivoCSV() {
        return archivoCSV;
    }
}
