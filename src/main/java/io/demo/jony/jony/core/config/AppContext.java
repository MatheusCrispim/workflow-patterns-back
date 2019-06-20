package io.demo.jony.jony.core.config;

import org.springframework.context.ApplicationContext;

/**
 * Contexto da aplicação.
 *
 * @author Virtus
 *
 */
public final class AppContext {

    private AppContext() {
        throw new IllegalStateException(AppContext.class.getName());
    }

    /**
     * Contexto da aplicação <i>Spring</i>.
     */
    private static ApplicationContext ctx;

    /**
     * Carrega o contexto da aplicação.
     *
     * @param appCtx
     *            Contexto da aplicação.
     */
    public static void loadApplicationContext(ApplicationContext appCtx) {

        ctx = appCtx;
    }

    /**
     * Recupera um <i>Bean</i>.
     *
     * @param clazz
     *            <i>Class</i> a ser recuperada.
     * @return <i>Bean</i> solicitado.
     */
    public static <T> T getBean(Class<T> clazz) {

        return ctx.getBean(clazz);
    }
}
