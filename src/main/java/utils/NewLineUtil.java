package utils;

// 入力した文字列の中の改行コードを<br />タグに変えて出力
public class NewLineUtil {
    public static String NL_change(String s) {
        String p = "\\r\\n|\\n\\r|\\n|\\r";
        return s.replaceAll(p, "<br />");
    }
}
