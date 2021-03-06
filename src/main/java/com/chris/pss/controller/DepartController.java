package com.chris.pss.controller;

import com.chris.pss.dao.DepartDao;
import com.chris.pss.dao.ProjectDao;
import com.chris.pss.entity.BaseResponse;
import com.chris.pss.entity.DepartEntity;
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
@RequestMapping("/depart")
public class DepartController {


    @RequestMapping("/major/{id}/modify")
    @ResponseBody
    public BaseResponse<Map> postModify(@PathVariable("id") int id, @RequestParam("start") String start, @RequestParam("end") String end) {
        long st = SimpleUtils.String2Date(start);
        long en = SimpleUtils.String2Date(end);
        if (st <= 0 || en <= 0 || st >= en) {
            return new BaseResponse<Map>(Const.ERROR_TIME_FORMAT, Const.ERROR_TIME_FORMAT_MSG, null);
        }
        boolean flag = new DepartDao().updateById(id, start, end);
        return SimpleUtils.generalResponseState(flag);
    }

    @RequestMapping("/{departId}/list")
    @ResponseBody
    public BaseResponse<List<DepartEntity>> getMajorById(@PathVariable("departId") int departId) {
        List<DepartEntity> majorList = new DepartDao().getMajorList(departId);
        return new BaseResponse<List<DepartEntity>>(majorList);
    }


    /**
     * 获得所有的Department，系别与专业
     */
    @RequestMapping("/list")
    @ResponseBody
    public BaseResponse<List<DepartEntity>> getDepartList() {
        List<DepartEntity> list = new DepartDao().getDepartList();
        if (list == null) {
            return new BaseResponse<List<DepartEntity>>(Const.ERROR_NOT_FOUND, Const.ERROR_NOT_FOUND_MSG_DEPARTMENT_ALL, null);
        }
        return new BaseResponse<List<DepartEntity>>(list);
    }
}
