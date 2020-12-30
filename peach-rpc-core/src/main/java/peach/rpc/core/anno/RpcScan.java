package peach.rpc.core.anno;

import org.springframework.context.annotation.Import;
import peach.rpc.core.spring.CustomScannerRegistrar;

import java.lang.annotation.*;

/**
 * custom rpc scan
 * Define rpc annotation scan path
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomScannerRegistrar.class)
@Documented
public @interface RpcScan {
    String[] basePackage();
}
