package General;

import java.util.ArrayList;
import java.util.List;

public class Entrenador {
	
	private String nombre;
	private int cantMedallas;
	private List <Pokemon> listaPokemons;
	public Entrenador(String nombre) {
		super();
		this.nombre = nombre;
		this.cantMedallas=0;
		this.listaPokemons = new ArrayList<Pokemon>() ;
	}
	public boolean verificarCaptura(Pokemon nuevo) {
		for (Pokemon p : listaPokemons) {
			if (p.getNombre().equalsIgnoreCase(nuevo.getNombre())){
				return true;
			}
			
		}
		return false;
		
	}
	public void capturar(Pokemon nuevo) {
		if (verificarCaptura(nuevo)== false) {
			listaPokemons.add(nuevo);
			System.out.println("Pokemon atrapado con éxito!!");
			
		} else {
			
			// El pokemon ya estaba capturado
			System.out.println("Pokemon "+ nuevo.getNombre() +" ya fue capturado");
		}
	}
	
}
