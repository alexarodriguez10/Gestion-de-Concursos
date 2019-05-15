package Tiposdeconcursos;

import clases.Envio;
import clases.Evaluacion;
import clasificacion.PuntuacionEquipo;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConcursoSecuencial extends ConcursoLimitado {

    private Map<String, Integer> estadoConcurso;

    public ConcursoSecuencial(String nombre, int numProblemas, long tiempoDuracion, int numIntentos) {
        super(nombre, numProblemas, tiempoDuracion, numIntentos);
        this.estadoConcurso = new HashMap<>();
    }

    @Override
    public boolean cumpleCondicionesConcurso(Envio e) {
        String equipo = e.getNombreEquipo();
        int numProblema = e.getNumProblema();
        if (this.estadoConcurso.containsKey(equipo)) {
            if (numProblema == this.estadoConcurso.get(equipo) && e.getResultado().equals(Evaluacion.ACEPTADO)) {
                numProblema++;
                this.estadoConcurso.replace(equipo, numProblema);
                return super.cumpleCondicionesConcurso(e);
            }
        } else {
            if (numProblema == 1 && e.getResultado().equals(Evaluacion.ACEPTADO)) {
                this.estadoConcurso.put(equipo, 2);
                return super.cumpleCondicionesConcurso(e);
            }
        }

        return false;
    }

    @Override
    public void clasificaciones() {
        Collections.sort(puntuaciones);
        System.out.println(".:ESTADO DEL CONCURSO:.");
        for(PuntuacionEquipo pq : puntuaciones) {
            System.out.println(pq.getEquipo() + " -> Problema Actual: " + pq.getPuntos());
        }
        System.out.println();
    }
    
    @Override
    protected ConcursoSecuencial clone() {
        ConcursoSecuencial c = (ConcursoSecuencial) super.clone();
        c.estadoConcurso = new HashMap<>(c.estadoConcurso);
        return c;
    }
}
