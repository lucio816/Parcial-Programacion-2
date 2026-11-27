
package util;

@FunctionalInterface
public interface Calculador<T> {
    
    double calcular(T elemento);
}