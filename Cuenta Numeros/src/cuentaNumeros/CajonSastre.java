package cuentaNumeros;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class CajonSastre {

	// Rango de num aleatorios
	static final int RANGO_DESDE = 0;
	static final int RANGO_HASTA = 1_000;
	
	// Cantidad de números generados
	static final int LONG_ARRAY_NUMS = 1_000_000;
	
	// Imprime/no imprme cuenta de caracteres.
	static final boolean DETALLE = true;

	// static int[] array = {3,4, 2,2,3,1,7,4,9,0,0,3};
	static int[] array = new int[LONG_ARRAY_NUMS];
	static ArrayList<Integer> numeros = new ArrayList<>();
	static ArrayList<Integer> repeticiones = new ArrayList<>();

	static Hashtable<Integer, Integer> hashtable = new Hashtable<>();
	static TreeMap<Integer, Integer> treeMap = new TreeMap<>();

	public static void main(String[] args) {

		// Rellena array con numeros aleatorios entre 0 y 100
		for (int i = 0; i < array.length; i++) {
			array[i] = ThreadLocalRandom.current().nextInt(RANGO_DESDE, RANGO_HASTA + 1);
		}

		long t = System.currentTimeMillis();
		cuentaArrayList();
		t = System.currentTimeMillis() - t;
		imprimeColeccion(numeros, t, !DETALLE);

		t = System.currentTimeMillis();
		cuentaHastable();
		t = System.currentTimeMillis() - t;
		imprimeColeccion(hashtable, t, !DETALLE);

		t = System.currentTimeMillis();
		cuentaTreeMap();
		t = System.currentTimeMillis() - t;
		imprimeColeccion(treeMap, t, !DETALLE);

	}

	static void cuentaArrayList() {
		int pos;
		for (int n : array) {
			if ((pos = numeros.indexOf(n)) != -1) { // Número ya tiene contador
				// int posRep = numeros.get(pos);
				repeticiones.set(pos, repeticiones.get(pos) + 1); // Incrementa contador
			} else {
				numeros.add(n);
				repeticiones.add(1);
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
			System.out.println("Clase:" + numeros.getClass().getName());
			System.out.println("Tiempo (mls): " + t);
			if (detalle)
				for (int k : numeros) {
					System.out.println(k + " --> " + repeticiones.get(i++));
				}
			System.out.println();
		} else if (o instanceof Hashtable) {
			System.out.println("Clase:" + hashtable.getClass().getName());
			System.out.println("Tiempo (mls): " + t);
			if (detalle)
				for (int k : hashtable.keySet())
					System.out.println(k + " --> " + hashtable.get(k));
			System.out.println();

		} else if (o instanceof TreeMap) {
			System.out.println("Clase:" + treeMap.getClass().getName());
			System.out.println("Tiempo (mls): " + t);
			if (detalle)
				for (int k : treeMap.keySet())
					System.out.println(k + " --> " + treeMap.get(k));
			System.out.println();

		}

	}

}
