package vista;


import java.io.IOException;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.DaoCocheMysql;
import modelo.persistencia.DaoPasajeroMysql;
import modelo.persistencia.DaoTransaccionMysql;
import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.interfaces.DaoPasajero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 
 */
public class Main {

	public static void main(String[] args) throws IOException {

		String cadenaConexion = "jdbc:mysql://localhost:3306/coches";
		String user = "root";
		String pass = ""; 

		Connection con = null;
		try {
			con = DriverManager.getConnection(cadenaConexion, user, pass);
		} catch (SQLException e) {
			System.out.println("No se ha podido establecer la conexión con la BD");
			System.out.println(e.getMessage());
			return;
		}

		System.out.println("Se ha establecido la conexión con la Base de datos");


        Scanner sc = new Scanner(System.in);
        Scanner entrada = new Scanner(System.in);
        int opcion;
        boolean salir = false;
        int id;
        String matricula;
        String modelo;
        String marca;
        String color;
        Coche c = new Coche();
        DaoCoche dc = new DaoCocheMysql();
        
        do {
        

		System.out.println("1- Alta nuevo coche");
		System.out.println("2- Baja coche por Id");
		System.out.println("3- Consultar coche por Id");
		System.out.println("4- Listado de coches");
		System.out.println("5- Modificar coche por ID");
		System.out.println("6- Gestión de pasajeros");
		System.out.println("7- Salir");
		System.out.println("Escribe una de las opciones: ");
		opcion = sc.nextInt();

        switch (opcion) {
            case 1:

                System.out.println("Añadir nuevo coche");
                System.out.println("Introduzca la matricula: ");
                matricula = entrada.nextLine();
                System.out.println("Introduzca la marca: ");
                marca = sc.next();
                System.out.println("Introduzca el modelo: ");
                modelo = entrada.nextLine();
                System.out.println("Introduzca el color: ");
                color = sc.next();
                
                c.setMatricula(matricula);
                c.setMarca(marca);
                c.setModelo(modelo);
                c.setColor(color);


                boolean alta = dc.alta(c);
                
                if(alta){
        			System.out.println("El coche se ha dado de alta");
        		}else{
        			System.out.println("El coche NO se ha dado de alta");
        		}

                System.out.println("**********************************");

                break;
                
            case 2:

                System.out.println("Introduza el Id del coche que quieres borrar");
                id = sc.nextInt();
        		
        		boolean baja = dc.baja(id);
        		if(baja){
        			System.out.println("El coche se ha dado de baja");
        		}else{
        			System.out.println("El coche NO se ha dado de baja");
        		}

                System.out.println("**********************************");

                System.out.println("Eliminado");

                break;
                
            case 3:

                System.out.println("Introduza el Id del coche que obtener");
                id = sc.nextInt();
        		
        		Coche car = dc.obtener(id);
        		
        		System.out.println(car);

                System.out.println("**********************************");

                break;    
                
            case 4:   
            	
            	System.out.println("********* LISTANDO TODOS LOS COCHES **********");
        		
            	System.out.println(dc.listar());
        			
        		break;
            case 5:   
            	
            	System.out.println("Introduzca el id a modificar: ");
                id = entrada.nextInt();      
                System.out.println("Introduzca la matricula: ");
                matricula = sc.next();
                System.out.println("Introduzca la marca: ");
                marca = sc.next();
                System.out.println("Introduzca el modelo: ");
                modelo = sc.next();
                System.out.println("Introduzca el color: ");
                color = sc.next();
            	
                c.setId(id);
                c.setMatricula(matricula);
                c.setMarca(marca);
                c.setModelo(modelo);
                c.setColor(color);
                
        			
            	boolean modificar = dc.modificar(c);
        		if(modificar){
        			System.out.println("El coche se ha modificado");
        		}else{
        			System.out.println("El coche NO se ha modificado");
        		}
        			
        		break;
        		
            case 6:   
            	
            	menuPasajeros();
            	
            	break;
        		
            case 7: 
            	
            	System.out.println("El programa ha finalizado");
            	salir=true;
            	break;
            	
            default:
                System.out.println("Error: Introduzca un número entre el 1 y el 7.");
                break;
                
        
	} } while (opcion!=7);               


	}
	
