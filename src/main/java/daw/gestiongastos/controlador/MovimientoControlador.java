package daw.gestiongastos.controlador;

import daw.gestiongastos.entidad.Categoria;
import daw.gestiongastos.entidad.Movimiento;
import daw.gestiongastos.servicio.ICategoriaServicio;
import daw.gestiongastos.servicio.MovimientoServicio;
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

    private static final Logger logger = LoggerFactory.getLogger(MovimientoControlador.class);

    @Autowired
    private MovimientoServicio movimientoServicio;


    @Autowired
    private ICategoriaServicio categoriaServicio;


    @GetMapping("/")
    public String iniciar(ModelMap modelo){
        List<Movimiento> movimientos = movimientoServicio.listarMovimientos();
        modelo.put("movimientos",movimientos);
        return "index";
    }

    @GetMapping("/agregar")
    public String mostrarAgregar(ModelMap modelo){
        // --- CAMBIO AQUÍ ---
        // Pasamos la lista de categorías al modelo
        List<Categoria> categorias = categoriaServicio.listarCategorias();
        modelo.put("categorias", categorias);
        // --- FIN DEL CAMBIO ---
        return "agregar";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("movimientoForma") Movimiento movimiento){
        // Spring se encarga de convertir el idCategoria del form a un objeto Categoria
        movimientoServicio.agregarMovimiento(movimiento);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable(value = "id") int idMovimiento, ModelMap modelo){

        Movimiento movimiento = movimientoServicio.buscarMovimientoPorId(idMovimiento);
        modelo.put("movimiento",movimiento);

        // --- CAMBIO AQUÍ ---
        // También pasamos la lista de categorías al editar
        List<Categoria> categorias = categoriaServicio.listarCategorias();
        modelo.put("categorias", categorias);
        // --- FIN DEL CAMBIO ---

        return "editar";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute("movimiento") Movimiento movimiento){
        movimientoServicio.agregarMovimiento(movimiento);
        return "redirect:/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") int idMovimiento, RedirectAttributes redirectAttributes){

        Movimiento movimiento = new Movimiento();
        movimiento.setIdMovimiento(idMovimiento);
        movimientoServicio.eliminarMovimiento(movimiento);
        redirectAttributes.addFlashAttribute("msg_exito", "¡Se eliminó correctamente!");

        return "redirect:/";
    }
}