package com.chris.pss.controller;

import com.chris.pss.dao.DepartDao;
import com.chris.pss.dao.ProjectDao;
import com.chris.pss.dao.StudentDao;
import com.chris.pss.entity.BaseResponse;
import com.chris.pss.entity.DepartEntity;
import com.chris.pss.entity.ProjectEntity;
import com.chris.pss.entity.StudentEntity;
import com.chris.pss.utils.Const;
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
@RequestMapping("/student")
public class StudentController {

    @RequestMapping("/login")
    @ResponseBody
    public BaseResponse<StudentEntity> login(@RequestParam("sno") String sno, @RequestParam("pwd") String pwd) {
        StudentEntity student = new StudentDao().findStudentBySno(sno);
        if (student == null) {//账号错误
            return new BaseResponse<StudentEntity>(Const.ERROR_LOGIN, Const.ERROR_LOGIN_SNO, null);
        }
        if (!student.getPwd().equals(pwd)) {//密码错误
            return new BaseResponse<StudentEntity>(Const.ERROR_LOGIN, Const.ERROR_LOGIN_PWD, null);
        }
        student.setPwd(null);//保护密码
        return new BaseResponse<StudentEntity>(student);
    }

    @RequestMapping("/{sno}")
    @ResponseBody
    public BaseResponse<StudentEntity> getStuBySno(@PathVariable("sno") String sno) {
        StudentEntity student = new StudentDao().findStudentBySno(sno);
        if (student != null) {
            student.setPwd(null);//保护密码
            return new BaseResponse<StudentEntity>(student);
        }
        return new BaseResponse<StudentEntity>(Const.ERROR_NOT_FOUND
                , Const.ERROR_NOT_FOUND_MSG_STUDENT
                , null);
    }

    @RequestMapping("/{sno}/project")
    @ResponseBody
    public BaseResponse<ProjectEntity> getProjectBySno(@PathVariable("sno") String sno) {
        ProjectEntity project = new ProjectDao().getProjectByStudentSno(sno);
        return new BaseResponse<ProjectEntity>(project);
    }

    @RequestMapping("/online/count")
    @ResponseBody
    public BaseResponse<Map> getOnline(@RequestParam(value = "duration", required = false) Long duration) {
        Long count = new StudentDao().findLatestHeartBeatCount(duration);
        //
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        return new BaseResponse<Map>(map);
    }

    @RequestMapping("/online")
    @ResponseBody
    public BaseResponse<Map> postOnline(@RequestParam("sno") String sno, @RequestParam(value = "duration", required = false) Long duration) {
        boolean refreshOk = new StudentDao().refreshHeartBeatTime(sno);
        if (!refreshOk) {
            return new BaseResponse<Map>(Const.ERROR_SERVER, Const.ERROR_SERVER_MSG, null);
        }
        Long count = new StudentDao().findLatestHeartBeatCount(duration);
        //
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        return new BaseResponse<Map>(map);
    }

    @RequestMapping("{sno}/reset_pwd")
    @ResponseBody
    public BaseResponse<Map> postResetPwd(@PathVariable("sno") String sno,
                                          @RequestParam("pwd") String pwd,
                                          @RequestParam("newPwd") String newPwd) {
        boolean flag = new StudentDao().resetPwd(sno, pwd, newPwd);
        if (!flag) {
            return new BaseResponse<Map>(Const.ERROR_SERVER, "更改密码失败", null);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        return new BaseResponse<Map>(map);
    }
}
