package com.atbaoan.serviceedu.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emma
 * @create 2021-07-15 23:29
 * @description
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    //多个小节
    private List<VideoVo> children = new ArrayList<>();
}
