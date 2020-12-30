package peach.rpc.core.util;

/**
 * @ClassName StringUtil
 * @Description 字符串工具类
 * @Author lidong
 * @Date 2020/12/1
 * @Version 1.0
 */
public class StringUtil {

    public static final String EMPTY = "";

    private StringUtil() {

    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }
}