	public static void menuPasajeros() {
		String cadenaConexion = "jdbc:mysql://localhost:3306/coches";
		String user = "root";
		String pass = ""; 
		Connection con = null;
		try {
			con = DriverManager.getConnection(cadenaConexion, user, pass);
		} catch (SQLException e) {
			System.out.println("No se ha podido establecer la conexión con la BD");
			System.out.println(e.getMessage());
			return;
		}

		System.out.println("Se ha establecido la conexión con la Base de datos");


        Scanner sc = new Scanner(System.in);
        Scanner entrada = new Scanner(System.in);
        int opcion;
        boolean salir = false;
        int id;
        String nombre;
        int edad;
        double peso;
        int idCoche;
        Pasajero p = new Pasajero();
        DaoPasajero dp = new DaoPasajeroMysql();
        DaoTransaccionMysql tr = new DaoTransaccionMysql();
        Coche c = new Coche();
        
        do {
        

		System.out.println("1- Añadir nuevo pasajero");
		System.out.println("2- Borrar pasajero por id");
		System.out.println("3- Consulta pasajero por id");
		System.out.println("4- Listar todos los pasajeros");
		System.out.println("5- Añadir Pasajero a coche");
		System.out.println("6- Eliminar pasajero de un coche");
		System.out.println("7- Listar pasajeros de un coche");
		System.out.println("8- Volver al menú principal");
		System.out.println("Escribe una de las opciones: ");
		opcion = sc.nextInt();

        switch (opcion) {
            case 1:

                System.out.println("Añadir nuevo pasajero");
                System.out.println("Introduzca el nombre del Pasajero: ");
                nombre = entrada.nextLine();
                System.out.println("Introduzca la edad: ");
                edad = sc.nextInt();
                System.out.println("Introduzca el peso: ");
                peso = entrada.nextDouble();

                
                p.setNombre(nombre);
                p.setEdad(edad);
                p.setPeso(peso);


                boolean alta = dp.alta(p);
                
                if(alta){
        			System.out.println("El pasajero se ha dado de alta");
        		}else{
        			System.out.println("El pasajero NO se ha dado de alta");
        		}

                System.out.println("**********************************");

                break;
                
            case 2:

                System.out.println("Introduza el Id del pasajero que quieres borrar");
                id = sc.nextInt();
        		
        		boolean baja = dp.baja(id);
        		if(baja){
        			System.out.println("El pasajero se ha dado de baja");
        		}else{
        			System.out.println("El pasajero NO se ha dado de baja");
        		}

                System.out.println("**********************************");

                System.out.println("Eliminado");

                break;
                
            case 3:

                System.out.println("Introduza el Id del pasajero que quiere obtener");
                id = sc.nextInt();
        		
        		Pasajero p1 = dp.obtener(id);
        		
        		System.out.println(p1);

                System.out.println("**********************************");

                break;    
                
            case 4:   
            	
            	System.out.println("********* LISTANDO TODOS LOS PASAJEROS **********");
        		
            	System.out.println(dp.listar());
        			
        		break;
            case 5:   
            	
            	System.out.println("Introduza el Id del coche al que quiere asignar el pasajero");
                idCoche = sc.nextInt();
            	System.out.println("Introduza el Id del pasajero que quiere asignar");
                id = sc.nextInt();
                
                p.setNombre(p.getNombre());
                p.setEdad(p.getEdad());
                p.setPeso(p.getPeso());
                p.setIdcoche(idCoche);
                p.setId(id);
                
                boolean asignar = tr.anadir(p);
                
        		if(asignar){
        			System.out.println("El pasajero se ha asignado");
        		}else{
        			System.out.println("El pasajero NO se ha asignado");
        		}
        		
        		System.out.println("***********************************");
                
                
            	break;
            	
            case 6:   
            	
            	System.out.println("Introduza el Id del coche al que quiere borrar el pasajero");
                idCoche = sc.nextInt();
            	System.out.println("Introduza el Id del pasajero que quiere dejar sin asignación");
                id = sc.nextInt();
                
                p.setNombre(p.getNombre());
                p.setEdad(p.getEdad());
                p.setPeso(p.getPeso());
                p.setIdcoche(idCoche);
                p.setId(id);
                
                boolean deletear = tr.eliminar(p);
                
        		if(deletear){
        			System.out.println("El pasajero se ha borrado del coche " + idCoche);
        		}else{
        			System.out.println("El pasajero NO se ha borrado");
        		}
        		
        		System.out.println("***********************************");

            	break;
        		
            case 7: 
            	
            	System.out.println("Introduza el Id del coche del cual quiere listar todos los pasajeros");
            	idCoche = sc.nextInt();
                p.setIdcoche(idCoche);
                
                System.out.println(tr.listarPasajeros(idCoche));
                
                
            	break;
            case 8: 
	
            	System.out.println("Saliendo al menú principal");
            	salir=true;
            	break;
            	
            default:
                System.out.println("Error: Introduzca un número entre el 1 y el 8.");
                break;
                
        
	} } while (opcion!=8);               
	}
	}
	
