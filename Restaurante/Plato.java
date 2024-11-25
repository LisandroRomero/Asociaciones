package Restaurante;

import java.util.ArrayList;

public class Plato {
    private String nombre;
    private double precio;
    private boolean esBebida;
    private ArrayList<Ingrediente> ingredientes;

    public Plato(String nombre, double precio, boolean esBebida) {
        this.nombre = nombre;
        this.precio = precio;
        this.esBebida = esBebida;
        this.ingredientes = new ArrayList<>();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        if (!esBebida) {
            ingredientes.add(ingrediente);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append("\n");
        sb.append("Precio: $").append(precio).append("\n");
        if (!esBebida) {
            sb.append("Ingredientes:\n");
            sb.append(String.format("%-20s %-10s %-10s\n", "Nombre", "Cantidad", "Unidad de Medida"));
            for (Ingrediente ingrediente : ingredientes) {
                sb.append(ingrediente).append("\n");
            }
        }
        sb.append("---------------------------------------------");
        return sb.toString();
    }

}
