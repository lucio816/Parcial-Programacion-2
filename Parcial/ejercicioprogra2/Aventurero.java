public abstract class Aventurero {
    protected String codigo;
    protected String nombre;
    protected Rango rango;

    public Aventurero(String codigo, String nombre, Rango rango) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.rango = rango;
    }

    public String ObtenerCodigo() {
        return codigo;
    }

    public String ObtenerNombre() {
        return nombre;
    }

    public Rango ObtenerRango() {
        return rango;
    }

    public abstract int CalcularPoder();

    public abstract void Atacar();

    public abstract void MostrarDatos();
}