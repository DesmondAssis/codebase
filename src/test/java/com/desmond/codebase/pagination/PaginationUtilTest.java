package com.desmond.codebase.pagination;

import com.desmond.codebase.BaseTest;
import com.desmond.codebase.pagination.vo.PaginationReqParam;
import javafx.scene.control.Pagination;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Li.Xiaochuan on 16/3/22.
 */
public class PaginationUtilTest extends BaseTest{

    private List<Integer> list = null;
    private PaginationReqParam paginationReqParam;

    @Before
    public void before() {
        Arrays.asList(1,2,3,4,5,6,7);
        paginationReqParam = new PaginationReqParam();
    }

    @Test
    public void testPagination_offset_outOfbound() {
        paginationReqParam = new PaginationReqParam();
        paginationReqParam.setOffset(-1);
        List<Integer> result = PaginationUtil.getProperPaginationList(paginationReqParam, list);

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testPagination_pagesize_outOfbound() {
        paginationReqParam = new PaginationReqParam();
        paginationReqParam.setPageSize(9);
        List<Integer> result = PaginationUtil.getProperPaginationList(paginationReqParam, list);

        Assert.assertEquals(7, result.size());
    }

    @Test
    public void testPagination_() {
        paginationReqParam = new PaginationReqParam();
        paginationReqParam.setPageSize(9);
        List<Integer> result = PaginationUtil.getProperPaginationList(paginationReqParam, list);

        Assert.assertEquals(7, result.size());
    }

    @Test
    public void testPagination_fromIdx_gt_size() {
        paginationReqParam = new PaginationReqParam();
        paginationReqParam.setOffset(8);
        paginationReqParam.setPageSize(1);
        List<Integer> result = PaginationUtil.getProperPaginationList(paginationReqParam, list);

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testPagination_normal() {
        paginationReqParam = new PaginationReqParam();
        paginationReqParam.setOffset(7);
        paginationReqParam.setPageSize(15);
        List<Integer> result = PaginationUtil.getProperPaginationList(paginationReqParam, list);

        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testPagination_normal_page1() {
        paginationReqParam = new PaginationReqParam();
        paginationReqParam.setOffset(0);
        paginationReqParam.setPageSize(3);
        List<Integer> result = PaginationUtil.getProperPaginationList(paginationReqParam, list);

        Assert.assertEquals(3, result.size());
    }

    @Test
    public void testPagination_normal_page2() {
        paginationReqParam = new PaginationReqParam();
        paginationReqParam.setOffset(3);
        paginationReqParam.setPageSize(3);
        List<Integer> result = PaginationUtil.getProperPaginationList(paginationReqParam, list);

        Assert.assertEquals(3, result.size());
    }

    @Test
    public void testPagination_normal_page3() {
        paginationReqParam = new PaginationReqParam();
        paginationReqParam.setOffset(6);
        paginationReqParam.setPageSize(3);
        List<Integer> result = PaginationUtil.getProperPaginationList(paginationReqParam, list);

        Assert.assertEquals(1, result.size());
    }
}
