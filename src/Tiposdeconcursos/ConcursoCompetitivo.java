package Tiposdeconcursos;
import clases.Concurso;
import clases.Envio;
import clases.Evaluacion;
import java.util.ArrayList;

public class ConcursoCompetitivo extends Concurso {
    private ArrayList<Envio> enviosAceptados;
    public ConcursoCompetitivo(String nombre, int numProblemas, long tiempoDuracion) {
        super(nombre, numProblemas, tiempoDuracion);
        this.enviosAceptados = new ArrayList<>();
    }
    
    public boolean isProblemaResuelto(int numP) {
        for(Envio e : this.enviosAceptados) {
            if(e.getNumProblema() == numP) {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean cumpleCondicionesConcurso(Envio e) {
        if(e.getResultado().equals(Evaluacion.ACEPTADO)) {
            if(enviosAceptados.isEmpty()) {
                enviosAceptados.add(e);
                return true;
            }else{
                if(enviosAceptados.contains(e)) {
                    return false;
                }else{
                    enviosAceptados.add(e);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected ConcursoCompetitivo clone() {
        ConcursoCompetitivo c = (ConcursoCompetitivo) super.clone();
        c.enviosAceptados = new ArrayList<>(c.enviosAceptados);
        return c;
    }
}