package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoTransaccion;

public class DaoTransaccionMysql implements DaoTransaccion {

	private Connection conexion;
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/coches";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	// Le pido el ID al cual quiero el idCoche que está en null y le asigno un número
	@Override
	public boolean anadir(Pasajero p) {
		if(!abrirConexion()){
			return false;
		}
		boolean anadir = true;
				
		String query = "update pasajeros set idcoche=? WHERE ID=?" ;
		try {
			
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, p.getIdCoche());	
			ps.setInt(2, p.getId());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				anadir = false;
			

		} catch (SQLException e) {
			System.out.println("Proceso de asignación en coche -> Error al insertar: " + p);
			anadir = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return anadir;
	}

	// Proceso contrario al método anterior. Convierto el idCoche en null para que se pierda su asignación
	@Override
	public boolean eliminar(Pasajero p) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		
		try {	

			String query = "update pasajeros set idcoche=null WHERE ID=?" ;
				PreparedStatement ps = conexion.prepareStatement(query);
				
				ps.setInt(1, p.getId());
				
				int numeroFilasAfectadas = ps.executeUpdate();
				if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			borrado = false;
			System.out.println("Proceso de eliminar la asignación");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado; 
}

	@Override
	public List<Pasajero> listarPasajeros(int idCoche) {
		if(!abrirConexion()){
			return null;
		}		
		Pasajero pasajero = null;
		List<Pasajero> listaPasajerosEnCoche = new ArrayList<>();
		
		String query = "select id,nombre,edad,peso,idcoche from pasajeros where idcoche=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idCoche);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getDouble(4));
				pasajero.setIdcoche(rs.getInt(5));	

				listaPasajerosEnCoche.add(pasajero);
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener los "
					+ "pasajeros");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return listaPasajerosEnCoche;
	}

}

	