
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        GremioAventurero gremio = new GremioAventurero("Guardianes de Eldoria");
        
        try {
            gremio.agregarAventurero(new Guerrero("G-01", "Arin", Rango.ORO, 30));
            gremio.agregarAventurero(new Mago("M-02", "Lyra", Rango.PLATA, 45));
            gremio.agregarAventurero(new Arquero("A-03", "Nox", Rango.BRONCE, 18));
            gremio.agregarAventurero(new Guerrero("G-04", "Borin", Rango.PLATA, 20));
            gremio.agregarAventurero(new Mago("G-01", "Arin", Rango.BRONCE, 99));
        } catch (AventureroDuplicado e) {
            System.out.println("No se pudo agregar el aventurero: " + e.getMessage());
        }

        System.out.println("Aventureros registrados:");
        mostrarAventureros(gremio.obtenerAventureros());

        System.out.println("\nAtaques:");
        mostrarAtaques(gremio.obtenerAventureros());

        System.out.println("\nEntrenamientos:");
        mostrarEntrenamientos(gremio.obtenerAventureros());

        System.out.println("\nPoder de combate:");
        mostrarPoderes(gremio.obtenerAventureros());

        System.out.println("\nAventureros de rango PLATA:");
        mostrarAventureros(gremio.filtrarPorRango(Rango.PLATA));

        System.out.println("\nAventureros con poder >= 55:");
        mostrarAventureros(gremio.obtenerAventurerosPoderosos(55));
    }

    private static void mostrarAventureros(List<Aventurero> aventureros) {
        for (Aventurero a : aventureros) {
            a.MostrarDatos();
    }
}

    private static void mostrarAtaques(List<Aventurero> aventureros) {
        for (Aventurero a : aventureros) {
            a.Atacar();
        }
    }

    private static void mostrarEntrenamientos(List<Aventurero> aventureros) {
        for (Aventurero a : aventureros) {
            if (a instanceof Entrenable) {
                ((Entrenable) a).Entrenar();
            }
        }
    }

    private static void mostrarPoderes(List<Aventurero> aventureros) {
        for (Aventurero a : aventureros) {
            System.out.println(a.ObtenerNombre() + " - Poder: " + a.CalcularPoder());
        }
    }
}