package Colegio;

import java.util.ArrayList;

public class Alumno {
    private String nombreCompleto;
    private long legajo;
    private ArrayList<Nota> notas;

    public Alumno(String nombreCompleto, long legajo) {
        this.nombreCompleto = nombreCompleto;
        this.legajo = legajo;
        this.notas = new ArrayList<>();
    }

    public void agregarNota(Nota nota) {
        notas.add(nota);
    }

    public double calcularPromedio() {
        double suma = 0;
        for (Nota nota : notas) {
            suma += nota.getNotaExamen();
        }
        return notas.isEmpty() ? 0 : suma / notas.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Alumno: ").append(nombreCompleto).append(", Legajo: ").append(legajo).append("\n");
        sb.append("Notas:\n");
        for (Nota nota : notas) {
            sb.append("  ").append(nota).append("\n");
        }
        sb.append("Promedio: ").append(calcularPromedio()).append("\n");
        return sb.toString();
    }
}
