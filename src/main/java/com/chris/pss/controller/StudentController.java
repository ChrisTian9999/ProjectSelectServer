package com.chris.pss.controller;

import com.chris.pss.dao.DepartDao;
import com.chris.pss.dao.StudentDao;
import com.chris.pss.dto.StudentLoginDTO;
import com.chris.pss.dto.StudentOnlineCountDTO;
import com.chris.pss.entity.BaseResponse;
import com.chris.pss.entity.DepartmentEntity;
import com.chris.pss.entity.StudentEntity;
import com.chris.pss.utils.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by noonecode on 2017/5/8.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @RequestMapping("/login")
    @ResponseBody
    public BaseResponse<StudentLoginDTO> login(@RequestParam("sno") String sno, @RequestParam("pwd") String pwd) {
        StudentEntity student = new StudentDao().findStudentBySno(sno);
        if (student == null) {//账号错误
            return new BaseResponse<StudentLoginDTO>(Const.ERROR_LOGIN, Const.ERROR_LOGIN_SNO, null);
        }
        if (!student.getPwd().equals(pwd)) {//密码错误
            return new BaseResponse<StudentLoginDTO>(Const.ERROR_LOGIN, Const.ERROR_LOGIN_PWD, null);
        }
        student.setPwd(null);//保护密码
        DepartmentEntity major = new DepartDao().findMajorById(student.getDepartmentId());
        List<DepartmentEntity> departList = new DepartDao().getDepartList();
        return new BaseResponse<StudentLoginDTO>(new StudentLoginDTO(student, major, null, departList));
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

    @RequestMapping("/sno/{sno}")
    @ResponseBody
    public BaseResponse<StudentEntity> getStuBySno(@PathVariable("sno") String sno) {
        StudentEntity student = new StudentDao().findStudentBySno(sno);
        if (student != null) {
            student.setPwd(null);//保护密码
            return new BaseResponse<StudentEntity>(student);
        }
        return new BaseResponse<StudentEntity>(Const.ERROR_NOT_FOUND, Const.ERROR_NOT_FOUND_MSG_STUDENT, null);
    }


    @RequestMapping("/online")
    @ResponseBody
    public BaseResponse<StudentOnlineCountDTO> getStuBySno(@RequestParam("sno") String sno, @RequestParam(value = "duration", required = false) Long duration) {
        Long dur = duration == null ? (10 * 1000) : duration;
        boolean refreshOk = new StudentDao().refreshHeartBeatTime(sno);
        if (!refreshOk) {
            return new BaseResponse<StudentOnlineCountDTO>(Const.ERROR_SERVER, Const.ERROR_SERVER_MSG, null);
        }
        Long count = new StudentDao().findLatestHeartBeatCount(dur);
        return new BaseResponse<StudentOnlineCountDTO>(new StudentOnlineCountDTO(count));
    }
}
