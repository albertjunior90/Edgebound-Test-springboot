package edgebound.Biblioteca;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
	List<Libro> findBySeccion(String seccion);
}
