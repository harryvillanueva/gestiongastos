package daw.gestiongastos.servicio;

import daw.gestiongastos.entidad.Movimiento;

import java.util.List;

// 💪 Muy buena idea la de usar una interfaz
public interface IMovimientoServicio {


    // Sobran "public" (conceptualmente, los métodos de una interfaz son públicos)
    public List<Movimiento> listarMovimientos();
    public Movimiento buscarMovimientoPorId(Integer idMovimiento);
    public void agregarMovimiento(Movimiento movimiento);
    public void eliminarMovimiento(Movimiento movimiento);


}
