package com.atbaoan.serviceedu.service;

import com.atbaoan.serviceedu.entity.EduSubject;
import com.atbaoan.serviceedu.entity.vo.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author emma
 * @since 2021-05-31
 */
public interface EduSubjectService extends IService<EduSubject> {

    List<String> importExcelData(MultipartFile file);

    List<OneSubject> getAllSubject();
}
