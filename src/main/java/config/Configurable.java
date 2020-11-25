package src.main.java.config;

/**
 * @InterfaceName Configurable
 * @Description Config interface.
 * @Author lidong
 * @Date 2020/11/25
 * @Version 1.0
 */
public interface Configurable {


    <T> T getOption(PeachRpcOption<T> option);

    <T> Configurable setOption(PeachRpcOption<T> option, T value);
}
