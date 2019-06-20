package io.demo.jony.jony.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import io.demo.jony.jony.core.util.MessageUtil;

import java.util.Locale;

/**
 * Exception for business errors or validations. <br>
 *
 * Should contain a user friendly message. <br>
 * Ex: "There is an item with the same name".
 *
 * @author Virtus
 */
public class BusinessException extends Exception {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Exception code.
	 */
	private String code;
	
	/**
	 * Constructor.
	 *
	 * @param code
	 * 		Message Code.
	 */
	public BusinessException(String code) {
		this(code, LocaleContextHolder.getLocale());
	}

	/**
	 * Constructor.
	 * 
	 * @param code
	 * 		Code.
	 * @param locale
	 * 		Locale.
	 */
	public BusinessException(String code, Locale locale) {
		this(code, locale, null);
	}

	/**
	 * Constructor.
	 *
	 * @param code
	 * 		Message code.
	 * @param e
	 * 		Exception.
	 */
	public BusinessException(String code, Locale locale, Exception e) {
		super(MessageUtil.findMessage(code, locale), e);
		this.code = code;
		
		logger.error(this.getMessage());
	}

	/**
	 * Gets the exception code.
	 * 
	 * @return Exception code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the exception code.
	 * 
	 * @param code
	 * 		Exception code.
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
