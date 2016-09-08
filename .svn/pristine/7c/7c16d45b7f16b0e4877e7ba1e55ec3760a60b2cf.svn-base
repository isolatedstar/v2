package com.zllh.mall.mmbmmanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zllh.utils.common.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.base.controller.BaseController;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.mall.common.model.BusAreaTree;
import com.zllh.mall.common.model.MtMember;
import com.zllh.mall.common.model.MtMmbAreaBiz;
import com.zllh.mall.common.model.MtMuser;
import com.zllh.mall.mmbmmanage.service.IMMBBizTypeService;
import com.zllh.mall.mmbmmanage.service.IMMBService;
import com.zllh.mall.mmbmmanage.service.IMmbAreaBizService;
import com.zllh.mall.quote.service.AreaTreeService;
import com.zllh.utils.common.DateUtil;
import com.zllh.utils.common.DictionaryUtil;
import com.zllh.utils.common.UUIDCreater;


/**
 * @author luobo
 * @ClassName: MMBController
 * @Description: 会员管理
 * @date 2016-03-08 下午1:50:35
 */
@Controller
@RequestMapping("/mmb")
public class MMBController extends BaseController {

    @Autowired
    private IMMBService mmbService;// 会员操作service
    @Autowired
    private IMMBBizTypeService mmbBizTypeService;// 业务类型service
    @Autowired
    private IMmbAreaBizService mmbAreaBizService;//地域业务类型service
    @Autowired
    private AreaTreeService areaService;

    /**
     * @param @param  会员对象
     * @param @return 是否成功 json[msg:ok] or[msg:erro]
     * @return String
     * @throws
     * @Title: createMMB
     * @Description: 创建会员
     * @author luobo
     * @date 2016-03-07 下午2:12:09
     */
    @RequestMapping("/createMMBNoFilter")
    @ResponseBody
    public Map<String, Object> createMMB(
            @RequestParam(value = "mmbFname", required = false) String mmbFname,
            @RequestParam(value = "mmbSname", required = false) String mmbSname,
            @RequestParam(value = "mmbEngSname", required = false) String mmbEngSname,
            @RequestParam(value = "mmbType", required = false) Integer mmbType,
            @RequestParam(value = "mmbHomepage", required = false) String mmbHomepage,
            @RequestParam(value = "mmbEmail", required = false) String mmbEmail,
            @RequestParam(value = "mmbPhone", required = false) String mmbPhone,
            @RequestParam(value = "mmbAddress", required = false) String mmbAddress,
            @RequestParam(value = "mmbAreaId", required = false) String mmbAreaId,
            @RequestParam(value = "bizTypes", required = false) String bizTypes,
            @RequestParam(value = "operationName", required = false) String operationName,
            @RequestParam(value = "operationPassWord", required = false) String operationPassWord) {
        // 定义返回信息map
        Map<String, Object> infoMap = null;
        // uuid生成会员id
        String id = UUIDCreater.getUUID();
        // 组装新增对象信息
        MtMember mmb = new MtMember();
        mmb.setId(id);
        mmb.setMmbFname(mmbFname);
        mmb.setMmbSname(mmbSname);
        mmb.setMmbType(mmbType);
        mmb.setMmbHomepage(mmbHomepage);
        mmb.setMmbEngSname(mmbEngSname);
        mmb.setMmbEmail(mmbEmail);
        mmb.setMmbPhone(mmbPhone);
        mmb.setMmbAddress(mmbAddress);
        mmb.setMmbAreaId(mmbAreaId);
        mmb.setCreatTime(DateUtil.getNowDate());
        //默认的会员是启用状态
        mmb.setMmbStatus(DictionaryUtil.MMB_STATUS_USING);
        // 执行新增
        infoMap = mmbService.createMMB(mmb, operationName, operationPassWord, bizTypes);
        return infoMap;
    }

    /**
     * 进入编辑会员信息
     *
     * @param mmbId
     * @return
     */
    @RequestMapping("/toEditMmbInfoNoFilter")
    @ResponseBody
    public MtMember toEditMmbInfo(
            @RequestParam(value = "id", required = false) String mmbId) {
        // 根据所选id查询所有信息，包括关联的PMuser以及PMMBBIZType信息
        MtMember pubMmb = mmbService.queryMmbById(mmbId);
        // 返回查询到的对象
        return pubMmb;
    }

