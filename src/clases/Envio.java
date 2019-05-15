package clases;

import java.util.Objects;

public class Envio {
    private final String nombreEquipo;
    private final int numProblema;
    private final String solucion;
    private final Evaluacion resultado;

    public Envio(String nombreEquipo, int numProblema, String solucion, Evaluacion resultado) {
        this.nombreEquipo = nombreEquipo;
        this.numProblema = numProblema;
        this.solucion = solucion;
        this.resultado = resultado;
    }
    
    //Metodos de Consulta
    public String getNombreEquipo() { return nombreEquipo; }
    public int getNumProblema() { return numProblema; }
    public String getSolucion() { return solucion; }
    public Evaluacion getResultado() { return resultado; }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
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
        final Envio other = (Envio) obj;
        if (this.numProblema != other.numProblema) {
            return false;
        }
        return Objects.equals(this.nombreEquipo, other.nombreEquipo);
    }
}
