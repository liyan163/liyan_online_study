package com.atbaoan.serviceedu.controller;


import com.atbaoan.commonutils.R;
import com.atbaoan.serviceedu.entity.vo.CourseInfoForm;
import com.atbaoan.serviceedu.service.EduCourseService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author emma
 * @since 2021-02-17
 */
@RestController
@RequestMapping("/serviceedu/eduCourse")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
        String courseId = eduCourseService.addCourseInfo(courseInfoForm);
        return R.ok().data("courseId", courseId);
    }

    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
        int i = eduCourseService.updateCourseInfo(courseInfoForm);
        return R.ok();
    }

    //获取课程详情
    @GetMapping("/getCourInfoDetail/{id}")
    public R getCourInfoDetail(@PathVariable("id") String id) {
        CourseInfoForm courseInfoForm = eduCourseService.getCourInfoDetail(id);
        return R.ok().data("courseInfoForm", courseInfoForm);
    }
}
