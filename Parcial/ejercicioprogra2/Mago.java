public class Mago extends Aventurero {
    private int mana;

    public Mago(String codigo, String nombre, Rango rango, int mana) {
        super(codigo, nombre, rango);
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