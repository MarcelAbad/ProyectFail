import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Prestamo {

    private Usuario usuario;
    private Libro libro;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinEstimada;
    private boolean devuelto;
    private int numeroEjemplar;

    public Prestamo(Usuario usuario, Libro libro, LocalDateTime fechaInicio, LocalDateTime fechaFinEstimada, int NumeroEjemplar) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaInicio = fechaInicio;
        this.fechaFinEstimada = fechaFinEstimada;
        this.devuelto = false;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFinEstimada() {
        return fechaFinEstimada;
    }

    public void setFechaFinEstimada(LocalDateTime fechaFinEstimada) {
        this.fechaFinEstimada = fechaFinEstimada;
    }


    public void setDevuelto() {
        this.devuelto = devuelto;
    }


    public int getNumeroEjemplar() {
        return numeroEjemplar;
    }

    public void setNumeroEjemplar(int numeroEjemplar) {
        this.numeroEjemplar = numeroEjemplar;
    }

    public boolean isDevuelto() {
        return devuelto;
    }
    public void marcarDevuelto() {
        this.devuelto = true;
    }

    public Integer calcularRetrasoEnDias(LocalDateTime hoy) {
        if (hoy == null || fechaFinEstimada == null) {
            return null;
        }
        long dias = ChronoUnit.DAYS.between(fechaFinEstimada.toLocalDate(), hoy.toLocalDate());
        return (int) dias;
    }

    @Override
    public String toString() {
        return "Prestamo{usuario=" + usuario.getId() +
                ", libro=" + libro.getIsbn() +
                ", ejemplar=" + numeroEjemplar +
                ", inicio=" + fechaInicio +
                ", finEstimada=" + fechaFinEstimada +
                ", devuelto=" + devuelto + "}";
    }
}
