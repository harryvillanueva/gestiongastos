package daw.gestiongastos.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idMovimiento;
    double monto;
    String movimientoTipo;
    Date fechaMovimiento;
    String descripcion;
    String categoria;
}
