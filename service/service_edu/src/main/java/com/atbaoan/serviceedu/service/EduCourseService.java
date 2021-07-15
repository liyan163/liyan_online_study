package com.atbaoan.serviceedu.service;

import com.atbaoan.serviceedu.entity.EduCourse;
import com.atbaoan.serviceedu.entity.vo.CourseInfoForm;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author emma
 * @since 2021-02-17
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfo(CourseInfoForm courseInfoForm);

    int updateCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourInfoDetail(String id);
}
