import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.io.*;
import java.util.*;
import java.io.*;
import java.util.*;
import java.io.*;
import java.util.*;
import java.io.*;
class ControladorTest {
    
	@Test
	void testCrear_grafo() throws FileNotFoundException {
		File archivo;
		Controlador controlador = new Controlador();
		String ruta = "C:\\Users\\ealva\\Documents\\GitHub\\HT10\\guategrafo.txt";
		archivo = new File(ruta);
	    Scanner obj = new Scanner(archivo);
	    ArrayList<String> array = new ArrayList<>();
	    String texto = "";
	    while (obj.hasNextLine()) {
	        texto = obj.nextLine();
	        if (!texto.equals("")) {
	            array.add(texto);
	        }
	    }
	    controlador.crear_grafo(array);
	    
	    assertEquals(controlador.center(), "\nEl centro es la Ciudad de: Antigua");
		
		
	}

	@Test
	void testRutaCorta() throws FileNotFoundException {
		File archivo;
		Controlador controlador = new Controlador();
		String ruta = "C:\\Users\\ealva\\Documents\\GitHub\\HT10\\guategrafo.txt";
		archivo = new File(ruta);
	    Scanner obj = new Scanner(archivo);
	    ArrayList<String> array = new ArrayList<>();
	    String texto = "";
	    while (obj.hasNextLine()) {
	        texto = obj.nextLine();
	        if (!texto.equals("")) {
	            array.add(texto);
	        }
	    }
	    controlador.crear_grafo(array);
	    
	    assertEquals(controlador.rutaCorta("Santa-Lucia","Antigua"), "\nSi desea ir de Santa-Lucia a Antigua le recomendamos tomar la siguiente ruta: Santa-Lucia ---> Escuintla ---> Antigua. Con un recorrido de 40 km.");
	}


	@Test
	void testCenter() throws FileNotFoundException {
		File archivo;
		Controlador controlador = new Controlador();
		String ruta = "C:\\Users\\ealva\\Documents\\GitHub\\HT10\\guategrafo.txt";
		archivo = new File(ruta);
	    Scanner obj = new Scanner(archivo);
	    ArrayList<String> array = new ArrayList<>();
	    String texto = "";
	    while (obj.hasNextLine()) {
	        texto = obj.nextLine();
	        if (!texto.equals("")) {
	            array.add(texto);
	        }
	    }
	    controlador.crear_grafo(array);
	    
	    assertEquals(controlador.center(), "\nEl centro es la Ciudad de: Antigua");
	}

}
