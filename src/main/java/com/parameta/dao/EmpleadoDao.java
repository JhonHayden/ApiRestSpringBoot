package com.parameta.dao;

import com.parameta.entidades.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoDao extends CrudRepository<Empleado, Long> {

}
