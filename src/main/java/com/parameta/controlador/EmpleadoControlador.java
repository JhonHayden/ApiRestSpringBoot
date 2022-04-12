package com.parameta.controlador;

import com.parameta.entidades.Empleado;
import com.parameta.servicio.EmpleadoService;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class EmpleadoControlador {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/")
    public String inicio(Model model) {
        var empleados = empleadoService.listarEmpleados();

        log.info("Ejecutando el Controlador de Spring MVC");
        model.addAttribute("empleados", empleados);
        return "index";
    }

    @GetMapping("/agregar")// path de el html 
    public String agregar(Empleado empleado, Model model) {

        model.addAttribute("fechaActual", fechaActual());
        model.addAttribute("mayorEdad", mayorEdad());
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Empleado empleado, Errors errores, Model model) {
        String fechaNacim = empleado.getFechaNacimiento().toString();

        //convierto a string el sql.Date de FechaNacimiento
        String fechaVincul = empleado.getFechaVinculacion().toString();

        //calcular edad Empleado
        DateTimeFormatter fmtEdad = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNac = LocalDate.parse(fechaNacim, fmtEdad);
        LocalDate ahora = LocalDate.now();

        Period periodoEdad = Period.between(fechaNac, ahora);
        //calcular tiempoVinculacion
        DateTimeFormatter fmtTiempoVinculacion = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaVinculacion = LocalDate.parse(fechaVincul, fmtTiempoVinculacion);

        Period periodoTiempoVinculacion = Period.between(fechaVinculacion, ahora);

        Integer yearsedad = periodoEdad.getYears();
        Integer edadMeses = periodoEdad.getMonths();
        Integer edadDias = periodoEdad.getDays();

        Integer yearsVinculacion = periodoTiempoVinculacion.getYears();
        Integer mesesVinculacion = periodoTiempoVinculacion.getMonths();
        System.out.println(yearsedad);

        if (yearsedad > 17) {

            empleado.setEdad(yearsedad.toString() + "/" + edadMeses.toString() + "/" + edadDias.toString());
            empleado.setTiempoVinculacion(yearsVinculacion.toString() + "/" + mesesVinculacion.toString());

            empleadoService.guardar(empleado);
            return "redirect:/"; // me retorna a la pagina de inicio
        } else {
            var mesaje = "menor de edad";
            model.addAttribute("mesaje", mesaje);
            return "modificar";
        }
    }

    @GetMapping("/editar/{idempleado}")// path de el html 
    public String editar(Empleado empleado, Model model) {
        empleado = empleadoService.encontrarEmpleado(empleado);
        model.addAttribute("fechaActual", fechaActual());
        model.addAttribute("mayorEdad", mayorEdad());
        model.addAttribute("empleado", empleado);// me permite compartir con la vista el objeto empleado encontrado
        return "modificar";
    }

    @GetMapping("/eliminar")// path de el html .. 
    public String eliminar(Empleado empleado, Model model) {
        empleadoService.eliminar(empleado);
        model.addAttribute("empleado", empleado);// me permite compartir con la vista el objeto empleado encontrado
        return "redirect:/";
    }

    public static String fechaActual() {
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        String fechaActual = date.toString();

        return fechaActual;
    }

    public static String mayorEdad() {

        String actual = "";

        for (int i = 0; i < 4; i++) {

            actual = actual + fechaActual().charAt(i);
        }

        Integer actualINTEGER = Integer.parseInt(actual);

        Integer mayorEdadINTEGER = actualINTEGER - 19;
        String mayorEdadSTRING = mayorEdadINTEGER.toString();
        System.out.println("añoMayorEdad:");
        System.out.println(mayorEdadSTRING);

        return mayorEdadSTRING;
    }

}
