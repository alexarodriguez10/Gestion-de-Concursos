package Tiposdeconcursos;

import clases.Concurso;
import clases.Envio;
import java.util.HashMap;
import java.util.Map;

public class ConcursoLimitado extends Concurso {

    private final int numIntentos;
    private Map<Envio, Integer> intentos;

    public ConcursoLimitado(String nombre, int numProblemas, long tiempoDuracion, int numIntentos) {
        super(nombre, numProblemas, tiempoDuracion);
        this.numIntentos = numIntentos;
        this.intentos = new HashMap<>();
    }

    @Override
    public boolean cumpleCondicionesConcurso(Envio e) {
        if (this.intentos.containsKey(e)) {
            int numI = this.intentos.get(e);
            if (numI < this.numIntentos) {
                numI++;
                this.intentos.replace(e, numI);
                return true;
            }

            return false;
        } else {
            this.intentos.put(e, 1);
            return true;
        }
    }

    @Override
    protected ConcursoLimitado clone() {
        ConcursoLimitado c = (ConcursoLimitado) super.clone();
        c.intentos = new HashMap<>(c.intentos);
        return c;
    }
}
