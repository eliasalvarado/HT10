/**
 * Clase Nodo. Sera la encargada de simular cada nodo del grafo
 * Autores: 
 *      Herber Sebastian Silva Muñoz -	21764
 *      Daniel Esteban Morales Urizar - 21785 
 *      Elias Alberto Alvarado Raxon -	21808
 *      
 * Fecha de creacion: 20/05/2022
 */

public class Nodo
{
    private String ciudad;

    public Nodo()
    {

    }

    public Nodo(String ciudad){
        this.ciudad = ciudad;
    }
    
    /** 
     * @return String
     */
    public String getNombre() {
        return ciudad;
    }
}