package edgebound.Biblioteca;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/libros")
public class BibliotecaService {
	
	private final LibroRepository libroRepo;
	
	public BibliotecaService(LibroRepository libroRepo) {
		this.libroRepo = libroRepo;
	}
	
	@GetMapping
	List<Libro> Read(){
		return libroRepo.findAll();
	}
	
	@PostMapping
	Libro newLibro(@RequestBody Libro newLibro) {
		return libroRepo.save(newLibro);
	}

	@PutMapping("/{id}")
	Libro reemplazarLibro(@RequestBody Libro nuevoLibro, @PathVariable Long id) {

	    return libroRepo.findById(id)
	      .map(libro -> {
	        libro.setAutor(nuevoLibro.getAutor());
	        libro.setFechaRegistro(nuevoLibro.getFechaRegistro());
	        libro.setNombre(nuevoLibro.getNombre());
	        libro.setSeccion(nuevoLibro.getSeccion());
	        return libroRepo.save(nuevoLibro);
	      })
	      .orElseGet(() -> {
	        nuevoLibro.setId(id);
	        return libroRepo.save(nuevoLibro);
	      });
	  }
	
	@DeleteMapping("/{id}")
	void deleteEmployee(@PathVariable Long id) {
	    libroRepo.deleteById(id);
	}

	@GetMapping("/Seccion/{seccionNombre}")
	List<Libro> ordenadosAlfabeticamentePorCategoria(@PathVariable String seccionNombre,
													 @RequestParam(name="filtro",required = false) String filtro)
	{
		List<Libro> librosDeCategoria = libroRepo.findBySeccion(seccionNombre);
		switch(filtro){
			case "alfabeticamente":
				librosDeCategoria.sort(Comparator.comparing(Libro::getNombre));
				return librosDeCategoria;
			case "fechaRegistro":
				librosDeCategoria.sort(Comparator.comparing(Libro::getFechaRegistro));
				return librosDeCategoria;
			case "alfabeticamenteFechaRegistro":
				librosDeCategoria.sort((Libro l1, Libro l2)->{
					if(l1.getNombre() == l2.getNombre())
						return l1.getFechaRegistro().compareTo(l2.getFechaRegistro());
					else
						return l1.getNombre().compareTo(l2.getNombre());
				});
				return librosDeCategoria;
			default:
				return librosDeCategoria;
		}		
	}
	
	@GetMapping("/librosPorSeccion")
	HashMap<String,Integer> librosPorSeccion()
	{
		HashMap <String,Integer>m = new HashMap<String, Integer>();
        for (Libro l : libroRepo.findAll()) {
        	String keySeccion = l.getSeccion();
        	m.put(keySeccion, m.getOrDefault(keySeccion, 0) + 1);
        }
        return m;
	}
}
