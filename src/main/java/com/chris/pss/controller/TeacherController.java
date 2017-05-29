package com.chris.pss.controller;

import com.chris.pss.dao.DepartDao;
import com.chris.pss.dao.TeacherDao;
import com.chris.pss.entity.BaseResponse;
import com.chris.pss.entity.DepartEntity;
import com.chris.pss.entity.TeacherEntity;
import com.chris.pss.utils.Const;
import com.chris.pss.utils.EmptyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by noonecode on 2017/5/8.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    /**
     * 教师登陆
     */
    @RequestMapping("/login")
    @ResponseBody
    public BaseResponse<Map> login(@RequestParam("tno") String tno, @RequestParam("pwd") String pwd) {
        TeacherEntity teacher = new TeacherDao().findTchByTno(tno);
        if (teacher == null) {//账号错误
            return new BaseResponse<Map>(Const.ERROR_LOGIN, Const.ERROR_LOGIN_TNO, null);
        }
        if (EmptyUtils.isEmpty(pwd) || !pwd.equals(teacher.getPwd())) {//密码错误
            return new BaseResponse<Map>(Const.ERROR_LOGIN, Const.ERROR_LOGIN_PWD, null);
        }
        teacher.setPwd(null);//保护密码

        DepartEntity depart = new DepartDao().findById(teacher.getDepartmentId());
        if (depart == null) {
            return new BaseResponse<Map>(Const.ERROR_NOT_FOUND, Const.ERROR_NOT_FOUND_MSG_DEPART, null);
        }
        List<DepartEntity> departList = new DepartDao().getDepartList();
        //
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tch", teacher);
        map.put("depart", depart);
        map.put("extras", departList);
        return new BaseResponse<Map>(map);
    }

    /**
     * 拿教师除学院之外的信息
     */
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

    /**
     * 拿教师除学院之外的基本信息
     */
    @RequestMapping("/tno/{tno}")
    @ResponseBody
    public BaseResponse<TeacherEntity> getTchByTno(@PathVariable("tno") String tno) {
        TeacherEntity teacher = new TeacherDao().findTchByTno(tno);
        if (teacher != null) {
            teacher.setPwd(null);//密码保护
            return new BaseResponse<TeacherEntity>(teacher);
        }
        return new BaseResponse<TeacherEntity>(Const.ERROR_NOT_FOUND, Const.ERROR_NOT_FOUND_MSG_TEACHER, null);
    }

    /**
     * 根据教师的编号获得教师的信息
     */
    @RequestMapping("/info/tno/{tno}")
    @ResponseBody
    public BaseResponse<Map> getTchInfoByTno(@PathVariable("tno") String tno) {
        TeacherEntity teacher = new TeacherDao().findTchByTno(tno);
        if (teacher == null) {//账号错误
            return new BaseResponse<Map>(Const.ERROR_LOGIN, Const.ERROR_LOGIN_TNO, null);
        }
        teacher.setPwd(null);//保护密码
        DepartEntity depart = new DepartDao().findById(teacher.getDepartmentId());
        if (depart == null) {
            return new BaseResponse<Map>(Const.ERROR_NOT_FOUND, Const.ERROR_NOT_FOUND_MSG_DEPART, null);
        }
        //
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tch", teacher);
        map.put("depart", depart);
        return new BaseResponse<Map>(map);
    }

}
