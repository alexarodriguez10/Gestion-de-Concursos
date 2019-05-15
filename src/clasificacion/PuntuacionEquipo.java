package clasificacion;

import java.util.Objects;

public class PuntuacionEquipo implements Comparable<PuntuacionEquipo> {

    private final String equipo;
    private int puntos;

    public PuntuacionEquipo(String equipo) {
        this.equipo = equipo;
        this.puntos = 0;
    }
    
    //Metodos de Consulta
    public String getEquipo() { return equipo; }
    public int getPuntos() { return puntos; }  
    
    //Funcionalidades
    public void incrementarPuntos(int n) { this.puntos += n; }
    public void decrementarPuntos(int n) { this.puntos -= n; }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.equipo);
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
        final PuntuacionEquipo other = (PuntuacionEquipo) obj;
        return Objects.equals(this.equipo, other.equipo);
    }
    
    

    @Override
    public int compareTo(PuntuacionEquipo o) {
        int res = 0;
        if(this.puntos > o.puntos) {
            res = 1;
        } else if(this.puntos < o.puntos) {
            res = -1;
        }
        
        return res;
    }
}