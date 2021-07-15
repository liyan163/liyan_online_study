package com.atbaoan.serviceedu.controller;


import com.atbaoan.commonutils.R;
import com.atbaoan.serviceedu.entity.vo.OneSubject;
import com.atbaoan.serviceedu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author emma
 * @since 2021-05-31
 */
@RestController
@RequestMapping("/serviceedu/eduSubject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        List<String> errorMsg =  eduSubjectService.importExcelData(file);
        if (CollectionUtils.isEmpty(errorMsg)){
            return R.ok();
        }
        return R.error().data("errorMsg",errorMsg);


    }

    @PostMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> allSubject =  eduSubjectService.getAllSubject();
        return R.ok().data("allSubject",allSubject);
    }
}

