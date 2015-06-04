package org.xxh.interview.utils.Methods;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2015/5/22.
 */
public class AppMethods {

    public static byte[] toByteArray(InputStream input) {
        if(input == null) {
            return null;
        } else {
            ByteArrayOutputStream output = null;
            byte[] result = null;

            try {
                output = new ByteArrayOutputStream();
                byte[] e = new byte[102400];
                boolean n = false;

                int n1;
                while(-1 != (n1 = input.read(e))) {
                    output.write(e, 0, n1);
                }

                result = output.toByteArray();
            } catch (Exception var9) {
                var9.printStackTrace();
            } catch (OutOfMemoryError var10) {
                var10.printStackTrace();
            } finally {
                closeQuietly((InputStream)input);
                closeQuietly((OutputStream)output);
            }

            return result;
        }
    }

    public static void closeQuietly(InputStream is) {
        try {
            if(is != null) {
                is.close();
            }
        } catch (Exception var2) {
            ;
        }

    }

    public static void closeQuietly(OutputStream os) {
        try {
            if(os != null) {
                os.close();
            }
        } catch (Exception var2) {
            ;
        }

    }
}
