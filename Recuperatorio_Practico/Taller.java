
public class Taller extends Actividad implements Reservable{
    private final int minutosPractica;
    private int cuposDisponibles;
    
    public Taller (String codigo,String titulo,FranjaHoraria franja,int duracionBase,int minutosPractica,int cuposDisponibles){
        super(codigo,titulo,franja,duracionBase);
        if (minutosPractica < 0) {
            throw new IllegalArgumentException("Los minutos de practica no deben ser negativos");
        }
        if (cuposDisponibles < 0) {
            throw new IllegalArgumentException("Los cupos disponibles no deben ser negativos");
        }
        this.minutosPractica = minutosPractica;
        this.cuposDisponibles = cuposDisponibles;
    }

    @Override
    public int calcularDuracionTotal(){
        return duracionBase + minutosPractica;
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
    public void mostrarDatos() {
        System.out.println("Taller codigo:" + codigo + ", titulo:" + titulo + ", franja:" + franja + ", duracionBase:" + duracionBase + ", minutosPractica:" + minutosPractica + ", cupos=" + cuposDisponibles );
    }
    public int obtenerMinutosPractica() {
       return minutosPractica;
    }

}

