package com.desmond.codebase.encode;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by Li.Xiaochuan on 15/10/31.
 */
public class EncodeUtil {
    public static void main(String[] args) throws Exception {
        String utfs1 = "被誉为当今最顶尖的巴赫钢琴家 – 休伊特Angela Hewitt，将于2月27日星期四晚上8时在香港大学李兆基会议中心大会堂献技，演奏巴赫，贝多芬及李斯特的作品。诚邀各位莅临大会堂欣赏休伊特在全新的史坦威钢琴展现各曲精妙，剖析这几段独立、缤纷、却又相互影响，气质迥异的乐曲。\\r\\n\\r\\n休伊特以11年时间灌录巴赫的所有重要键盘作品，被誉为当代唱片史上最辉煌成就之一。她的音乐修养和精湛技艺使她成为最迷人与最受欢迎的古典音乐演奏家之一。她的其他唱片包括贝多芬、舒曼、梅湘、拉威尔、萧邦、库普兰，拉摩与夏布里耶。 \\r\\n\\r\\n《乐语融融》演前艺人谈将于当晚6时45分至7时30分在香港大学大会堂举行。休伊特与港大人文学院院长蔡宽量教授将深谈贝多芬的《降A大调钢琴奏鸣曲》，作品110。作为教育学府，香港大学希望透过深入讨论和示范，让观众和学生对音乐作品有更深层次的体会。欢迎持票人士及香港大学持有学生或职员证人士参加，免费入场。";
        String utfs = "休伊特Angela Hewitt，将";

        System.out.println(utfSubstr(utfs1, 100));
    }

    public static String ordc(String str) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
            str = str.substring(0, 1);
            byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
            if (bytes != null && bytes.length > 0) {
                return String.format("%02X", bytes[0]);
            }
        }

        return str;
    }

    public static int ord(String str) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
            str = str.substring(0, 1);
            byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
            if (bytes != null && bytes.length > 0) {
                return Integer.parseInt(String.format("%02X", bytes[0]), 16);
            }
        }

        return 0;
    }

    /**
     * 调整字符在屏幕上显示宽度.
     * 汉字占两个字符宽度.
     * @param name
     * @param length 指定的长度，超过部分将用...代替.
     * @return
     */
    public static String utfSubstr(String name, int length) {
        String value = name;
        int len = 0; //定义返回的字符串长度
        int j = 0;
        int idx = 0; // 当前index 的位置.
        //按照指定编码得到byte[]
        byte[] b_name = new byte[0];
        try {
            b_name = name.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (b_name.length > 0) {
            while (true) {
                short tmpst = (short) (b_name[j] & 0xF0);
                if (tmpst >= 0xB0) {
                    if (tmpst < 0xC0) {
                        j += 2;
                        len += 2;
                    } else if ((tmpst == 0xC0) || (tmpst == 0xD0)) {
                        j += 2;
                        len += 2;
                    } else if (tmpst == 0xE0) {
                        j += 3;
                        len += 2;
                    } else if (tmpst == 0xF0) {
                        short tmpst0 = (short) (((short) b_name[j]) & 0x0F);
                        if (tmpst0 == 0) {
                            j += 4;
                            len += 2;
                        } else if ((tmpst0 > 0) && (tmpst0 < 12)) {
                            j += 5;
                            len += 2;
                        } else if (tmpst0 > 11) {
                            j += 6;
                            len += 2;
                        }
                    }
                } else {
                    j += 1;
                    len += 1;
                }
                idx++;
                if (j > b_name.length - 1) {
                    break;
                } else if (len - 3 >= length) {
                    value = value.substring(0, idx) + "...";
                    break;
                }
            }
        }

        return value;
    }
}
