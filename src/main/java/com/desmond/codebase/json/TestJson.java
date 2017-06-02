package com.desmond.codebase.json;

import com.desmond.codebase.json.vo.DataApiJsonVo;
import com.desmond.codebase.json.vo.TestVo;

import static com.desmond.codebase.print.Print.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Li.Xiaochuan on 16/3/13.
 */
public class TestJson {
    public static void main(String[] args) {
        String json = JsonConverterUtil.objectToJson(new TestVo(1, "titl"));

        long maxId = 353836576, minId = 1;
        int step = 4000 - 1;
        int c = 0;
        for (long i = minId; i <= maxId;) {
            long tmpMinId = i;
            long tmpMaxId = (tmpMinId + step) > maxId ? maxId : (tmpMinId + step);

            i = tmpMaxId + 1;
//            print("id >={} and id<={};", tmpMinId, tmpMaxId);
            c ++;
        }

        print("count: {}, \n{}, \n{}, \n{}", "惠州(()).'],.,".replaceAll("'", ""), "同城‘音符'，一起免费学钢琴".replaceAll("'", ""),  "同城‘音符'，一'起免费学钢琴","同城‘音符'，一'起免费学钢琴".replaceAll("'", ""));
    }
}
