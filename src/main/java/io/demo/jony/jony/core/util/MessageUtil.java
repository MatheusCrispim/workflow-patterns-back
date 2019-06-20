package io.demo.jony.jony.core.util;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for Messages using i18n.
 *
 * @author Virtus
 */
public final class MessageUtil {

    private static final Logger LOGGER = Logger.getLogger(MessageUtil.class.getName());

    /**
     * Private constructor.
     */
    private MessageUtil() {
    }

    /**
     * Finds the message with default locale.
     *
     * @param code Message code.
     * @return Message.
     */
    public static String findMessage(String code) {
        try {
            return findMessage(code, Locale.getDefault());
        } catch (Exception ex) {
            LOGGER.log(Level.ALL, ex.toString(), ex);
        }
        return code;
    }

    /**
     * Finds the message with the locale specified.
     *
     * @param code   Message code.
     * @param locale Current locale
     * @return Message translated.
     */
    public static String findMessage(String code, Locale locale) {
        try {
            return ResourceBundle.getBundle("messages", locale).getString(code);
        } catch (Exception ex) {
            LOGGER.log(Level.ALL, ex.toString(), ex);
        }
        return code;
    }
}
