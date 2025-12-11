import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BibliotecaService {

    private Map<String, Libro> librosPorIsbn = new HashMap<>();
    private Map<String, Usuario> usuariosPorId = new HashMap<>();
    private ArrayList<Prestamo> prestamos = new ArrayList<>();

    public boolean registrarLibro(Libro libro) {
        if (libro == null || libro.getIsbn() == null || libro.getIsbn().isEmpty()){
            return false;
        }
        librosPorIsbn.put(libro.getIsbn(), libro);
        return true;
    }


    public boolean registrarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getId()== null || usuario.getId().isEmpty()) {
            return false;
        }
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()){
            return false;
        }
        if (usuariosPorId.containsKey(usuario.getId())) {
            return false;
        }
        usuariosPorId.remove(usuario.getId(), usuario);
        return true;
        }

    public Prestamo prestarLibro(String idUsuario, String isbn) {
        Usuario usuario = usuariosPorId.get(idUsuario);
        Libro libro = librosPorIsbn.get(isbn);

        if (usuario == null) {
            System.out.println("No existe usuario");
        }
        if (libro == null) {
            System.out.println("No existe libro");
        }
        if (!puedePrestar(idUsuario, isbn)) {
            System.out.println("No se puede prestar");
            return null;
        }

        Integer ejemplar = libro.prestarEjemplar();
        if (ejemplar == null) {
            System.out.println("No existe ejemplar");
            return null;
        }
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime finEstimada = ahora.plusDays(14);


        Prestamo p = new Prestamo(usuario, libro, ahora, finEstimada, ejemplar);
        prestamos.add(p);
        usuario.agregarPrestamoActivo(p);

        return p;
    }

    public boolean devolverLibro(String idUsuario, String isbn) {
        for (Prestamo prestamo : new ArrayList<>(prestamos)) {
            if (prestamo.getUsuario().getId().equals(idUsuario) && prestamo.getLibro().getIsbn().equals(isbn) && prestamo.getLibro().getIsbn().equals(isbn) && !prestamo.isDevuelto()) {
                    prestamo.marcarDevuelto();
                    prestamo.getLibro().devolverEjemplar(prestamo.getNumeroEjemplar());
                    prestamo.getUsuario().quitarPrestamoActivo(prestamo);
                    return true;
            }
        }
        return false;
    }

    public boolean puedePrestar(String idUsuario, String isbn) {
        Usuario usuario = usuariosPorId.get(idUsuario);
        Libro libro = librosPorIsbn.get(isbn);
        if (usuario == null || libro == null) {
            return false;
        }
        int prestamosActivosUsuario = 0;
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getUsuario().getId().equals(usuario.getId()) && !prestamo.isDevuelto()) {
                prestamosActivosUsuario++;
            }
        }
        if (prestamosActivosUsuario >= usuario.getMaximoPrestamosSimultaneos()) {
            return false;
        }
        return libro.estaDisponible();
    }
}
