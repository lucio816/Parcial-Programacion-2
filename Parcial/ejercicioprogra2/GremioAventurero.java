
import java.util.ArrayList;
import java.util.List;

public class GremioAventurero {
    private String nombre;
    private List<Aventurero> aventureros;

    public GremioAventurero(String nombre) {
        this.nombre = nombre;
        this.aventureros = new ArrayList<>();
    }

    public void agregarAventurero(Aventurero a) {
        for (Aventurero item : aventureros) {
            if (item.ObtenerCodigo().equalsIgnoreCase(a.ObtenerCodigo()) && 
                item.ObtenerNombre().equalsIgnoreCase(a.ObtenerNombre())) {
                throw new AventureroDuplicado("Ya existe un aventurero con codigo " + a.ObtenerCodigo() + " y nombre " + a.ObtenerNombre());
            }
        }
        aventureros.add(a);
    }

    public List<Aventurero> obtenerAventureros() {
        List<Aventurero> resultado = new ArrayList<>();
        for (Aventurero a : aventureros) {
            resultado.add(a);
        }
        return resultado;
    }

    public List<Aventurero> filtrarPorRango(Rango rangoBuscado) {
        List<Aventurero> resultado = new ArrayList<>();
        for (Aventurero a : aventureros) {
            if (a.ObtenerRango() == rangoBuscado) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    public List<Aventurero> obtenerAventurerosPoderosos(int poderMinimo) {
        List<Aventurero> resultado = new ArrayList<>();
        for (Aventurero a : aventureros) {
            if (a.CalcularPoder() >= poderMinimo) {
                resultado.add(a);
            }
        }
        return resultado;
    }
}