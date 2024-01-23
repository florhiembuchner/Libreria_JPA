/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author Flor Hiembuchner
 */
@Entity
public class Prestamo {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   
   @Temporal(TemporalType.DATE)
   private Date fechaPrestamo;
   
   @Temporal(TemporalType.DATE)
   private Date fechaDevolucion;
   
   @OneToOne
   private Libro libro;
   
   @OneToOne
   private Cliente cliente;
   
   private Boolean alta;

   public Prestamo() {
   }

    public Prestamo(Date fechaPrestamo, Date dechaDevolucion, Libro libro, Cliente cliente, Boolean alta) {
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = dechaDevolucion;
        this.libro = libro;
        this.cliente = cliente;
        this.alta = alta;
    }
    

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date dechaDevolucion) {
        this.fechaDevolucion = dechaDevolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Integer getId() {
        return id;
    }

    
    
   
   
   
   
}
