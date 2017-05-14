package com.chris.pss.controller;

import com.chris.pss.dao.DepartDao;
import com.chris.pss.dao.TeacherDao;
import com.chris.pss.dto.TeacherLoginDTO;
import com.chris.pss.entity.BaseResponse;
import com.chris.pss.entity.DepartmentEntity;
import com.chris.pss.entity.TeacherEntity;
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
@RequestMapping("/teacher")
public class TeacherController {

    @RequestMapping("/login")
    @ResponseBody
    public BaseResponse<TeacherLoginDTO> login(@RequestParam("tno") String tno, @RequestParam("pwd") String pwd) {
        TeacherEntity teacher = new TeacherDao().findTchByTno(tno);
        if (teacher == null) {//账号错误
            return new BaseResponse<TeacherLoginDTO>(Const.ERROR_LOGIN, Const.ERROR_LOGIN_TNO, null);
        }
        if (!teacher.getPwd().equals(pwd)) {//密码错误
            return new BaseResponse<TeacherLoginDTO>(Const.ERROR_LOGIN, Const.ERROR_LOGIN_PWD, null);
        }
        teacher.setPwd(null);//保护密码
        DepartmentEntity depart = new DepartDao().findDepartById(teacher.getDepartmentId());
        List<DepartmentEntity> departList = new DepartDao().getDepartList();
        return new BaseResponse<TeacherLoginDTO>(new TeacherLoginDTO(teacher, depart, departList));
    }

    @RequestMapping("/id/{id}")
    @ResponseBody
    public BaseResponse<TeacherEntity> getTchById(@PathVariable("id") int id) {
        TeacherEntity teacher = new TeacherDao().findById(id);
        if (teacher != null) {
            teacher.setPwd(null);//密码保护
            return new BaseResponse<TeacherEntity>(teacher);
        }
        return new BaseResponse<TeacherEntity>(Const.ERROR_NOT_FOUND, Const.ERROR_NOT_FOUND_MSG_TEACHER, null);
    }

    @RequestMapping("/tno/{sno}")
    @ResponseBody
    public BaseResponse<TeacherEntity> getTchByTno(@PathVariable("sno") String sno) {
        TeacherEntity teacher = new TeacherDao().findTchByTno(sno);
        if (teacher != null) {
            teacher.setPwd(null);//密码保护
            return new BaseResponse<TeacherEntity>(teacher);
        }
        return new BaseResponse<TeacherEntity>(Const.ERROR_NOT_FOUND, Const.ERROR_NOT_FOUND_MSG_TEACHER, null);
    }

}
