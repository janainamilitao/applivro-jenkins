package com.domain.applivro.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MessageKey {

	@Getter
	@AllArgsConstructor
	public enum Error {

		REQUIRED( "O valor é obrigatório" ),
		INVALID( "O valor informado é inválido" ),
		LENGTH( "Deve ter {0} caracteres" ),
		MIN_SIZE( "Não deve conter menos de {0} caracteres" ),
		MAX_SIZE( "Não deve exceder mais de {0} caracteres" ),
		BOUND_SIZE( "Deve conter entre {0} e {1} caracteres" );

		private String key;
	}
	
	
	@Getter
	@AllArgsConstructor
	public enum Success {

		SUCCESS_OPERATION( "Operação realizada com sucesso!" );

		private String key;
	}
}
