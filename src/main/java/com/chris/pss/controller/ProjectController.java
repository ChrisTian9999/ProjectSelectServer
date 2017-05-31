package com.chris.pss.controller;

import com.chris.pss.dao.ProjectDao;
import com.chris.pss.dao.StudentDao;
import com.chris.pss.entity.BaseResponse;
import com.chris.pss.entity.DepartEntity;
import com.chris.pss.entity.ProjectEntity;
import com.chris.pss.entity.StudentEntity;
import com.chris.pss.utils.Const;
import com.chris.pss.utils.EmptyUtils;
import com.chris.pss.utils.SimpleUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by noonecode on 2017/5/8.
 */
@Controller
@RequestMapping("/project")
public class ProjectController {


    @RequestMapping("/create")
    @ResponseBody
    public BaseResponse<Map> postCreate(
            @RequestParam("majorId") Integer majorId,
            @RequestParam("teacherId") Integer teacherId,
            @RequestParam("title") String title,
            @RequestParam("detail") String detail,
            @RequestParam("ranking") Integer ranking) {
        boolean flag = new ProjectDao().create(majorId, teacherId, title, detail, ranking);
        return SimpleUtils.generalResponseState(flag);
    }

    /**
     * 获得所有的课题的列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public BaseResponse<List<ProjectEntity>> getAll() {
        List<ProjectEntity> list = new ProjectDao().getAll();
        return new BaseResponse<List<ProjectEntity>>(list);
    }

    /**
     * 获得教师所有的课题的列表
     */
    @RequestMapping("/teacher/{tno}/list")
    @ResponseBody
    public BaseResponse<List<ProjectEntity>> getProjectListByTno(@PathVariable("tno") String tno) {
        List<ProjectEntity> list = new ProjectDao().getProjectListByTno(tno);
        return new BaseResponse<List<ProjectEntity>>(list);
    }

    /**
     * 获得学院所有的课题的列表
     *
     * @param isChecked 是否通过审核
     */
    @RequestMapping("/depart/{departId}/list")
    @ResponseBody
    public BaseResponse<List<ProjectEntity>> postCreate(
            @PathVariable("departId") int departId
            , @RequestParam("isChecked") Boolean isChecked) {
        List<ProjectEntity> list = new ProjectDao().getByCheckState(departId, isChecked);
        return new BaseResponse<List<ProjectEntity>>(list);
    }

    @RequestMapping("{projectId}/reset_state")
    @ResponseBody
    public BaseResponse<Map> postResetCheckState(
            @PathVariable("projectId") int projectId,
            @RequestParam("isChecked") Boolean isChecked) {
        boolean flag = new ProjectDao().resetCheckState(projectId, isChecked);
        return SimpleUtils.generalResponseState(flag);
    }

    /**
     * 获得某个专业的通过审核的课题
     */
    @RequestMapping("/major/{majorId}/list/checked")
    @ResponseBody
    public BaseResponse<List<ProjectEntity>> getMajorCheckedProjectList(@PathVariable("majorId") int majorId) {
        List<ProjectEntity> projectList = new ProjectDao().getMajorCheckedProjectList(majorId);
        return new BaseResponse<List<ProjectEntity>>(projectList);
    }

    /**
     * 学生选择课题
     */
    @RequestMapping("/{projectId}/student/{studentId}/select")
    @ResponseBody
    public BaseResponse<ProjectEntity> postSelectProject(
            @PathVariable("projectId") int projectId,
            @PathVariable("studentId") int studentId) {
        //首先需要检测是否在选择时间内
        StudentEntity stuById = new StudentDao().findById(studentId);
        DepartEntity major = stuById.getMajor();
        long time = System.currentTimeMillis();
        long st = SimpleUtils.String2Date(major.getTimeBegin());
        long en = SimpleUtils.String2Date(major.getTimeEnd());
        if (st <= 0 || en <= 0 || st > en//错误数据
                || time < st) {
            return new BaseResponse<ProjectEntity>(Const.ERROR_SERVER, "选题未开始", null);
        } else if (time >= en) {
            return new BaseResponse<ProjectEntity>(Const.ERROR_SERVER, "选题已结束", null);
        }
        //在选题时间内
        ProjectDao projectDao = new ProjectDao();
        //当课题已被选择的状态时
        ProjectEntity project = projectDao.findById(projectId);
        StudentEntity student = project.getStudent();
        if (student != null) {
            if (student.getId() == studentId) {
                return new BaseResponse<ProjectEntity>(Const.ERROR_SERVER, "您已选择", null);
            } else if (student.getId() != studentId) {
                return new BaseResponse<ProjectEntity>(Const.ERROR_SERVER, "他人已选择", null);
            }
        }
        //当前课题是未被选择
        //当前选择过其他课题
        if (projectDao.getProjectByStudentId(studentId) != null) {
            return new BaseResponse<ProjectEntity>(Const.ERROR_SERVER, "至多只能选择一个课题", null);
        }
        boolean flag = projectDao.postSelectProject(projectId, studentId);
        if (flag) {
            ProjectEntity projectEntity = projectDao.findById(projectId);
            return new BaseResponse<ProjectEntity>(projectEntity);
        }
        return new BaseResponse<ProjectEntity>(Const.ERROR_SERVER, "未知错误", null);
    }
}
