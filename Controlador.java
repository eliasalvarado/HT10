import java.util.ArrayList;


public class Controlador
{
    int[][] matrizAd = new int[0][0];
    String[][] noCiudadesvector = new String[0][0];
	ArrayList<Nodo> rutas = new ArrayList<Nodo>();


    public void crear_grafo(ArrayList<String> texto)
	{
		matrizAd = new int[0][0];
		noCiudadesvector = new String[0][0];
		rutas = new ArrayList<Nodo>();

		String linea = "";
		String[] palabras = new String[2];
		String n1 = "";
		String n2 = "";
		int d = 0;
		
        for(int i = 0; i < texto.size(); i++)
		{
            linea = texto.get(i);
            palabras = linea.split(" ");
            n1 = palabras[0];
            n2 = palabras[1];
            d = Integer.parseInt(palabras[2]);
            boolean existeOrigen = false, existeDestino = false;
            int p1 = 0;
            int p2 = 0;

            for(int noCiudad = 0; noCiudad < this.rutas.size(); noCiudad++)
			{
                if(this.rutas.get(noCiudad).getNombre().equals(n1))
				{
                    existeOrigen = true;
                    p1 = noCiudad;
                }
                if(this.rutas.get(noCiudad).getNombre().equals(n2))
				{
                    existeDestino = true;
                    p2 = noCiudad;
                }
            }

			Nodo temporal = new Nodo();
			
            if(!existeOrigen)
			{
                temporal = new Nodo(n1);
                this.rutas.add(temporal);
                p1 = this.rutas.indexOf(temporal);
            }

            if(!existeDestino)
			{
                temporal = new Nodo(n2);
                this.rutas.add(temporal);
                p2 = this.rutas.indexOf(temporal);
            }

            agregarAdyasencia(p1, p2, d, existeOrigen, existeDestino);

        }
        actualizar(matrizAd, noCiudadesvector);
    }

    public String modificar(String origen, String destino, int d)
	{
        boolean existeOrigen = false;
        boolean existeDestino = false;
        int p1 = 0;
        int p2 = 0;

        for(int i = 0; i < this.rutas.size(); i++)
		{
            if(this.rutas.get(i).getNombre().equals(origen))
			{
                existeOrigen = true;
                p1 = i;
            }
            if (this.rutas.get(i).getNombre().equals(destino))
			{
                existeDestino = true;
                p2 = i;
            }
        }
        if(existeOrigen && existeDestino)
		{
            matrizAd[p1][p2] = d;
            matrizAd[p2][p1] = d;
            return "\nEl grafo se ha modificado exitosamente.";
        }
        else return "\nLo sentimos. No se logro completar la accion.";
    }

	public int[][] agregarAdyasencia(int p1, int p2, int d, boolean existeOrigen, boolean existeDestino)
	{
        int tamanio = this.matrizAd.length;
        int[][] temporal = new int[0][0];
        if (!existeOrigen && !existeDestino) temporal = new int[tamanio + 2][tamanio + 2];
		else if (!existeOrigen || !existeDestino) temporal = new int[tamanio + 1][tamanio + 1]; 
		else temporal = new int[tamanio][tamanio];
        for (int i = 0; i < tamanio; i++) for (int j = 0; j < tamanio; j++) temporal[i][j] = matrizAd[i][j];

        temporal[p1][p2] = d;
        temporal[p2][p1] = d;

        matrizAd = arreglar(temporal);
        return matrizAd;
    }

    public void actualizar(int[][] matrizAd, String[][] noCiudadesvector)
	{
        for(int k = 0; k < this.matrizAd.length; k++)
		{
            for(int i = 0; i < this.matrizAd.length; i++)
			{
                for(int j = 0; j < this.matrizAd.length; j++)
				{
                    if(matrizAd[i][k] != -1 && matrizAd[k][j] != -1)
					{
                        int valor = matrizAd[i][k] + matrizAd[k][j];
                        if(matrizAd[i][j] == -1)
						{
                            matrizAd[i][j] = valor;
                            noCiudadesvector[i][j] = this.rutas.get(k).getNombre();
                        } 
						else
						{
                            if(valor < matrizAd[i][j])
							{
                                matrizAd[i][j] = valor;
                                noCiudadesvector[i][j] = this.rutas.get(k).getNombre();
                            }
                        }
                    }
                }
            }
        }
    }

