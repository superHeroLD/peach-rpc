package src.main.java.config;

/**
 * @ClassName PeachRpcOptions
 * @Description The base implementation class of the configuration item.
 * @Author lidong
 * @Date 2020/11/25
 * @Version 1.0
 */
public class PeachRpcOption<T> {

    private final String name;
    private final T defaultValue;

    protected PeachRpcOption(String name, T defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public String name() {
        return name;
    }

    public T defaultValue() {
        return defaultValue;
    }

    public static <T> PeachRpcOption<T> valueOf(String name) {
        return new PeachRpcOption<>(name, null);
    }

    public static <T> PeachRpcOption<T> valueOf(String name, T defaultValue) {
        return new PeachRpcOption<>(name, defaultValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (null == o || getClass() != o.getClass()) {
            return false;
        }

        PeachRpcOption<?> that = (PeachRpcOption<?>) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
