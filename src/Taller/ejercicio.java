package Taller;

import java.util.Scanner;

public class ejercicio {

	public static void main(String[] args) {
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
            
        }while (opcion !=3);
        }
	
		public static void menuUsuario(int n) {
			String usuario;
			String contraseña;
			Scanner s = new Scanner(System.in);
			if (n==1){
				System.out.print("Usuario: ");
				usuario= s.next();
				System.out.print("Contraseña: ");
				contraseña = s.next();
				
				
			}
			
		}
	}

