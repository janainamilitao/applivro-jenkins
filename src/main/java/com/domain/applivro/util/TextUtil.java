package com.domain.applivro.util;

import org.springframework.util.StringUtils;

public final class TextUtil {

	public static final boolean isCPF( final String cpf ) {
		if( !StringUtils.isEmpty( cpf ) ) return cpf.replaceAll( "\\D", "" ).length() == 11;
		return false;
	}

	public static final boolean isCNPJ( final String cnpj ) {
		if( !StringUtils.isEmpty( cnpj ) ) return cnpj.replaceAll( "\\D", "" ).length() == 14;
		return false;
	}

	public static final String formatarCPFCNPJ(final String cpfCnpj) {
		if(StringUtils.isEmpty( cpfCnpj )) return null;

		String docFormatado = cpfCnpj;
		if(cpfCnpj.length() == 11) {
			docFormatado = TextUtil.formatarCPF( cpfCnpj );
		} else if(cpfCnpj.length() == 14) {
			docFormatado = TextUtil.formatarCNPJ( cpfCnpj );
		}
		return docFormatado;
	}

	private static String formatarCPF( final String cpf ) {
		if(StringUtils.isEmpty( cpf )) return null;

		if(cpf.length() == 11)
			return new StringBuffer().append( cpf.substring( 0, 3 ) ).append( "." )
				.append( cpf.substring( 3, 6 ) ).append( "." )
				.append( cpf.substring( 6, 9 ) ).append( "-" )
				.append( cpf.substring( 9, cpf.length() ) )
				.toString();
		return cpf;
	}

	private static String formatarCNPJ( final String cnpj ) {
		if(StringUtils.isEmpty( cnpj )) return null;

		if(cnpj.length() == 14)
			return new StringBuffer().append( cnpj.substring( 0, 2 ) ).append( "." )
				.append( cnpj.substring( 2, 5 ) ).append( "." )
				.append( cnpj.substring( 5, 8 ) ).append( "/" )
				.append( cnpj.substring( 8, 12 ) ).append( "-" )
				.append( cnpj.substring( 12, cnpj.length() ) )
				.toString();
		return cnpj;
	}

	public static final String retornarDigitos(final String texto) {
		if(StringUtils.isEmpty( texto )) return texto;
		return texto.replaceAll( "\\D", "" );
	}

	public static boolean isGreaterThan( final String str, final int size ) {
		if ( StringUtils.isEmpty( str ) )
			return false;
		return str.length() > size;
	}

	public static boolean isLessThan( final String str, final int size ) {
		if ( StringUtils.isEmpty( str ) )
			return false;
		return str.length() < size;
	}

	public static boolean isBetween( final String str, final int min, final int max ) {
		return isGreaterThan( str, min ) && isLessThan( str, max );
	}

	public static boolean hasLengthEqual( final String str, final int size ) {
		if( StringUtils.isEmpty( str ) )
			return false;
		return str.length() == size;
	}
}
