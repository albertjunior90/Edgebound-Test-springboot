package edgebound.Biblioteca;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Libro {
	
	@Id
	@GeneratedValue
	Long id;
	
	String nombre;

	String autor;
	
	String seccion;
	
	LocalDate fechaRegistro;
	
	public Libro() {
		
	}
	
	public Libro(Long libroID, String nombre, String autor, String seccion, LocalDate fechaRegistro) {
		super();
		this.id = libroID;
		this.nombre = nombre;
		this.autor = autor;
		this.seccion = seccion;
		this.fechaRegistro = fechaRegistro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}	
}
