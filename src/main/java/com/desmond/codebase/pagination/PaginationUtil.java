package com.desmond.codebase.pagination;

import com.desmond.codebase.pagination.vo.PaginationReqParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Li.Xiaochuan on 16/3/22.
 */
public class PaginationUtil {
    public static List<Integer> getProperPaginationList(PaginationReqParam paginationReqParam, List<Integer> idList) {
        List<Integer> finalList = new ArrayList<>(15);
        int offset = paginationReqParam.getOffset() > 0 ? paginationReqParam.getOffset() : 0;
        int pageSize = paginationReqParam.getPageSize() > 0 ? paginationReqParam.getPageSize() : 15;
        int size = idList.size();

        int fromIdx = offset;
        int toIdx = offset + pageSize;

        if(fromIdx >= size) {
            return finalList;
        }

        toIdx = Math.min(toIdx, size);

        if(fromIdx >= toIdx) {
            return finalList;
        }

        return idList.subList(fromIdx, toIdx);
    }
}
