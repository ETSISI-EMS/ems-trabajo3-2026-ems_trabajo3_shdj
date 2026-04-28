package com.practica.lista;

import java.util.LinkedList;
import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

/**
 * Nodo que representa un instante de tiempo único.
 * Contiene una lista de todas las coordenadas registradas en ese momento.
 */
public class NodoTemporal implements Comparable<NodoTemporal> {

	private final LinkedList<NodoPosicion> listaCoordenadas;
	private FechaHora fecha;

	/**
	 * Método factoría para crear un nodo a partir de una posición.
	 * Crea automáticamente el primer NodoPosicion dentro de la lista.
	 */
	public static NodoTemporal fromPosicionPersona(PosicionPersona pp) {
		NodoTemporal nt = new NodoTemporal();
		nt.fecha = pp.getFechaPosicion();
		NodoPosicion np = NodoPosicion.fromPosicionPersona(pp);
		nt.listaCoordenadas.add(np);
		return nt;
	}

	public NodoTemporal() {
		super();
		this.listaCoordenadas = new LinkedList<>();
		this.fecha = null;
	}

	public FechaHora getFecha() {
		return fecha;
	}

	public void setFecha(FechaHora fecha) {
		this.fecha = fecha;
	}

	/**
	 * Combina las coordenadas de otro nodo temporal en este.
	 * Si la coordenada ya existe, suma las personas; si no, la añade.
	 */
	public void combineNodes(NodoTemporal other) {
		if (this.fecha == null || !this.fecha.equals(other.fecha)) {
			return;
		}
		for (NodoPosicion npOther : other.listaCoordenadas) {
			int index = listaCoordenadas.indexOf(npOther);
			if (index == -1) {
				listaCoordenadas.add(new NodoPosicion(npOther));
			} else {
				listaCoordenadas.get(index).combine(npOther);
			}
		}
	}

	/**
	 * Calcula el total de personas en este instante sumando todas las coordenadas.
	 * Usado por el Stream en ListaContactos.
	 */
	public int countPersonas() {
		return listaCoordenadas.stream()
				.map(NodoPosicion::getNumPersonas)
				.reduce(0, Integer::sum);
	}

	/**
	 * Devuelve el número de coordenadas diferentes en este instante.
	 */
	public int countCoordenadas() {
		return listaCoordenadas.size();
	}

	/**
	 * Comprueba si este instante está entre dos fechas dadas.
	 */
	public boolean betweenTimes(FechaHora inicio, FechaHora fin) {
		return fecha.compareTo(inicio) >= 0 && fecha.compareTo(fin) <= 0;
	}

	@Override
	public int compareTo(NodoTemporal other) {
		return this.fecha.compareTo(other.fecha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		NodoTemporal other = (NodoTemporal) obj;
		return fecha.equals(other.fecha);
	}

	@Override
	public String toString() {
		return String.format("%s;%s", fecha.getFecha().toString(), fecha.getHora().toString());
	}
}