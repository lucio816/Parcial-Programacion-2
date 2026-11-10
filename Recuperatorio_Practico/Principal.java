import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Festival festival = new Festival("Ciencia Abierta");
        
        try {
            festival.agregarActividad(new Charla("C01", "IA Creativa", FranjaHoraria.MATUTINA, 40, 10, 2));
            festival.agregarActividad(new Taller("T02", "Robotica", FranjaHoraria.VESPERTINA, 60, 30, 1));
            festival.agregarActividad(new MuestraInteractiva("M03", "Ciencia Viva", FranjaHoraria.MATUTINA, 25, 4));
            festival.agregarActividad(new Charla("C04", "Astronomia", FranjaHoraria.NOCTURNA, 35, 15, 1));
            festival.agregarActividad(new Taller("C01", "IA Creativa", FranjaHoraria.NOCTURNA, 20, 5, 3));
        } catch (ActividadDuplicada e) {
            System.out.println("Duplicado: " + e.getMessage());
        }

        System.out.println("ACTIVIDADES");
        mostrarActividades(festival.obtenerActividades());

        System.out.println("DURACIONES");
        mostrarDuraciones(festival.obtenerActividades());

        System.out.println("PRESENTACIONES");
        realizarPresentaciones(festival.obtenerActividades());

        System.out.println("RESERVAS");
        reservarActividades(festival.obtenerActividades());

        System.out.println("MATUTINA");
        mostrarActividades(festival.filtrarPorFranja(FranjaHoraria.MATUTINA));

        System.out.println("HASTA 50 MINUTOS");
        mostrarActividades(festival.obtenerActividadesDeDuracionMax(50));
    }

    private static void mostrarActividades(List<Actividad> actividades) {
        for (Actividad a : actividades) {
            a.mostrarDatos();
        }
    }

    private static void mostrarDuraciones(List<Actividad> actividades) {
        for (Actividad a : actividades) {
            System.out.println(a.obtenerTitulo() + ": " + a.calcularDuracionTotal() + " minutos.");
        }
    }

    private static void realizarPresentaciones(List<Actividad> actividades) {
        for (Actividad a : actividades) {
            if (a instanceof Presentable) {
                ((Presentable) a).presentar();
            } else {
                System.out.println(a.obtenerTitulo() + " no realiza presentacion.");
            }
        }
    }

    private static void reservarActividades(List<Actividad> actividades) {
        for (Actividad a : actividades) {
            if (a instanceof Reservable) {
                try {
                    String mensaje = ((Reservable) a).reservar();
                    System.out.println(mensaje);
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println(a.obtenerTitulo() + " no admite reservas.");
            }
        }
    }
}