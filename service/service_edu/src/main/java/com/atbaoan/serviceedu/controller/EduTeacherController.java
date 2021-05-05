package com.atbaoan.serviceedu.controller;



import com.atbaoan.commonutils.R;
import com.atbaoan.commonutils.ResultData;
import com.atbaoan.serviceedu.entity.EduTeacher;
import com.atbaoan.serviceedu.entity.vo.QueryTeacher;
import com.atbaoan.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author emma
 * @since 2021-02-17
 */
@RestController
@RequestMapping("/serviceedu/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;


    @ApiOperation(value = "查询teacher对象list集合")
    @GetMapping("/getAll")
    public ResultData getAll() {
        QueryWrapper wrapper = new QueryWrapper();
        List list = eduTeacherService.list(wrapper);
        return ResultData.ok().data("list", list);
    }

    @ApiOperation(value = "通过id删除teacher对象")
    @DeleteMapping("/deleteById/{id}")
    public ResultData deleteById(
            @ApiParam(name = "id", value = "teacher的id", required = true)
            @PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        if (b) {
            return ResultData.ok();
        } else {
            return ResultData.error();
        }
    }


    @ApiOperation(value = "通过id查询teacher对象")
    @GetMapping("/getById/{id}")
    public EduTeacher getById(
            @ApiParam(name = "id", value = "teacher的id", required = true)
            @PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return teacher;
    }

    @ApiOperation(value = "添加讲师")
    @PostMapping("/addTeacher")
    public ResultData addTeacher(
            @ApiParam(name = "teacher", value = "讲师实体", required = true)
            @RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return ResultData.ok();
        } else {
            return ResultData.error();
        }
    }

    @ApiOperation(value = "不带条件的分页查询")
            @GetMapping("/queryTeacherPage/{current}/{size}")
            public ResultData queryTeacherPage(
            @ApiParam(name = "current", value = "当前记录数", required = true)
            @PathVariable Long current,
            @ApiParam(name = "size", value = "每页记录数", required = true)
            @PathVariable Long size
    ) {
        Page<EduTeacher> page = new Page<>(current, size);
        eduTeacherService.page(page, null);
        long total = page.getTotal();
        List<EduTeacher> list = page.getRecords();
        return ResultData.ok().data("list", list).data("total", total);
    }

    @ApiOperation(value = "带条件的分页查询")
    @PostMapping("/queryTeacherPageWithWrapper/{current}/{size}")
    public R queryTeacherPageWithWrapper(
            @ApiParam(name = "current", value = "当前记录数", required = true)
            @PathVariable Long current,
            @ApiParam(name = "size", value = "每页记录数", required = true)
            @PathVariable Long size,
            @ApiParam(name = "teacher", value = "teacher查询实体", required = true)
            @RequestBody QueryTeacher queryTeacher
    ) {
        Page<EduTeacher> page = new Page<>(current,size);


        //调用方法实现条件查询带分页
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //判断条件值是否为空
        String name = queryTeacher.getName();
        Integer level = queryTeacher.getLevel();
        String begin = queryTeacher.getBegin();
        String end = queryTeacher.getEnd();

        if(!StringUtils.isEmpty(name)) {//name值不为空
            //拼接条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            //拼接条件
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            //拼接条件
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            //拼接条件
            wrapper.le("gmt_create",end);
        }
        wrapper.orderByDesc("gmt_create");

        eduTeacherService.page(page, wrapper);

        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }


    @ApiOperation(value = "通过id更新讲师信息")
    @PutMapping("/updateTeacher")
    public ResultData updateTeacher(
            @ApiParam(name = "teacher", value = "讲师实体", required = true)
            @RequestBody EduTeacher teacher
    ) {
        boolean b = eduTeacherService.updateById(teacher);
        if (b) {
            return ResultData.ok();
        } else {
            return ResultData.error();
        }
    }

}

