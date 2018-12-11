package com.domain.applivro.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Component
public class WebMessage {

	private final ObjectMapper objectMapper = new ObjectMapper();

	public static final String FEEDBACK_TOASTR = "feedback";

	public static final String MESSAGE_SUCCESS = "success";
	public static final String MESSAGE_ERROR = "error";

	@Resource
	private MessageSource messageSource;

	private String getMessage( String messageKey, Object ... params ) {
		Locale current = LocaleContextHolder.getLocale();
		log.info( "Locale: {}", current );
		try {
			return this.messageSource.getMessage( messageKey, params, current );
		} catch (NoSuchMessageException e) {
			return messageKey;
		}
	}

	public void addSuccess( Model attributes, String messageKey, Object ... params ) {
		addMessage( FeedType.SUCCESS, attributes, messageKey, params );
	}
	

	public void addError( Model attributes, String messageKey, Object ... params ) {
		addMessage( FeedType.ERROR, attributes, messageKey, params );
	}

	private void addMessage( FeedType type, Model attributes, String messageKey, Object ... params ) {
		log.info( "Adicionando mensagem {} com a chave: '{}' e os parâmetros: {}", type, messageKey, params );
		String message = getMessage( messageKey, params );
		log.info( "Mensagem: {}", message );
		if( attributes instanceof RedirectAttributes ) {
			messageRedirect( type, ( RedirectAttributes ) attributes, message );
		} else {
			message( type, attributes, message );
		}
	}

	private void messageRedirect( FeedType type, RedirectAttributes attributes, String message ) {
		Map<String, List<String>> feedback = getFeedbackToFlash( attributes );
		registreMessage( type, message, feedback );
		attributes.addFlashAttribute( FEEDBACK_TOASTR, feedback );
	}

	private void message( FeedType type, Model model, String message ) {
		Map<String, List<String>> feedback = getFeedback( model );
		registreMessage( type, message, feedback );
		model.addAttribute( FEEDBACK_TOASTR, feedback );
	}

	private void addFeedback( FeedType type, String message, Map<String, List<String>> feedback ) {
		if ( feedback.containsKey( type.value ) ) {
			feedback.get( type.value ).addAll( new LinkedList<>( Arrays.asList( message ) ) );
		} else {
			feedback.put( type.value, new LinkedList<>( Arrays.asList( message ) ) );
		}
	}
	
	
	private Map<String, List<String>> getFeedbackToFlash( RedirectAttributes attributes ) {
		  Object property = attributes.getFlashAttributes().get( FEEDBACK_TOASTR );
		  Map<String, List<String>> feedback = new HashMap<>();
		  if( property instanceof HashMap )
		   feedback = ImmutableMap.<String, List<String>>builder().putAll( ( Map<? extends String, ? extends List<String>> ) property ).build();
		  return feedback;
	}
	
	private Map<String, List<String>> getFeedback(  Model attributes) {
		  Object property = attributes.asMap().get( FEEDBACK_TOASTR );
		  Map<String, List<String>> feedback = new HashMap<>();
		  if( property instanceof HashMap )
		   feedback = ImmutableMap.<String, List<String>>builder().putAll( ( Map<? extends String, ? extends List<String>> ) property ).build();
		  return feedback;
	}

	
	private void registreMessage(FeedType type, String message, Map<String, List<String>> feedback) {
		try {
			addFeedback( type, message, feedback );
			System.out.println( this.objectMapper.writeValueAsString( feedback ) );
		}catch (JsonProcessingException e) {
			log.error( "Houve um problema ao converter mensagem para JSON. {}", e );
		}
	}

	@Getter
	@AllArgsConstructor
	private enum FeedType {
		SUCCESS( "success" ),
		ERROR( "error" );

		private String value;
	}
}