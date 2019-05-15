package clases;

import clasificacion.PuntuacionEquipo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Concurso implements Cloneable {

    protected String nombre;
    protected final int numProblemas;
    protected Set<String> equipos;
    protected final long tiempoDuracion;
    protected List<String> soluciones;
    protected List<Envio> envios;
    protected boolean iniciado;

    //
    protected long fin; //Marca final de tiempo
    protected List<PuntuacionEquipo> puntuaciones;

    protected Concurso(String nombre, int numProblemas, long tiempoDuracion) {
        this.nombre = nombre;
        this.numProblemas = numProblemas;
        this.tiempoDuracion = tiempoDuracion;
        this.equipos = new HashSet<>();
        this.soluciones = new ArrayList<>();
        this.iniciado = false;
    }

    //Metodos de Consulta
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<String> getEquipos() {
        return equipos;
    }

    public int getNumProblemas() {
        return numProblemas;
    }

    public boolean isIniciado() {
        return iniciado;
    }

    public void setIniciado(boolean iniciado) {
        this.iniciado = iniciado;
    }

    public List<String> getSoluciones() {
        return new ArrayList<>(soluciones);
    }

    public List<Envio> getEnvios() {
        return new ArrayList<>(envios);
    }

    //Funcionalidades para los Equipos
    public void añadirEquipos(String... nombres) {
        Collections.addAll(equipos, nombres);
    }

    public boolean eliminarEquipos(String nombre) {
        return equipos.remove(nombre);
    }

    //Funcionaidades para las Soluciones
    public String getSolucion(int numProblema) {
        return soluciones.get(numProblema - 1);
    }

    public void establecerSolucionProblema(int numProblema, String solucion) {
        this.soluciones.add(numProblema - 1, solucion);
    }

    public boolean isPreparado() {
        return (soluciones.size() == this.numProblemas);
    }

    public boolean iniciar() {
        if (isPreparado()) {
            setIniciado(true);
            this.envios = new ArrayList<>();
            this.puntuaciones = new ArrayList<>();
            fin = System.currentTimeMillis() + (this.tiempoDuracion * 60000);
            return true;
        }

        return false;
    }

    public boolean enMarcha() {
        if (iniciado) {
            long actual = System.currentTimeMillis();
            return (actual < fin);
        }
        return false;
    }

    //Funcionalidades para los Envios
    public abstract boolean cumpleCondicionesConcurso(Envio e);
    public Envio registrarEnvio(String equipo, int numP, String respuesta) {
        if (cumpleCondicionesGenerales(equipo, numP, respuesta)) {
            String str = getSolucion(numP);
            PuntuacionEquipo pq = new PuntuacionEquipo(equipo);
            Envio e;
            if (str.equals(respuesta)) {
                e = new Envio(equipo, numP, respuesta, Evaluacion.ACEPTADO);
                if (puntuaciones.contains(pq)) {
                    for (PuntuacionEquipo p : puntuaciones) {
                        if (p.getEquipo().equals(equipo)) {
                            p.incrementarPuntos(3);
                        }
                    }
                } else {
                    pq.incrementarPuntos(3);
                    puntuaciones.add(pq);
                }
            } else {
                e = new Envio(equipo, numP, respuesta, Evaluacion.RECHAZADO);
                if (puntuaciones.contains(pq)) {
                    for (PuntuacionEquipo p : puntuaciones) {
                        if (p.getEquipo().equals(equipo)) {
                            p.decrementarPuntos(1);
                        }
                    }
                } else {
                    pq.decrementarPuntos(1);
                    puntuaciones.add(pq);
                }
            }

            if (cumpleCondicionesConcurso(e)) {
                this.envios.add(e);
                return e;
            }
        }

        return null;
    }

    protected boolean cumpleCondicionesGenerales(String equipo, int numP, String respuesta) {
        if (this.equipos.contains(equipo)) {
            if (numP > 0 && numP <= this.numProblemas && respuesta != null && !respuesta.isEmpty()) {
                if (enMarcha()) {
                    for (Envio e : this.envios) {
                        String str = e.getNombreEquipo();
                        if (equipo.equals(str) && numP == e.getNumProblema()) {
                            if (e.getResultado().equals(Evaluacion.ACEPTADO)) {
                                return false;
                            }
                        }
                    }

                    return true;
                }
            }
        }

        return false;
    }

    //Funcionalidades para las Puntuaciones
    public void clasificaciones() {
        Collections.sort(puntuaciones);
        System.out.println(".:CLASIFICACION:.");
        for (PuntuacionEquipo pq : puntuaciones) {
            System.out.println(pq.getEquipo() + " -> " + pq.getPuntos());
        }
        System.out.println();
    }

    //Metodos de la Clase Object
    @Override
    protected Concurso clone() {
        try {
            Concurso c = (Concurso) super.clone();
            c.envios = new ArrayList<>();
            c.puntuaciones = new ArrayList<>();
            c.equipos = new HashSet<>(c.equipos);
            c.soluciones = new ArrayList<>(c.soluciones);
            return c;
        } catch (CloneNotSupportedException e) {
            System.err.println("ERROR EN LA CLONACION: " + e.getMessage());
        }

        return null;
    }
}
