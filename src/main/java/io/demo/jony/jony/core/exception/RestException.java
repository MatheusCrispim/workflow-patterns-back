package io.demo.jony.jony.core.exception;

/**
 * Exception for REST operations.
 * 
 * @author Virtus
 *
 */
public class RestException extends RuntimeException {

    /**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Message error.
	 */
    private final String message;

    /**
     * Message arguments. 
     */
    private final Object[] args;

    /**
     * Constructor.
     *
     * @param message
     * 		Message.
     * @param args
     * 		Arguments.
     */
    public RestException(String message, Object[] args) {
		super();
		this.message = message;
		this.args = args;
	}

	@Override
	public String getMessage() {
		return message;
	}
    
    public Object[] getArgs() {
        return this.args;
    }
}