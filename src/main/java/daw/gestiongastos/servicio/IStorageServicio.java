package daw.gestiongastos.servicio;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IStorageServicio {

    void init(); // Para crear el directorio

    // Devuelve el nombre único del fichero guardado
    String store(MultipartFile file);
    Resource loadAsResource(String filename);
    void delete(String filename);
}