package com.atbaoan.serviceedu.service.impl;

import com.atbaoan.common.handler.EmmaException;
import com.atbaoan.serviceedu.entity.EduCourse;
import com.atbaoan.serviceedu.entity.EduCourseDescription;
import com.atbaoan.serviceedu.entity.vo.CourseInfoForm;
import com.atbaoan.serviceedu.mapper.EduCourseMapper;
import com.atbaoan.serviceedu.service.EduCourseDescriptionService;
import com.atbaoan.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author emma
 * @since 2021-02-17
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;


    public EduCourseServiceImpl() {
    }

    @Override
    @Transactional
    public String addCourseInfo(CourseInfoForm courseInfoForm) {
        //1 添加课程表信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int courseId = baseMapper.insert(eduCourse);

        //2 添加课程描述表信息
        if (courseId == 0){
            throw new EmmaException(20002,"添加课程失败");
        }
            EduCourseDescription eduCourseDescription = new EduCourseDescription();
            eduCourseDescription.setDescription(courseInfoForm.getDescription());
            eduCourseDescription.setId(eduCourse.getId());
            eduCourseDescriptionService.save(eduCourseDescription);
        return eduCourse.getId();
    }

    @Override
    public int updateCourseInfo(CourseInfoForm courseInfoForm) {
        //查询是否有该课程
        if (StringUtils.isEmpty(courseInfoForm.getId())){
            throw new EmmaException(20002,"修改课程id不可为空");
        }
        EduCourse query = new EduCourse();
        query.setId(courseInfoForm.getId());
        EduCourse eduCourse = baseMapper.selectById(query);
        if (eduCourse == null){
            throw new EmmaException(20002,"要修改的课程不存在");
        }
        //修改课程
        EduCourse updateCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,updateCourse);
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",eduCourse.getId());
        int update = baseMapper.update(updateCourse, queryWrapper);
        if (update > 0){
            //修改课程描述
            EduCourseDescription eduCourseDescription = new EduCourseDescription();
            QueryWrapper<EduCourseDescription> descriptionQueryWrapper = new QueryWrapper<>();
            descriptionQueryWrapper.eq("id",courseInfoForm.getId());
            BeanUtils.copyProperties(courseInfoForm,descriptionQueryWrapper);
            eduCourseDescriptionService.update(eduCourseDescription,descriptionQueryWrapper);
        }
        return 0;
    }

    //获取课程详情
    @Override
    public CourseInfoForm getCourInfoDetail(String id) {
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        //获取课程信息
        if (StringUtils.isEmpty(id)){
            throw new EmmaException(20002,"课程详情缺少课程id");
        }
        EduCourse eduCourse = baseMapper.selectById(id);
        if (eduCourse == null){
            return null;
        }
        //查询课程描述
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);
        //封装最终参数
        BeanUtils.copyProperties(eduCourse,courseInfoForm);
        courseInfoForm.setDescription(eduCourseDescription.getDescription());
        return courseInfoForm;
    }
}
