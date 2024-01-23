/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import java.util.List;
import libreria.entidades.Libro;

/**
 *
 * @author Flor Hiembuchner
 */
public class LibroDaoExt extends DAO<Libro>{
    @Override
    public void guardar(Libro l) {
        super.guardar(l);
    }
    
    @Override
    public void editar(Libro l) {
        super.editar(l);
    }
    
    @Override
    public void eliminar(Libro libro) {
        super.eliminar(libro);
    }
    public Libro buscarLibroISBN(Long ISBN) {
        conectar();
        Libro libro = em.find(Libro.class, ISBN);
        desconectar();
        return libro;
    }

    public List<Libro> buscarLibroTitulo(String titulo) {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo AND l.alta = TRUE").setParameter("titulo", titulo).getResultList();
        desconectar();
        return libros;
    }

    public List<Libro> buscarLibroAutor(String nombre) {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.autor.id = (SELECT a.id FROM Autor a where a.nombre = :nombre AND a.alta = TRUE)").setParameter("nombre", nombre).getResultList();
        desconectar();
        return libros;
    }

    public List<Libro> buscarLibroEditorial(String nombre) {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.editorial.id = (SELECT e.id FROM Editorial e where e.nombre = :nombre AND e.alta = TRUE)").setParameter("nombre", nombre).getResultList();
        desconectar();
        return libros;
    }
     public List<Libro> validarISBN(Long isbn) {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l where l.isbn = :isbn").setParameter("isbn", isbn).getResultList();
        desconectar();
        return libros;
    }
}

