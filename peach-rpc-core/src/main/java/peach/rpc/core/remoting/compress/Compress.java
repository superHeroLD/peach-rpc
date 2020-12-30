package peach.rpc.core.remoting.compress;

/**
 * @InterfaceName Compress
 * @Description Compress interface
 * @Author lidong
 * @Date 2020/12/2
 * @Version 1.0
 */
public interface Compress {
    /**
     * 压缩
     */
    byte[] compress(byte[] bytes);

    /**
     * 解压缩
     */
    byte[] decompress(byte[] bytes);
}
