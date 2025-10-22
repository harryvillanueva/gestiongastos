package daw.gestiongastos.controlador;

import daw.gestiongastos.entidad.Movimiento;
import daw.gestiongastos.servicio.MovimientoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MovimientoControlador {

    private static final Logger logger = LoggerFactory.getLogger(MovimientoControlador.class);

    @Autowired
    MovimientoServicio movimientoServicio;

    @GetMapping("/")
    public String iniciar(ModelMap modelo){
        List<Movimiento> movimientos = movimientoServicio.listarMovimientos();
        modelo.put("movimientos",movimientos);
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(ModelMap modelo){
        List<Movimiento> agregarMovimiento = movimientoServicio.listarMovimientos();
        agregarMovimiento.forEach((contacto) -> logger.info(contacto.toString()));
        modelo.put("contactos",agregarMovimiento);
        return "agregar";
    }
}
