package br.com.fortium.bibliorium.service;


public interface LivroService extends Service {
	boolean isIsbnCadastrado(String isbn);
}
