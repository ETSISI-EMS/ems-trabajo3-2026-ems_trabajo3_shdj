package com.practica.genericas;


import com.practica.excecption.EmsInvalidNumberOfDataException;

public class PosicionPersona {
	private Coordenada coordenada;
	private String documento;
	private FechaHora fechaPosicion;
	public Coordenada getCoordenada() {
		return coordenada;
	}
	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public FechaHora getFechaPosicion() {
		return fechaPosicion;
	}
	public void setFechaPosicion(FechaHora fechaPosicion) {
		this.fechaPosicion = fechaPosicion;
	}
	@Override
	public String toString() {
		String cadena = "";
        cadena += String.format("%s;", getDocumento());
        FechaHora fecha = getFechaPosicion();        
        cadena+=String.format("%02d/%02d/%04d;%02d:%02d;", 
	        		fecha.getFecha().getDia(), 
	        		fecha.getFecha().getMes(), 
	        		fecha.getFecha().getAnio(),
	        		fecha.getHora().getHora(),
	        		fecha.getHora().getMinuto());
        cadena+=String.format("%.4f;%.4f\n", getCoordenada().getLatitud(), 
	        		getCoordenada().getLongitud());
	
		return cadena;
	}
	// Dentro de PosicionPersona.java

// ... resto de la clase ...

	public static PosicionPersona parsePosicionPersona(String[] datos) throws EmsInvalidNumberOfDataException {
		// AÑADIR ESTA COMPROBACIÓN
		if (datos.length != Constantes.MAX_DATOS_LOCALIZACION) {
			throw new EmsInvalidNumberOfDataException("Número de datos incorrecto para LOCALIZACION");
		}

		PosicionPersona pp = new PosicionPersona();
		pp.setDocumento(datos[1]);
		pp.setFechaPosicion(FechaHora.parseFecha(datos[2], datos[3]));
		pp.setCoordenada(new Coordenada(Float.parseFloat(datos[4]), Float.parseFloat(datos[5])));
		return pp;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		PosicionPersona other = (PosicionPersona) obj;
		return documento.equals(other.documento) &&
				fechaPosicion.equals(other.fechaPosicion);
	}
		
}
