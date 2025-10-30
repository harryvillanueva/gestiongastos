package daw.gestiongastos.aplicacion;

import daw.gestiongastos.entidad.Movimiento;
import org.springframework.ui.ModelMap;

public interface IMovimientoAplicacionServicio {


    void prepararDashboard(ModelMap modelo);


    void prepararPaginaAgregar(ModelMap modelo);


    void guardarMovimiento(Movimiento movimiento);


    Movimiento prepararPaginaEditar(int idMovimiento, ModelMap modelo);


    void eliminarMovimiento(int idMovimiento);
}