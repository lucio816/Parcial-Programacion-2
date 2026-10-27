public class Arquero extends Aventurero implements Entrenable {
    private final int precision;

    public Arquero(String codigo, String nombre, Rango rango, int precision) {
        super(codigo, nombre, rango);
        this.precision = precision;
    }

    @Override
    public int CalcularPoder() {
        return precision * 3;
    }

    @Override
    public void Atacar() {
        System.out.println("El arquero " + nombre + " dispara una flecha.");
    }

    @Override
    public void Entrenar() {
        System.out.println("El arquero " + nombre + " realiza practica de tiro.");
    }

    @Override
    public void MostrarDatos() {
        System.out.println("Arquero [codigo=" + codigo + ", nombre=" + nombre + ", rango=" + rango + ", precision=" + precision + "]");
    }

    public int ObtenerPrecision() {
        return precision;
    }
}