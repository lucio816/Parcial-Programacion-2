public abstract class Aventurero {
    protected final String codigo;
    protected final String nombre;
    protected final Rango rango;

    public Aventurero(String codigo, String nombre, Rango rango) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede ser nulo ni estar en blanco.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni estar en blanco.");
        }
        if (rango == null) {
            throw new IllegalArgumentException("El rango es obligatorio.");
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aventurero that = (Aventurero) o;
        return codigo.equalsIgnoreCase(that.codigo) && nombre.equalsIgnoreCase(that.nombre);
    }
}