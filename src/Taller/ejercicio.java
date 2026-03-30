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

		private static void menuInternoUsuario(Scanner s, int i) {
			// TODO Auto-generated method stub
			
		}


				
			
			
		}
	

