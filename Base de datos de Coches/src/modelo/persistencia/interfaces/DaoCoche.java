package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;

public interface DaoCoche {
	public boolean alta(Coche p); //1
	public boolean baja(int id); //2
	public Coche obtener(int id); //3
	public List<Coche> listar(); //4
	public boolean modificar(Coche p); //5

}
