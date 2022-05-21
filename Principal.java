/**
 * Clase Principal. Sera la encargada de interactuar con el usuario
 * Autores: 
 *      Herber Sebastian Silva Muñoz -	21764
 *      Daniel Esteban Morales Urizar - 21785 
 *      Elias Alberto Alvarado Raxon -	21808
 *      
 * Fecha de creacion: 20/05/2022
 */

import java.util.*;
import java.io.*;


public class Principal
{
	/** 
	 * @param args
	 */
	public static void main(String[] args) {
        File archivo;
        Scanner sw = new Scanner(System.in);
		Controlador controlador = new Controlador();
        String menu = "\n¿Que desea realizar?\n1. Agregar conexion \n2. Buscar ruta mas corta\n3. Calcular el centro\n4. Salir";
        boolean Cicloprincipal = true;
		int opcion = 0;
		String ciudad1 = "";
		String ciudad2 = "";
		String distancia = "";

        try {
            //String ruta = "F:\\Programacion\\HojaDeTrabajo10\\src\\guategrafo.txt";
			System.out.println("\nIngrese la ruta de su archivo txt.");
			String ruta = sw.nextLine() + "\\guategrafo.txt";
            archivo = new File(ruta);
            Scanner obj = new Scanner(archivo);
            ArrayList<String> array = new ArrayList<>();
            String texto;
            while (obj.hasNextLine()) {
                texto = obj.nextLine();
                if (!texto.equals("")) {
                    array.add(texto);
                }
            }

            obj.close();
            

            controlador.crear_grafo(array);

            while (Cicloprincipal)
			{
                opcion = pregunta(menu, 4);
                switch (opcion) {
                    case 1:
                        System.out.println("\nIngrese nombre de ciudad de salida");
                        ciudad1 = sw.nextLine();
                        System.out.println("\nIngrese nombre de ciudad de llegada");
                        ciudad2 = sw.nextLine();
                        System.out.println("\nIngrese la distancia entre ciudades en km");
                        distancia = sw.nextLine();
                        String agregar = ciudad1.trim() + " " + ciudad2.trim() + " " + distancia.trim();
                        array.add(agregar);
                        controlador.crear_grafo(array); //Se vuelve a crear el grafo con la nueva coneccion
                        break;
                    case 2:
                        //Busca la ruta mas corta
						System.out.println("Ingrese nombre de ciudad de salida");
                        ciudad1 = sw.nextLine();
                        System.out.println("Ingrese nombre de ciudad de llegada");
                        ciudad2 = sw.nextLine();
						System.out.println(controlador.rutaCorta(ciudad1,ciudad2));
                        break;
                    case 3:
                        //calcula el centro
						System.out.println(controlador.center());
                        break;
                    case 4:
                        Cicloprincipal = false;
                        break;
                    default:
                        System.out.println("\nIngrese una opcion valida");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.print(e);
            e.printStackTrace();
        }

    }
	
	/** 
	 * @param pregunta
	 * @param opciones
	 * @return int
	 */
	public static int pregunta(String pregunta, int opciones)
    {
        boolean bucle = true;
        int respuesta = 0;
        Scanner scanner = new Scanner(System.in);
        try 
        {
            while(bucle)
            {
                System.out.println(pregunta);
                respuesta = scanner.nextInt();
                scanner.nextLine();
                if(respuesta > 0 && respuesta <= opciones) bucle = false;
                else System.out.println("\nRepuesta no valida.\n");
            }    
        } catch (Exception e) {
            System.out.println("\nRepuesta no valida. Ingrese solamente numeros.\n");
            respuesta = pregunta(pregunta, opciones);
        }
        return respuesta;
    }
}