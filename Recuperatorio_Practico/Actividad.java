import java.util.Objects;

public abstract class Actividad {
    protected final String codigo;
    protected final String titulo;
    protected final FranjaHoraria franja;
    protected final int duracionBase;

    public Actividad(String codigo, String titulo, FranjaHoraria franja, int duracionBase) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo no debe ser nulo ni estar vacia");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El titulo no puede ser nulo ni estar en blanco");
        }
        if (franja == null) {
            throw new IllegalArgumentException("La franja horaria no debe ser vacia");
        }
        if (duracionBase <= 0) {
            throw new IllegalArgumentException("La duracion base tiene que ser mayor que cero");
        }
        this.codigo = codigo;
        this.titulo = titulo;
        this.franja = franja;
        this.duracionBase = duracionBase;
    }

    public String obtenerCodigo() {
        return codigo;
    }

    public String obtenerTitulo() {
        return titulo;
    }

    public FranjaHoraria obtenerFranja() {
        return franja;
    }

    public int obtenerDuracionBase() {
        return duracionBase;
    }

    public abstract int calcularDuracionTotal();

    public abstract void mostrarDatos();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actividad actividad = (Actividad) o;
        return codigo.equalsIgnoreCase(actividad.codigo) && titulo.equalsIgnoreCase(actividad.titulo);
    }
}