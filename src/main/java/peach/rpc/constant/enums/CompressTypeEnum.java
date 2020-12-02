package peach.rpc.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @EnumName CompressTypeEnum
 * @Description 压缩方法枚举
 * @Author lidong
 * @Date 2020/12/2
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum CompressTypeEnum {
    /**
     * gzip
     */
    GZIP((byte) 0x01, "gzip");

    private final byte code;
    private final String name;

    public static String getName(byte code) {
        for (CompressTypeEnum c : CompressTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }
}
