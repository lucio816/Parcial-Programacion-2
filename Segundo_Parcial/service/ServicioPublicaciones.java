
package service;

import java.util.List;
import model.EstadoPublicacion;
import model.Publicacion;
import model.TipoPublicacion;


public interface ServicioPublicaciones {
    static void mostrarPublicaciones(List<? extends Publicacion> publicaciones) {
    for (Publicacion publicacion : publicaciones) {
        System.out.println(publicacion);
        }
    }
    static void cargarPublicacionesDePrueba(List<? super Publicacion > publicaciones) {
        publicaciones.add(new Publicacion(204,"Caiman en el supermercado",
        "FloridaMan", 50000, 12500,
        TipoPublicacion.ANIMALES,
        EstadoPublicacion.ACTIVA));
        publicaciones.add(new Publicacion(201,"Abuela gana carrera callejera",
        "Grandma87", 25000,5000,
        TipoPublicacion.PERSECUCION,
        EstadoPublicacion.ACTIVA));
        publicaciones.add(new Publicacion(206,"Fiesta en el pantano",
        "DJSwamp",8000,400,
        TipoPublicacion.FIESTA,
        EstadoPublicacion.DENUNCIADA));
        publicaciones.add(new Publicacion(203, "Turista confunde patrullero con taxi",
        "FloridaMan", 30000, 6000,
        TipoPublicacion.HUMOR,
        EstadoPublicacion.MONETIZADA));
        publicaciones.add(new Publicacion(205,"Influencer intenta domesticar un flamenco",
        "LolaViral",12000, 960,
        TipoPublicacion.ANIMALES,
        EstadoPublicacion.ACTIVA));
        publicaciones.add(new Publicacion(202,"DJ transmite desde una lancha",
        "DJSwamp",4000, 200,
        TipoPublicacion.FIESTA,
        EstadoPublicacion.DENUNCIADA));
    }
    
}