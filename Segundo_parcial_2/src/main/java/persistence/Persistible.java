
package persistence;

import java.io.IOException;
import java.util.function.Function;
import java.util.function.Predicate;


public interface Persistible <T> {
    void guardarBinario(String ruta)throws IOException;
    void cargarBinario(String ruta)throws IOException, ClassNotFoundException;
    void guardarCSV(String ruta, String encabezado) throws IOException;
    void cargarCSV( String ruta, Function<String, ? extends T> conversor) throws IOException;
    void exportarReporte(String ruta, String titulo, Predicate<? super T> criterio,
    Function<? super T, String> formateador) throws IOException;
}
