/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Flor Hiembuchner
 */
public class DAO<T> {
     protected final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("JPAPU");
     protected EntityManager em = EMF.createEntityManager();
    
    // OBJETIVO:conectar con la base de datos
    // se verifica si la conexión está realizada, 
    // si no está realizada, la conecta.
    protected void conectar() {
        if (!em.isOpen()) {
            em = EMF.createEntityManager();
        }
    }

    // OBJETIVO: desconectar la base de datos
    // Se verifica si existe una conexión, la cierra y desconecta el programa de la base de datos
    protected void desconectar() {
        if (em.isOpen()) {
            em.close();
        }
    }
    
    // OBJETIVO: persistir un objeto en la base de datos.
    // Toma como parámetro el objeto a persistir, genérico, 
    // lo que puede aceptar cualquier tipo de objeto (Dirección, Mastoca, Persona)
    protected void guardar(T objeto){
        conectar();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    // OBJETIVO: modificar una tupla de una base de datos.
    // Recibe como parámetro el objeto con los datos cambiados (debe mantener
    // la misma llave primaria) y lo reemplaza por el anterior.
    protected void editar(T objeto){
        conectar();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    // OBJETIVO: eliminar un registro de la base de datos.
    // Como parámetro se pasa el objeto a eliminar de la base de datos.
    // Se busca en la base de datos el registro que contenga la misma información
    // que el parámetro recibido, y se elimina.
    protected void eliminar(T objeto){
        conectar();
        em.getTransaction().begin();
        em.remove(objeto);
        em.getTransaction().commit();
        desconectar();
    }
}
