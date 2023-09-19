package control;

import model.EscritorJSON;
import model.Estudiante;
import model.LectorCSV;
import java.util.ArrayList;
import java.util.Scanner;

public class AppConversion {

    private LectorCSV lectorCSV;
    private ArrayList<Estudiante> estudiantes;

    public void start() {

        lectorCSV = new LectorCSV(leerRuta());
        // verificamos el csv antes de crear la lista de estudiantes
        if (lectorCSV.verificarArchivo()) {
            estudiantes = crearListaEstudiantes();
            // verificamos que el csv tuviera datos que permitieran crear los estudiantes
            //antes de crear el JSON
            if (!estudiantes.isEmpty()) {
                String rutaCSV = lectorCSV.getArchivoCSV().getAbsolutePath();
                // creamos la ruta del json a partir de la ruta del csv para que el archivo se genere en la misma carpeta
                String rutaJSON = rutaCSV.substring(0, rutaCSV.lastIndexOf(".")) + ".json";
                EscritorJSON escritorJSON = new EscritorJSON(rutaJSON);
                escritorJSON.crearJSON(estudiantes);
            } else {
                System.err.println("El archivo no contiene valores correctos");
            }
        } else {
            System.err.println("La ruta proporcionada no corresponde a un archivo csv");
        }

    }

    /**
     * Se pide al usuario la ruta del directorio
     * @return la ruta ingresada
     */
    public String leerRuta() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Ingrese la ruta del archivo .csv: ");
        return keyboard.nextLine();
    }

    /**
     * Crea el arraylist de estudiantes a partir del archivo csv cargado
     * @return el arraylist de estudiantes
     */
    public ArrayList<Estudiante> crearListaEstudiantes() {

        estudiantes = new ArrayList<>();
        String texto = "";
        texto = lectorCSV.leerCSV();
        Scanner sc = new Scanner(texto).useDelimiter(",|[\n]+|[\r\n]");

        while (sc.hasNext()) {
            String id = sc.next();
            String nombre = sc.next();
            String apellido = sc.next();
            estudiantes.add(new Estudiante(id, nombre, apellido));
            sc.nextLine();
        }
        sc.close();
        return estudiantes;
    }

}
