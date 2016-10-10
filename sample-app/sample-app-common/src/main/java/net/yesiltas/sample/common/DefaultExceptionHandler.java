package net.yesiltas.sample.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Default exception handler to make sure that all the exceptions are logged
 * 
 * @author Meltem
 *
 */
@ControllerAdvice
public class DefaultExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Logs any exception thrown by any controller
	 * 
	 * @param exception
	 *            exception to log
	 */
	@ExceptionHandler(Exception.class)
	public void handleException(Exception exception) {
		logger.error(exception.toString(), exception);
	}
}
