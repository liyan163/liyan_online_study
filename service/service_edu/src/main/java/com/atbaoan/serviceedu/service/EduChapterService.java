package com.atbaoan.serviceedu.service;

import com.atbaoan.serviceedu.entity.EduChapter;
import com.atbaoan.serviceedu.entity.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author emma
 * @since 2021-07-15
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoById(String id);

    int deleteChapterByCourseId(String chapterid);
}
