package service;

import exceptions.ElementoDuplicadoException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import model.CSVSerializable;
import persistence.Persistible;
import util.Calculador;

public class PlataformaViral<T extends CSVSerializable & Serializable & Comparable<T>> implements Administrable<T>, Persistible<T>, Iterable<T> {

    private final List<T> elementos;

    public PlataformaViral() {
        this.elementos = new ArrayList<>();
    }

    @Override
    public void agregar(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento no puede ser nulo");
        }
        if (elementos.contains(elemento)) {
            throw new ElementoDuplicadoException("El elemento ya existe en la plataforma: " + elemento);
        }
        elementos.add(elemento);
    }
    @Override
    public void agregarTodos(List<? extends T> nuevosElementos) {
        if (nuevosElementos == null) {
            return;
        }
        for (T elemento : nuevosElementos) {
            agregar(elemento);
        }
    }

    @Override
    public int cantidad() {
        return elementos.size();
    }
    @Override
    public List<T> obtenerTodos() {
        return new ArrayList<>(elementos);
    }

    @Override
    public List<T> filtrar(Predicate<? super T> criterio) {
        List<T> resultado = new ArrayList<>();
        for (T elemento : elementos) {
            if (criterio.test(elemento)) {
                resultado.add(elemento);
            }
        }
        return resultado;
    }
    @Override
    public int contar(Predicate<? super T> criterio) {
        int contador = 0;
        for (T elemento : elementos) {
            if (criterio.test(elemento)) {
                contador++;
            }
        }
        return contador;
    }

    @Override
    public int actualizar(Predicate<? super T> criterio, Consumer<? super T> accion) {
        int afectados = 0;
        for (T elemento : elementos) {
            if (criterio.test(elemento)) {
                accion.accept(elemento);
                afectados++;
            }
        }
        return afectados;
    }

    @Override
    public int eliminarSi(Predicate<? super T> criterio) {
        int eliminados = 0;
        Iterator<T> iterador = elementos.iterator();
        while (iterador.hasNext()) {
            T elemento = iterador.next();
            if (criterio.test(elemento)) {
                iterador.remove();
                eliminados++;
            }
        }
        return eliminados;
    }

    @Override
    public void ordenar() {
        Collections.sort(elementos);
    }

    @Override
    public void ordenar(Comparator<? super T> comparador) {
        elementos.sort(comparador);
    }
    @Override
    public <R> List<R> seleccionarYTransformar(Predicate<? super T> criterio, Function<? super T, ? extends R> transformacion) {
        List<R> resultado = new ArrayList<>();
        for (T elemento : elementos) {
            if (criterio.test(elemento)) {
                resultado.add(transformacion.apply(elemento));
            }
        }
        return resultado;
    }
    public double promediar(Predicate<? super T> criterio, Calculador<? super T> calculador) {
        double suma = 0.0;
        int contador = 0;
        for (T elemento : elementos) {
            if (criterio.test(elemento)) {
                suma += calculador.calcular(elemento);
                contador++;
            }
        }
        if (contador == 0) {
            return 0.0;
        }
        return suma / contador;
    }

    @Override
    public Iterator<T> iterator() {
        return elementos.iterator();
    }
    @Override
    public void guardarBinario(String ruta) throws IOException {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta))) {
            salida.writeObject(new ArrayList<>(elementos));
        }
    }
    @Override
    @SuppressWarnings("unchecked")
    public void cargarBinario(String ruta) throws IOException, ClassNotFoundException {
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            throw new IOException("No existe el archivo binario: " + ruta);
        }
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ruta))) {
            List<T> leidos = (List<T>) entrada.readObject();
            elementos.clear();
            elementos.addAll(leidos);
        }
    }
    @Override
    public void guardarCSV(String ruta, String encabezado) throws IOException {
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            if (encabezado != null && !encabezado.isEmpty()) {
                escritor.write(encabezado);
                escritor.newLine();
            }
            for (T elemento : elementos) {
                escritor.write(elemento.toCSV());
                escritor.newLine();
            }
        }
    }

    @Override
    public void cargarCSV(String ruta, Function<String, ? extends T> conversor) throws IOException {
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            throw new IOException("No existe el archivo CSV: " + ruta);
        }
        List<T> cargados = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            lector.readLine();
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    try {
                        T elemento = conversor.apply(linea);
                        if (elemento != null) {
                            cargados.add(elemento);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Linea CSV ignorada por error de formato o valor: " + linea);
                    }
                }
            }
        }
        elementos.clear();
        elementos.addAll(cargados);
    }
    @Override
    public void exportarReporte(String ruta, String titulo, Predicate<? super T> criterio, Function<? super T, String> formateador) throws IOException {
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            escritor.write(titulo);
            escritor.newLine();
            for (T elemento : elementos) {
                if (criterio.test(elemento)) {
                    escritor.write(formateador.apply(elemento));
                    escritor.newLine();
                }
            }
        }
    }
}