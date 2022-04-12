package com.parameta.entidades;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "empleado", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"numeroDocumento"})})// regla para numeros de cedulas unicos no repetidos  
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idempleado;

    @NotEmpty
    @Column(name = "nombres", nullable = false)
    private String nombres;

    @NotEmpty
    @Column(name = "edad", nullable = false)
    private String edad;

    @NotEmpty
    @Column(name = "tiempoVinculacion", nullable = false)
    private String tiempoVinculacion;

    @NotEmpty
    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @NotEmpty
    @Column(name = "tipoDocumento", nullable = false)
    private String tipoDocumento;

    @NotEmpty
    @Column(name = "numeroDocumento", nullable = false)
    private String numeroDocumento;

    @NotEmpty
    @Column(name = "cargo", nullable = false)
    private String cargo;

    @Column(name = "salario", nullable = false)
    private double salario;

//    @Past
//    @NotNull(message = "No puede estar vacio")
    @Column(name = "fechaNacimiento", nullable = false)
//    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date fechaNacimiento;

//    @PastOrPresent
    @Column(name = "fechaVinculacion", nullable = false)
//    @NotNull(message = "No puede estar vacio")
//    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date fechaVinculacion;

    public Long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Long idempleado) {
        this.idempleado = idempleado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getFechaNacimiento() {

        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaVinculacion() {
        return fechaVinculacion;
    }

    public void setFechaVinculacion(Date fechaVinculacion) {
        this.fechaVinculacion = fechaVinculacion;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTiempoVinculacion() {
        return tiempoVinculacion;
    }

    public void setTiempoVinculacion(String tiempoVinculacion) {
        this.tiempoVinculacion = tiempoVinculacion;
    }

    public Empleado() {
    }

}
