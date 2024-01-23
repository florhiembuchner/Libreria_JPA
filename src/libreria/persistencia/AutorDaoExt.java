/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import java.util.List;
import libreria.entidades.Autor;

/**
 *
 * @author Flor Hiembuchner
 */
public class AutorDaoExt extends DAO<Autor>{
   
    @Override
    public void guardar(Autor autor) {
        super.guardar(autor);
    }
    
    @Override
    public void editar(Autor autor) {
        super.editar(autor);
    }
    
    @Override
    public void eliminar(Autor autor) {
        super.eliminar(autor);
    }
    
     public Autor buscarAutor(Integer id) {
        conectar();
        Autor autor = em.find(Autor.class, id);
        desconectar();
        return autor;
    }
     
     public List<Autor> buscarAutorNombre(String nombre) {
        conectar();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a where a.nombre = :nombre AND a.alta = TRUE").setParameter("nombre", nombre).getResultList();
        desconectar();
        return autores;
    }

    public List<Autor> validarNombreAutor(String nombre) {
        conectar();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a where a.nombre = :nombre").setParameter("nombre", nombre).getResultList();
        desconectar();
        return autores;
    }
}
