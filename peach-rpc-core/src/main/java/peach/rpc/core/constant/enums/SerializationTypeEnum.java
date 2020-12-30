package peach.rpc.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @EnumName SerializationTypeEnum
 * @Description 序列化类型枚举
 * @Author lidong
 * @Date 2020/12/2
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum SerializationTypeEnum {

    /**
     * KYRO
     */
    KYRO((byte) 0x01, "kyro"),

    /**
     * protostuff 序列化大一点的文件时，性能比较好
     */
    PROTOSTUFF((byte) 0x02, "protostuff"),

    /**
     * Hessian
     */
    HESSIAN((byte) 0x03, "hessian");

    private final byte code;
    private final String name;

    public static String getName(byte code) {
        for (SerializationTypeEnum c : SerializationTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }
}
