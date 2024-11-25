package Factura;

import java.util.ArrayList;
import java.util.Scanner;

public class Facturacion {

    // Definición de la variable global "artículos" (10 filas por 3 columnas)
    private static final String[][] artículos = {
        {"101", "Leche", "25"},
        {"102", "Gaseosa", "30"},
        {"103", "Fideos", "15"},
        {"104", "Arroz", "28"},
        {"105", "Vino", "120"},
        {"106", "Manteca", "20"},
        {"107", "Lavandina", "18"},
        {"108", "Detergente", "46"},
        {"109", "Jabón en Polvo", "96"},
        {"110", "Galletas", "60"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear una instancia de Factura
        Factura factura = new Factura();

        // Solicitar datos de la factura
        System.out.print("Ingrese la fecha de la factura (dd/mm/aaaa): ");
        String fechaFactura = scanner.nextLine();
        factura.setFechaFactura(fechaFactura);

        long numeroFactura;
        while (true) {
            System.out.print("Ingrese el número de factura (entero positivo): ");
            try {
                numeroFactura = Long.parseLong(scanner.nextLine());
                if (numeroFactura > 0) {
                    factura.setNumeroFactura(numeroFactura);
                    break;
                } else {
                    System.out.println("El número de factura debe ser mayor a cero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }

        String cliente;
        while (true) {
            System.out.print("Ingrese el nombre del cliente: ");
            cliente = scanner.nextLine();
            if (!cliente.isEmpty()) {
                factura.setCliente(cliente);
                break;
            } else {
                System.out.println("El cliente no puede estar vacío.");
            }
        }

        // Proceso de carga de artículos
        boolean continuar = true;
        while (continuar) {
            System.out.print("Ingrese el código del artículo: ");
            String codigoArticulo = scanner.nextLine();

            // Buscar el código en el array de artículos
            String[] articuloEncontrado = buscarArticulo(codigoArticulo);
            if (articuloEncontrado == null) {
                System.out.println("El código ingresado no existe, intente nuevamente.");
                continue;
            }

            // Solicitar cantidad a facturar
            int cantidad;
            while (true) {
                System.out.print("Ingrese la cantidad a facturar: ");
                try {
                    cantidad = Integer.parseInt(scanner.nextLine());
                    if (cantidad > 0) {
                        break;
                    } else {
                        System.out.println("La cantidad debe ser mayor a cero.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingrese un número válido.");
                }
            }

            // Crear el objeto DetalleFactura
            DetalleFactura detalle = new DetalleFactura();
            detalle.setCodigoArticulo(articuloEncontrado[0]);
            detalle.setNombreArticulo(articuloEncontrado[1]);
            detalle.setCantidad(cantidad);

            double precioUnitario = Double.parseDouble(articuloEncontrado[2]);
            detalle.setPrecioUnitario(precioUnitario);

            // Calcular descuento
            double descuento = 0;
            if (cantidad > 5) {
                descuento = precioUnitario * 0.1;
            }
            detalle.setDescuentoItem(descuento);

            // Calcular subtotal
            double subtotal = (precioUnitario * cantidad) - (descuento * cantidad);
            detalle.setSubTotal(subtotal);

            // Agregar el detalle a la factura
            factura.agregarDetalle(detalle);

            // Preguntar si desea continuar
            System.out.print("¿Desea agregar otro artículo? (s/n): ");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("n")) {
                continuar = false;
            }
        }

        // Calcular el total de la factura
        factura.calcularMontoTotal();

        // Imprimir la factura
        imprimirFactura(factura);

        scanner.close();
    }

    // Método para buscar un artículo por código
    private static String[] buscarArticulo(String codigo) {
        for (String[] articulo : artículos) {
            if (articulo[0].equals(codigo)) {
                return articulo;
            }
        }
        return null;
    }

    // Método para imprimir la factura
    private static void imprimirFactura(Factura factura) {
        System.out.println("\nFecha: " + factura.getFechaFactura());
        System.out.println("Número: " + factura.getNumeroFactura());
        System.out.println("Cliente: " + factura.getCliente());
        System.out.println("Código  Nombre         Cantidad Precio  Descuento Subtotal");
        for (DetalleFactura detalle : factura.getDetallesFactura()) {
            System.out.printf("%-7s %-14s %-8d %-6.2f %-9.2f %.2f\n",
                    detalle.getCodigoArticulo(),
                    detalle.getNombreArticulo(),
                    detalle.getCantidad(),
                    detalle.getPrecioUnitario(),
                    detalle.getDescuentoItem() * detalle.getCantidad(),
                    detalle.getSubTotal());
        }
        System.out.printf("Total: %.2f\n", factura.getTotalCalculadoFactura());
    }
}
