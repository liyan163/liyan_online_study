package com.atbaoan.serviceedu.controller;


import com.atbaoan.commonutils.R;
import com.atbaoan.serviceedu.entity.EduChapter;
import com.atbaoan.serviceedu.entity.vo.ChapterVo;
import com.atbaoan.serviceedu.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author emma
 * @since 2021-07-15
 */
@Api("章节管理")
@RestController
@RequestMapping("/serviceedu/eduChapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    @ApiOperation(value = "根据课程id获取章节和小节")
    @GetMapping("/getChapterVideoById/{id}")
    public R getChapterVideoById(@PathVariable("id") String id) {
        List<ChapterVo> list = chapterService.getChapterVideoById(id);
        return R.ok().data("list",list);
    }

    @ApiOperation(value = "添加章节")
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter chapter){
        chapterService.save(chapter);
        return R.ok();
    }
    @ApiOperation(value = "删除章节，如果章节中有小节就不能删除")
    @DeleteMapping("{chapterid}")
    public R deleteChapter(
            @ApiParam(name = "章节id",value = "chapterid",required = true)
            @PathVariable String chapterid){

        chapterService.deleteChapterByCourseId(chapterid);
        return R.ok();
    }
    @ApiOperation(value = "通过id获取章节对象")
    @GetMapping("{chapterId}")
    public R getChapterById(@PathVariable String chapterId){
        EduChapter eduChapter= chapterService.getById(chapterId);
        return R.ok().data("eduChapter",eduChapter);
    }
    @ApiOperation(value = "通过章节对象修改章节")
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return R.ok();
    }
}

