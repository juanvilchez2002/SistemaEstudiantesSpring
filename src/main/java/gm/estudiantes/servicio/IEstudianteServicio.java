package gm.estudiantes.servicio;

import gm.estudiantes.modelo.Estudiante;

import java.util.List;

public interface IEstudianteServicio {

    //definiendo los metodos a usar
    public List<Estudiante> listarEstudiantes();
    public Estudiante buscarEstudiantePorId(Integer idEstudiante);
    //aqui se guardara y se modificara los registros
    public void guardarEstudiante(Estudiante estudiante);
    public void eliminarEstudiante(Estudiante estudiante);


}
