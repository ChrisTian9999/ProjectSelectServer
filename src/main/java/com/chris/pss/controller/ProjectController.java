package com.chris.pss.controller;

import com.chris.pss.dao.ProjectDao;
import com.chris.pss.entity.BaseResponse;
import com.chris.pss.entity.ProjectEntity;
import com.chris.pss.utils.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
        boolean flag = new ProjectDao()
                .create(entity.getDepartmentId(), entity.getMajorId(), entity.getTeacherId(), entity.getTitle(), entity.getDetail(), entity.getRanking());
        if (flag) {
            HashMap<String, Boolean> map = new HashMap<String, Boolean>();
            map.put("flag", true);
            return new BaseResponse<Map>(map);
        }
        return new BaseResponse<Map>(Const.ERROR_SERVER, Const.ERROR_SERVER_MSG, null);
    }

}
