package com.practica.lista;

import com.practica.genericas.Coordenada;
import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

import java.util.LinkedList;

public class ListaContactos {
	// Debe estar definido así en la parte superior de ListaContactos.java
	private LinkedList<NodoTemporal> lista = new LinkedList<>();

	/**
	 * Insertamos en la lista de nodos temporales, y a la vez inserto en la lista de nodos de coordenadas. 
	 * En la lista de coordenadas metemos el documento de la persona que está en esa coordenada 
	 * en un instante 
	 */
	public void insertarNodoTemporal(PosicionPersona p) {
		// 1. Creamos un nodo temporal a partir de la posición
		NodoTemporal nt = NodoTemporal.fromPosicionPersona(p);

		// 2. Si la lista está vacía, lo añadimos el primero
		if (lista.isEmpty()) {
			lista.addFirst(nt);
			return;
		}

		// 3. Buscamos si ya existe un nodo para esa fecha (Issue #1: evitar duplicados)
		int index = lista.indexOf(nt);
		if (index == -1) {
			// Si no existe, lo insertamos en su posición ordenada
			insertInPosition(nt);
		} else {
			// Si existe, combinamos las coordenadas (delegación)
			lista.get(index).combineNodes(nt);
		}
	}

	



	public int tamanioLista () {
		return this.lista.size(); // Usa el método size() de la lista
	}

	public String getPrimerNodo() {
		return lista.getFirst().toString(); // Obtiene el primer elemento y usa su toString
	}

	/**
	 * Métodos para comprobar que insertamos de manera correcta en las listas de 
	 * coordenadas, no tienen una utilidad en sí misma, más allá de comprobar que
	 * nuestra lista funciona de manera correcta.
	 */
	public int numPersonasEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		return lista.stream()
				.filter(nt -> nt.betweenTimes(inicio, fin))
				.map(NodoTemporal::countPersonas)
				.reduce(0, Integer::sum);
	}

	public int numNodosCoordenadaEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		return lista.stream()
				.filter(nt -> nt.betweenTimes(inicio, fin))
				.map(NodoTemporal::countCoordenadas)
				.reduce(0, Integer::sum);
	}
	
	
	
	@Override
	public String toString() {
		String cadena = "";
		for (NodoTemporal aux : lista) {
			cadena += aux.toString() + " ";
		}
		return cadena.trim();
	}
	private void insertInPosition(NodoTemporal nt) {
		int pos = 0;
		for (NodoTemporal actual : lista) {
			if (nt.compareTo(actual) < 0) {
				break;
			}
			pos++;
		}
		lista.add(pos, nt);
	}
	
	
	
}
