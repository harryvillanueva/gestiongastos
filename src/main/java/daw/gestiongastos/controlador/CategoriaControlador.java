package daw.gestiongastos.controlador;

import daw.gestiongastos.entidad.Categoria;
import daw.gestiongastos.servicio.ICategoriaServicio;
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
public class CategoriaControlador {

    @Autowired
    private ICategoriaServicio categoriaServicio;

    // Listar todas las categorías
    @GetMapping("/categorias")
    public String listarCategorias(ModelMap modelo) {
        List<Categoria> categorias = categoriaServicio.listarCategorias();
        modelo.put("categorias", categorias);
        // Usaremos una nueva carpeta 'categorias' en templates
        return "categorias/index";
    }

    // Mostrar formulario para agregar
    @GetMapping("/categorias/agregar")
    public String mostrarAgregarCategoria() {
        return "categorias/agregar";
    }

    // Guardar nueva categoría
    @PostMapping("/categorias/agregar")
    public String agregarCategoria(@ModelAttribute("categoriaForma") Categoria categoria, RedirectAttributes redirectAttributes) {
        categoriaServicio.guardarCategoria(categoria);
        redirectAttributes.addFlashAttribute("msg_exito", "¡Categoría guardada correctamente!");
        return "redirect:/categorias";
    }

    // Mostrar formulario para editar
    @GetMapping("/categorias/editar/{id}")
    public String mostrarEditarCategoria(@PathVariable(value = "id") int idCategoria, ModelMap modelo) {
        Categoria categoria = categoriaServicio.buscarCategoriaPorId(idCategoria);
        if (categoria == null) {
            // Manejar error si no se encuentra
            return "redirect:/categorias";
        }
        modelo.put("categoria", categoria);
        return "categorias/editar";
    }

    // Guardar cambios de edición
    @PostMapping("/categorias/editar")
    public String editarCategoria(@ModelAttribute("categoria") Categoria categoria, RedirectAttributes redirectAttributes) {
        categoriaServicio.guardarCategoria(categoria); // save sirve tanto para crear como para actualizar
        redirectAttributes.addFlashAttribute("msg_exito", "¡Categoría actualizada correctamente!");
        return "redirect:/categorias";
    }

    // Eliminar categoría
    @GetMapping("/categorias/eliminar/{id}")
    public String eliminarCategoria(@PathVariable(value = "id") int idCategoria, RedirectAttributes redirectAttributes) {
        try {
            Categoria categoria = new Categoria(idCategoria);
            categoriaServicio.eliminarCategoria(categoria);
            redirectAttributes.addFlashAttribute("msg_exito", "¡Categoría eliminada correctamente!");
        } catch (Exception e) {
            // Si la categoría está en uso por un movimiento, dará un error de BD
            redirectAttributes.addFlashAttribute("msg_error", "No se puede eliminar la categoría, está siendo usada por un movimiento.");
        }
        return "redirect:/categorias";
    }
}