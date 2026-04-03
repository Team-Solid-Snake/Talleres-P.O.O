package Taller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio 
{
	
	static String[] contraseñas= new String[50];
	static String[] usuarios= new String[50];
	static int cantidadUsuarios=0;
	static String[] regUsuarios = new String[300];
    static String[] regFechas = new String[300];
    static int[] regHoras = new int[300];
    static String[] regActividades = new String[300];
    static int cantRegistros = 0;
	
	
	

	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
        Scanner s = new Scanner(System.in);
        boolean cargado = false; 
        cargarRegistros();
        cargarUsuarios();
        int opcion = 0;
        
        System.out.println("");
        do {
            System.out.println();
            System.out.println("1) Menu de Usuarios");
            System.out.println("2) Menu de Análisis");
     
            System.out.println("3) Salir");
            System.out.print("> ");

            String entrada = s.nextLine();
            System.out.println();

            try {
                opcion = Integer.parseInt(entrada);
                if (opcion < 1 || opcion > 3) 
                {
                	System.out.println("Error, ingrese una opción válida.");
                	
                }


            } catch (Exception e) {
                System.out.println("Error, ingrese solamente números.");
                opcion = 0;
            }
           
            cargado = opciones(s, opcion, cargado);
            
        } while (opcion != 3);
        	if (opcion == 3) 
        	{
        		System.out.print("Adios!");
             		
        	}
        }
	

		private static void cargarRegistros() 
		{
		// TODO Auto-generated method stub
			cantRegistros=0;
			try 
			{
				File file= new File("Registros.txt");
				Scanner s= new Scanner(file);
				
				while (s.hasNextLine() && cantRegistros <= 300) 
				{
					
	                String linea = s.nextLine();
                    String[] partes = linea.split(";");
                    
                    regUsuarios[cantRegistros] = partes[0];
                    regFechas[cantRegistros] = partes[1];
                    regHoras[cantRegistros] = Integer.parseInt(partes[2]);
                    regActividades[cantRegistros] = partes[3];
                    
                    cantRegistros++;
                
					}	
			} catch (Exception e) 
			{
				System.out.println("Error, el archivo Registros.txt no ha sido encontrado, por favor intente nuevamente.");
				
			}
	}
		private static void guardarRegistros() 
		{
	        try 
	        {
	            FileWriter writer = new FileWriter("Registros.txt");
	            for (int i = 0; i < cantRegistros; i++) 
	            {
	            	String linea = regUsuarios[i] + ";" + regFechas[i] + ";" + regHoras[i] + ";" + regActividades[i];
	            	writer.write(linea + "\n");
	            	
	            }
	            writer.close();
	        } catch (IOException e) 
	        {
	            System.out.println("Error al guardar el archivo Registros.txt");
	        
	        }
	    }

		private static boolean opciones(Scanner s, int opcion, boolean cargado) throws IOException 
		{
		// TODO Auto-generated method stub
			switch(opcion) 
			{
			case 1: {
					iniciarSesion(s);
				break;
				
			}
			case 2:
					menuDos(s);
					break;
			}
		return false;
		
	}

		public static void iniciarSesion(Scanner s) throws IOException 
		{
			
		    System.out.print("Usuario: ");
		    String usuIngresado = s.nextLine();

		    System.out.print("Contraseña: ");
		    String conIngresada = s.nextLine();


		    boolean encontrado = false;
		    int i =0;
		    while (!encontrado && i < cantidadUsuarios ) 
		    {    
		        if (usuarios[i].equalsIgnoreCase(usuIngresado) && contraseñas[i].equalsIgnoreCase(conIngresada)) {
		            System.out.println("Acceso correcto! Bienvenido " + usuarios[i]);
		            encontrado = true;
		            menuInternoUsuario(s, i,usuIngresado); 
		            break;
		            
		        }
		        else 
		        {
		        	i++;
		        	
		        }
		    }
		    if (!encontrado) System.out.println("Credenciales erróneas o el usuario ingresado no existe.");
		    
		}
		
		
		private static void cargarUsuarios() throws FileNotFoundException 
		{
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
				
			}catch (Exception e) 
			{
				// TODO: handle exception
				System.out.println("Error, el archivo Usuarios.txt no ha sido encontrado, por favor intente nuevamente.");
			
			}
		}
		
		private static void menuDos(Scanner s) {
			// TODO Auto-generated method stub
			System.out.println("Bienvenido al menú de análisis!");
			System.out.println();
			System.out.println("Que desea realizar?");
			System.out.println();
			System.out.println("1) Actividad más realizada.");
			System.out.println("2) Actividad más realizada por cada usuario.");
			System.out.println("3) Usuario con mayor procastinación.");
			System.out.println("4) Ver todas las actividades.");
			System.out.println("5) Regresar.");
						
			try 
			{
				int opcion = Integer.parseInt(s.nextLine());
				
				while (opcion > 5 || opcion < 1) 
				{
					System.out.println("Error, ingrese una opción válida.");
					opcion = Integer.parseInt(s.nextLine());
					
				}
				if (opcion == 1){
					actividadMasRealizada();
					
				} else if (opcion ==2) 
				{
					actividadMasRealizadaPorUsuario();
					
				} else if (opcion==3) 
				{
					usuarioMayorProcastinacion();
					
				} else if (opcion==4) 
				{
					verTodasLasActividades();
					
				}
				} catch (Exception e) 
				{
					System.out.println("Error, ingrese solamente números.");
					
				}
		}
		
		private static void usuarioMayorProcastinacion() 
		{
			// TODO Auto-generated method stub
			String max = "";
			int maxHoras = -1;
			
			for (int i = 0; i < cantidadUsuarios; i++) 
				
			{
				String user =usuarios[i];
				int suma= 0;
				for (int j = 0; j < cantRegistros; j++) 
					
				{
					if (regUsuarios[j].equalsIgnoreCase(user)) 
					{
		                suma += regHoras[j];
		            
					}
				
				}
				if (suma > maxHoras) 
				{
					maxHoras=suma;
					max=user;
			
				}
			}
			System.out.println("\nEl usuario con mayor procrastinación es: " + max);
	        System.out.println("Total de horas consumidas: " + maxHoras + " hrs.\n");
	        
		}

		private static void actividadMasRealizadaPorUsuario() 
		{
			// TODO Auto-generated method stub
			for ( int n =0; n<cantidadUsuarios; n++) 
			{
				String actividadMax="";
			
				String usuarioPorVer = usuarios[n];
				int maxConteo = 0;
				int maxHoras = 0;
				for (int i = 0; i < cantRegistros; i++) 
				{
					if (regUsuarios[i].equalsIgnoreCase(usuarioPorVer)) 
					{
						String actividadN = regActividades[i];
						int contador = 0;
						int cantHoras =0;
					
						for (int j = 0; j < cantRegistros; j++) 
						{
							if (regActividades[j].equalsIgnoreCase(actividadN) && regUsuarios[j].equalsIgnoreCase(usuarioPorVer))
							{
							contador++;
							cantHoras = cantHoras + regHoras[j];
							
							}
						}
						if (contador > maxConteo) 
						{
							maxConteo = contador;
							actividadMax = actividadN;
							maxHoras = cantHoras;
							
						}
					}
		
				}
				if (maxConteo > 0) 
				{
					System.out.println("*"+ usuarioPorVer+"->"+ actividadMax+" -> con "+ maxHoras + " horas registradas");
				}
			}	
			System.out.println();
		}

		private static void verTodasLasActividades() 
		{
			// TODO Auto-generated method stub
			System.out.println("\n--- LISTA DE TODAS LAS ACTIVIDADES ---");
			if (cantRegistros == 0) 
			{
				System.out.println("No hay actividades registradas aún.");
				return;
				
			}
			
			for (int i = 0; i < cantRegistros; i++) 
			{
				System.out.println((i+1) + ") Usuario: " + regUsuarios[i] + " | Actividad: " + regActividades[i] + " | Duración: " + regHoras[i] + " hrs | Fecha: " + regFechas[i]);
				
			}
			System.out.println();
			
		}
		
		private static void actividadMasRealizada() 
		{
			String actividadMax="";
			int maxConteo = 0;
			int maxHoras=0;
			for (int i = 0; i < cantRegistros; i++) 
			{
				String actividadN= regActividades[i];
				int contador= 0;
				int cantHoras=0;
				for (int j = 0; j < cantRegistros; j++) 
				{
					if (regActividades[j].equalsIgnoreCase(actividadN))
					{
						contador++;
						cantHoras= cantHoras+ regHoras[j];
						
					}
				}
				if (contador > maxConteo) 
				{
					maxConteo= contador;
					actividadMax=actividadN;
					maxHoras=cantHoras;
					
				}
			}
			System.out.println("La actividad más realizada es " + actividadMax + " con "+ maxHoras + " horas registradas");
			
		}

		private static void menuInternoUsuario(Scanner s, int i, String usuIngresado) 
		{
			// TODO Auto-generated method stub
			System.out.println("Que deseas realizar?");
			System.out.println();
			System.out.println("1) Registrar actividad.");
			System.out.println("2) Modificar actividad.");
			System.out.println("3) Eliminar actividad.");
			System.out.println("4) Cambiar contraseña.");
			System.out.println("5) Regresar.");
			
			try 
			{
				int opcion= Integer.parseInt(s.nextLine());
			
				while (opcion > 5 || opcion < 1)
				{
					System.out.println("Error, ingrese una opción válida.");
					opcion= Integer.parseInt(s.nextLine());
				}
				if (opcion == 1)
				{
					registrarActividad(s, usuIngresado);
				
				} else if (opcion ==2) 
				{
					modificarActividad(s,usuIngresado);
				
				} else if (opcion ==3)
				{
					eliminarActividad(s, usuIngresado);
				
				} else if (opcion ==4) 
				{
					cambiarContraseña(s, usuIngresado,i);
				
				}
			} catch (Exception e) 
			{
				System.out.println("Error, ingrese solamente números.");
				
			}
		}

		private static void modificarActividad(Scanner s, String usuIngresado) 
		{
			// TODO Auto-generated method stub
			System.out.println();
			System.out.println("Cual actividad desea modificar?");
			System.out.println();
			for (int j = 0; j < cantRegistros; j++) {
		        if (regUsuarios[j].equalsIgnoreCase(usuIngresado)) {
		            System.out.println(j + ") " + regActividades[j] + " [" + regFechas[j] + " - " + regHoras[j] + "hrs]");
		        }
		    }
			System.out.println("0) Regresar.");
			 
			int indice= Integer.parseInt(s.nextLine());
			if (indice == 0) 
			{
				return;
				
			}
			
			if (indice > 0 && indice < cantRegistros && regUsuarios[indice].equalsIgnoreCase(usuIngresado)) 
			{
				System.out.println("Que deseas modificar?");
				System.out.println();
				System.out.println("0) Regresar.");
				System.out.println("1) Fecha");
				System.out.println("2) Duracion.");
				System.out.println("3) Tipo de actividad");
				int indCambio= Integer.parseInt(s.nextLine());
				
				if (indCambio == 1) 
				{
					System.out.println("Día (DD): ");
					String dia = s.nextLine();
					
					try 
					{
						while (Integer.valueOf(dia) < 1 || Integer.valueOf(dia) > 31) 
						{
							System.out.println("Error, ingrese un día válido.");
							dia = s.nextLine();
							
						}
						
					} catch(Exception e)
					{
						System.out.println("Error, ingrese solamente números del 01 al 31.");
						dia = s.nextLine();
					
					}
					
					System.out.println("Mes (MM): ");
					String mes = s.nextLine();
					
					try 
					{
						while (Integer.valueOf(mes) < 1 || Integer.valueOf(mes) > 12) 
						{
							System.out.println("Error, ingrese un mes válido.");
							mes = s.nextLine();
							
						}
						
					} catch(Exception e){
						System.out.println("Error, ingrese solamente números del 01 al 12.");
						mes = s.nextLine();
					
					}
					
					System.out.println("Año (AAAA): ");
					String año = s.nextLine();
					
					try 
					{
						while (Integer.valueOf(año) <= 0 || Integer.valueOf(año) > 2026) 
						{
							System.out.println("Error, ingrese un año válido.");
							año = s.nextLine();
							
						}
						
					} catch(Exception e)
					{
						System.out.println("Error, ingrese un año válido.");
						año = s.nextLine();
					
					}
					
					regFechas[indice] = (dia + "/" + mes + "/" + año);
				} else if (indCambio == 2) 
				{
					System.out.println("Nueva duración: ");
					regHoras[indice]=Integer.parseInt(s.nextLine());
					
				} else if (indCambio == 3) 
				{
					System.out.println("Nueva Actividad: ");
					regActividades[indice]=s.nextLine();
					
				} else if (indCambio == 0) 
				{
					return;
					
				}
				guardarRegistros();
				System.out.println("Cambios realizados con exito!");
				
			} else 
			{
				System.out.println("El índice ingresado no es válido.");
				
			}
			
		}


		private static void cambiarContraseña(Scanner s, String usuIngresado, int i) 
		{
			System.out.println("Nueva contraseña: ");
			String contraNueva= s.nextLine();
			contraseñas[i]= contraNueva;
			guardarUsuarios();
			System.out.println("Contraseña cambiada!");
			
		}


		private static void guardarUsuarios() {
			    try 
			    {
			        FileWriter writer = new FileWriter("Usuarios.txt");
			        for (int i = 0; i < cantidadUsuarios; i++) 
			        {
			            writer.write(usuarios[i] + ";" + contraseñas[i] + "\n");
			            
			        }
			        writer.close();
			    } catch (IOException e) 
			    {
			        System.out.println("Error al guardar el archivo Usuarios.txt");
			        
			    }
		}


		private static void eliminarActividad(Scanner s, String usuIngresado) 
		{
			// TODO Auto-generated method stub
			System.out.println("\n--- Eliminar Actividad ---");
		    
		    boolean tiene = false;
		    for (int j = 0; j < cantRegistros; j++) 
		    {
		        if (regUsuarios[j].equalsIgnoreCase(usuIngresado)) {
		            System.out.println(j + ") " + regActividades[j] + " [" + regFechas[j] + "]");
		            tiene = true;
		            
		        }
		    }

		    if (!tiene) 
		    {
		        System.out.println("No tienes actividades para eliminar.");
		        return;
		        
		    }

		    System.out.print("Ingresa el índice de la actividad a eliminar: ");
		    try 
		    {
		        int indice = Integer.parseInt(s.nextLine());

		       
		        if (indice >= 0 && indice < cantRegistros && regUsuarios[indice].equalsIgnoreCase(usuIngresado)) 
		        {      
		            for (int k = indice; k < cantRegistros - 1; k++) 
		            {
		                regUsuarios[k] = regUsuarios[k + 1];
		                regFechas[k] = regFechas[k + 1];
		                regHoras[k] = regHoras[k + 1];
		                regActividades[k] = regActividades[k + 1];
		                
		            }
		            
		            cantRegistros--;
		            guardarRegistros(); 
		            System.out.println("Actividad eliminada con éxito.");
		        } else 
		        {
		            System.out.println("El índice ingresado no es válido.");
		            
		        }
		    } catch (Exception e) 
		    {
		        System.out.println("Error al ingresar el índice.");
		        
		    }
		
		}


		private static void registrarActividad(Scanner s, String usuarios2) 
		{
			if (cantRegistros >= 300) 
			{
		        System.out.println("Límite de registros alcanzado.");
		        return;
		        
		    }
		    System.out.println("\n--- Nuevo Registro ---");
		    System.out.println("Fecha (DD/MM/AAAA)");
		    System.out.println("Día (DD): ");
			String dia = s.nextLine();
			
			try 
			{
				while (Integer.valueOf(dia) < 1 || Integer.valueOf(dia) > 31) 
				{
					System.out.println("Error, ingrese un día válido.");
					dia = s.nextLine();
					
				}
			} catch(Exception e)
			{
				System.out.println("Error, ingrese solamente números del 01 al 31.");
				dia = s.nextLine();
			
			}
			
			System.out.println("Mes (MM): ");
			String mes = s.nextLine();
			try 
			{
				while (Integer.valueOf(mes) < 1 || Integer.valueOf(mes) > 12) 
				{
					System.out.println("Error, ingrese un mes válido.");
					mes = s.nextLine();
					
				}
				
			} catch(Exception e)
			{
				System.out.println("Error, ingrese solamente números del 01 al 12.");
				mes = s.nextLine();
			
			}
			
			System.out.println("Año (AAAA): ");
			String año = s.nextLine();
			try 
			{
				while (Integer.valueOf(año) <= 0 || Integer.valueOf(año) > 2026) 
				{
					System.out.println("Error, ingrese un año válido.");
					año = s.nextLine();
					
				}
				
			} catch(Exception e)
			{
				System.out.println("Error, ingrese un año válido.");
				año = s.nextLine();
			
			}
		    
			String fecha = (dia + "/" + mes + "/" + año);	
		    System.out.print("Horas invertidas: ");
		    int horas = Integer.parseInt(s.nextLine());
		    System.out.print("Actividad realizada: ");
		    String actividad = s.nextLine();

		
		    regUsuarios[cantRegistros] = usuarios2;
		    regFechas[cantRegistros] = fecha;
		    regHoras[cantRegistros] = horas;
		    regActividades[cantRegistros] = actividad;
		    
		    cantRegistros++;
		    guardarRegistros(); 
		    System.out.println("¡Actividad guardada!");
		    
		}
}
	

