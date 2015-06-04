package org.xxh.interview.utils;

import java.io.UnsupportedEncodingException;

public class Base64 {
    private static final String ENCODING = "UTF-8";
    private static final byte[] encodingTable = new byte[]{(byte)65, (byte)66, (byte)67, (byte)68, (byte)69, (byte)70, (byte)71, (byte)72, (byte)73, (byte)74, (byte)75, (byte)76, (byte)77, (byte)78, (byte)79, (byte)80, (byte)81, (byte)82, (byte)83, (byte)84, (byte)85, (byte)86, (byte)87, (byte)88, (byte)89, (byte)90, (byte)97, (byte)98, (byte)99, (byte)100, (byte)101, (byte)102, (byte)103, (byte)104, (byte)105, (byte)106, (byte)107, (byte)108, (byte)109, (byte)110, (byte)111, (byte)112, (byte)113, (byte)114, (byte)115, (byte)116, (byte)117, (byte)118, (byte)119, (byte)120, (byte)121, (byte)122, (byte)48, (byte)49, (byte)50, (byte)51, (byte)52, (byte)53, (byte)54, (byte)55, (byte)56, (byte)57, (byte)43, (byte)47};
    private static final byte[] decodingTable = new byte[128];

    public Base64() {
    }

    public static byte[] base64Decode(String txt) {
        if(txt == null) {
            return null;
        } else {
            Object bs = null;

            try {
                byte[] bs1 = txt.getBytes("UTF-8");
                return decode((byte[])bs1);
            } catch (UnsupportedEncodingException var3) {
                var3.printStackTrace();
                return null;
            }
        }
    }

    public static String base64Encode(byte[] data) {
        if(data == null) {
            return null;
        } else {
            byte[] result = encode(data);
            String s = null;

            try {
                s = new String(result, "UTF-8");
            } catch (UnsupportedEncodingException var4) {
                var4.printStackTrace();
            }

            return s;
        }
    }

    public static byte[] encode(byte[] data) {
        int modulus = data.length % 3;
        byte[] bytes;
        if(modulus == 0) {
            bytes = new byte[4 * data.length / 3];
        } else {
            bytes = new byte[4 * (data.length / 3 + 1)];
        }

        int dataLength = data.length - modulus;
        int b1 = 0;

        int b2;
        for(b2 = 0; b1 < dataLength; b2 += 4) {
            int a1 = data[b1] & 255;
            int a2 = data[b1 + 1] & 255;
            int a3 = data[b1 + 2] & 255;
            bytes[b2] = encodingTable[a1 >>> 2 & 63];
            bytes[b2 + 1] = encodingTable[(a1 << 4 | a2 >>> 4) & 63];
            bytes[b2 + 2] = encodingTable[(a2 << 2 | a3 >>> 6) & 63];
            bytes[b2 + 3] = encodingTable[a3 & 63];
            b1 += 3;
        }

        int d1;
        switch(modulus) {
            case 0:
            default:
                break;
            case 1:
                d1 = data[data.length - 1] & 255;
                b1 = d1 >>> 2 & 63;
                b2 = d1 << 4 & 63;
                bytes[bytes.length - 4] = encodingTable[b1];
                bytes[bytes.length - 3] = encodingTable[b2];
                bytes[bytes.length - 2] = 61;
                bytes[bytes.length - 1] = 61;
                break;
            case 2:
                d1 = data[data.length - 2] & 255;
                int d2 = data[data.length - 1] & 255;
                b1 = d1 >>> 2 & 63;
                b2 = (d1 << 4 | d2 >>> 4) & 63;
                int b3 = d2 << 2 & 63;
                bytes[bytes.length - 4] = encodingTable[b1];
                bytes[bytes.length - 3] = encodingTable[b2];
                bytes[bytes.length - 2] = encodingTable[b3];
                bytes[bytes.length - 1] = 61;
        }

        return bytes;
    }

    public static byte[] decode(byte[] data) {
        data = discardNonBase64Bytes(data);
        byte[] bytes;
        if(data[data.length - 2] == 61) {
            bytes = new byte[(data.length / 4 - 1) * 3 + 1];
        } else if(data[data.length - 1] == 61) {
            bytes = new byte[(data.length / 4 - 1) * 3 + 2];
        } else {
            bytes = new byte[data.length / 4 * 3];
        }

        int i = 0;

        byte b1;
        byte b2;
        byte b3;
        byte b4;
        for(int j = 0; i < data.length - 4; j += 3) {
            b1 = decodingTable[data[i]];
            b2 = decodingTable[data[i + 1]];
            b3 = decodingTable[data[i + 2]];
            b4 = decodingTable[data[i + 3]];
            bytes[j] = (byte)(b1 << 2 | b2 >> 4);
            bytes[j + 1] = (byte)(b2 << 4 | b3 >> 2);
            bytes[j + 2] = (byte)(b3 << 6 | b4);
            i += 4;
        }

        if(data[data.length - 2] == 61) {
            b1 = decodingTable[data[data.length - 4]];
            b2 = decodingTable[data[data.length - 3]];
            bytes[bytes.length - 1] = (byte)(b1 << 2 | b2 >> 4);
        } else if(data[data.length - 1] == 61) {
            b1 = decodingTable[data[data.length - 4]];
            b2 = decodingTable[data[data.length - 3]];
            b3 = decodingTable[data[data.length - 2]];
            bytes[bytes.length - 2] = (byte)(b1 << 2 | b2 >> 4);
            bytes[bytes.length - 1] = (byte)(b2 << 4 | b3 >> 2);
        } else {
            b1 = decodingTable[data[data.length - 4]];
            b2 = decodingTable[data[data.length - 3]];
            b3 = decodingTable[data[data.length - 2]];
            b4 = decodingTable[data[data.length - 1]];
            bytes[bytes.length - 3] = (byte)(b1 << 2 | b2 >> 4);
            bytes[bytes.length - 2] = (byte)(b2 << 4 | b3 >> 2);
            bytes[bytes.length - 1] = (byte)(b3 << 6 | b4);
        }

        return bytes;
    }

