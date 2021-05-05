package com.atbaoan.serviceedu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Emma
 * @create 2021-02-21 16:52
 * @description 分页条件实体类
 */
@Data
public class QueryTeacher {
    @ApiModelProperty(value = "讲师名称")
    private  String name;

    @ApiModelProperty(value = "讲师级别")
    private Integer level;

    @ApiModelProperty(value = "创建开始时间" ,example = "2021-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "创建结束时间", example = "2021-01-01 10:10:10")
    private String end;

}
