package com.zllh.mall.mmbmmanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.base.controller.BaseController;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.mall.common.model.MtMemberRelationship;
import com.zllh.mall.common.model.MtMuser;
import com.zllh.mall.mmbmmanage.service.IMmbRelationshipService;
import com.zllh.utils.common.DateUtil;
import com.zllh.utils.common.DictionaryUtil;
import com.zllh.utils.common.UUIDCreater;


/**
 * @author luobo
 * @ClassName: MMBController
 * @Description: 会员关系管理
 * @date 2016-03-08 下午1:50:35
 */
@Controller
@RequestMapping("/mmbRela")
public class MRTSPController extends BaseController {

    @Autowired
    private IMmbRelationshipService mmbRelationshipService;

    /**
     * 根据条件查询会员关系数据
     *
     * @param model
     * @param mmbId
     * @param type
     * @return
     */
    @RequestMapping("/queryMmbRelationshipsByMidNoFilter")
    @ResponseBody
    public List<MtMemberRelationship> queryMmbRelationshipsByMid(
            @RequestParam(value = "mmbId", required = false) String mmbId,
            @RequestParam(value = "type", required = false) Integer type) {
        logger.info("===queryMmbRelationshipsByMid====");
        // 查询条件
//		Map<String, Object> map = new HashMap<String, Object>();
//		if (!StringUtils.isBlank(mmbId))
//			map.put("mmbId", mmbId);
        // 如果是买，执行查询买方为传入mmbId,否则改变sql中的where条件
//		map.put("bizType", Integer.valueOf(type));
        // 执行条件查询总数(以mmbid为买方id查询总数)
//		int count = mmbRelationshipService.countMmbRelationShipForBuy(map);
//		// 分页数据初始化
//		int pageNo = getPageNo();
//		int pageSize = getPageSize();
        // 处理页码和页面记录数
//		pageNo = (pageNo == 0 || pageNo < 1) ? 1 : pageNo;
//		pageSize = (pageSize == 0 || pageSize < 1) ? 10 : pageSize;
        // 当前总页数
//		int totalPageCount = (count + pageSize - 1) / pageSize;
//		if (pageNo > totalPageCount) {
//			pageNo = (count + pageSize - 1) / pageSize;
//		}
        // 分页查询数据map
        Map<String, Object> map1 = new HashMap<String, Object>();
        // 接收页面参数并传递给service
        if (!StringUtils.isBlank(mmbId))
            map1.put("mmbId", mmbId);
        // 如果是买，执行查询买方为传入mmbId,否则改变sql中的where条件
        map1.put("bizType", Integer.valueOf(type));
//		map1.put("startFirst", pageNo > 0 ? (pageNo - 1) * pageSize : 0);
//		map1.put("startEnd", pageSize);
        // 执行查询得到list数据
        List<MtMemberRelationship> list = mmbRelationshipService
                .queryMmbRelationShipForBuy(map1);
        // 将页面构造于分页页面对象中
//		page = new Page(count, pageNo, pageSize, list);
        // 返回数据模型
//		request.setAttribute("page", page);
//		model.addAttribute("page", page);
        // 返回json数据
        return list;
    }

    /**
     * 进入待审批会员列表
     *
     * @return
     */
    @RequestMapping("/toApprovalRelaMmbs")
    public String toApprovalRelaMmbs() {
        //获取当前会员信息传入主页面供查询使用
        UserExtendBean user = (UserExtendBean) this.session.getAttribute("user");
        if (user != null) {
            MtMuser muser = user.getMuser();
            String uid = muser.getMmbId();
            //会员id存入页面
            if (user != null)
                request.setAttribute("mmbId", uid);
        }
        return "mall/mmbRela/pending_rela_mmb";
    }

    /**
     * 进入会员关注目录
     *
     * @return
     */
    @RequestMapping("/toConcernRelaMmbs")
    public String toConcernRelaMmbs(Model model) {
        //获取当前会员信息传入主页面供查询使用
        UserExtendBean user = (UserExtendBean) this.session.getAttribute("user");
        String uid = "";
        if (user != null) {
            MtMuser muser = user.getMuser();
            uid = muser.getMmbId();
            //会员id存入页面
            if (user != null)
                request.setAttribute("mmbId", uid);
        }

        //int oneNums,twoNums,threeNums,fourNums,fiveNums;
        int[] levelNums =new int[6];

        Map<String,Object> levelMap = new HashMap<>();
        levelMap.put("mmbId",uid);

        for(int i = 0; i < 5; i++){
            levelMap.put("grade",i+1);
            levelNums[i] = mmbRelationshipService.getConcernRelaNumsByLevel(levelMap);
        }

        request.setAttribute("levelNums", levelNums);

        return "mall/mmbRela/concern_rela_mmb";
    }

