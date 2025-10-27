package daw.gestiongastos.controlador;

import daw.gestiongastos.entidad.Movimiento;
import daw.gestiongastos.servicio.IMovimientoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MovimientoControlador {
    private static final String REDIRECT_HOME = "redirect:/";
    // Si declaras un logger, úsalo; si no, bórralo.
    private static final Logger logger = LoggerFactory.getLogger(MovimientoControlador.class);

    @Autowired
    private IMovimientoServicio movimientoServicio;

    @GetMapping("/")
    public String iniciar(ModelMap modelo){
        List<Movimiento> movimientos = movimientoServicio.listarMovimientos();
        modelo.put("movimientos",movimientos);
        return "index";
    }

    @GetMapping("/agregar")
    public String mostrarAgregar() {
        return "agregar";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("movimientoForma") Movimiento movimiento){
        movimientoServicio.agregarMovimiento(movimiento);
        return REDIRECT_HOME;
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable(value = "id") int idMovimiento, ModelMap modelo){
        Movimiento movimiento = movimientoServicio.buscarMovimientoPorId(idMovimiento);
        modelo.put("movimiento",movimiento);
        return "editar";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute("movimiento") Movimiento movimiento){
        // ❗¿No sería .editarMovimiento?
        movimientoServicio.agregarMovimiento(movimiento);
        return REDIRECT_HOME;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") int idMovimiento, RedirectAttributes redirectAttributes){
        // ❗ No es responsabilidad del controlador
        Movimiento movimiento = new Movimiento();
        movimiento.setIdMovimiento(idMovimiento);
        movimientoServicio.eliminarMovimiento(movimiento);
        // 💪 Sí es responsabilidad del controlador
        redirectAttributes.addFlashAttribute("msg_exito", "¡Se eliminó correctamente!");

        return REDIRECT_HOME;
    }
}