package com.chris.pss.controller;

import com.chris.pss.dao.ProjectDao;
import com.chris.pss.entity.BaseResponse;
import com.chris.pss.entity.ProjectEntity;
import com.chris.pss.utils.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by noonecode on 2017/5/8.
 */
@Controller
@RequestMapping("/project")
public class ProjectController {


    @RequestMapping("/create")
    @ResponseBody
    public BaseResponse<ProjectEntity> postCreate(@ModelAttribute ProjectEntity entity) {
        ProjectEntity projectEntity = new ProjectDao().save(entity);
        if (projectEntity != null) {
            return new BaseResponse<ProjectEntity>(projectEntity);
        }
        return new BaseResponse<ProjectEntity>(Const.ERROR_SERVER, Const.ERROR_SERVER_MSG, null);
    }

}
