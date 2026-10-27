public class Guerrero extends Aventurero implements Entrenable {
    private int fuerza;

    public Guerrero(String codigo, String nombre, Rango rango, int fuerza) {
        super(codigo, nombre, rango);
        this.fuerza = fuerza;
    }

    @Override
    public int CalcularPoder() {
        return fuerza * 2;
    }

    @Override
    public void Atacar() {
        System.out.println("El guerrero " + nombre + " realiza un ataque fisico.");
    }

    @Override
    public void Entrenar() {
        System.out.println("El guerrero " + nombre + " realiza entrenamiento de fuerza.");
    }

    @Override
    public void MostrarDatos() {
        System.out.println("Guerrero [codigo=" + codigo + ", nombre=" + nombre + ", rango=" + rango + ", fuerza=" + fuerza + "]");
    }

    public int ObtenerFuerza() {
        return fuerza;
    }
}