	public int[][] arreglar(int[][] matrizAd)
	{
        int tamanio = this.matrizAd.length;
        noCiudadesvector = new String[tamanio][tamanio];
        for(int i = 0; i < tamanio; i++)
		{
            for(int j = 0; j < tamanio; j++)
			{
                noCiudadesvector[i][j] = this.rutas.get(j).getNombre();
                if(i == j)
				{
                    matrizAd[i][j] = 0;
                    noCiudadesvector[i][j] = "0";
                } 
				else if(matrizAd[i][j] == 0) matrizAd[i][j] = -1;
            }
        }
        return matrizAd;
    }

    public String rutaCorta(String origen, String destino)
	{
        Floyd floyd = new Floyd();
		return floyd.algoritmoFloyd(origen, destino,this.rutas, this.matrizAd, this.noCiudadesvector);
    }

    public void agregarMatrizad(String n1, String n2, int d)
	{
        boolean existeOrigen = false;
        boolean existeDestino = false;
        int p1 = 0;
        int p2 = 0;

        for(int i = 0; i < this.rutas.size(); i++)
		{
            if(this.rutas.get(i).getNombre().equals(n1))
			{
                existeOrigen = true;
                p1 = i;
            }
            if(this.rutas.get(i).getNombre().equals(n2))
			{
                existeDestino = true;
                p2 = i;
            }
        }

        if(!existeOrigen)
		{
            Nodo n = new Nodo(n1);
            this.rutas.add(n);
            p1 = this.rutas.indexOf(n);
        }

        if(!existeDestino)
		{
            Nodo n = new Nodo(n2);
            this.rutas.add(n);
            p2 = this.rutas.indexOf(n);
        }

        agregarAdyasencia(p1, p2, d, existeOrigen, existeDestino);
    }

    public void eliminar(String n1, String n2)
	{
        for(int i = 0; i < this.matrizAd.length; i++)
		{
            for(int j = 0; j < matrizAd[i].length; j++)
			{
                System.out.print(matrizAd[i][j] + " ");
            }
            System.out.println();
        }
        for(int i = 0; i < noCiudadesvector.length; i++)
		{
            for(int j = 0; j < noCiudadesvector[i].length; j++)
			{
                System.out.print(noCiudadesvector[i][j] + " ");
            }
            System.out.println();
        }
        boolean existeOrigen = false;
        boolean existeDestino = false;
        int p1 = 0;
        int p2 = 0;

        for(int i = 0; i < this.rutas.size(); i++)
		{
            if(this.rutas.get(i).getNombre().equals(n1))
			{
                existeOrigen = true;
                p1 = i;
            }
            if(this.rutas.get(i).getNombre().equals(n2))
			{
                existeDestino = true;
                p2 = i;
            }
        }

        if(existeOrigen && existeDestino)
		{
            matrizAd[p1][p2] = 0;
            matrizAd[p2][p1] = 0;
        }
        for(int i = 0; i < this.matrizAd.length; i++)
		{
            for (int j = 0; j < matrizAd[i].length; j++)
			{
                System.out.print(matrizAd[i][j] + " ");
            }
            System.out.println();
        }
        for(int i = 0; i < noCiudadesvector.length; i++)
		{
            for(int j = 0; j < noCiudadesvector[i].length; j++)
			{
                System.out.print(noCiudadesvector[i][j] + " ");
            }
            System.out.println();
        }
        matrizAd = arreglar(matrizAd);
        actualizar(matrizAd, noCiudadesvector);
        for(int i = 0; i < this.matrizAd.length; i++)
		{
            for(int j = 0; j < matrizAd[i].length; j++)
			{
                System.out.print(matrizAd[i][j] + " ");
            }
            System.out.println();
        }
        for(int i = 0; i < noCiudadesvector.length; i++)
		{
            for (int j = 0; j < noCiudadesvector[i].length; j++)
			{
                System.out.print(noCiudadesvector[i][j] + " ");
            }
            System.out.println();
        }
    }

	public String center(){
		int max;
		int min;
		int centerIndex = 0;
		int[] ex = new int[matrizAd[0].length];
		for(int i = 0; i < matrizAd.length; i++){
			max = matrizAd[0][i];
			for(int a = 0; a < matrizAd[i].length; a++){
				if(max < matrizAd[a][i]){max = matrizAd[a][i];}
			}
			ex[i] = max;
		}
		min = ex[0];
		for(int b = 1; b < ex.length; b++){
			if(ex[b] < min){
				min = ex[b];
				centerIndex = b;
			}
		}
		return "\nEl centro es la Ciudad de: " + rutas.get(centerIndex).getNombre();
	}
}