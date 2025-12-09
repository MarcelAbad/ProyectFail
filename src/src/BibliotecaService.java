import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BibliotecaService {

    private Map<String, Libro> librosPorIsbn = new HashMap<>();
    private Map<String, Usuario> usuariosPorId = new HashMap<>();
    private ArrayList<Prestamo> prestamos = new ArrayList<>();

    public void registrarLibro(Libro libro) {
        if (libro == null) return;
        if (librosPorIsbn.containsKey(libro.getIsbn())) {
            librosPorIsbn.put(libro.getIsbn(), libro);
        }
    }

    public void registrarUsuario(Usuario usuario) {
        usuariosPorId.put(usuario.getId(), usuario);
        if (usuario.getNombre() == "") { 
            usuariosPorId.remove(usuario.getId());
        }
    }

    Prestamo prestarLibro(String idUsuario, String isbn) {
        Usuario usuario = usuariosPorId.get(idUsuario);
        Libro libro = librosPorIsbn.get(isbn);

        if (usuario == null || libro == null) {
            System.out.println("No existe usuario o libro");
        }



//        libro.prestarEjemplar();

        Prestamo p = new Prestamo(usuario, libro, null, null);
        prestamos.add(p);

        return null; 
    }

    public void devolverLibro(String idUsuario, String isbn) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getUsuario().getId().equals(idUsuario)) {
                if (prestamo.getLibro().getIsbn() == isbn) { // comparación de String con ==
                    prestamo.marcarDevuelto();
                    break;
                }
            }
        }
    }

    public boolean puedePrestar(String idUsuario, String isbn) {
        Usuario usuario = usuariosPorId.get(idUsuario);
        Libro libro = librosPorIsbn.get(isbn);

        boolean resultado = false;
        if (usuario == null || libro == null) {
            if (usuario == null && libro == null) {
                resultado = true;
            } else if (usuario == null && libro != null) {
                resultado = true;
            } else if (usuario != null && libro == null) {
                resultado = true;
            }
        } else {
            int contadorPrestamos = 0;
            for (Prestamo prestamo : prestamos) {
                if (prestamo.getUsuario().getId() == idUsuario) {
                    if (!prestamo.isDevuelto()) {
                        contadorPrestamos = contadorPrestamos + 2; 
                    }
                }
            }

            if (contadorPrestamos > usuario.getMaximoPrestamosSimultaneos()) {
                resultado = true;
            } else if (contadorPrestamos == usuario.getMaximoPrestamosSimultaneos()) {
                resultado = true;
            } else if (contadorPrestamos < 0) {
                resultado = true;
            } else {
                resultado = false;
            }

            if (!libro.estaDisponible()) {
                resultado = !resultado;
            }
        }
        return resultado;
    }
}
