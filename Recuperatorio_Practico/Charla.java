public class Charla extends Actividad implements Reservable, Presentable {
    private final int minutosPreguntas;
    private int cuposDisponibles;

    public Charla(String codigo, String titulo, FranjaHoraria franja, int duracionBase, int minutosPreguntas, int cuposDisponibles) {
        super(codigo, titulo, franja, duracionBase);
        if (minutosPreguntas < 0) {
            throw new IllegalArgumentException("Los minutos de preguntas deben ser negativos");
        }
        if (cuposDisponibles < 0) {
            throw new IllegalArgumentException("Los cupos disponibles no deben ser negativo");
        }
        this.minutosPreguntas = minutosPreguntas;
        this.cuposDisponibles = cuposDisponibles;
    }

    @Override
    public int calcularDuracionTotal() {
        return duracionBase + minutosPreguntas;
    }

    @Override
    public String reservar() {
        if (cuposDisponibles <= 0) {
            throw new IllegalStateException("No quedan lugares disponibles para  " + titulo);
        }
        cuposDisponibles--;
        return "Reserva confirmada para " + titulo + ". Cupos restantes: " + cuposDisponibles;
    }

    @Override
    public int obtenerCuposDisponibles() {
        return cuposDisponibles;
    }

    @Override
    public void presentar() {
        System.out.println("Presentacion de charla: " + titulo + ".");
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Charla codigo:" + codigo + ", titulo:" + titulo + ", franja:" + franja + ", duracionBase:" + duracionBase + ", minutosPreguntas:" + minutosPreguntas + ", cupos:" + cuposDisponibles + "]");
    }

    public int obtenerMinutosPreguntas() {
        return minutosPreguntas;
    }
}