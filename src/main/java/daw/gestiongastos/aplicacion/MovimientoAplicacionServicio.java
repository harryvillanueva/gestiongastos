package daw.gestiongastos.aplicacion;

import daw.gestiongastos.entidad.Categoria;
import daw.gestiongastos.entidad.Movimiento;
import daw.gestiongastos.servicio.ICategoriaServicio;
import daw.gestiongastos.servicio.IMovimientoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class MovimientoAplicacionServicio implements IMovimientoAplicacionServicio {


    @Autowired
    private IMovimientoServicio movimientoServicio;

    @Autowired
    private ICategoriaServicio categoriaServicio;

    @Override
    @Transactional(readOnly = true)
    public void prepararDashboard(ModelMap modelo) {
        List<Movimiento> movimientos = movimientoServicio.listarMovimientos();
        Double totalIngresos = movimientoServicio.getTotalIngresos();
        Double totalGastos = movimientoServicio.getTotalGastos();
        Double balance = totalIngresos - totalGastos;

        modelo.put("movimientos", movimientos);
        modelo.put("totalIngresos", totalIngresos);
        modelo.put("totalGastos", totalGastos);
        modelo.put("balance", balance);
    }

    @Override
    @Transactional(readOnly = true)
    public void prepararPaginaAgregar(ModelMap modelo) {
        List<Categoria> categorias = categoriaServicio.listarCategorias();
        modelo.put("categorias", categorias);
    }

    @Override
    @Transactional
    public void guardarMovimiento(Movimiento movimiento) {
        movimientoServicio.agregarMovimiento(movimiento);

    }

    @Override
    @Transactional(readOnly = true)
    public Movimiento prepararPaginaEditar(int idMovimiento, ModelMap modelo) {
        Movimiento movimiento = movimientoServicio.buscarMovimientoPorId(idMovimiento);
        modelo.put("movimiento", movimiento);
        List<Categoria> categorias = categoriaServicio.listarCategorias();
        modelo.put("categorias", categorias);
        return movimiento;
    }

    @Override
    @Transactional
    public void eliminarMovimiento(int idMovimiento) {
        Movimiento movimiento = new Movimiento();
        movimiento.setIdMovimiento(idMovimiento);
        movimientoServicio.eliminarMovimiento(movimiento);

    }

}