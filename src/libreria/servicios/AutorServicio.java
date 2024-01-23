/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;


import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.persistencia.AutorDaoExt;

/**
 *
 * @author Flor Hiembuchner
 */
public class AutorServicio {
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    AutorDaoExt dao = new AutorDaoExt();
    
    //Siempre que se crea un autor, se lo da de alta en el sistema
    public Autor crearAutor(){
        System.out.println("Ingrese el nombre del autor");
        String nombre = leer.next();
        Autor autor= new Autor();
        if(dao.validarNombreAutor(nombre).isEmpty() && nombre != null){
            try{
                autor.setAlta(Boolean.TRUE);
                autor.setNombre(nombre);
                dao.guardar(autor);
                return autor;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        
        }else{
            System.out.println("El nombre ingresado ya está registrado o es nulo");
            return null;
        }
    }
    
    public Autor crearAutor(String nombre){
        
        Autor autor= new Autor();
        if(dao.validarNombreAutor(nombre).isEmpty() && nombre != null){
            try{
                autor.setAlta(Boolean.TRUE);
                autor.setNombre(nombre);
                dao.guardar(autor);
                return autor;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        
        }else{
            System.out.println("El nombre ingresado ya está registrado o es nulo");
            return null;
        }
    }
   
    public void darAltaBajaModificar(){
        Integer id;
        Integer opcion;
        Integer aux;
        Boolean alta;
        String nombre;
        Autor a= new Autor();
        
        System.out.println("Ingrese el id del autor que desea dar alta/baja/modificar");
        id = leer.nextInt();
        
        do{
        System.out.println("1- Alta/Baja \n2-Modificar");
        opcion = leer.nextInt();
        }while (!(opcion == 1 || opcion == 2));
        
        switch (opcion) {
            case 1:{
                System.out.println("1- Alta \n2-Baja");
                aux=leer.nextInt();
                if(aux==1){
                    alta=Boolean.TRUE;
                }else{
                    alta=Boolean.FALSE;
                }
                
                try{
                    a=dao.buscarAutor(id);
                    a.setAlta(alta);
                    dao.guardar(a);
                
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            
                break;
            }
                
            case 2:{
                System.out.println("Ingrese el nombre que desea modificar");
                nombre = leer.next();
                try{
                    a=dao.buscarAutor(id);
                    a.setNombre(nombre);
                    dao.guardar(a);    
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }
    
    public List<Autor> buscarAutorNombre() {
         System.out.println("Ingrese el nombre del autor");
         String nombre = leer.next();
        try{
            return dao.buscarAutorNombre(nombre);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    public List<Autor> buscarAutorNombre(String nombre) {
         
        try{
            return dao.buscarAutorNombre(nombre);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }

   

    
}
