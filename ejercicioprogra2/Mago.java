public class Mago extends Aventurero {
    private final int mana;

    public Mago(String codigo, String nombre, Rango rango, int mana) {
        super(codigo, nombre, rango);
        if (mana <= 0) {
            throw new IllegalArgumentException("El mana debe ser mayor a cero.");
        }
        this.mana = mana;
    }

    @Override
    public int CalcularPoder() {
        return mana + 10;
    }

    @Override
    public void Atacar() {
        System.out.println("El mago " + nombre + " lanza un hechizo.");
    }

    @Override
    public void MostrarDatos() {
        System.out.println("Mago [codigo=" + codigo + ", nombre=" + nombre + ", rango=" + rango + ", mana=" + mana + "]");
    }

    public int ObtenerMana() {
        return mana;
    }
}