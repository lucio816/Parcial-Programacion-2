package app;

import config.AppConfig;
import exceptions.ElementoDuplicadoException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.EstadoPublicacion;
import model.Publicacion;
import model.TipoPublicacion;
import service.PlataformaViral;
import service.ServicioPublicaciones;

public class main {

    public static void main(String[] args) {
        try {
            PlataformaViral<Publicacion> plataforma;
            plataforma = new PlataformaViral<>();
            List<Publicacion> publicacionesDemo = new ArrayList<>();
            ServicioPublicaciones.cargarPublicacionesDePrueba(publicacionesDemo);
            plataforma.agregarTodos(publicacionesDemo);

            System.out.println("PUBLICACIONES CARGADAS:");
            for (Publicacion p : plataforma) {
                System.out.println(p);
            }

            System.out.println("\nPUBLICACIONES ACTIVAS:");
            plataforma.filtrar(p -> p.getEstado() == EstadoPublicacion.ACTIVA)
                    .forEach(p -> System.out.println(p));

            System.out.println("\nPUBLICACIONES VIRALES:");
            plataforma.filtrar(p -> p.getReproducciones() >= 20000 && p.getIntereaccion() >= 15.0)
                    .forEach(p -> System.out.println(p));

            System.out.println("\nCONTENIDOS DE ENTRETENIMIENTO:");
            plataforma.filtrar(p -> (p.getTipo() == TipoPublicacion.HUMOR || p.getTipo() == TipoPublicacion.ANIMALES) && p.getEstado() != EstadoPublicacion.DENUNCIADA)
                    .forEach(p -> System.out.println(p));

            int bajaPopularidad = plataforma.contar(p -> p.getReproducciones() < 10000 || p.getIntereaccion() < 10.0);
            System.out.println("Publicaciones con baja popularidad: " + bajaPopularidad);

            int monetizadas = plataforma.actualizar(
                    p -> p.getEstado() == EstadoPublicacion.ACTIVA && p.getReproducciones() >= 20000 && p.getIntereaccion() >= 15.0,
                    p -> p.setEstado(EstadoPublicacion.MONETIZADA)
            );
            System.out.println("Publicaciones monetizadas: " + monetizadas);

            int promocionadas = plataforma.actualizar(
                    p -> p.getEstado() == EstadoPublicacion.MONETIZADA,
                    p -> {p.setReproducciones(p.getReproducciones() + 5000);p.setLikes(p.getLikes() + 1000);
                    }
            );
            System.out.println("Publicaciones promocionadas: " + promocionadas);

            List<String> titulos = plataforma.seleccionarYTransformar(
                    p -> p.getEstado() == EstadoPublicacion.MONETIZADA,
                    p -> p.getTitulo()
            );
            titulos.forEach(t -> System.out.println(t));

            List<Double> interacciones = plataforma.seleccionarYTransformar(
                    p -> p.getEstado() != EstadoPublicacion.DENUNCIADA,
                    p -> p.getIntereaccion()
            );
            interacciones.forEach(i -> System.out.println(i));

            double promedioReproducciones = plataforma.promediar(
                    p -> p.getEstado() == EstadoPublicacion.MONETIZADA,
                    p -> p.getReproducciones()
            );
            System.out.println("Promedio de reproducciones: " + promedioReproducciones);

            double ingresoPromedio = plataforma.promediar(
                    p -> p.getEstado() == EstadoPublicacion.MONETIZADA,
                    p -> p.getReproducciones() * 0.02 + p.getLikes() * 0.05
            );
            System.out.println("Ingreso publicitario promedio: $" + ingresoPromedio);

            System.out.println("\nPUBLICACIONES ORDENADAS POR ID:");
            plataforma.ordenar();
            plataforma.forEach(p -> System.out.println(p));

            System.out.println("\nRANKING DE POPULARIDAD:");
            plataforma.ordenar((p1, p2) -> Double.compare(p2.getIntereaccion(), p1.getIntereaccion()));
            plataforma.forEach(p -> System.out.println(p));

            System.out.println("\nPUBLICACIONES POR AUTOR:");
            plataforma.ordenar(
                    Comparator.comparing(Publicacion::getAutor, String.CASE_INSENSITIVE_ORDER)
                            .thenComparing(Comparator.comparingInt(Publicacion::getReproducciones).reversed())
            );
            plataforma.forEach(p -> System.out.println(p));

            plataforma.exportarReporte(
                    AppConfig.ARCHIVO_TXT,
                    AppConfig.TITULO_REPORTE,
                    p -> p.getEstado() == EstadoPublicacion.MONETIZADA && p.getIntereaccion() >= 15.0,
                    p -> "[" + p.getId() + "] " + p.getTitulo() + " @" + p.getAutor() + " " + p.getIntereaccion() + "%"
            );

            plataforma.guardarBinario(AppConfig.ARCHIVO_BINARIO);
            PlataformaViral<Publicacion> recuperadaBinario = new PlataformaViral<>();
            recuperadaBinario.cargarBinario(AppConfig.ARCHIVO_BINARIO);
            System.out.println("\nPUBLICACIONES DESDE BINARIO:");
            recuperadaBinario.forEach(p -> System.out.println(p));

            plataforma.guardarCSV(
                    AppConfig.ARCHIVO_CSV,
                    Publicacion.getCSVHeader()
            );
            PlataformaViral<Publicacion> recuperadaCSV = new PlataformaViral<>();
            recuperadaCSV.cargarCSV(
                    AppConfig.ARCHIVO_CSV,
                    linea -> Publicacion.fromCSV(linea)
            );
            System.out.println("\nPUBLICACIONES DESDE CSV:");
            recuperadaCSV.forEach(p -> System.out.println(p));

            int eliminadas = plataforma.eliminarSi(p -> p.getEstado() == EstadoPublicacion.DENUNCIADA);
            System.out.println("Publicaciones eliminadas: " + eliminadas);
            System.out.println("Publicaciones restantes: " + plataforma.cantidad());
            ServicioPublicaciones.mostrarPublicaciones(plataforma.obtenerTodos());

            List<Object> destinoGenerico = new ArrayList<>();
            ServicioPublicaciones.cargarPublicacionesDePrueba(destinoGenerico);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error de archivo: " + e.getMessage());
        } catch (ElementoDuplicadoException e) {
            System.err.println(e.getMessage());}
    }
}