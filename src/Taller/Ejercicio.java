package Taller;

import java.io.File;
import java.io.FileNotFoundException;
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


		private static boolean opciones(Scanner s, int opcion, boolean cargado) throws IOException {
		// TODO Auto-generated method stub
			switch(opcion) {
			case 1: {
					iniciarSesion(s);
				
			}
			
			
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
		    while (!encontrado) {
		        
		        if (usuarios[i].equalsIgnoreCase(usuIngresado) && contraseñas[i].equalsIgnoreCase(conIngresada)) {
		            System.out.println("Acceso correcto! Bienvenido " + usuarios[i]);
		            encontrado = true;
		            menuInternoUsuario(s, i); 
		        
		        }
		    }
		    if (!encontrado) System.out.println("Credenciales incorrectas.");
		}
		
		
		private static void cargarUsuarios() throws FileNotFoundException {
			
			int i=0;
			
			try {
				File file = new File("Usuarios.txt");
				
				Scanner s= new Scanner(file);
				while (s.hasNextLine()) {
				
					String linea= s.nextLine();
					String[] partes= linea.split(";");
					
					String user= partes[0];
					String pass=partes[1];
					
					usuarios[i]=user;
					contraseñas[i]=pass;
					i++;
				}
				s.close();
				
			
				
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("Archivo usuarios no encontrado, revisa donde tienes alojado tu arch");
			}
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
		
			int opcion= Integer.parseInt(s.nextLine());
			
			while (opcion > 5 || opcion < 1) {
				System.out.println("Ingresaste valores fuera de rango");
				opcion= Integer.parseInt(s.nextLine());
			}
			if (opcion == 1){
				registrarActividad(s, usuarios[i]);
				
			} else if (opcion ==2) {
				
			}
			
			
			
		}


		private static void registrarActividad(Scanner s, String usuarios2) {
			// TODO Auto-generated method stub
			
		}


				
			
			
		}
	

