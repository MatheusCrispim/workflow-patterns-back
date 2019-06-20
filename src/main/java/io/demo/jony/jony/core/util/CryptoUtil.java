package io.demo.jony.jony.core.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Util class for cryptography.
 *
 * @author Virtus
 */
public final class CryptoUtil {

    private CryptoUtil() {
        throw new IllegalStateException(CryptoUtil.class.getName());
    }

    /**
     * Encrypt the value.
     *
     * @param value Value.
     * @return Encrypted value.
     */
    public static String hash(String value) {
    	  try {
              return new String(DigestUtils.sha1Hex(value));
          } catch (Exception ex) {
              throw new RuntimeException(ex);
          }
    }
}
