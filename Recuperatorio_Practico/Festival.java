import java.util.ArrayList;
import java.util.List;

public class Festival {
    private final String nombre;
    private final List<Actividad> actividades;

    public Festival(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del festival no puede estar vacio.");
        }
        this.nombre = nombre;
        this.actividades = new ArrayList<>();
    }

    public void agregarActividad(Actividad a) throws ActividadDuplicada {
        if (a == null) {
            throw new IllegalArgumentException("La actividad no puede ser nula.");
        }
        for (Actividad item : actividades) {
            if (item.equals(a)) {
                throw new ActividadDuplicada("Ya existe la actividad " + a.obtenerCodigo() + " " + a.obtenerTitulo() + ".");
            }
        }
        actividades.add(a);
    }

    public List<Actividad> obtenerActividades() {
        return new ArrayList<>(actividades);
    }

    public List<Actividad> filtrarPorFranja(FranjaHoraria franjaBuscada) {
        List<Actividad> resultado = new ArrayList<>();
        for (Actividad a : actividades) {
            if (a.obtenerFranja() == franjaBuscada) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    public List<Actividad> obtenerActividadesDeDuracionMaxima(int limite) {
        if (limite <= 0) {
            throw new IllegalArgumentException("El limite de duracion debe ser positivo");
        }
        List<Actividad> resultado = new ArrayList<>();
        for (Actividad a : actividades) {
            if (a.calcularDuracionTotal() <= limite) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    public String obtenerNombre() {
        return nombre;
    }
}