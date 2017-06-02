package com.desmond.codebase.string;

import com.desmond.codebase.number.NumberUtil;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.desmond.codebase.print.Print.printnb;

/**
 * Created by Li.Xiaochuan on 15/10/28.
 */
public class RegexUtil {

    public static int size = 0;

    public static void main(String[] args) {
//        find();

//        testMatch();

        testFfetch();
    }


    private static void testFfetch() {
        fetch("type= \"push_marketing1\";");
        fetch(" type= \"push_marketing2\";");
        fetch(" type =\"push_marketing3\";");
        fetch(" type = \"push_marketing4\" ;");
        fetch(" type = \"push_marketing5\"; ");
    }

    public static String fetch(String str) {
        String regex = "\\w*type\\s*=\\s*\\\"\\w*\\\"\\s*;";
        String r = fetchType(regex, str);

        if(StringUtils.isNotBlank(r)) {
            size ++;
            regex = "\\\"\\w*\\\"";
            return fetchType(regex, r);
        }

        return null;
    }

    private static String fetchType(String regex, String str) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        String result = "";

        while (m.find()) {
            result = m.group();
        }

        return result;
    }

    private static void find() {
        String reg = "<h3.*?>([^<]+)<\\/h3>";
        String imgReg = "<img\\s+([^>]*?src\\s*=\\s*\"[^\"]+\".*?)>";
        String imgStr = "test<br><img src=\"http://cdn.wanzhoumo.com/data/public/activity" +
                "/2015/06/05_38/14334912339677.gif\" data-ratio=\"1.44\">泸沽湖位于川sort西南";

        String tr = "testt<h3>行程安排</h3><image src/><br>sdfdf<br><h3>第 1 天。</h3><h3>第 2 天：早收费）。</h3>tt";
        String tr1 = "<h3>行程安排</h3><image src/><h3>第 1 天。</h3><h3>第 2 天：早收费）。</h3>tt";
        String tr2 = "<br>testt<h3>行程安排</h3><br>test<image src/><h3>第 1 天。</h3><h3>第 2 天：早收费）。</h3>";

        List<SplitVO> splitVOList = splitFunc(imgStr, imgReg);

        for(SplitVO vo : splitVOList) {
            printnb(vo.isMatched() + "=" + vo.getContent());
        }
    }

    private static void testMatch() {
        String url1 = "inapp://nearby",
                url2 = "inapp://dailylist",
                url3 = "inapp://calendar",
                url4 = "https://m.wanzhoumo.com/topic/519",
                url5 = "https://m.wanzhoumo.com/disney/list";
        System.out.println(match(url1, "inapp://nearby"));
        System.out.println(match(url2, "inapp://dailylist"));
        System.out.println(match(url3, "inapp://calendar"));

        System.out.println(match(url4, "/topic/[0-9]+"));
        System.out.println(match(url5, "/disney/list"));
        System.out.println(fetchTopicId(url4, "/topic/[0-9]+"));
    }

    public static int fetchTopicId(String str, String regex) {
        if(StringUtils.isNotBlank(str)) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(str);

            while (m.find()) {
                String numStr = m.group();
                if(StringUtils.isNotBlank(numStr)) {
                    String[] args = numStr.split("/");
                    if(args.length == 3) {
                        return NumberUtil.toInt(args[2]);
                    }
                }
            }
        }

        return 0;
    }

    public static boolean match(String str, String regex) {
        if(StringUtils.isNotBlank(str)) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(str);

            return m.find();
        }

        return false;
    }

    /**
     * 内容分割，模仿： preg_split($preg, $str, -1, PREG_SPLIT_DELIM_CAPTURE)
     * --from http://php.net/manual/zh/function.preg-split.php
     * @param str
     * @param regex
     * @return
     */
    public static List<SplitVO> splitFunc(String str, String regex) {
        List<SplitVO> splitVOList = new ArrayList<>();
        if(StringUtils.isNotBlank(str)) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(str);

            int start = 0;
            while(m.find()) {
                String content = str.substring(start, m.start());
                if(StringUtils.isNotBlank(content)) {
                    splitVOList.add(new SplitVO(false, content));
                }
                splitVOList.add(new SplitVO(true, m.group(1)));

                start = m.end();
            }
            String content = str.substring(start);
            if(StringUtils.isNotBlank(content)) {
                splitVOList.add(new SplitVO(false, content));
            }
        }

        return splitVOList;
    }

    /**
     * 将html转化为指定格式。场景：将活动内容切分title/text/image
     * @author Jason
     * @param str
     * @return
     */
    public static String strToRichContent(String str) {
        return null;
    }

}
