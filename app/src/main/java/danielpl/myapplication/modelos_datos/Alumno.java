package danielpl.myapplication.modelos_datos;

import java.io.Serializable;

public class Alumno implements Serializable {
    private String nombre;
    private String apellido;
    private String ciclo;
    private char grupo;

    public Alumno() {
    }

    public Alumno(String nombre, String apellido, String ciclo, char grupo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciclo = ciclo;
        this.grupo = grupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public char getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return  "Nombre => " + nombre + "\n" +
                "Apellido => " + apellido + "\n" +
                "Ciclo => " + ciclo + "\n" +
                "Grupo => " + grupo ;
    }
}
