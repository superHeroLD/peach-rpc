package peach.rpc.core.remoting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lidong
 * @date 2020/12/6
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RpcServiceProperties {

    /**
     * service version
     */
    private String version;

    /**
     * when the interface has multiple implementation classes, distinguish by group
     */
    private String group;

    private String serviceName;

    public String toRpcServiceName() {
        return this.getServiceName() + this.getGroup() + this.getVersion();
    }
}
