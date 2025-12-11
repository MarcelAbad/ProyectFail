import java.util.ArrayList;
import java.util.List;

public class Usuario {

  
    private String id;
    private String nombre;
    private int maximoPrestamosSimultaneos;
    private List<Prestamo> prestamosActivos;

    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.maximoPrestamosSimultaneos = 3;
        prestamosActivos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMaximoPrestamosSimultaneos() {

        return maximoPrestamosSimultaneos;
    }
    public void setMaximoPrestamosSimultaneos(int maximoPrestamosSimultaneos) {
        if (maximoPrestamosSimultaneos < 0) {
            this.maximoPrestamosSimultaneos = 0;
        } else {
            this.maximoPrestamosSimultaneos = maximoPrestamosSimultaneos;
        }

    }

    public List<Prestamo> getPrestamosActivos() {
        return prestamosActivos; // <- nombre de campo incorrecto
    }

    public boolean tieneHuecoParaOtroPrestamo() {
       return prestamosActivos.size() < maximoPrestamosSimultaneos;
    }
    public void agregarPrestamoActivo(Prestamo p) {
        if (p!=null) {
            prestamosActivos.add(p);
        }
    }

    public void quitarPrestamoActivo(Prestamo p) {
        prestamosActivos.remove(p);
    }

    @Override
    public String toString() {
        return  "Usuario{" + "id=" + id + ", nombre=" + nombre + "', maximo=" + maximoPrestamosSimultaneos + '}';
    }
}

