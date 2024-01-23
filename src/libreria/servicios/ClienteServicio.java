/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;


import java.util.List;
import java.util.Scanner;
import libreria.entidades.Cliente;
import libreria.persistencia.ClienteDaoExt;


/**
 *
 * @author Flor Hiembuchner
 */
public class ClienteServicio {
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    ClienteDaoExt dao = new ClienteDaoExt();
    
    public Cliente crearClienteNuevo(){
        Long dni;
        String nombre;
        String apellido;
        String telefono;
        System.out.println("Ingrese el documento del Cliente");
        dni= leer.nextLong();
        
        System.out.println("Ingrese el nombre del Cliente");
        nombre = leer.next();
        
        System.out.println("Ingrese el apellido del Cliente");
        apellido = leer.next();
        
        System.out.println("Ingrese el telefono del Cliente");
        telefono = leer.next();
        
        Cliente c= new Cliente();
        if (dao.validarNombreCliente(nombre).isEmpty()) {
            try{
                c.setDocumento(dni);
                c.setNombre(nombre);
                c.setApellido(apellido);
                c.setTelefono(telefono);
                c.setAlta(Boolean.TRUE);
                dao.guardar(c);
                
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        } else {
            System.out.println("Nombre de autor ya registrado.");
        }

        
        return c;
        
    }
    
    public void darAltaBajaModificar(){
        Integer opcion, aux, id;
        Boolean alta;
        Cliente cliente= new Cliente();
        
        System.out.println("Ingrese el id del autor que desea dar alta/baja/modificar");
        id = leer.nextInt();
        try{
            cliente=dao.buscarClienteID(id);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
       
        do{
            System.out.println("Seleccione qué desea realizar:");
            System.out.println("1- Alta/Baja \n2-Modificar");
            opcion = leer.nextInt();
        }while(opcion != 1 && opcion != 2);
        
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
                    
                    cliente.setAlta(alta);
                    dao.guardar(cliente);
                
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            
                break;
            }
            case 2:{
                System.out.println("Seleccione qué desea editar acerca del cliente:");
                System.out.println("1- nombre");
                System.out.println("2- apellido");
                System.out.println("3- documento");
                System.out.println("4- telefono");
              
                aux = leer.nextInt();
                
                switch(aux){
                    case 1:{
                    System.out.println("Ingrese el nombre:");
                    String nombre = leer.next();  
                    try{
                        cliente.setNombre(nombre);
                        dao.guardar(cliente);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                    }
                    case 2:{
                    System.out.println("Ingrese el apellido:");
                    String apellido = leer.next();  
                    try{
                        cliente.setApellido(apellido);
                        dao.guardar(cliente);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                    }
                    case 3:{
                    System.out.println("Ingrese el documento:");
                    Long documento = leer.nextLong();  
                    try{
                        cliente.setDocumento(documento);
                        dao.guardar(cliente);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                    }
                    case 4:{
                    System.out.println("Ingrese el telefono:");
                    String telefono = leer.next();  
                    try{
                        cliente.setTelefono(telefono);
                        dao.guardar(cliente);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                    }
                    default: System.out.println("OPCION ERRÓNEA");
                }
            }    
        }
    }
    
    public List<Cliente> buscarClienteNombre() {
        System.out.println("Ingrese el nombre del cliente");
        String nombre = leer.next();
        try{
            return dao.buscarClientePorNombre(nombre);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }

   

    public List<Cliente> buscarClienteDocumento() {
        System.out.println("Ingrese el documento del cliente");
        Long documento = leer.nextLong();
        try{
            return dao.buscarClientePorDocumento(documento);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
     public List<Cliente> buscarClienteDocumento(Long documento) {
       
        try{
            return dao.buscarClientePorDocumento(documento);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
}
