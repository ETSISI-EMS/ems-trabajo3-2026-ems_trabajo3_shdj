package com.practica.lista;

import com.practica.genericas.Coordenada;
import com.practica.genericas.PosicionPersona;

/**
 * Nodo para la lista de coordenadas. Guarda cuántas personas están
 * en una coordenada en un momento específico.
 */
public class NodoPosicion {

	private Coordenada coordenada;
	private int numPersonas;
	private NodoPosicion siguiente; // Para poder recorrer la lista si fuera necesario

	/**
	 * MÉTODO CLAVE 1: Factory method que te pide NodoTemporal
	 */
	public static NodoPosicion fromPosicionPersona(PosicionPersona pp) {
		return new NodoPosicion(pp.getCoordenada(), 1);
	}

	public NodoPosicion() {
		super();
	}

	public NodoPosicion(Coordenada coordenada, int numPersonas) {
		super();
		this.coordenada = coordenada;
		this.numPersonas = numPersonas;
	}

	/**
	 * Constructor por copia
	 */
	public NodoPosicion(NodoPosicion np) {
		this.coordenada = new Coordenada(np.coordenada.getLatitud(),
				np.coordenada.getLongitud());		this.numPersonas = np.numPersonas;
	}

	/**
	 * MÉTODO CLAVE 2: La lógica de combinación que te pide NodoTemporal
	 * Si la coordenada es la misma, simplemente sumamos las personas.
	 */
	public void combine(NodoPosicion other) {
		if (this.coordenada.equals(other.coordenada)) {
			this.numPersonas += other.numPersonas;
		}
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public NodoPosicion getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(NodoPosicion siguiente) {
		this.siguiente = siguiente;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		NodoPosicion other = (NodoPosicion) obj;
		return this.coordenada.equals(other.coordenada);
	}

	@Override
	public int hashCode() {
		return this.coordenada.hashCode();
	}
}