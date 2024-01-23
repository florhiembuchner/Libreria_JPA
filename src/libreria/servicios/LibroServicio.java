/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.LibroDaoExt;

/**
 *
 * @author Flor Hiembuchner
 */
public class LibroServicio {
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    LibroDaoExt dao = new LibroDaoExt();
    AutorServicio as = new AutorServicio();
    EditorialServicio es = new EditorialServicio();
    
     public Libro crearLibro() {
        Libro libro = new Libro();
        System.out.println("Ingrese el ISBN del libro");
        Long isbn = leer.nextLong();
        if(dao.validarISBN(isbn).isEmpty()){
            
            System.out.println("Ingrese el titulo del libro");
            String titulo = leer.next();
            System.out.println("Ingrese el año de edición");
            int anio = leer.nextInt();
            System.out.println("Ingrese la cantidad de ejemplares");
            int cantidadEjemplares = leer.nextInt();
            try{
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(cantidadEjemplares);
            libro.setEjemplaresRestantes(cantidadEjemplares);
            libro.setEjemplaresPrestados(0);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            
            System.out.println("Ingrese el nombre del autor");
            String nombreAutor = leer.next();
            if(as.buscarAutorNombre(nombreAutor).isEmpty()) {
                System.out.println("Autor no encontrado, ¿desea registrarlo? S/N");
                String respuesta = leer.next();
                if (respuesta.equalsIgnoreCase("S")) {
                    try {
                        Autor autor = as.crearAutor(nombreAutor);
                        libro.setAutor(autor);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                   
                }
            }else {
                System.out.println("Autor ya registrado");
                libro.setAutor(as.buscarAutorNombre(nombreAutor).get(0));
            }
            
            System.out.println("Ingrese el nombre de la editorial");
            String nombreEditorial = leer.next();
            if (es.buscarEditorialNombre(nombreEditorial).isEmpty()) {
                System.out.println("Editorial no encontrada, ¿desea registrarla? S/N");
                String respuesta = leer.next();
                if (respuesta.equalsIgnoreCase("S")) {
                    try {
                    Editorial editorial = es.crearEditorial(nombreEditorial);
                    libro.setEditorial(editorial);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                   
                }
            } else {
                System.out.println("Editorial ya registrada");
                libro.setEditorial(es.buscarEditorialNombre(nombreEditorial).get(0));
               
            }
        }else{
            System.out.println("El ibsn ingresado ya está registrado");
            return null;
        }
        
        try{
            dao.guardar(libro);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return libro;
    }
     
     public List<Libro> buscarLibroTitulo() {
        System.out.println("Ingrese el titulo del libro");
        List<Libro> libros;
        String titulo = leer.next();
         try {
            libros = dao.buscarLibroTitulo(titulo);
            return libros;
         } catch (Exception e) {
            e.getMessage();
            return null;
         }
       
        
    }

    public List<Libro> buscarLibroTitulo(String titulo) {
        try{
            return dao.buscarLibroTitulo(titulo);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }   
    }
    public List<Libro> buscarLibroAutor() {
        System.out.println("Ingrese el nombre del autor");
        String autor = leer.next();
        try{
            return dao.buscarLibroAutor(autor);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }   
    }
    public List<Libro> buscarLibroAutor(String autor) {
        
        try{
            return dao.buscarLibroAutor(autor);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }   
    }

    public void imprimirLibroAutor() {
        
        List<Libro> libros = buscarLibroAutor();
        
        if (libros.isEmpty()) {
            System.out.println("No hay libros de ese autor");
        } else {
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }
    
    public void imprimirLibroEditorial(String editorial) {
        
        List<Libro> libros = buscarLibroEditorial();
        if (libros.isEmpty()) {
            System.out.println("No hay libros de esa editorial");
        } else {
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }
    public List<Libro> buscarLibroEditorial() {
        System.out.println("Ingrese el nombre de la editorial");
        String editorial = leer.next();
        try{
            return dao.buscarLibroEditorial(editorial);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }   
        
    }
    public List<Libro> buscarLibroEditorial(String editorial) {
       
        try{
            return dao.buscarLibroEditorial(editorial);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }   
        
    }

    public void darAltaBajaModificar() {
        Long ISBN;
        Libro libro= new Libro();
       
        System.out.println("Ingrese el ISBN del libro que desea dar alta/baja/modificar");
        ISBN = leer.nextLong();
        
        try {
           libro = dao.buscarLibroISBN(ISBN);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        int opcion;
        do {
            System.out.println("1- Alta/Baja \n2 - Modificar");
            opcion = leer.nextInt();
            
        }while(opcion != 1 && opcion != 2);
                    
        switch (opcion) {
            case 1:{
                Boolean alta;
                if (libro.getAlta()) {
                    alta=Boolean.FALSE;
                } else {
                   alta=Boolean.TRUE;
                }
                
                try {
                    libro.setAlta(alta);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 2:{
                System.out.println("Seleccione qué desea cambiar:");
                System.out.println("1- Año de edición");
                System.out.println("2- El autor");
                System.out.println("3- La editorial");
                System.out.println("4- El título");
                System.out.println("5- La cantidad de ejemplares");
                opcion = leer.nextInt();
                switch (opcion) {
                    case 1:{
                            System.out.println("Ingrese el año:");
                            int anio = leer.nextInt();
                            try {
                                libro.setAnio(anio);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                           }

                    case 2:{
                        
                            System.out.println("Ingrese el nombre del autor");
                            String nombreAutor = leer.next();
                            if (as.buscarAutorNombre(nombreAutor).isEmpty()) {
                                System.out.println("Autor no encontrado, ¿desea registrarlo? S/N");
                                String respuesta = leer.next();
                                if (respuesta.equalsIgnoreCase("S")) {
                                    try {
                                        Autor autor = as.crearAutor(nombreAutor);
                                        libro.setAutor(autor);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                   
                                    
                                }
                            } else {
                                System.out.println("Autor ya registrado");
                                
                                try {
                                    libro.setAutor(as.buscarAutorNombre(nombreAutor).get(0));
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                
                                
                            }
                        
                        break;
                    }
                    case 3:{
                        
                            System.out.println("Ingrese el nombre de la editorial");
                            String nombreEditorial = leer.next();
                            if (es.buscarEditorialNombre(nombreEditorial).isEmpty()) {
                                System.out.println("Editorial no encontrada, ¿desea registrarla? S/N");
                                String respuesta = leer.next();
                                if (respuesta.equalsIgnoreCase("S")) {
                                    try {
                                        Editorial editorial = es.crearEditorial(respuesta);
                                        libro.setEditorial(editorial);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            } else {
                                System.out.println("Editorial ya registrada");
                                try {
                                    libro.setEditorial(es.buscarEditorialNombre(nombreEditorial).get(0));
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            break;
                        } 
                        
                    case 4:{
                        System.out.println("Ingrese el titulo que desea modificar");
                        String titulo = leer.next();
                        try {
                            libro.setTitulo(titulo);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        
                        break;
                    }
                        
                    case 5:{
                        System.out.println("Ingrese la nueva cantidad de ejemplares");
                                int cantidadEjemplares = leer.nextInt();
                                if (libro.getEjemplaresRestantes() == libro.getEjemplares()) {
                                    try {
                                        libro.setEjemplaresRestantes(cantidadEjemplares);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());  
                                     }
                                }
                        try {
                            libro.setEjemplares(cantidadEjemplares);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());  
                        }
                                
                         break;
                            
                        }
                }
                break;
            }
        }
        try{
        dao.guardar(libro);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Libro buscarLibroISBN() {
        long isbn;
        System.out.println("Ingrese el ISBN del libro que desea buscar");
        isbn = leer.nextLong();
            try {
             return dao.buscarLibroISBN(isbn);   
              
            } catch (Exception e) {
                System.out.println("Número no reconocido");
                return null;
            }
       

        
    }

    public boolean buscarLibroISBN(long ISBN) {
        try {
            if (dao.buscarLibroISBN(ISBN) == null) {
                return false;
            }
        } catch (Exception e) {
        }
        return true;
    }

    public Libro traerLibroISBN(long ISBN) {
        try {
            return dao.buscarLibroISBN(ISBN);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
}