package com.chris.pss.controller;

import com.chris.pss.dao.DepartDao;
import com.chris.pss.dao.StudentDao;
import com.chris.pss.entity.BaseResponse;
import com.chris.pss.entity.DepartmentEntity;
import com.chris.pss.entity.StudentEntity;
import com.chris.pss.utils.Const;
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

    @RequestMapping("/sno/{sno}")
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


    @RequestMapping("/login")
    @ResponseBody
    public BaseResponse<Map> login(@RequestParam("sno") String sno, @RequestParam("pwd") String pwd) {
        StudentEntity student = new StudentDao().findStudentBySno(sno);
        if (student == null) {//账号错误
            return new BaseResponse<Map>(Const.ERROR_LOGIN, Const.ERROR_LOGIN_SNO, null);
        }
        if (!student.getPwd().equals(pwd)) {//密码错误
            return new BaseResponse<Map>(Const.ERROR_LOGIN, Const.ERROR_LOGIN_PWD, null);
        }
        student.setPwd(null);//保护密码

        DepartmentEntity depart = new DepartDao().findById(student.getDepartmentId());
        if (depart == null) {
            return new BaseResponse<Map>(Const.ERROR_NOT_FOUND, Const.ERROR_NOT_FOUND_MSG_DEPART, null);
        }
        List<DepartmentEntity> departList = new DepartDao().getDepartList();
        //
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stud", student);
        map.put("major", depart);
        map.put("proj", null);
        map.put("extras", departList);
        return new BaseResponse<Map>(map);
    }


    @RequestMapping("/id/{id}")
    @ResponseBody
    public BaseResponse<StudentEntity> getStuById(@PathVariable("id") int id) {
        StudentEntity student = new StudentDao().findById(id);
        if (student != null) {
            student.setPwd(null);//保护密码
            return new BaseResponse<StudentEntity>(student);
        }
        return new BaseResponse<StudentEntity>(Const.ERROR_NOT_FOUND, Const.ERROR_NOT_FOUND_MSG_STUDENT, null);
    }


    @RequestMapping(value = "/online/count", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<Map> getStuBySno(@RequestParam(value = "duration", required = false) Long duration) {
        Long dur = duration == null ? (10 * 1000) : duration;
        Long count = new StudentDao().findLatestHeartBeatCount(dur);
        //
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        return new BaseResponse<Map>(map);
    }

    @RequestMapping("/online")
    @ResponseBody
    public BaseResponse<Map> getStuBySno(@RequestParam("sno") String sno, @RequestParam(value = "duration", required = false) Long duration) {
        Long dur = duration == null ? (10 * 1000) : duration;
        boolean refreshOk = new StudentDao().refreshHeartBeatTime(sno);
        if (!refreshOk) {
            return new BaseResponse<Map>(Const.ERROR_SERVER, Const.ERROR_SERVER_MSG, null);
        }
        Long count = new StudentDao().findLatestHeartBeatCount(dur);
        //
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        return new BaseResponse<Map>(map);
    }
}
