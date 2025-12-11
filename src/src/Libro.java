import java.util.ArrayList;
import java.util.List;

public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private ArrayList<Integer> ejemplaresTotales;
    private ArrayList<Integer> ejemplaresDisponibles;

    public Libro(String isbn, String titulo, String autor, int anioPublicacion,
                 ArrayList<Integer> ejemplaresTotales, ArrayList<Integer> ejemplaresDisponibles) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.ejemplaresTotales = new ArrayList<>(ejemplaresTotales);
        this.ejemplaresDisponibles = new ArrayList<>(ejemplaresDisponibles);
    }

    public Libro(String isbn, String titulo, String autor, int anioPublicacion,
                 ArrayList<Integer> ejemplaresTotales) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.ejemplaresTotales = new ArrayList<>(ejemplaresTotales);
        this.ejemplaresDisponibles = new ArrayList<>(ejemplaresTotales);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public List<Integer> getEjemplaresTotales() {
        return new ArrayList<>(ejemplaresTotales); // Devuelve copia para proteger la lista original
    }

    public void setEjemplaresTotales(ArrayList<Integer> ejemplaresTotales) {
        this.ejemplaresTotales = ejemplaresTotales;
    }

    public List<Integer> getEjemplaresDisponibles() {
        return new ArrayList<>(ejemplaresDisponibles); // Devuelve copia
    }

    public void setEjemplaresDisponibles(ArrayList<Integer> ejemplaresDisponibles) {
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    public boolean estaDisponible() {
        return !ejemplaresDisponibles.isEmpty();
    }

    public Integer prestarEjemplar() {
        if (ejemplaresDisponibles.isEmpty()) {
            return null; // No hay ejemplares disponibles
        } else {
            Integer ejemplarPrestado = ejemplaresDisponibles.remove(0);
            return ejemplarPrestado;
        }
    }


//    public boolean prestarEjemplarEspecifico(Integer numeroEjemplar) {
//        return ejemplaresDisponibles.remove(numeroEjemplar);
//    }


    public boolean devolverEjemplar(Integer numeroEjemplar) {
        if (ejemplaresTotales.contains(numeroEjemplar)) {
            if (!ejemplaresDisponibles.contains(numeroEjemplar)) {
                ejemplaresDisponibles.add(numeroEjemplar);
                return true;
            }
        }
        return false;
    }

//
//    public boolean agregarEjemplar(Integer nuevoEjemplar) {
//        if (!ejemplaresTotales.contains(nuevoEjemplar)) {
//            ejemplaresTotales.add(nuevoEjemplar);
//            ejemplaresDisponibles.add(nuevoEjemplar);
//            return true;
//        }
//        return false;
//    }


//    public boolean eliminarEjemplar(Integer numeroEjemplar) {
//        if (ejemplaresTotales.contains(numeroEjemplar)) {
//            // Solo podemos eliminar si está disponible
//            if (ejemplaresDisponibles.contains(numeroEjemplar)) {
//                ejemplaresTotales.remove(numeroEjemplar);
//                ejemplaresDisponibles.remove(numeroEjemplar);
//                return true;
//            }
//        }
//        return false;
//    }


    public List<Integer> getEjemplaresPrestados() {
        List<Integer> prestados = new ArrayList<>(ejemplaresTotales);
        prestados.removeAll(ejemplaresDisponibles);
        return prestados;
    }


//    public int getCantidadPrestados() {
//        return ejemplaresTotales.size() - ejemplaresDisponibles.size();
//    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anioPublicacion=" + anioPublicacion +
                ", ejemplaresTotales=" + ejemplaresTotales +
                ", ejemplaresDisponibles=" + ejemplaresDisponibles +
                ", ejemplaresPrestados=" + getEjemplaresPrestados() +
                '}';
    }
}