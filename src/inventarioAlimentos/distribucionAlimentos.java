package inventarioAlimentos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import inventarioAlimentos.conexiones.*;
//import inventarioAlimentos.conexiones.Consumidor;
//import inventarioAlimentos.conexiones.Productor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class distribucionAlimentos {

    public static void main(String[] args) {

        System.out.println("INICIANDO.");
        Productor productor = new Productor();
        Consumidor consumidor = new Consumidor();
        ObjectMapper objectMapper = new ObjectMapper();

        String ventaJSON = null, compraJSON = null;

        // Iniciar el consumidor en un hilo separado
        Thread consumidorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                consumidor.iniciarConsumidor();

            }
        });
        consumidorThread.start();

        // Crear instancias de Compra y Venta
        Compra compra = new Compra(15, "Pan", "Pan tajado", 100, 1, 10);
        Venta venta = new Venta(9, 5, 1);

        try {
            // Serializa el objeto JSON a STRING
            compraJSON = objectMapper.writeValueAsString(compra);
            ventaJSON = objectMapper.writeValueAsString(venta);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(distribucionAlimentos.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Enviar la instancia de la transaccion como una COMPRA y como una VENTA para
        // que la registre el consumidor en la base de datos
        productor.enviarTransaccion(compraJSON);
        productor.enviarTransaccion(ventaJSON);

        // Esperar a que el usuario termine
        try {
            consumidorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("La aplicación ha terminado.");
    }
}
