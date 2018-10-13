package com.domain.applivro.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MessageKey {

	@Getter
	@AllArgsConstructor
	public enum Error {

		REQUIRED( "required.message" ),
		INVALID( "invalid.value.message" ),
		LENGTH( "length.message" ),
		MIN_SIZE( "min.message" ),
		MAX_SIZE( "max.message" ),
		BOUND_SIZE( "bound.size.message" );

		private String key;
	}

	@Getter
	@AllArgsConstructor
	public enum Success {

		SUCCESS_OPERATION( "success.operation.message" );

		private String key;
	}
}