    /**
     * 进入会员合作目录
     *
     * @return
     */
    @RequestMapping("/toOperationRelaMmbs")
    public String toOperationRelaMmbs(Model model) {
        //获取当前会员信息传入主页面供查询使用
        UserExtendBean user = (UserExtendBean) this.session.getAttribute("user");
        if (user != null) {
            MtMuser muser = user.getMuser();
            String uid = muser.getMmbId();
            //会员id存入页面
            if (user != null)
                request.setAttribute("mmbId", uid);

            //查询该会员的业务合作会员目录
            List operationRelaList = mmbRelationshipService.getOperationRelaByMmbId(uid);
            model.addAttribute("operationRelaList", operationRelaList);

        }
        return "mall/mmbRela/operation_rela_mmb";
    }

    /**
     * 分页查询待审核会员关系数据
     *
     * @param pageSize
     * @param mmbId
     * @param pageNo
     * @return map
     */
    @RequestMapping("/queryMmbRelationships")
    @ResponseBody
    public Map<String, Object> queryMmbRelationships(
            @RequestParam(value = "mmbId", required = false) String mmbId,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        logger.info("===queryMmbRelationships====");
        Map<String, Object> returnMap = new HashMap<String, Object>();
        // 查询条件
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtils.isBlank(mmbId))
            map.put("mmbId", mmbId);
        // 执行条件查询总数(以mmbid为买方id查询总数)
        int count = mmbRelationshipService.countMmbRelationShips(map);
        // 分页数据初始化
//		int pageNo = getPageNo();
//		int pageSize = getPageSize();
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
        if (!StringUtils.isBlank(mmbId))
            map1.put("mmbId", mmbId);
        map1.put("startFirst", pageNo > 0 ? (pageNo - 1) * pageSize : 0);
        map1.put("startEnd", pageSize);
        // 执行查询得到list数据
        List<MtMemberRelationship> list = mmbRelationshipService
                .queryMmbRelationShips(map1);
        returnMap.put("relaMmbsCount", count);
        returnMap.put("relaMmbs", list);
        // 将页面构造于分页页面对象中
//		page = new Page(count, pageNo, pageSize, list);
        // 返回数据模型
//		request.setAttribute("page", page);
//		model.addAttribute("page", page);
        // 返回数据
        return returnMap;
    }

    /**
     * 批量执行提交会员关系
     */
    @RequestMapping("/batchSaveRelaMMbs")
    public void batchSaveRelaMMbs() {
        String relamMmbsId = request.getParameter("mmbIds");
        String mmbId = request.getParameter("mid");
        String type = request.getParameter("type");
        boolean flag = mmbRelationshipService.batchAddMmbRelas(relamMmbsId, mmbId, type);
        // 返回执行结果（json数据）
        if (flag) {
            // 成功返回0表示
            outJson("0");
        } else {
            outJson("1");
        }
    }

    /**
     * 批量执行新增会员关系数据
     */
    @RequestMapping("/batchAddRelationShips")
    public void batchAddRelationShips() {
        // 获取jsonArray字符串
        String jsonStr = request.getParameter("");
        // 将获取到的json数组转成对象
        List<MtMemberRelationship> list = mmbRelationshipService
                .jsonArrayStrToObjects(jsonStr);
        List<MtMemberRelationship> listAll = new ArrayList<MtMemberRelationship>();
        MtMemberRelationship mmbRelationship = new MtMemberRelationship();
        if (list != null && list.size() > 0) {
            // 迭代获取到的数据重新截取成一个个待新增的对象
            for (MtMemberRelationship mmbRela : list) {
                mmbRelationship.setId(UUIDCreater.getUUID());
                mmbRelationship.setCreateTime(DateUtil.getNowDate());
                mmbRelationship.setMmbId(mmbRela.getMmbId());
                mmbRelationship.setRelaMmbId(mmbRela.getRelaMmbId());
                // 买卖都存0，借贷存1
                mmbRelationship.setBizType(DictionaryUtil.MMB_BIZ_TYPE_BUY);
                // 这里的关系不需要审核
                mmbRelationship
                        .setRelaStatus(DictionaryUtil.MMB_RELATIONSHIP_STATUS_NOMAL);
                // 这里保存的关系默认为合作关系
                mmbRelationship
                        .setRelaType(DictionaryUtil.MMB_RELATIONSHIP_TYPE_OPRATION);
                listAll.add(mmbRelationship);
            }
            // 调用service执行批量新增获取到的信息
            boolean flag = mmbRelationshipService
                    .batchAddMmbRelationship(listAll);
            // 返回执行结果（json数据）
            if (flag) {
                // 成功返回0表示
                outJson("0");
            } else {
                outJson("1");
            }
        }
    }

    /**
     * 批量执行删除会员关系信息
     */
    @RequestMapping("/batchDeleteRelationShips")
    public void batchDeleteRelationShips() {
        // 获取id字符串
        String ids = request.getParameter("mmbIds");
        List<String> list = new ArrayList<String>();
        // 将获取到的字符串截取批量执行删除
        if (!StringUtils.isBlank(ids)) {
            if (ids.contains(",")) {
                //分隔成数组
                String[] relaIds = ids.split(",");
                if (relaIds != null && relaIds.length > 0) {
                    for (int i = 0; i < relaIds.length; i++) {
                        list.add(relaIds[i]);
                    }
                }
            } else {
                list.add(ids);
            }
        }
        // 执行批量删除
        boolean flag = mmbRelationshipService.deleteMmbRelationships(list);
        // 返回执行结果（json数据）
        if (flag) {
            // 成功返回0表示
            outJson("0");
        } else {
            outJson("1");
        }
    }

    /**
     * 会员关系审核
     */
    @RequestMapping("/verifyMmbRelationship")
    public void verifyMmbRelationship() {
        boolean flag = false;
        // 获取同意或者拒绝审核状态
        String type = request.getParameter("type");
        // 获取同意会员关系数据id
        String id = request.getParameter("id");
        // 如果是同意状态，则修改当前选择数据，将状态修改为审核通过
        if (!StringUtils.isBlank(type)
                && DictionaryUtil.MMB_RELATIONSHIP_STATUS_NOMAL_.equals(type)) {
            MtMemberRelationship mmbRelationship = new MtMemberRelationship();
            mmbRelationship.setId(id);
            mmbRelationship.setRelaStatus(DictionaryUtil.MMB_RELATIONSHIP_STATUS_NOMAL);
            // 执行修改状态
            flag = mmbRelationshipService.updateMmbRelationship(mmbRelationship);
        }
        // 否则如果是拒绝状态，直接删除该关系
        else {
            flag = mmbRelationshipService.deleteMmbRelationshipById(id);
        }
        if (flag) {
            outJson("0");
        } else {
            outJson("1");
        }
    }

    /**
     * ajax请求动态修改关注会员等级
     */
    @RequestMapping("/changeConcernGrade")
    public void changeConcernGrade() {
        // 获取当前会员id,关系会员id,以及关注等级
        String id = request.getParameter("Id");
        //String mmbId = request.getParameter("");
        //String relaMmbId = request.getParameter("");
        String grade = request.getParameter("grade");
        // 封装待修改数据
        MtMemberRelationship mmbRela = new MtMemberRelationship();
        mmbRela.setId(id);
//		mmbRela.setMmbId(mmbId);
//		mmbRela.setRelaMmbId(relaMmbId);
        mmbRela.setRelaGrade(Integer.valueOf(grade));
        // 调用service，执行修改
        boolean flag = mmbRelationshipService.updateMmbRelationship(mmbRela);
        // 返回结果
        if (flag) {
            outJson("0");
        } else {
            outJson("1");
        }
    }

    /**
     * 点击升级至业务合作页面
     *
     * @return
     */
    @RequestMapping("/toUpgradebizOperation")
    @ResponseBody
    public Map<String, Object> toUpgradebizOperation(Model model) {
        //返回值
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取mmbid,relaMmbId待存入
        String mmbId = request.getParameter("mmbId");
        String relaMmbId = request.getParameter("relaMmbId");
        //通过mmbid以及relaMmbId查询买卖关系返回map
        map = this.mmbRelationshipService.upMmbRelaToBizOperation(
                mmbId, relaMmbId);
        return map;
    }

    /**
     * 停用合作关系
     *
     * @return
     */
    @RequestMapping("/stopBizRelationShip")
    @ResponseBody
    public String stopBizRelationShip() {
        String result = null;
        //获取参数
        String mmbId = request.getParameter("mmbId");
        String relaMmbId = request.getParameter("relaMmbId");
        String type = request.getParameter("type"); // 1 是采购 2 是销售
        result = mmbRelationshipService.stopBizRelationShip(mmbId, relaMmbId, type);
        return result;
    }

    /**
     * 启用合作关系
     *
     * @return
     */
    @RequestMapping("/openBizRelationShip")
    @ResponseBody
    public String openBizRelationShip() {
        String result = null;
        //获取参数
        String mmbId = request.getParameter("mmbId");
        String relaMmbId = request.getParameter("relaMmbId");
        String type = request.getParameter("type"); // 1 是采购 2 是销售
        result = mmbRelationshipService.openBizRelationShip(mmbId, relaMmbId, type);
        return result;
    }

    /**
     * 降级为关注
     */
    @RequestMapping("/lowerToConcernOperation")
    public void lowerToConcernOperation() {
        // 获取mmbid,relaMmbId
        String mmbId = request.getParameter("mmbId");
        String relaMmbId = request.getParameter("relaMmbId");
        String bizType = request.getParameter("bizType");

        // 降级为关注其实就是将保存的两条业务合作关系数据删除
        Map<String, Object> map = new HashMap<String, Object>();
       /* //买
        if(bizType != null && ("0").equals(bizType) ){
            map.put("mmbId", mmbId);
            map.put("relaMmbId", relaMmbId);
        }else  if(bizType != null && ("1").equals(bizType) ){
            map.put("mmbId",relaMmbId);
            map.put("relaMmbId",mmbId);
        }*/

        map.put("mmbId", mmbId);
        map.put("relaMmbId", relaMmbId);

        //降级为仅关注  1、没有关注关系则新增关注关系 2、有关注关系则直接删除合作关系保留原关注关系
        int flag = mmbRelationshipService.lowerToConcernOperation(map);
        if (flag > 0) {
            outJson("0");
        } else {
            outJson("1");
        }
    }

    /**
     * 根据会员关注等级查询相关会员信息
     *
     * @return
     */
    @RequestMapping("/getMmbsByGrade")
    @ResponseBody
    public List<MtMemberRelationship> getMmbsByGrade() {
        // 获取当前会员id以及点击的等级
        String mmbId = request.getParameter("mmbId");
        String grade = request.getParameter("type");
        List<MtMemberRelationship> list = null;
        // 不为空查询数据返回界面
        if (!StringUtils.isBlank(mmbId) && !StringUtils.isBlank(grade)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("mmbId", mmbId);
            map.put("grade", Integer.parseInt(grade));
            list = mmbRelationshipService.queryMmbRelationShipsByGrade(map);
        }
        return list;
    }

    /**
     * 根据会员合作业务类型相关会员信息
     *
     * @return
     */
    @RequestMapping("/getMmbsByType")
    @ResponseBody
    public List<MtMemberRelationship> getMmbsByType() {
        // 获取当前会员id以及点击的合作类型
        String mmbId = request.getParameter("mmbId");
        //0:买 1：卖
        String type = request.getParameter("type");
        List<MtMemberRelationship> list = null;
        // 不为空查询数据返回界面
        if (!StringUtils.isBlank(mmbId) && !StringUtils.isBlank(type)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("mmbId", mmbId);
            map.put("bizType", Integer.parseInt(type));
            list = mmbRelationshipService.queryMmbRelationShipForBuy(map);
        }
        return list;
    }

    /**
     * 根据会员业务合作类型查询相关会员信息
     *
     * @return
     */
    @RequestMapping("/getMmbsByTypes")
    @ResponseBody
    public List<MtMemberRelationship> getMmbsByTypes() {
        // 获取当前会员id以及点击的关系类型
        String mmbId = request.getParameter("");
        String type = request.getParameter("");
        List<MtMemberRelationship> list = null;
        // 不为空查询数据返回界面
        if (!StringUtils.isBlank(mmbId) && !StringUtils.isBlank(type)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("mmbId", mmbId);
            map.put("type", type);
            list = mmbRelationshipService.queryMmbRelationShipsByType(map);
        }
        return list;
    }

	/*public String toAdjustRelationship() {
        // 获取mmbid,relaMmbId
		String mmbId = request.getParameter("");
		String relaMmbId = request.getParameter("");

		return null;
	}*/

    /**
     * 取消关注
     * @param id  会员关系ID
     */
    @RequestMapping("/cancelConcern")
    public void cancelConcern(String id){

        //直接删除会员关系 记录
        boolean flag = mmbRelationshipService.deleteMmbRelationshipById(id);

        if (flag) {
            outJson("0");
        } else {
            outJson("1");
        }
    }
}
