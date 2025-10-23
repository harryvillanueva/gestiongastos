package daw.gestiongastos.controlador;

import daw.gestiongastos.entidad.Movimiento;
import daw.gestiongastos.servicio.MovimientoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MovimientoControlador {

    private static final Logger logger = LoggerFactory.getLogger(MovimientoControlador.class);

    @Autowired
    private MovimientoServicio movimientoServicio;

    @GetMapping("/")
    public String iniciar(ModelMap modelo){
        List<Movimiento> movimientos = movimientoServicio.listarMovimientos();
        modelo.put("movimientos",movimientos);
        return "index";
    }

    @GetMapping("/agregar")
    public String mostrarAgregar(ModelMap modelo){
        return "agregar";
    }


    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("movimientoForma") Movimiento movimiento){
        movimientoServicio.agregarMovimiento(movimiento);
        return "redirect:/";
    }
}
