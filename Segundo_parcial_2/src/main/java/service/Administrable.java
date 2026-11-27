
package service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Administrable <T> {
    void agregar(T elemento);
    void agregarTodos(List<? extends T> elementos);
    int cantidad();
    List<T> obtenerTodos();
    List<T> filtrar(Predicate<? super T> criterio);
    int contar(Predicate<? super T> criterio);
    int actualizar(
    Predicate<? super T> criterio,
    Consumer<? super T> accion
    );
    int eliminarSi(Predicate<? super T> criterio);
    void ordenar();
    void ordenar(Comparator<? super T> comparador);
    <R> List<R> seleccionarYTransformar( Predicate<? super T> criterio,
    Function<? super T, ? extends R> transformacion);
}
