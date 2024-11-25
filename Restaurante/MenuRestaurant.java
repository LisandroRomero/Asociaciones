package Restaurante;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuRestaurant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Plato> platos = new ArrayList<>();

        System.out.println("Ingrese la cantidad de platos:");
        int cantidadPlatos = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        for (int i = 0; i < cantidadPlatos; i++) {
            System.out.println("Ingrese el nombre del plato:");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese el precio del plato:");
            double precio = scanner.nextDouble();

            System.out.println("¿Es bebida? (true/false):");
            boolean esBebida = scanner.nextBoolean();
            scanner.nextLine(); // Consumir salto de línea

            Plato plato = new Plato(nombre, precio, esBebida);

            if (!esBebida) {
                System.out.println("Ingrese la cantidad de ingredientes:");
                int cantidadIngredientes = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                for (int j = 0; j < cantidadIngredientes; j++) {
                    System.out.println("Ingrese el nombre del ingrediente:");
                    String ingredienteNombre = scanner.nextLine();

                    System.out.println("Ingrese la cantidad:");
                    double cantidad = scanner.nextDouble();
                    scanner.nextLine(); // Consumir salto de línea

                    System.out.println("Ingrese la unidad de medida:");
                    String unidad = scanner.nextLine();

                    plato.agregarIngrediente(new Ingrediente(ingredienteNombre, cantidad, unidad));
                }
            }
            platos.add(plato);
        }

        System.out.println("\n............................MENÚ............................");
        for (Plato plato : platos) {
            System.out.println(plato);
        }
        scanner.close();
    }
}
