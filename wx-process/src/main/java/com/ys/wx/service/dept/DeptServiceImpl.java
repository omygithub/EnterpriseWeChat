package com.ys.wx.service.dept;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ys.wx.model.dept.DepartmentForImport;
import com.ys.wx.model.dept.DepartmentWraper;
import com.ys.wx.service.deptandperson.IQueryInfoWXService;
import com.ys.wx.service.deptandperson.IQueryInfoWXServicePortType;
import com.ys.wx.utils.XStreamUtil;
import com.ys.wx.vo.ReturnVo;

/**
 *  部门服务实现
 * @author wangzequan
 *@Copyright:Copyright (c) 二龙湖基地组织  2015 ~ 2016 版权所有
 */

@Service
public class DeptServiceImpl implements IDeptService {

    private static final Logger logger = Logger.getLogger(DeptServiceImpl.class);

    @Resource
    private IQueryInfoWXService iQueryInfowxService;

    public ReturnVo<List<DepartmentForImport>> getEHRDeptData() throws Exception {
        logger.info(">>>>>>>>>>>>开始获取部门信息数据>>>>>>>>>>>>>>\n");
        ReturnVo<List<DepartmentForImport>> rVo = new ReturnVo<List<DepartmentForImport>>(0, "失败");
        IQueryInfoWXServicePortType type = iQueryInfowxService.getIQueryInfoWXServiceSOAP11PortHttp();
//        String dateStr = "1970-01 00:00:00";
        String wxResponse = type.getDeptWXAll();
        DepartmentWraper departmentwraper = XStreamUtil.toBean(wxResponse, DepartmentWraper.class);
        rVo.setData(departmentwraper.getDepts());
        rVo.setCode(1);
        rVo.setMsg("成功");
        logger.info(">>>>>>>>>>>>完成获取部门信息数据>>>>>>>>>>>>>>\n");
        return rVo;
    }

}
