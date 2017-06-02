package com.desmond.codebase.file.datacenter;

import com.desmond.codebase.exception.NotEqualsException;
import com.desmond.codebase.file.FileUtils;
import com.desmond.codebase.print.Print;
import com.desmond.codebase.string.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Li.Xiaochuan on 16/5/7.
 */
public class DataVisitTypeUtil {
    private final String FILE_NAME_POINTS = "/Users/gk/java/doc/data_center/phase_2/points.csv";
    private final String FILE_NAME_ALL_DATA_TYPES = "/Users/gk/java/doc/data_center/phase_2/all_visit_types.csv";
    public static void main(String[] args) throws IOException, NotEqualsException {
        DataVisitTypeUtil util = new DataVisitTypeUtil();
        List<String> allTypeList = util.getAllVisitTypes();
        List<TableVo> tableVoList = util.getTableVos();

//        FileUtils.assertEquals(5, tableVoList.size());
        FileUtils.assertEquals(548, allTypeList.size());

        int diffSize = 0;
        for (TableVo vo : tableVoList) {
            if(!allTypeList.contains(vo.getVisitPage())) {
                Print.print("visit_page: {}", vo.getVisitPage());
                diffSize ++;
//                Print.print("insert into `data_visit_tables_mapping` (`table_name`,`visit_page`,`visit_page_desc`," +
//                        "`is_need_full_index`) values ('{}','{}','{}',1);", vo.getTableName(), vo.getVisitPage(), vo.getVistPageDesc());
            } else {
                Print.print("insert into `data_visit_tables_mapping` (`table_name`,`visit_page`,`visit_page_desc`," +
                        "`is_need_full_index`) values ('{}','{}','{}',1);", vo.getTableName(), vo.getVisitPage(), vo.getVistPageDesc());
            }

        }

        Print.print("diff size: {}", diffSize);

    }

    private List<String> getAllVisitTypes() throws IOException {
        List<String> stringList = FileUtils.getContentByLine(FILE_NAME_ALL_DATA_TYPES);
        return stringList.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());
    }

    private List<TableVo> getTableVos() throws IOException {
        List<String> stringList = FileUtils.getContentByLine(FILE_NAME_POINTS);
        return stringList.stream().map(this::mapToTableVo)
                .filter(this::isValidTable)
                .collect(Collectors.toList());
    }

    private TableVo mapToTableVo(String str) {
        if(StringUtils.isNotBlank(str)) {
            String[] arr = str.split(",");
            if(arr.length > 2 && StringUtils.isNotBlank(arr[0])) {
                return new TableVo(arr[0], arr[1], arr[2]);
            }
        }

        return null;
    }

    private boolean isValidTable(TableVo tableVo) {
        return tableVo != null && StringUtils.isNotBlank(tableVo.getTableName());
    }
}
