
public class MuestraInteractiva extends Actividad implements Presentable {
    private final int estaciones;
    
    public MuestraInteractiva (String codigo,String titulo,FranjaHoraria franja,int duracionBase,int estaciones){
        super(codigo,titulo,franja,duracionBase);
        if (estaciones <= 0) {
            throw new IllegalArgumentException("La cantidad de estaciones debe ser mayor a cero");
        }
        this.estaciones = estaciones;
    }
    @Override
    public int calcularDuracionTotal(){
        return duracionBase + (5 * estaciones);
    }
    
    @Override
    public void presentar() {
        System.out.println("Presentacion de muestra: " + titulo );
    }
    
    @Override
    public void mostrarDatos(){
        System.out.println("Muestra Interactiva codigo:" + codigo + ", titulo:" + titulo + ", franja:" + franja + ", duracionBase:" + duracionBase + "estaciones:" + estaciones);
    }

    public int obtenerEstaciones() {
       return estaciones;
    }

}

