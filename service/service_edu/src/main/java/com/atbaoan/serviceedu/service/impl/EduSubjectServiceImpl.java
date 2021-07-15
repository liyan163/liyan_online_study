package com.atbaoan.serviceedu.service.impl;

import com.atbaoan.common.handler.EmmaException;
import com.atbaoan.serviceedu.entity.EduSubject;
import com.atbaoan.serviceedu.entity.vo.OneSubject;
import com.atbaoan.serviceedu.entity.vo.QueryTeacher;
import com.atbaoan.serviceedu.entity.vo.TwoSubject;
import com.atbaoan.serviceedu.mapper.EduSubjectMapper;
import com.atbaoan.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.schema.Collections;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author emma
 * @since 2021-05-31
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //根据excel表格中的一级和二级课程分类将数据保存到subject表中
    @Autowired
    private EduSubjectMapper eduSubjectMapper;
    @Override
    public List<String>  importExcelData(MultipartFile file) {
        List<String> errorMsg = new ArrayList<>();
        //循环遍历excel表格中的数据
        try {
            InputStream inputStream = file.getInputStream();
            Workbook xssfWorkbook = new HSSFWorkbook(inputStream);
            //读取第一个sheet
            Sheet sheet = xssfWorkbook.getSheetAt(0);
            //遍历读取row
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i < lastRowNum; i++) {
                //获取row
                Row row = sheet.getRow(i);
                //获取第一列
                Cell cellOne = row.getCell(0);
                if (cellOne == null){
                    String error = "第"+i+"行第一列数据为空";
                    errorMsg.add(error);
                }
                String cellOneValue = cellOne.getStringCellValue();
                if (StringUtils.isEmpty(cellOneValue)){
                    String error = "第"+i+"行第一列数据为空";
                    errorMsg.add(error);
                }
                EduSubject oneSubject =existOne(cellOneValue);
                if (oneSubject == null){
                    //不存在则新增一级菜单
                    oneSubject = new EduSubject();
                    oneSubject.setTitle(cellOneValue);
                    oneSubject.setParentId("0");
                    eduSubjectMapper.insert(oneSubject);
                }
                //获取第二列
                Cell cellTwo = row.getCell(1);
                if (cellTwo == null){
                    String error = "第"+i+"行第二列数据为空";
                    errorMsg.add(error);
                }
                String cellTwoValue = cellTwo.getStringCellValue();
                if (StringUtils.isEmpty(cellTwoValue)){
                    String error = "第"+i+"行第二列数据为空";
                    errorMsg.add(error);
                }
                EduSubject subjectTwo = existTwo(cellTwoValue,oneSubject.getId());
                if (subjectTwo == null){
                    //不存在则新增二级菜单
                    EduSubject eduSubject = new EduSubject();
                    eduSubject.setTitle(cellTwoValue);
                    eduSubject.setParentId(oneSubject.getId());
                    eduSubjectMapper.insert(eduSubject);
                }
            }
            return errorMsg;
        } catch (IOException e) {
            e.printStackTrace();
            throw new EmmaException(20002,"上传课程文档错误");
        }
    }

    /**
     * 获取所有的一级分类，一级分类中包含一级下面所有的二级分类
     * @return
     */
    @Override
    public List<OneSubject> getAllSubject() {
        List<OneSubject> result = new ArrayList<>();
        //查询所有的一级分类
        QueryWrapper<EduSubject> oneQuery = new QueryWrapper<>();
        oneQuery.eq("parent_id","0");
        List<EduSubject> eduSubjects = eduSubjectMapper.selectList(oneQuery);
        if (CollectionUtils.isEmpty(eduSubjects)){
           return result;
        }
        //找出所有的二级分类
        QueryWrapper<EduSubject> twoQuery = new QueryWrapper<>();
        twoQuery.ne("parent_id","0");
        List<EduSubject> allTwoEduSubject = eduSubjectMapper.selectList(twoQuery);

        for (EduSubject eduSubject : eduSubjects) {
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);
            List<TwoSubject> childSubject = new ArrayList<>();
            //查找所有的一级分类下的二级分类
            if (CollectionUtils.isEmpty(allTwoEduSubject)){
                result.add(oneSubject);
                continue;
            }
            for (EduSubject twoSubject : allTwoEduSubject) {
                if (twoSubject.getParentId().equals(eduSubject.getId())){
                    TwoSubject two = new TwoSubject();
                    BeanUtils.copyProperties(twoSubject,two);
                    childSubject.add(two);
                }
            }
            oneSubject.setChildSubject(childSubject);
            result.add(oneSubject);
        }
        return result;
    }

    //判断二级菜单是否已经存在
    private EduSubject existTwo(String cellTwo,String oneId) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",cellTwo);
        wrapper.eq("parent_id",oneId);
        return  eduSubjectMapper.selectOne(wrapper);

    }
//判断一级菜单是否已经存在
    private EduSubject existOne(String cellOne) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",cellOne);
        wrapper.eq("parent_id","0");
        return  eduSubjectMapper.selectOne(wrapper);
    }
}
