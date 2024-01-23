/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import libreria.entidades.Cliente;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.persistencia.PrestamoDaoExt;

/**
 *
 * @author Flor Hiembuchner
 */
public class PrestamoServicio {
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    PrestamoDaoExt dao = new PrestamoDaoExt();
    LibroServicio ls = new LibroServicio();
    ClienteServicio cs = new ClienteServicio();
    
    @SuppressWarnings("UnusedAssignment")
    public void darPrestamo() {
        
        Prestamo prestamo = new Prestamo();
        String titulo;
        boolean flag=true;
                do{
                System.out.println("Ingrese el título del libro que desea retirar");
                titulo = leer.next();
                if (ls.buscarLibroTitulo(titulo).isEmpty()) {
                    System.out.println("El libro no está en stock.");
                    System.out.println("¿Desea buscar otro libro? S/N");
                    String respuesta = leer.next();
                    if (!respuesta.equalsIgnoreCase("S")) {
                        flag=false;
                    }
                }else{
                    flag=false;
                }
                }while(flag);
                
                 Libro libro=new Libro();
                    try {
                        libro = ls.buscarLibroTitulo(titulo).get(0);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                   
                    if (libro.getEjemplaresRestantes() > 0) {
                        
                        try {
                            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1);
                            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - 1);
                            prestamo.setLibro(libro);
                            ls.dao.guardar(libro);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                      
                    } else {
                        System.out.println("Ya no quedan ejemplares del libro para prestar :c");
                       
                    }
                
            
                Cliente cliente;
            
                System.out.println("Ingrese el DNI del cliente");
                long dni = leer.nextLong();
                if (cs.buscarClienteDocumento(dni).isEmpty()) {
                    System.out.println("Cliente no registrado, ¿desea ingresarlo? S/N");
                    String respuesta2 = leer.next();
                    if (respuesta2.equalsIgnoreCase("S")) {
                        
                            cliente = cs.crearClienteNuevo();
                    }
                        
                    
                } else {
                    try {
                        cliente = cs.buscarClienteDocumento(dni).get(0);
                        prestamo.setCliente(cliente);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            Date fechaPrestamo = new Date();
                    try {
                        prestamo.setFechaPrestamo(fechaPrestamo);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
            
            
                try {
                    System.out.println("¿Cuándo hará la devolución?");
                    System.out.println("DD/MM/AAAA");
                    String fechaDevo = leer.next();
                    int dia = Integer.parseInt(fechaDevo.substring(0, 2));
                    int mes = Integer.parseInt(fechaDevo.substring(3, 5));
                    int anio = Integer.parseInt(fechaDevo.substring(6, 10));
                    Date fechaDevolucion = new Date(anio - 1900, mes - 1, dia);
                    if (fechaDevolucion.before(fechaPrestamo)) {
                        System.out.println("Fecha mal ingresada, ingrese nuevamente");
                    } else {
                        prestamo.setFechaDevolucion(fechaDevolucion);
                        
                    }
                } catch (NumberFormatException e) {
                    System.out.println("No se reconoce el formato de la fecha.");
                }
                
        try {
            prestamo.setAlta(true);
            dao.guardar(prestamo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
            
    }
                
       
                
        
    

    public void devolverLibro() {
        Long dni;
        boolean bandera=false;
        
        do {            
            System.out.println("Ingrese el DNI del cliente");
            dni = leer.nextLong();
            try {
               
                if (cs.buscarClienteDocumento(dni).isEmpty()) {
                    System.out.println("DNI no encontrado.");
                    
                }else{
                    bandera=true;
                }
            } catch (Exception e) {
                System.out.println("DNI mal ingresado.");
            }
        } while (!bandera);
        
        
        List<Prestamo> prestamos = busquedaLibrosCliente(dni);
        int numero = 0;
        if (prestamos.isEmpty()) {
            System.out.println("No tenés libros para devolver!");
        } else {
            ArrayList<Integer> numeroPrestamos = new ArrayList<>();
            System.out.println("Seleccione cuál libro desea devolver: ");
            int i = 1;
            for (Prestamo prestamo : prestamos) {
                System.out.println(i + ". " + prestamo);
                i++;
                numeroPrestamos.add(prestamo.getId());
            }
            bandera = false;
            do {
                try {
                    System.out.println("Ingrese el ID del préstamo que desea finalizar");
                    numero = leer.nextInt();
                    for (Integer numeros : numeroPrestamos) {
                        if (numero == numeros) {
                            bandera = true;
                        }
                    }
                    if (!bandera) {
                      
                        System.out.println("ID no reconocido");
                    }
                } catch (Exception e) {
                    System.out.println("Número mal ingresado.");
                    leer.next();
                }
            } while (!bandera);
        }
        darBajaPrestamo(numero);
    }

    public List<Prestamo> busquedaLibrosCliente(long dni) {
        return dao.buscarPrestamoCliente(dni);
    }

    public void darBajaPrestamo(Integer id) {
        Prestamo prestamo = dao.buscarPrestamoId(id);
        if (prestamo.getAlta()) {
            prestamo.setAlta(false);
            try {
                dao.editar(prestamo);
                Libro libro = ls.traerLibroISBN(prestamo.getLibro().getIsbn());
            if (libro.getEjemplaresPrestados() < libro.getEjemplares() && libro.getEjemplaresPrestados() > 0) {
                libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() - 1);
                libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() + 1);
                prestamo.setLibro(libro);
                ls.dao.guardar(libro);
            }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
            
        }
    }
}
