package knowledge;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * base62 encode and decode
 */
public class Base62 {

    private static final char[] encodes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
            .toCharArray();
    private static final int[] decodes = new int[256];

    static {
        for (int i = 0; i < encodes.length; i++) {
            decodes[encodes[i]] = i;
        }
    }

    public static String encode(byte[] datas) {
        int pos = 0, val = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < datas.length; i++) {
            val = (val << 8 | (datas[i] & 0xFF));
            pos += 8;

            while (pos >= 6) {
                char c = encodes[val >> (pos -= 6)];
                builder.append(
                        c == 'i' ? "ia" :
                                c == '+' ? "ib" :
                                        c == '/' ? "ic" : String.valueOf(c)
                );
                val &= ((1 << pos) - 1);
            }
        }
        if (pos > 0) {
            val <<= (pos - 6);
            char c = encodes[val];
            builder.append(
                    c == 'i' ? "ia" :
                            c == '+' ? "ib" :
                                    c == '/' ? "ic" : String.valueOf(c)
            );
        }
        return builder.toString();
    }

    public static byte[] decode(String s) {
        if (s == null) {
            return null;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        int val = 0, pos = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == 'i') {
                c = chars[++i];
                c = c == 'a' ? 'i' :
                        c == 'b' ? '+' :
                                c == 'c' ? '/' : chars[--i];
            }
            val = val << 6 | decodes[c];
            pos += 6;
            if (pos >= 8) {
                outputStream.write(val >> (pos -= 8));
                val &= ((1 << pos) - 1);
            }
        }
        return outputStream.toByteArray();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "我是一个好人";
        String encodeString = Base62.encode(s.getBytes("utf-8"));

        byte[] decodeByte = Base62.decode(encodeString);

        System.out.println(new String(decodeByte, "utf-8").equals(s));

    }

}
