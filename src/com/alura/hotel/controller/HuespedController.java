package com.alura.hotel.controller;

import java.util.List;

import com.alura.hotel.dao.HuespedDao;
import com.alura.hotel.factory.ConexionFactory;
import com.alura.hotel.modelos.Huesped;

import java.sql.Date;

public class HuespedController {

    private HuespedDao huespedDao;

    public HuespedController() {
        var factory = new ConexionFactory();
        this.huespedDao = new HuespedDao(factory.getConexion());
    }

    public List<Huesped> listar() {
        return huespedDao.listar();
    }
    
        public List<Huesped> listarBusqueda(String cadena) {
        return huespedDao.listarBusqueda(cadena);
    }

    public void guardar(Huesped huesped) {
        this.huespedDao.guardar(huesped);
    }

    public int modificar(int idHuesped,  String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, int idReserva) {
        return this.huespedDao.modificar(idHuesped, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
    }

    public int eliminar(Integer idHuesped) {
        return huespedDao.eliminar(idHuesped);
    }

}
