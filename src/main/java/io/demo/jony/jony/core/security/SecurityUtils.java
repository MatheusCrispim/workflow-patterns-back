package io.demo.jony.jony.core.security;

import javax.servlet.http.HttpServletResponse;

/**
 * Utils for Security.
 *
 * @author Virtus
 *
 */
public final class SecurityUtils {

    private SecurityUtils() {
        throw new IllegalStateException(SecurityUtils.class.getName());
    }

    /**
     * Fills the Header with the Access Control.
     *
     * @param response
     *            Response.
     * @return Response.
     */
    public static HttpServletResponse fillAccessControlHeader(HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, content-type, refresh-token, user");
        response.setHeader("Access-Control-Expose-Headers", "authorization, content-type, refresh-token, user");

        return response;
    }
}
