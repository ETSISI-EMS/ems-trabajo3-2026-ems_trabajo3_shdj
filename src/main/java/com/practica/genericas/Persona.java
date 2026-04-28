package com.practica.genericas;


import com.practica.excecption.EmsInvalidNumberOfDataException;

public class Persona {
	private String nombre, apellidos, documento, email, direccion, cp;
	FechaHora fechaNacimiento;

	public Persona() {

	}
	public Persona(Persona p) {
		this.nombre = p.nombre;
		this.apellidos = p.apellidos;
		this.documento = p.documento;
		this.email = p.email;
		this.direccion = p.direccion;
		this.cp = p.cp;
		this.fechaNacimiento = p.fechaNacimiento;
	}
	public static Persona parsePersona(String[] datos) throws EmsInvalidNumberOfDataException {
		// AÑADIR ESTA COMPROBACIÓN
		if (datos.length != Constantes.MAX_DATOS_PERSONA) {
			throw new EmsInvalidNumberOfDataException("Número de datos incorrecto para PERSONA");
		}

		Persona p = new Persona();
		p.setDocumento(datos[1]);
		p.setNombre(datos[2]);
		p.setApellidos(datos[3]);
		p.setEmail(datos[4]);
		p.setDireccion(datos[5]);
		p.setCp(datos[6]);
		p.setFechaNacimiento(FechaHora.parseFecha(datos[7]));
		return p;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Persona other = (Persona) obj;
		return documento.equals(other.documento);
	}
	public Persona(String nombre, String apellidos, String documento, String email, String direccion,
			FechaHora fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.documento = documento;
		this.email = email;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public FechaHora getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(FechaHora fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		FechaHora fecha = getFechaNacimiento();
		String cadena = "";
		// Documento
		cadena += String.format("%s;", getDocumento());
		// Nombre y apellidos
		cadena += String.format("%s,%s;", getApellidos(), getNombre());
		// correo electrónico
		cadena += String.format("%s;", getEmail());
        // Direccion y código postal
		cadena += String.format("%s,%s;", getDireccion(), getCp());
        // Fecha de nacimiento
		cadena+=String.format("%02d/%02d/%04d\n", fecha.getFecha().getDia(), 
        		fecha.getFecha().getMes(), 
        		fecha.getFecha().getAnio());

		return cadena;
	}
	// Dentro de Persona.java

}
