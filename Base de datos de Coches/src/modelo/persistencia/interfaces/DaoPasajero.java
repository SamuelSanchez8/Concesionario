package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Pasajero;

public interface DaoPasajero {
	public boolean alta(Pasajero p2); //1
	public boolean baja(int id); //2
	public Pasajero obtener(int id); //3
	public List<Pasajero> listar(); //4

}
