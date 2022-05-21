/**
 * Clase Floyd. Sera la encargada ejecutar el algoritmo de Floyd y de esa forma encontrar la ruta mas corta
 * Autores: 
 *      Herber Sebastian Silva Muñoz -	21764
 *      Daniel Esteban Morales Urizar - 21785 
 *      Elias Alberto Alvarado Raxon -	21808
 *      
 * Fecha de creacion: 20/05/2022
 */

import java.util.ArrayList;

public class Floyd
{
    /** 
     * @param origen
     * @param destino
     * @param rutas
     * @param matrizAd
     * @param noCiudadesvector
     * @return String
     */
    public String algoritmoFloyd(String origen, String destino, ArrayList<Nodo> rutas, int[][] matrizAd, String[][] noCiudadesvector)
    {
        int nOrigen = -1;
        int nDestino = -1;
        for(int i = 0; i < rutas.size(); i++)
        {
            if(rutas.get(i).getNombre().equals(origen))
            {
                nOrigen = i;
                break;
            }
        }
        for(int j = 0; j < rutas.size(); j++)
        {
            if(rutas.get(j).getNombre().equals(destino))
            {
                nDestino = j;
                break;
            }
        }
        if(nOrigen > -1 && nDestino > -1)
        {
            int distancia = matrizAd[nOrigen][nDestino];
            String recorrido = noCiudadesvector[nOrigen][nDestino];
            if(!recorrido.equals(destino))
            {
                return "\nSi desea ir de " + origen + " a " + destino + " le recomendamos tomar la siguiente ruta: " + origen + " ---> " + recorrido + " ---> " + destino + ". Con un recorrido de " + distancia + " km.";
            } 
            else if(recorrido.equals(destino))
            {
                return "\nSi desea ir de " + origen + " a " + destino + " le recomendamos tomar la siguiente ruta: " + origen + " ---> " + recorrido + ". Con un recorrido de " + distancia + " km.";
            } 
            else if(recorrido.equals("0")) 
            {
                return "\nSi desea ir de " + origen + " a " + destino + " le recomendamos tomar la siguiente ruta: " + origen + " ---> " + destino + ". Con un recorrido de " + distancia + " km.";
            }
        }
        else return "\nLo lamentamos, la ruta no existe.";
        return "\nLo lamentamos, la ruta no existe.";
    }
}