    public static byte[] decode(String data) {
        data = discardNonBase64Chars(data);
        byte[] bytes;
        if(data.charAt(data.length() - 2) == 61) {
            bytes = new byte[(data.length() / 4 - 1) * 3 + 1];
        } else if(data.charAt(data.length() - 1) == 61) {
            bytes = new byte[(data.length() / 4 - 1) * 3 + 2];
        } else {
            bytes = new byte[data.length() / 4 * 3];
        }

        int i = 0;

        byte b1;
        byte b2;
        byte b3;
        byte b4;
        for(int j = 0; i < data.length() - 4; j += 3) {
            b1 = decodingTable[data.charAt(i)];
            b2 = decodingTable[data.charAt(i + 1)];
            b3 = decodingTable[data.charAt(i + 2)];
            b4 = decodingTable[data.charAt(i + 3)];
            bytes[j] = (byte)(b1 << 2 | b2 >> 4);
            bytes[j + 1] = (byte)(b2 << 4 | b3 >> 2);
            bytes[j + 2] = (byte)(b3 << 6 | b4);
            i += 4;
        }

        if(data.charAt(data.length() - 2) == 61) {
            b1 = decodingTable[data.charAt(data.length() - 4)];
            b2 = decodingTable[data.charAt(data.length() - 3)];
            bytes[bytes.length - 1] = (byte)(b1 << 2 | b2 >> 4);
        } else if(data.charAt(data.length() - 1) == 61) {
            b1 = decodingTable[data.charAt(data.length() - 4)];
            b2 = decodingTable[data.charAt(data.length() - 3)];
            b3 = decodingTable[data.charAt(data.length() - 2)];
            bytes[bytes.length - 2] = (byte)(b1 << 2 | b2 >> 4);
            bytes[bytes.length - 1] = (byte)(b2 << 4 | b3 >> 2);
        } else {
            b1 = decodingTable[data.charAt(data.length() - 4)];
            b2 = decodingTable[data.charAt(data.length() - 3)];
            b3 = decodingTable[data.charAt(data.length() - 2)];
            b4 = decodingTable[data.charAt(data.length() - 1)];
            bytes[bytes.length - 3] = (byte)(b1 << 2 | b2 >> 4);
            bytes[bytes.length - 2] = (byte)(b2 << 4 | b3 >> 2);
            bytes[bytes.length - 1] = (byte)(b3 << 6 | b4);
        }

        return bytes;
    }

    private static byte[] discardNonBase64Bytes(byte[] data) {
        byte[] temp = new byte[data.length];
        int bytesCopied = 0;

        for(int newData = 0; newData < data.length; ++newData) {
            if(isValidBase64Byte(data[newData])) {
                temp[bytesCopied++] = data[newData];
            }
        }

        byte[] var4 = new byte[bytesCopied];
        System.arraycopy(temp, 0, var4, 0, bytesCopied);
        return var4;
    }

    private static String discardNonBase64Chars(String data) {
        StringBuffer sb = new StringBuffer();
        int length = data.length();

        for(int i = 0; i < length; ++i) {
            if(isValidBase64Byte((byte)data.charAt(i))) {
                sb.append(data.charAt(i));
            }
        }

        return sb.toString();
    }

    private static boolean isValidBase64Byte(byte b) {
        return b == 61?true:(b >= 0 && b < 128?decodingTable[b] != -1:false);
    }

    static {
        int i;
        for(i = 0; i < 128; ++i) {
            decodingTable[i] = -1;
        }

        for(i = 65; i <= 90; ++i) {
            decodingTable[i] = (byte)(i - 65);
        }

        for(i = 97; i <= 122; ++i) {
            decodingTable[i] = (byte)(i - 97 + 26);
        }

        for(i = 48; i <= 57; ++i) {
            decodingTable[i] = (byte)(i - 48 + 52);
        }

        decodingTable[43] = 62;
        decodingTable[47] = 63;
    }
}