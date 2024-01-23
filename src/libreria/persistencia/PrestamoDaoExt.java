/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import java.util.List;
import libreria.entidades.Prestamo;



/**
 *
 * @author Flor Hiembuchner
 */
public class PrestamoDaoExt extends DAO<Prestamo>{
    @Override
    public void guardar(Prestamo p) {
        super.guardar(p);
    }
    
    @Override
    public void editar(Prestamo p) {
        super.editar(p);
    }
    
    @Override
    public void eliminar(Prestamo prestamo) {
        super.eliminar(prestamo);
    }
    
    public Prestamo buscarPrestamoId(Integer id) {
        conectar();
        Prestamo prestamo = em.find(Prestamo.class, id);
        desconectar();
        return prestamo;
    }

    public List<Prestamo> buscarPrestamoCliente(long dni) {
        conectar();
        List<Prestamo> prestamos = em.createQuery("SELECT P FROM Prestamo p WHERE p.cliente.documento = :dni AND p.alta = TRUE").setParameter("dni", dni).getResultList();
        desconectar();
        return prestamos;
    }
    
}
