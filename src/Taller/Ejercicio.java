package Taller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio {
	
	static String[] contraseñas= new String[50];
	static String[] usuarios= new String[50];
	static int cantidadUsuarios=0;
	static String[] regUsuarios = new String[300];
    static String[] regFechas = new String[300];
    static int[] regHoras = new int[300];
    static String[] regActividades = new String[300];
    static int cantRegistros = 0;
	
	
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        Scanner s = new Scanner(System.in);
        boolean cargado = false; 
        cargarRegistros();
        cargarUsuarios();
        int opcion = 0;
        
        System.out.println("");
        do {
            System.out.println();
            System.out.println("1) Menu de usuario");
            System.out.println("2) Menu de Analisis");
     
            System.out.println("3) Salir");
            System.out.print("> ");

            String entrada = s.nextLine();
            System.out.println();

            try {
                opcion = Integer.parseInt(entrada);


            } catch (Exception e) {
                System.out.println("Por favor ingrese solamente números.");
                opcion = 0;
            }
           
            cargado= opciones(s, opcion, cargado);
            
        }while (opcion !=3);
        	System.out.print("Adios!");
        }
	

		private static void cargarRegistros() {
		// TODO Auto-generated method stub
			cantRegistros=0;
			try {
				File file= new File("Registros.txt");
				Scanner s= new Scanner(file);
				while (s.hasNextLine() && cantRegistros < 300) {
					
	                String linea = s.nextLine();
                    String[] partes = linea.split(";");
                    
                    regUsuarios[cantRegistros] = partes[0];
                    regFechas[cantRegistros] = partes[1];
                    regHoras[cantRegistros] = Integer.parseInt(partes[2]);
                    regActividades[cantRegistros] = partes[3];
                    
                    cantRegistros++;
                
	                }
			} catch (Exception e) {
				System.out.println("Error al cargar los registros");
			}
		
	}
		private static void guardarRegistros() {
	        try {
	            FileWriter writer = new FileWriter("Registros.txt");
	            for (int i = 0; i < cantRegistros; i++) {
	            	String linea = regUsuarios[i] + ";" + regFechas[i] + ";" + regHoras[i] + ";" + regActividades[i];
	            	writer.write(linea + "\n");
	            }
	            writer.close();
	        } catch (IOException e) {
	            System.out.println("Error al guardar Registros.txt");
	        }
	    }


		private static boolean opciones(Scanner s, int opcion, boolean cargado) throws IOException {
		// TODO Auto-generated method stub
			switch(opcion) {
			case 1: {
					iniciarSesion(s);
				
			}
			case 2:
					menuDos(s);
			}
		return false;
	}

		public static void iniciarSesion(Scanner s) throws IOException {
			
		    System.out.print("Usuario: ");
		    String usuIngresado = s.nextLine();

		    System.out.print("Contraseña: ");
		    String conIngresada = s.nextLine();


		    boolean encontrado = false;
		    int i =0;
		    while (!encontrado ) {
		        
		        if (usuarios[i].equalsIgnoreCase(usuIngresado) && contraseñas[i].equalsIgnoreCase(conIngresada)) {
		            System.out.println("Acceso correcto! Bienvenido " + usuarios[i]);
		            encontrado = true;
		            menuInternoUsuario(s, i); 
		            break;
		        }
		        else {
		        	
		        	i++;
		        }
		    }
		    if (!encontrado) System.out.println("Credenciales incorrectas.");
		}
		
		
		private static void cargarUsuarios() throws FileNotFoundException {
			
			cantidadUsuarios=0;
			
			try {
				File file = new File("Usuarios.txt");
				
				Scanner s= new Scanner(file);
				while (s.hasNextLine()) {
				
					String linea= s.nextLine();
					String[] partes= linea.split(";");
					
					String user= partes[0];
					String pass=partes[1];
					
					usuarios[cantidadUsuarios]=user;
					contraseñas[cantidadUsuarios]=pass;
					cantidadUsuarios++;
				}
				s.close();
				
			
				
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("Archivo usuarios.txt no encontrado, revisa donde tienes alojado tu arch");
			}
		}
		
		private static void menuDos(Scanner s) {
			// TODO Auto-generated method stub
			System.out.println("Bienvenido al menu de analisis!");
			System.out.println();
			System.out.println("Que desea realizar?");
			System.out.println();
			System.out.println("1) Activida más realizada");
			System.out.println("2) Actividad más realizada por cada usuario");
			System.out.println("3) Usuario con mayor procastinación");
			System.out.println("4) Ver todas las actividades");
			System.out.println("5) Salir");
			
			try {
				int opcion= Integer.parseInt(s.nextLine());
				
				while (opcion > 5 || opcion < 1) {
					System.out.println("Ingresaste valores fuera de rango");
					opcion= Integer.parseInt(s.nextLine());
				}
				if (opcion == 1){
					actividadMasRealizada();
					
				} else if (opcion ==2) {
					actividadMasRealizadaPorUsuario();
				}else if (opcion==3) {
					usuarioMayorProcastinacion();
				}else if (opcion==4) {
					verTodasLasActividades();
				}
				} catch (Exception e) {
					System.out.println("Error al ingresar");
				}
		}


		
		private static void usuarioMayorProcastinacion() {
			// TODO Auto-generated method stub
			String max="";
			int maxHoras=-1;
			
			for (int i = 0; i < cantidadUsuarios; i++) {
				String user =usuarios[i];
				int suma= 0;
				for (int j = 0; j < cantRegistros; j++) {
					if (regUsuarios[i].equalsIgnoreCase(user)) {
		                suma += regHoras[i];
		            
				}
				
			}
		if (suma > maxHoras) {
			maxHoras=suma;
			max=user;
		}
		}
			System.out.println("\nEl usuario con mayor procrastinación es: " + max);
	        System.out.println("Total de horas consumidas: " + maxHoras + " hrs.\n");
		}


		private static void actividadMasRealizadaPorUsuario() {
			// TODO Auto-generated method stub
			for ( int n =0; n<cantidadUsuarios; n++) {
			String actividadMax="";
			
			String usuarioPorVer=usuarios[n];
			int maxConteo = 0;
			int maxHoras=0;
			for (int i = 0; i < cantRegistros; i++) {
				if (regUsuarios[i].equalsIgnoreCase(usuarioPorVer)) {
					String actividadN= regActividades[i];
					int contador= 0;
					int cantHoras=0;
					for (int j = 0; j < cantRegistros; j++) {
						if (regActividades[j].equalsIgnoreCase(actividadN) && regUsuarios[j].equalsIgnoreCase(usuarioPorVer)){
							contador++;
							cantHoras = cantHoras + regHoras[j];
							}
					}
						if (contador > maxConteo) {
							maxConteo = contador;
							actividadMax = actividadN;
							maxHoras = cantHoras;}}
		
			}
			if (maxConteo >0) {
			System.out.println("*"+ usuarioPorVer+"->"+ actividadMax+" -> con "+ maxHoras + " horas registradas");
		}
			}	
			System.out.println();
		}


		private static void verTodasLasActividades() {
			// TODO Auto-generated method stub
			System.out.println("\n--- LISTA DE TODAS LAS ACTIVIDADES ---");
			if (cantRegistros == 0) {
				System.out.println("No hay actividades registradas aún.");
				return;
			}
			
			for (int i = 0; i < cantRegistros; i++) {
				System.out.println((i+1) + ") Usuario: " + regUsuarios[i] + " | Actividad: " + regActividades[i] + " | Duración: " + regHoras[i] + " hrs | Fecha: " + regFechas[i]);
			}
			System.out.println("--------------------------------------");
		}
		


		private static void actividadMasRealizada() {
			String actividadMax="";
			int maxConteo = 0;
			int maxHoras=0;
			for (int i = 0; i < cantRegistros; i++) {
				String actividadN= regActividades[i];
				int contador= 0;
				int cantHoras=0;
				for (int j = 0; j < cantRegistros; j++) {
					if (regActividades[j].equalsIgnoreCase(actividadN)){
						contador++;
						cantHoras= cantHoras+ regHoras[j];
					}
				}
				if (contador > maxConteo) {
					maxConteo= contador;
					actividadMax=actividadN;
					maxHoras=cantHoras;
				}
			}
			System.out.println("La actividad mas realizada es "+ actividadMax + " Con "+ maxHoras +" horas registradas" );
		}


		private static void menuInternoUsuario(Scanner s, int i) {
			// TODO Auto-generated method stub
			System.out.println("Que deseas realizar?");
			System.out.println();
			System.out.println("1) Registrar actividad.");
			System.out.println("2) Modificar actividad.");
			System.out.println("3) Eliminar actividad.");
			System.out.println("4) Cambiar actividad.");
			System.out.println("5) Salir.");
			
			System.out.println(">");
			try {
			int opcion= Integer.parseInt(s.nextLine());
			
			while (opcion > 5 || opcion < 1) {
				System.out.println("Ingresaste valores fuera de rango");
				opcion= Integer.parseInt(s.nextLine());
			}
			if (opcion == 1){
				registrarActividad(s, usuarios[i]);
				
			} else if (opcion ==2) {
				
			}
			} catch (Exception e) {
				System.out.println("Error al ingresar");
			}
			
			
		}


		private static void registrarActividad(Scanner s, String usuarios2) {
			// TODO Auto-generated method stub
			
		}


				
			
			
		}
	