    /**
     * @param @param  会员对象
     * @param @return 是否成功 json[msg:ok] or[msg:erro]
     * @return String
     * @throws
     * @Title: editMMB
     * @Description:编辑会员
     * @author luobo
     * @date 2016-03-07 下午2:12:09
     */
    @RequestMapping("/updateMMBNoFilter")
    @ResponseBody
    public Map<String, Object> updateMMB(@RequestParam(value = "id1", required = false) String id,
                                         @RequestParam(value = "mmbFname1", required = false) String mmbFname,
                                         @RequestParam(value = "mmbEngSname1", required = false) String mmbEngSname,
                                         @RequestParam(value = "mmbSname1", required = false) String mmbSname,
                                         @RequestParam(value = "mmbType1", required = false) Integer mmbType,
                                         @RequestParam(value = "mmbHomepage1", required = false) String mmbHomepage,
                                         @RequestParam(value = "mmbEmail1", required = false) String mmbEmail,
                                         @RequestParam(value = "mmbPhone1", required = false) String mmbPhone,
                                         @RequestParam(value = "mmbAddress1", required = false) String mmbAddress,
                                         @RequestParam(value = "mmbAreaId1", required = false) String mmbAreaId,
                                         @RequestParam(value = "bizTypes1", required = false) String bizTypes) {
        // 定义返回信息map
        Map<String, Object> infoMap = new HashMap<String, Object>();
        // 组装修改对象信息
        MtMember mmb = new MtMember();
        mmb.setId(id);
        mmb.setMmbFname(mmbFname);
        mmb.setMmbSname(mmbSname);
        mmb.setMmbType(mmbType);
        mmb.setMmbHomepage(mmbHomepage);
        mmb.setMmbEmail(mmbEmail);
        mmb.setMmbPhone(mmbPhone);
        mmb.setMmbEngSname(mmbEngSname);
        mmb.setMmbAddress(mmbAddress);
        mmb.setMmbAreaId(mmbAreaId);
        //执行修改操作
        infoMap = mmbService.updateMMB(mmb, bizTypes);
        return infoMap;
    }

    /**
     * ajax返回json提示信息
     *
     * @param @param  会员对象
     * @param @return 是否成功 json[msg:ok] or[msg:erro]
     * @return String
     * @throws
     * @Title: pauseMMB
     * @Description:暂停会员
     * @author luobo
     * @date 2015-12-28 下午2:02:57
     */
    @RequestMapping("/pauseMMBNoFilter")
    @ResponseBody
    public String pauseMMB(@RequestParam(value = "id", required = false) String id) {
        logger.info("=======pauseMMB======");
        String returnStr = "";
        //组装修改状态对象
        MtMember pubmmb = new MtMember();
        if (!StringUtils.isBlank(id)) {
            pubmmb.setId(id);
            pubmmb.setMmbStatus(DictionaryUtil.MMB_STATUS_FORBID);
            boolean flag = mmbService.updateMmbStatus(pubmmb);
            //修改成功
            if (flag) {
                returnStr = "0";
            } else {
                returnStr = "1";
            }
        }
        return returnStr;
    }

    /**
     * @param @param  会员对象
     * @param @return 是否成功 json[msg:ok] or[msg:erro]
     * @return String
     * @throws
     * @Title: restartMMB
     * @Description:重启会员
     * @author yangdm
     * @date 2015-12-28 下午2:02:57
     */
    @RequestMapping("/restartMMBNoFilter")
    @ResponseBody
    public String restartMMB(String id) {
        logger.info("=======restartMMB======");
        String returnStr = "";
        //组装修改状态对象
        MtMember pubmmb = new MtMember();
        if (!StringUtils.isBlank(id)) {
            pubmmb.setId(id);
            pubmmb.setMmbStatus(DictionaryUtil.MMB_STATUS_USING);
            boolean flag = mmbService.updateMmbStatus(pubmmb);
            //修改成功
            if (flag) {
                returnStr = "0";
            } else {
                returnStr = "1";
            }
        }
        return returnStr;
    }

