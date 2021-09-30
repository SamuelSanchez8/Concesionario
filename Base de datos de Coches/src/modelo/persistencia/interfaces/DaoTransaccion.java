package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Pasajero;

public interface DaoTransaccion {
	public boolean anadir(Pasajero p); //1
	public boolean eliminar(Pasajero p); //2
	public List<Pasajero> listarPasajeros(int idCoche); //3

}
