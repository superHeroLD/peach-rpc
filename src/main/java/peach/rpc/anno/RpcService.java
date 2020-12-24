package peach.rpc.anno;

import java.lang.annotation.*;

/**
 * Rpc服务注解
 *
 * @author lidong
 * @date 2020-12-24
 */
@Inherited
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcService {

    /**
     * Service version, default value is empty string
     */
    String version() default "";

    /**
     * Service group, default value is empty string
     */
    String group() default "";
}
