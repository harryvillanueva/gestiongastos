package daw.gestiongastos.controlador;


import daw.gestiongastos.aplicacion.IMovimientoAplicacionServicio;
import daw.gestiongastos.entidad.Movimiento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MovimientoControlador {


    @Autowired
    private IMovimientoAplicacionServicio movimientoAplicacionServicio;



    @GetMapping("/")
    public String iniciar(ModelMap modelo) {

        movimientoAplicacionServicio.prepararDashboard(modelo);
        return "index";
    }

    @GetMapping("/agregar")
    public String mostrarAgregar(ModelMap modelo) {

        movimientoAplicacionServicio.prepararPaginaAgregar(modelo);
        return "agregar";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("movimientoForma") Movimiento movimiento) {

        movimientoAplicacionServicio.guardarMovimiento(movimiento);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable(value = "id") int idMovimiento, ModelMap modelo) {

        movimientoAplicacionServicio.prepararPaginaEditar(idMovimiento, modelo);
        return "editar";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute("movimiento") Movimiento movimiento) {

        movimientoAplicacionServicio.guardarMovimiento(movimiento);
        return "redirect:/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") int idMovimiento, RedirectAttributes redirectAttributes) {

        movimientoAplicacionServicio.eliminarMovimiento(idMovimiento);
        redirectAttributes.addFlashAttribute("msg_exito", "¡Se eliminó correctamente!");
        return "redirect:/";
    }
}