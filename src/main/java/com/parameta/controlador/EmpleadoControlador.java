package com.parameta.controlador;

import com.parameta.entidades.Empleado;
import com.parameta.servicio.EmpleadoService;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@RestController
//@RequestMapping("/api/empleado")
@Slf4j
public class EmpleadoControlador {
// 

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
    public String agregar(Empleado empleado) {
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Empleado empleado, Errors errores) {
//        Empleado empleadoListo = empleado;

//        if (errores.hasErrors()) {
//            return "modificar";
//        }
        //convierto a string el sql.Date de FechaNacimiento
        String fechaNacim = empleado.getFechaNacimiento().toString();

        //convierto a string el sql.Date de FechaNacimiento
        String fechaVincul = empleado.getFechaVinculacion().toString();

        System.out.println("fechaNacimiento:");
        System.out.println(fechaNacim);
        //calcular edad Empleado
        DateTimeFormatter fmtEdad = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNac = LocalDate.parse(fechaNacim, fmtEdad);
        LocalDate ahora = LocalDate.now();

        Period periodoEdad = Period.between(fechaNac, ahora);
        //calcular tiempoVinculacion
        DateTimeFormatter fmtTiempoVinculacion = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaVinculacion = LocalDate.parse(fechaVincul, fmtTiempoVinculacion);

        Period periodoTiempoVinculacion = Period.between(fechaVinculacion, ahora);
//        System.out.printf("Tu edad es: %s años, %s meses y %s días",
//                periodo.getYears(), periodo.getMonths(), periodo.getDays());

        Integer edadAños = periodoEdad.getYears();
        Integer edadMeses = periodoEdad.getMonths();
        Integer edadDias = periodoEdad.getDays();

        Integer añosVinculacion = periodoTiempoVinculacion.getYears();
        Integer mesesVinculacion = periodoTiempoVinculacion.getMonths();

        if (edadAños > 18) {
            System.out.println("si entre");
            System.out.println(empleado);
            empleado.setEdad(edadAños.toString() + "/" + edadMeses.toString() + "/" + edadDias.toString());
            empleado.setTiempoVinculacion(añosVinculacion.toString() + "/" + mesesVinculacion.toString());

            System.out.println(empleado);
            empleadoService.guardar(empleado);
            return "redirect:/"; // me retorna a la pagina de inicio
        } else {
            return "modificar";
        }
    }

    @GetMapping("/editar/{idempleado}")// path de el html 
    public String editar(Empleado empleado, Model model) {
        empleado = empleadoService.encontrarEmpleado(empleado);
        model.addAttribute("empleado", empleado);// me permite compartir con la vista el objeto empleado encontrado
        return "modificar";
    }

    @GetMapping("/eliminar")// path de el html .. 
    public String eliminar(Empleado empleado, Model model) {
        empleadoService.eliminar(empleado);
        model.addAttribute("empleado", empleado);// me permite compartir con la vista el objeto empleado encontrado
        return "redirect:/";
    }

}
