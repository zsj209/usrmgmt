package com.hrocloud.usrmgmt.dto;

import com.hrocloud.apigw.client.annoation.Description;

import java.io.Serializable;
import java.util.List;

@Description("分页")
public class PageDTO<T> implements Serializable {

    @Description("当前页码")
    public int page;//当前页码
    @Description("总页数")
    public int total;//总页数
    @Description("总行数")
    public int records;//数据行总数的数据
    @Description("表格中的数据")
    public List<T> rows;
    //public List<UserDTO> rows;

}
