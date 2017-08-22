/*
 * @(#)Pager.java 1.1 2016/03/24
 */
package com.craft.rms.Base;


import com.craft.rms.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页数据模型（针对EasyUI）
 * 
 */
public final class Pager {
    /** 总记录数，它的值不能<0 */
    private int total;
    /** 每页显示记录数,它的值不能<=0，默认值为10 */
    private int rows = 10;// 对应easyUI传过来的rows

    /** 当前页码,它的值不能<1，不能>getLastPage()。默认值为1 */
    private int page = 1;// 对应easyUI传过来的page
    public Pager() {
    }
    public Pager(int pageSize, int currentPage) {
        if(currentPage<getFirstPage()){
            currentPage=getFirstPage();
        }
        if(pageSize <= 0){
            pageSize=10;
        }
        this.rows = pageSize;
        this.page = currentPage;
        
    }

    /** 当前页的数据 */
    private List<?> data = new ArrayList(1);

    /**
     * @return 总记录数
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total
     *            总记录数，它的值不能为负数
     */
    public void setTotal(int total) {
        this.total = total;
        int lastPage = this.getLastPage();//得到最后一页的页码
        if(page > lastPage){
            this.setPage(lastPage);
        }
    }

    /**
     * 得到当前页的起始行数
     * @return
     */
    public int getStartRow(){
        return (getPage() - 1) * rows;
    }
    /**
     * 得到最后一页的第一行数
     * @return
     */
    public int getLastPageStartRow(){
        int lastPage = getLastPage();
        setPage(lastPage);
        return getStartRow();
    }
    /**
     * 得到当前页的最后一行的行数
     * 必须先给 total设值才能使用此方法，否则此方法得到的结束行无效
     * @return
     */
    public int getEndRow(){
        //计算当前页显示的最后一条记录的行数
        int startRow = getStartRow();
        return (startRow + rows) > total ? total : (startRow + rows);
    }
    /**
     * 得到当前页的最后一行的行数
     * @param total 总记录数
     * @return
     */
    public int getEndRow(int total){
        setTotal(total);
        //计算当前页显示的最后一条记录的行数
        int startRow = getStartRow();
        return (startRow + rows) > total ? total : (startRow + rows);
    }
    /**
     * @return 当前页数据，它是一个java.util.List集合
     */
    public List<?> getData() {
        if(data==null){
            data = new ArrayList(1);
        }
        return data;
    }
    /**
     * 设置当前页数据
     * 
     * @param data
     *            当前页数据
     */
    public void setData(List<?> data) {
        this.data = data;
    }

    /**
     * @return 每页显示的记录数（默认情况下为10）
     */
    public int getRows() {
        return rows;
    }
    
    /**
     * 设置每页显示 的记录数
     * 
     * @param rows
     *            每页显示记录数，它的值不能为负数和0
     */
    public void setRows(int rows) {
        if(rows <= 0){
            rows=10;
        }
        this.rows = rows;
    }

    /**
     * 获取当前页码时，若发现页码超出范围，则更正范围(略）
     * 
     * @return 当前页码（默认值为1）
     */
    public int getPage() {
        return page;
    }

    /**
     * 设置当前页码
     * 
     * @param page
     *            当前页码（它的值不能为负数和0或大于最大页码数）
     */
    public void setPage(int page) {
        if(page <= 0){
            page = 1;
        }
        this.page = page;
    }
    /**
     * 返回最大页码数，即总页数(也就是最后一页)，它的值不能为负数
     * total即总记录数必须是有效的值
     * @return 总页数（它的值是一个整数值）
     */
    private int getLastPage() {
        return (int) Math.ceil(total / (1.0 * rows));
    }

    /**
     * @return 首页面码，默认为1
     */
    private int getFirstPage() {
        return 1;
    }

    public void setAllData(List<?> list) {
        if(list!=null&&!list.isEmpty()){
            // 必须先设置总记录数据
            this.total = list.size();
            int lastPage=getLastPage();
            if(getPage()>lastPage){
                this.page=lastPage;
            }
            //计算当前页显示的第一条记录的行数
            int startRow = (getPage() - 1) * rows;
            //计算当前页显示的最后一条记录的行数
            int endRow = (startRow + rows) > total ? total : (startRow + rows);
            //得到这页的数据
            this.data = list.subList(startRow, endRow);
        }
    }

    @Override
    public String toString() {
        return StringUtils.joinStr("Pager [总记录数total=", total, ", 每页显示记录数rows=", rows, ", 当前页码page=", page,
                ", 当前页数据data记录数=", data.size(), "]");
    }
    
}
