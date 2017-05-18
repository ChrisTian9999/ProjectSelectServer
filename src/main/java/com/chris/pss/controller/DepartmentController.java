package com.chris.pss.controller;

import com.chris.pss.dao.DepartDao;
import com.chris.pss.entity.BaseResponse;
import com.chris.pss.entity.DepartmentEntity;
import com.chris.pss.utils.Const;
import com.chris.pss.utils.SimpleUtils;
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
@RequestMapping("/department")
public class DepartmentController {


    @RequestMapping("/major/{id}/modify")
    @ResponseBody
    public BaseResponse<Map> postModify(@PathVariable("id") int id, @RequestParam("start") long start, @RequestParam("end") long end) {
        if (start >= end) {
            return new BaseResponse<Map>(Const.ERROR_SERVER, Const.ERROR_SERVER_MSG, null);
        }
        String strStart = SimpleUtils.date2String(start);
        String strEnd = SimpleUtils.date2String(end);
        boolean flag = new DepartDao().updateById(id, strStart, strEnd);
        if (!flag) {
            return new BaseResponse<Map>(Const.ERROR_NOT_FOUND, Const.ERROR_NOT_FOUND_MSG_MAJOR, null);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        return new BaseResponse<Map>(map);
    }

    @RequestMapping("/major/{id}")
    @ResponseBody
    public BaseResponse<DepartmentEntity> getMajorById(@PathVariable("id") int id) {
        DepartmentEntity major = new DepartDao().findMajorById(id);
        if (major != null) {
            return new BaseResponse<DepartmentEntity>(major);
        }
        return new BaseResponse<DepartmentEntity>(Const.ERROR_NOT_FOUND, Const.ERROR_NOT_FOUND_MSG_MAJOR, null);
    }

    /**
     * 获得所有的Department，系别与专业
     */
    @RequestMapping("/list")
    @ResponseBody
    public BaseResponse<List<DepartmentEntity>> getDepartList() {
        List<DepartmentEntity> list = new DepartDao().getDepartList();
        if (list == null) {
            return new BaseResponse<List<DepartmentEntity>>(Const.ERROR_NOT_FOUND, Const.ERROR_NOT_FOUND_MSG_DEPARTMENT_ALL, null);
        }
        return new BaseResponse<List<DepartmentEntity>>(list);
    }
}
