package main;

import clases.Concurso;
import Tiposdeconcursos.ConcursoCompetitivo;
import Tiposdeconcursos.ConcursoLimitado;
import Tiposdeconcursos.ConcursoSecuencial;
import java.util.ArrayList;
import java.util.List;

public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Declara y construye un concurso limitado con nombre “Sesión 1”, 2 problemas, 5
        minutos de tiempo y límite de 1 envío por problema. Establece como soluciones a los
        problemas los valores “23” para el primero y “15” para el segundo.*/
        ConcursoLimitado cl = new ConcursoLimitado("Sesión 1", 2, 5, 1); 
        cl.establecerSolucionProblema(1, "23");
        cl.establecerSolucionProblema(2, "15");
        
        /*Declara y construye un concurso competitivo con nombre “Sesión 2”, 3 problemas y 15
        minutos de tiempo. Las soluciones a los problemas son “AACTG”, “null” y “[13, 98]”.*/
        ConcursoCompetitivo cc = new ConcursoCompetitivo("Sesión 2", 3, 15);
        cc.establecerSolucionProblema(1, "AACTG");
        cc.establecerSolucionProblema(2, "null");
        cc.establecerSolucionProblema(3, "[13, 98]");
        
        /*Declara y construye un concurso secuencial con nombre “Sesión 3”, 3 problemas, 30
        minutos y límite de 2 envíos por problema. Las soluciones a los problemas son “null”,
        “[0, 3]” y “AAA”.*/
        ConcursoSecuencial cs = new ConcursoSecuencial("Sesión 3", 3, 30, 2);
        cs.establecerSolucionProblema(1, "null");
        cs.establecerSolucionProblema(2, "[0, 3]");
        cs.establecerSolucionProblema(3, "AAA");
        
        /*Crea una lista de concursos y añade los tres concursos creados en los pasos
        anteriores.*/
        List<Concurso> concursos = new ArrayList<>();
        concursos.add(cl);
        concursos.add(cc);
        concursos.add(cs);
        
        /*Recorre los concursos y realiza lo siguiente:
            o Muestra el nombre del concurso.
            o Añade los equipos “Equipo 1”, “Equipo 2” y “Equipo 3”.
            o Inicia el concurso.
            o Recorre los equipos para que cada uno realice el envío “null” a todos los
            problemas.
            o Muestra la clasificación del concurso en el siguiente formato:
                Clasificación:
                    - Equipo 1 -> 2
                    - Equipo 2 -> -2
                    - Equipo 3 -> -2
            o Si el concurso es secuencial, consulta el estado del concurso y muestra la
            información por la consola en el siguiente formato:
                Estado del concurso:
                    - Equipo 1 -> problema actual: 2
                    - Equipo 2 -> problema actual: 2
                    - Equipo 3 -> problema actual: 2*/
        for(Concurso c : concursos) {
            System.out.println("NOMBRE DEL CONCURSO: " + c.getNombre());
            c.añadirEquipos("Equipo 1", "Equipo 2", "Equipo 3");
            c.iniciar();
            for(String str : c.getEquipos()) {
                for(int i = 1; i <= c.getNumProblemas(); i++) {
                    c.registrarEnvio(str, i, "null");
                }
            }
            c.clasificaciones();
        }
    }
}
