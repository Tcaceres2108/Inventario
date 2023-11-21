package inventarioAlimentos;

import java.util.Date;

public class Transaccion {

    private int id;
    private Date fechaHora;
    private int idSucursal;

    public Transaccion(int id, Date fechaHora, int idSucursal) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.idSucursal = idSucursal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
}
