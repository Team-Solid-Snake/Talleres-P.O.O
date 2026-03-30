package Taller;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ejercicio {
	
	static String[] contraseñas= new String[300];
	static String[] usuarios= new String[300];
	static int cantidadUsuarios=0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        Scanner s = new Scanner(System.in);
        boolean cargado = false; 

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
                menuUsuario(opcion);

            } catch (Exception e) {
                System.out.println("Por favor ingrese solamente números.");
                opcion = 0;
            }
           
            cargado= opciones(s, opcion, cargado);
            
        }while (opcion !=3);
        }
	
		private static boolean opciones(Scanner s, int opcion, boolean cargado) throws IOException {
		// TODO Auto-generated method stub
			switch(opcion) {
			case 1: {
				cargado = lecturaUsuarios(cargado);
				if (cargado) {
					iniciarSesion(s);
				}
				
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
		    for (int i = 0; i < cantidadUsuarios; i++) {
		        
		        if (usuarios[i].equals(usuIngresado) && contraseñas[i].equals(conIngresada)) {
		            System.out.println("Acceso correcto! Bienvenido " + usuarios[i]);
		            encontrado = true;
		            menuInternoUsuario(s, i); 
		            break;
		        }
		    }
		    if (!encontrado) System.out.println("Credenciales incorrectas.");
		}

		public static void menuUsuario(int n) {
			String usuario;
			Scanner s = new Scanner(System.in);
			if (n==1){
				System.out.print("Usuario: ");
				usuario= s.nextLine();
				System.out.println(usuario);	
		}
		}
		

		public static boolean lecturaUsuarios(boolean cargado) throws IOException {
			
			File file = new File("Usuarios.txt");
			Scanner s = new Scanner(file);
			int i = 0;
			while (s.hasNextLine()) {
				String line = s.nextLine();
				String[] partes= line.split(";");
				
				String usuario= partes[0];
				String contraseña = partes[1];
				
				contraseñas[i]=contraseña;
				usuarios[i]=usuario;
				
				i++;
				}
				cantidadUsuarios=i;
			
				
				
			
			return true;
		}

		public static void menuInternoUsuario(Scanner s, int indiceUsuario) throws IOException {
		    int opcionInterna = 0;
		    
		    String nombreActual = usuarios[indiceUsuario]; 

		    do {
		        System.out.println("\nBienvenido " + nombreActual + "!");
		        System.out.println("1) Registrar actividad");
		        System.out.println("2) Modificar actividad");
		        System.out.println("3) Eliminar actividad");
		        System.out.println("4) Cambiar contraseña");
		        System.out.println("5) Salir");
		        System.out.print("> ");

		        try {
		            opcionInterna = Integer.parseInt(s.nextLine());
		        } catch (Exception e) {
		            System.out.println("Error: Ingrese un número válido.");
		            opcionInterna = 0;
		        }
		    } while (opcionInterna != 5);
		}
			
		}
	


