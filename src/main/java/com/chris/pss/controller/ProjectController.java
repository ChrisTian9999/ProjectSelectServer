package com.chris.pss.controller;

import com.chris.pss.dao.ProjectDao;
import com.chris.pss.entity.BaseResponse;
import com.chris.pss.entity.ProjectEntity;
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
@RequestMapping("/project")
public class ProjectController {


    @RequestMapping("/create")
    @ResponseBody
    public BaseResponse<Map> postCreate(@RequestBody ProjectEntity entity) {
//        boolean flag = new ProjectDao()
//                .create(entity.getDepartmentId(), entity.getMajorId(), entity.getTeacherId(), entity.getTitle(), entity.getDetail(), entity.getRanking());
//        if (flag) {
//            HashMap<String, Boolean> map = new HashMap<String, Boolean>();
//            map.put("flag", true);
//            return new BaseResponse<Map>(map);
//        }
        return new BaseResponse<Map>(Const.ERROR_SERVER, Const.ERROR_SERVER_MSG, null);
    }

    /**
     * 获得所有的课题的列表
     *
     * @param isChecked 是否通过审核
     */
    @RequestMapping("/list")
    @ResponseBody
    public BaseResponse<List<ProjectEntity>> getAllByCheckState(@RequestParam(value = "isChecked", required = false) Boolean isChecked) {
        List<ProjectEntity> list = null;
        if (isChecked == null) {
            list = new ProjectDao().getAll();
        } else {
            list = new ProjectDao().getByCheckState(isChecked);
        }
        return new BaseResponse<List<ProjectEntity>>(list);
    }

    /**
     * 获得学院所有的课题的列表
     *
     * @param isChecked 是否通过审核
     */
    @RequestMapping("/depart/list")
    @ResponseBody
    public BaseResponse<List<ProjectEntity>> postCreate(
            @RequestParam("departId") int departId
            , @RequestParam("isChecked") Boolean isChecked) {
        List<ProjectEntity> list = new ProjectDao().getByCheckState(departId, isChecked);
        return new BaseResponse<List<ProjectEntity>>(list);
    }

}
