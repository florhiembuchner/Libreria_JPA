/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import java.util.List;
import libreria.entidades.Editorial;

/**
 *
 * @author Flor Hiembuchner
 */
public class EditorialDaoExt extends DAO<Editorial>{
    @Override
    public void guardar(Editorial e) {
        super.guardar(e);
    }
    
    @Override
    public void editar(Editorial e) {
        super.editar(e);
    }
    
    @Override
    public void eliminar(Editorial e){
        super.eliminar(e);
    }
    
    public Editorial buscarEditorial(Integer id) {
        conectar();
        Editorial editorial = em.find(Editorial.class, id);
        desconectar();
        return editorial;
    }

    public List<Editorial> buscarEditorialNombre(String nombre) {
        conectar();
        List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre = :nombre AND e.alta = TRUE").setParameter("nombre", nombre).getResultList();
        desconectar();
        return editoriales;
    }
    
    public List<Editorial> validarEditorialNombre(String nombre) {
        conectar();
        List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre = :nombre").setParameter("nombre", nombre).getResultList();
        desconectar();
        return editoriales;
    }
    public List<Editorial> validarNombreEditorial(String nombre) {
        conectar();
        List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e where e.nombre = :nombre").setParameter("nombre", nombre).getResultList();
        desconectar();
        return editoriales;
    }
}
