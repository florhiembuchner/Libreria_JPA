/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDaoExt;

/**
 *
 * @author Flor Hiembuchner
 */
public class EditorialServicio {
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    EditorialDaoExt dao = new EditorialDaoExt();
    
    public Editorial crearEditorial(){
        System.out.println("Ingrese el nombre de la editorial");
        String nombre = leer.next();
        Editorial editorial= new Editorial();
        if(dao.validarNombreEditorial(nombre).isEmpty() && nombre !=null){
            try{
                editorial.setAlta(Boolean.TRUE);
                editorial.setNombre(nombre);
                dao.guardar(editorial);
                return editorial;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        
        }else{
            System.out.println("El nombre ingresado ya está registrado o es nulo");
            return null;
        }
    }
    public Editorial crearEditorial(String nombre){
        
      
        Editorial editorial= new Editorial();
        if(dao.validarNombreEditorial(nombre).isEmpty() && nombre !=null){
            try{
                editorial.setAlta(Boolean.TRUE);
                editorial.setNombre(nombre);
                dao.guardar(editorial);
                return editorial;
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
        Editorial e= new Editorial();
        
        System.out.println("Ingrese el id de la editorial que desea dar de alta/baja o modificar");
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
                    e=dao.buscarEditorial(id);
                    e.setAlta(alta);
                    dao.guardar(e);
                
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            
                break;
            }
                
            case 2:{
                System.out.println("Ingrese el nombre que desea modificar");
                nombre = leer.next();
                try{
                    e=dao.buscarEditorial(id);
                    e.setNombre(nombre);
                    dao.guardar(e);    
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
                break;
            }
        }
    }
    public List<Editorial> buscarEditorialNombre() {
         System.out.println("Ingrese el nombre de la editorial");
         String nombre = leer.next();
        try{
            return dao.buscarEditorialNombre(nombre);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
     public List<Editorial> buscarEditorialNombre(String nombre ) {
         
         
        try{
            return dao.buscarEditorialNombre(nombre);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
}
