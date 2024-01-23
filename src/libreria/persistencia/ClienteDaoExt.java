/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import java.util.List;
import libreria.entidades.Cliente;

/**
 *
 * @author Flor Hiembuchner
 */
public class ClienteDaoExt extends DAO<Cliente>{
    @Override
    public void guardar(Cliente c) {
        super.guardar(c);
    }
    
    @Override
    public void editar(Cliente c) {
        super.editar(c);
    }
    
    @Override
    public void eliminar(Cliente c){
        super.eliminar(c);
    }
    
    public Cliente buscarClienteID(Integer id){
        conectar();
        Cliente cliente =em.find(Cliente.class, id);
        desconectar();
        return cliente;
    }
    
    public List<Cliente> buscarClientePorNombre(String nombre){
        conectar();
        List<Cliente> clientes= em.createQuery("SELECT c FROM Cliente c WHERE c.nombre = :nombre c.alta=TRUE").setParameter("nombre", nombre).getResultList();
        desconectar();
        return clientes;
    }
    
    public List<Cliente> buscarClientePorDocumento(long dni) {
        conectar();
        List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c where c.documento = :documento AND c.alta = TRUE").setParameter("documento", dni).getResultList();
        desconectar();
        return clientes;
    }
    public List<Cliente> validarNombreCliente(String nombre) {
        conectar();
        List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c where a.nombre = :nombre").setParameter("nombre", nombre).getResultList();
        desconectar();
        return clientes;
    }
    
    
    
}
