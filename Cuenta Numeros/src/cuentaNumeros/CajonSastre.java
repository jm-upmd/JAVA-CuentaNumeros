package cuentaNumeros;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class CajonSastre {

	// Rango de num aleatorios
	static final int RANGO_DESDE =1;
	static final int RANGO_HASTA = 20;
	
	// Cantidad de números generados
	static final int LONG_ARRAY_NUMS = 5_000_000;
	
	// Imprime/no imprme cuenta de caracteres.
	static final boolean DETALLE = false;

	static int[] array = new int[LONG_ARRAY_NUMS]; // Array con los núemros aleatorios
	
	// ArrayList 
	static ArrayList<Integer> numsArrayList = new ArrayList<>();
	static ArrayList<Integer> repArrayList = new ArrayList<>();
	
	// Vector. Misma funcionalidad pero sincronizado. Se puede usar en programación concurrente. 
	// Mas lento que ArrayList
	static Vector<Integer> numsVector = new Vector<>();
	static Vector<Integer> repVector = new Vector<>();
	
	// Array 
	static int[] repArray = new int[RANGO_HASTA - RANGO_DESDE + 1];

	// Hastable
	static Hashtable<Integer, Integer> hashtable = new Hashtable<>();
	
	//TreeMap
	static TreeMap<Integer, Integer> treeMap = new TreeMap<>();
	
	// Cabeceras de columnas para salida por consola
	// Cadena formato columnas
	static final String FORMATO_CAB = "%6s%15s\n";
	static final String FORMATO_FIL = "%,6d%,15d\n";
	

	public static void main(String[] args) {

		// Rellena array con numeros aleatorios entre 0 y 100
		for (int i = 0; i < array.length; i++) {
			array[i] = ThreadLocalRandom.current().nextInt(RANGO_DESDE, RANGO_HASTA + 1);
		}
		
		long t = System.currentTimeMillis();
		cuentaArray();
		t = System.currentTimeMillis() - t;
		imprimeColeccion(repArray, t, !DETALLE);

		t = System.currentTimeMillis();
		cuentaArrayList();
		t = System.currentTimeMillis() - t;
		imprimeColeccion(repArrayList, t, !DETALLE);
		
		t = System.currentTimeMillis();
		cuentaVector();
		t = System.currentTimeMillis() - t;
		imprimeColeccion(repVector, t, !DETALLE);

		t = System.currentTimeMillis();
		cuentaHastable();
		t = System.currentTimeMillis() - t;
		imprimeColeccion(hashtable, t, !DETALLE);

		t = System.currentTimeMillis();
		cuentaTreeMap();
		t = System.currentTimeMillis() - t;
		imprimeColeccion(treeMap, t, !DETALLE);

	}
	
	static void cuentaArray() {
		for (int n: array) {
			repArray[n-RANGO_DESDE]++;
		}
		
	}

	static void cuentaArrayList() {
		int pos;
		for (int n : array) {
			if ((pos = numsArrayList.indexOf(n)) != -1) { // Número ya tiene contador
				// int posRep = numeros.get(pos);
				repArrayList.set(pos, repArrayList.get(pos) + 1); // Incrementa contador
			} else {
				numsArrayList.add(n);
				repArrayList.add(1);
			}
		}
	}

	static void cuentaVector() {
		int pos;
		for (int n : array) {
			if ((pos = numsVector.indexOf(n)) != -1) { // Número ya tiene contador
				// int posRep = numeros.get(pos);
				repVector.set(pos, repVector.get(pos) + 1); // Incrementa contador
			} else {
				numsVector.add(n);
				repVector.add(1);
			}
		}
	}
	
	static void cuentaTreeMap() {
		for (int n : array) {
			if (treeMap.containsKey(n))
				treeMap.put(n, treeMap.get(n) + 1);
			else
				treeMap.put(n, 1);
		}
	}

	static void cuentaHastable() {

		Integer cont;
		for (int n : array) {
			if ((cont = hashtable.get(n)) != null)
				hashtable.put(n, cont + 1);
			else
				hashtable.put(n, 1);
		}
	}

	static void imprimeColeccion(Object o, long t, boolean detalle) {
		if (o instanceof ArrayList) {
			int i = 0;
			System.out.println("Clase:" + numsArrayList.getClass().getName());
			System.out.println("Tiempo (mls): " + t);
			if (detalle)
				System.out.printf(FORMATO_CAB,"Número","Repeticiones");
				for (int k : numsArrayList) {
					System.out.printf(FORMATO_FIL,k ,repArrayList.get(i++));
				}
			System.out.println();
		} else if (o instanceof Vector) {
			int i = 0;
			System.out.println("Clase:" + numsVector.getClass().getName());
			System.out.println("Tiempo (mls): " + t);
			System.out.printf(FORMATO_CAB,"Número","Repeticiones");
			if (detalle)
				for (int k : numsVector) {
					System.out.printf(FORMATO_FIL,k, repVector.get(i++));
				}
			System.out.println();
		}else if (o instanceof Hashtable) {
			System.out.println("Clase:" + hashtable.getClass().getName());
			System.out.println("Tiempo (mls): " + t);
			if (detalle)
				System.out.printf(FORMATO_CAB,"Número","Repeticiones");
				for (int k : hashtable.keySet())
					System.out.printf(FORMATO_FIL,k, hashtable.get(k));
			System.out.println();

		} else if (o instanceof TreeMap) {
			System.out.println("Clase:" + treeMap.getClass().getName());
			System.out.println("Tiempo (mls): " + t);
			if (detalle)
				System.out.printf(FORMATO_CAB,"Número","Repeticiones");
				for (int k : treeMap.keySet())
					System.out.printf(FORMATO_FIL,k,treeMap.get(k));
			System.out.println();

		} else  if (o instanceof int[]){
			System.out.println("Array:" );
			System.out.println("Tiempo (mls): " + t);
			if (detalle)
				System.out.printf(FORMATO_CAB,"Número","Repeticiones");
				for (int i=0; i< repArray.length;i++)
					System.out.printf(FORMATO_FIL, i + RANGO_DESDE ,repArray[i]);
			System.out.println();
			
		} 

	}

}


