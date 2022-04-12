package com.parameta.servicio;

import com.parameta.dao.EmpleadoDao;
import com.parameta.entidades.Empleado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoDao empleadoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> listarEmpleados() {
        return (List<Empleado>) empleadoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Empleado empleado) {
        empleadoDao.save(empleado);
    }

    @Override
    @Transactional
    public void eliminar(Empleado empleado) {
        empleadoDao.delete(empleado);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado encontrarEmpleado(Empleado empleado) {
        return empleadoDao.findById(empleado.getIdempleado()).orElse(null);// si no 
//        lo encuentra retorna un null por medio de .orElse(null)

    }

}
