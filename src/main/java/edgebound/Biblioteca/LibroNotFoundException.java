package edgebound.Biblioteca;

public class LibroNotFoundException extends RuntimeException {
	  LibroNotFoundException(Long id) {
	    super("Could not find employee " + id);
	  }
}