    /**
     * @param @param  会员对象
     * @param @return 会员列表
     * @return String
     * @throws
     * @Title: queryMMBList
     * @Description: 根据条件分页查询会员的列表
     * @author luobo
     * @date 2016-03-07 下午2:12:09
     */
    @RequestMapping("/queryMMBListNoFilter")
    @ResponseBody
    public Map<String, Object> queryMMBList(Model model,
                                            @RequestParam(value = "mmbFname", required = false) String mmbFname,
                                            @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        logger.info("===queryMMBList====");
        Map<String, Object> returnmap = new HashMap<String, Object>();
        // 查询条件
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtils.isBlank(mmbFname))
            map.put("mmbFname", mmbFname);
        // 执行条件查询总数
        int count = mmbService.countMmbList(map);
        // 处理页码和页面记录数
        pageNo = (pageNo == 0 || pageNo < 1) ? 1 : pageNo;
        pageSize = (pageSize == 0 || pageSize < 1) ? 10 : pageSize;
        // 当前总页数
        int totalPageCount = (count + pageSize - 1) / pageSize;
        if (pageNo > totalPageCount) {
            pageNo = (count + pageSize - 1) / pageSize;
        }
        // 分页查询数据map
        Map<String, Object> map1 = new HashMap<String, Object>();
        // 接收页面参数并传递给service
        if (!StringUtils.isBlank(mmbFname))
            map1.put("mmbFname", mmbFname);
        map1.put("startFirst", pageNo > 0 ? (pageNo - 1) * pageSize : 0);
        map1.put("startEnd", pageSize);
        // 执行查询得到list数据
        List<MtMember> list = mmbService.queryMMBList(map1);
        returnmap.put("mmbCount", count);
        returnmap.put("mmbList", list);
        // 将页面构造于分页页面对象中
//		page = new Page(count, pageNo, pageSize, list);
        // 返回数据模型
//		request.setAttribute("page", page);
//		// 返回查询条件
//		request.setAttribute("mmbFname", mmbFname);
//		model.addAttribute("mmbFname", mmbFname);
        // 返回页面视图
//		return "mall/mmb/mmb_manage";
        return returnmap;
    }

    /**
     * 左边菜单进入会员查询页面
     *
     * @return
     */
    @RequestMapping("/toMmbPageNoFilter")
    public String toMmbPage() {
        logger.info("====toMmbPageNoFilter====");
        return "mall/mmb/mmb_manage";
    }

    /**
     * @param @param  会员地域业务对象
     * @param @return
     * @return String
     * @throws
     * @Title: queryMMBByMid
     * @Description: 根据会员id单个会员对象
     * @author luobo
     * @date 2016-03-07 下午2:12:09
     */
    @RequestMapping("/queryMMBByMid")
    @ResponseBody
    public MtMember queryMMBByMid() {
        MtMember member = null;
        //获取mid
        String mid = request.getParameter("mmbId");
        String type = request.getParameter("type");
        String operateType = request.getParameter("operateType");//是买还是卖的关系 0：买 1：卖 2：借 3：贷
        UserExtendBean user = (UserExtendBean) this.session.getAttribute("user");
        if (user != null) {
            MtMuser muser = user.getMuser();
            String uid = muser.getMmbId();
            if (mid != null && uid != null) {
                member = mmbService.queryInfoById(mid, uid, type, operateType);
            }
        }
        return member;
    }

    /**
     * @param @param  会员地域业务对象
     * @param @return
     * @return String
     * @throws
     * @Title: queryAllAreaBizByMId
     * @Description: 根据会员id查询业务地域信息
     * @author luobo
     * @date 2016-03-07 下午2:12:09
     */
    @RequestMapping("/queryAllAreaBizByMIdNoFilter")
    @ResponseBody
    public List<MtMmbAreaBiz> queryAllAreaBizByMId(@RequestParam(value = "mid", required = false) String mid) {
        logger.info("===queryAllAreaBizByMId===");
        //查询
        List<MtMmbAreaBiz> list = mmbAreaBizService.queryMmbAreaBizByMid(mid);
        return list;
    }

    /**
     * 本方法用来查询所有节点树结构数据
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getTreeModel")
    @ResponseBody
    public List<BusAreaTree> getTreeModel() throws Exception {
        List<BusAreaTree> list = areaService.searchChina();
        return list;
    }

    /**
     * @param @param
     * @param @return
     * @return String
     * @throws
     * @Title: queryAllAreaBizByMId
     * @Description: 批量增加会员地域业务信息
     * @author luobo
     * @date 2016-03-07 下午2:12:09
     */
    @RequestMapping("/batchAddMMBAreaBiz")
    public void batchAddMMBAreaBiz() {
        // 获取jsonArray字符串
        String jsonStr = request.getParameter("jsonArry");
        String mmbId = request.getParameter("mmbId");
        //将获取到的json数组转成对象
        List<MtMmbAreaBiz> list = mmbAreaBizService.jsonArrayStrToObjects(jsonStr, mmbId);
        List<MtMmbAreaBiz> listAll = new ArrayList<MtMmbAreaBiz>();
        MtMmbAreaBiz mmbAreaBiz;
        String types = null;
        if (list != null && list.size() > 0) {
            //迭代获取到的数据重新截取成一个个待新增的对象
            for (MtMmbAreaBiz areaBiz : list) {
                types = areaBiz.getTypes();
                //如果获取到的业务类型有多个，截取成单个分别保存
                if (!StringUtils.isBlank(types) && types.contains(",")) {
                    String[] type_ = types.split(",");
                    for (int i = 0; i < type_.length; i++) {
                        String type = type_[i];
                        mmbAreaBiz = new MtMmbAreaBiz();
                        mmbAreaBiz.setId(UUIDCreater.getUUID());
                        mmbAreaBiz.setAreaId(areaBiz.getAreaId());
                        mmbAreaBiz.setBizType(Integer.parseInt(type));
                        mmbAreaBiz.setMmbId(areaBiz.getMmbId());
                        //放入list中用service执行批量新增
                        listAll.add(mmbAreaBiz);
                    }
                } else {
                    mmbAreaBiz = new MtMmbAreaBiz();
                    mmbAreaBiz.setId(UUIDCreater.getUUID());
                    mmbAreaBiz.setAreaId(areaBiz.getAreaId());
                    mmbAreaBiz.setBizType(Integer.parseInt(types));
                    mmbAreaBiz.setMmbId(areaBiz.getMmbId());
                    //放入list中用service执行批量新增
                    listAll.add(mmbAreaBiz);
                }
            }
            //调用service执行批量新增获取到的信息
            boolean flag = mmbAreaBizService.batchAddAreaBiz(listAll, mmbId);
            // 返回执行结果（json数据）
            if (flag) {
                //成功返回0表示
                outJson("0");
            } else {
                outJson("1");
            }
        }
    }

    /**
     * 添加和编辑会员时 验证简称:mmbSname  英文简称:mmbEngSname 注册全称:mmbFname 是否唯一
     *
     * @param checkType 检测类型
     * @return
     */
    @RequestMapping("/getNumByCheckType")
    @ResponseBody
    public int getNumByCheckType(String checkType, String checkValue,String id) {

        int resultNum = 0;
        String checkField = null;//待验证表字段
        switch (checkType) {
            case "mmbSname" : case "mmbSname1" :
                checkField = "mmb_sname";
                break;
            case "mmbEngSname": case "mmbEngSname1":
                checkField = "mmb_engSname";
                break;
            case "mmbFname" : case "mmbFname1" :
                checkField = "mmb_fname";
                break;
            case "operationName" :  case "operationName1" :
                checkField = "account_name";
                break;
            default :
                checkField = null;
                break;

        }

        resultNum = mmbService.getNumByCheckType(checkField,checkValue,id);

        return resultNum;
    }


    // 获取页面参数（地域-业务类型集合以及会员id）
    // 调用全部清除会员地域service
    // 调用清除制定会员全部地域service
    // 迭代地域-业务类型集合，调用创建会员地域service

    // 查询会员类型对应的角色：
    public String queryMMBForRole(String id) {
        return null;
    }
    // 获取用户的选择的地域用户关系类型
    // 更新用户关系
    // 返回到指定的页面

}
