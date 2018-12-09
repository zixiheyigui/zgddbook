import com.oraclewdp.ddbookmaket.util.MD5Util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class test {
    public static void main(String[]args){
        String str="admin";
        try {
            System.out.print(MD5Util.getEncryptedPwd(str));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
