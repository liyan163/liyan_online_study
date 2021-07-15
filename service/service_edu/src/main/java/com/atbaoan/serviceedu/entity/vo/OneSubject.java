package com.atbaoan.serviceedu.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Emma
 * @create 2021-06-10 23:17
 * @description
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> childSubject;
}
