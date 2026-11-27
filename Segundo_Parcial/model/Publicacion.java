
package model;

import java.io.Serializable;
import java.util.Objects;

public class Publicacion implements Comparable<Publicacion>, Serializable, CSVSerializable{
    private static final long serialVersionUID = 1L; 
    private static final String Encabezado_CSV ="id,titulo,autor,reproducciones,likes,tipo,estado";
    
    private final int id;
    private String titulo;
    private String autor;
    private int reproducciones;
    private int likes;
    private TipoPublicacion tipo;
    private EstadoPublicacion estado;

    public Publicacion(int id, String titulo, String autor, int reproducciones, int likes, TipoPublicacion tipo, EstadoPublicacion estado) {
        
        if(reproducciones < 0){
            throw new IllegalArgumentException ("Las reproducciones no deben ser menor que cero");        
        }
        
        if(likes< 0){
            throw new IllegalArgumentException ("Los likes no deben ser menor que cero");        
        }
        
        
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.reproducciones = reproducciones;
        this.likes = likes;
        this.tipo = tipo;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }


    public int getReproducciones() {
        return reproducciones;
    }
    public void setReproducciones(int reproducciones) {
        if(reproducciones < 0){
            throw new IllegalArgumentException ("Las reproducciones no deben ser menor que cero");        
        }
        this.reproducciones = reproducciones;
    }
    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        if(likes< 0){
            throw new IllegalArgumentException ("Los likes no deben ser menor que cero");        
        }
        
        this.likes = likes;
    }


    public TipoPublicacion getTipo() {
        return tipo;
    }
    public void setTipo(TipoPublicacion tipo) {
        this.tipo = tipo;
    }

    public EstadoPublicacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPublicacion estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Publicacion other = (Publicacion) obj;
        return this.id == other.id;
    }
   
    public double getIntereaccion(){
        if(reproducciones == 0){
            return 0.0;}
        return ((double) likes / reproducciones )* 100.0;
    }
    
    
    public static String getCSVHeader(){
        return Encabezado_CSV;
    }
    
    @Override
    public String toCSV(){
        return id + "," + titulo + "," + reproducciones + "," + likes + "," + tipo.name()+ ","+ estado.name();
    }


    public static Publicacion fromCSV(String linea){
        String[] partes = linea.split(",");
        if(partes.length != 7){
            throw new IllegalArgumentException ("Linea invalida: " + linea);        
        }
        
        
        int id = Integer.parseInt(partes[0]);
        String titulo = partes[1];
        String autor = partes[2];
        int reproducciones = Integer.parseInt(partes[3]);
        int likes = Integer.parseInt(partes[4]);
        TipoPublicacion tipo = TipoPublicacion.valueOf(partes[5].toUpperCase());
        EstadoPublicacion estado = EstadoPublicacion.valueOf(partes[6]);
        return new Publicacion (id , titulo , autor, reproducciones , likes, tipo ,estado);
    
    }

    @Override 
    public int compareTo(Publicacion otra){
        return Integer.compare(this.id , otra.id);
    }

    
    @Override
    public String toString() {
        return "Publicacion{" + "id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", reproducciones=" + reproducciones + ", likes=" + likes + ", tipo=" + tipo + ", estado=" + estado + '}';
    }
    

}
