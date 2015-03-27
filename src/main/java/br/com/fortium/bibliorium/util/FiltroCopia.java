package br.com.fortium.bibliorium.util;

import br.com.fortium.bibliorium.persistence.entity.Copia;
import br.com.fortium.bibliorium.persistence.enumeration.EstadoCopia;

import com.google.common.base.Predicate;

public class FiltroCopia implements Predicate<Copia> {

	private static final String TIPO_DISPONIVEL   = "disponivel";
	private static final String TIPO_INDISPONIVEL = "indisponivel";
	
	public static final FiltroCopia DISPONIVEL   = new FiltroCopia(TIPO_DISPONIVEL);
	public static final FiltroCopia INDISPONIVEL = new FiltroCopia(TIPO_INDISPONIVEL);
	
	private String tipo;
	
	private FiltroCopia(String tipo){
		this.tipo = tipo;
	}
	
	@Override
	public boolean apply(Copia input) {
		switch(this.tipo){
			case TIPO_DISPONIVEL : return  input.getEstado() == EstadoCopia.DISPONIVEL;
			case TIPO_INDISPONIVEL : return  (input.getEstado() == EstadoCopia.EMPRESTADA) || (input.getEstado() == EstadoCopia.RESERVADA);
			default : throw new IllegalArgumentException("Tipo não declarado");
		}
	}

}
