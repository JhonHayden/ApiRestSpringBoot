package com.parameta.servicio;

import com.parameta.entidades.Empleado;
import java.util.List;

public interface EmpleadoService {

    public List<Empleado> listarEmpleados();

    public void guardar(Empleado empleado);

    public void eliminar(Empleado empleado);

    public Empleado encontrarEmpleado(Empleado empleado);

}
