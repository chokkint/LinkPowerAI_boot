
package com.ai.ringball.controller.biz;

import com.ai.ringball.dao.biz.LpScoreIndexMid;
import com.ai.ringball.framework.base.BaseJsonController;
import com.ai.ringball.framework.constants.SysConstants;
import com.ai.ringball.framework.utility.common.DateUtils;
import com.ai.ringball.framework.utility.common.JsonUtils;
import com.ai.ringball.framework.utility.common.PageUtils;
import com.ai.ringball.framework.utility.common.ResultUtils;
import com.ai.ringball.service.biz.LpScoreIndexMidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/LpScoreIndexMidController")
public class LpScoreIndexMidController extends BaseJsonController {

	@Autowired
	private LpScoreIndexMidService lpScoreIndexMidService;

	@RequestMapping(value = "/getAllLpScoreIndexMid", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getAllLpScoreIndexMid(String salesId, String custId, String dataDate, PageUtils page,
			HttpServletRequest request) {

		if (dataDate == null) {
			dataDate = DateUtils.getDate3(new Date());
		} else {
			dataDate = dataDate.replaceAll("-", SysConstants.CONSTANT_NULL_STRING).replaceAll("/", SysConstants.CONSTANT_NULL_STRING);
		}

		List<LpScoreIndexMid> records = new ArrayList<LpScoreIndexMid>();
		records = lpScoreIndexMidService.selectAllByPage(salesId, custId, dataDate, page);

		return JsonUtils.parseFromObject(ResultUtils.createSuccessResult(records, null));
	}

	@RequestMapping(value = "/perHandlerSourceData", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String perHandlerSourceData(String salesId, String custId, String dataDate, HttpServletRequest request) {

		if (dataDate == null) {
			dataDate = SysConstants.CONSTANT_NULL_STRING;
		} else {
			dataDate = dataDate.replaceAll("-", SysConstants.CONSTANT_NULL_STRING).replaceAll("/", SysConstants.CONSTANT_NULL_STRING);
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = lpScoreIndexMidService.perHandlerSourceData(salesId, custId, dataDate);
		return JsonUtils.parseFromObject(resultMap);
	}

	@RequestMapping(value = "/generateLpActSuggestData", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String generateLpActSuggestData(String salesId, String custId, String dataDate, HttpServletRequest request) {

		if (dataDate == null) {
			dataDate = DateUtils.getDate3(new Date());
		} else {
			dataDate = dataDate.replaceAll("-", SysConstants.CONSTANT_NULL_STRING).replaceAll("/", SysConstants.CONSTANT_NULL_STRING);
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = lpScoreIndexMidService.generateLpActSuggestData(salesId, custId, dataDate);
		return JsonUtils.parseFromObject(resultMap);
	}

	@RequestMapping(value = "/generateLpDsModelData", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String generateLpDsModelData(String salesId, String custId, String dataDate, HttpServletRequest request) {

		if (dataDate == null) {
			dataDate = DateUtils.getDate3(new Date());
		} else {
			dataDate = dataDate.replaceAll("-", SysConstants.CONSTANT_NULL_STRING).replaceAll("/", SysConstants.CONSTANT_NULL_STRING);
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = lpScoreIndexMidService.generateLpDsModelData(salesId, custId, dataDate);
		return JsonUtils.parseFromObject(resultMap);
	}

	@RequestMapping(value = "/generateIndexScoreData", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String generateIndexScoreData(String salesId, String custId, String dataDate, HttpServletRequest request) {

		if (dataDate == null) {
			dataDate = DateUtils.getDate3(new Date());
		} else {
			dataDate = dataDate.replaceAll("-", SysConstants.CONSTANT_NULL_STRING).replaceAll("/", SysConstants.CONSTANT_NULL_STRING);
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = lpScoreIndexMidService.generateScoreIndexMidData(salesId, custId, dataDate);
		return JsonUtils.parseFromObject(resultMap);
	}

	/*
	@RequestMapping(value = "/generateScoreResultData", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String generateScoreResultData(String salesId, String custId, String dataDate, HttpServletRequest request) {

		if (dataDate == null) {
			dataDate = DateUtils.getDate3(new Date());
		} else {
			dataDate = dataDate.replaceAll("-", SysConstants.CONSTANT_NULL_STRING).replaceAll("/", SysConstants.CONSTANT_NULL_STRING);
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = lpScoreIndexMidService.generateScoreResultData(salesId, custId, dataDate);
		return JsonUtils.parseFromObject(resultMap);
	}

	@RequestMapping(value = "/generateScoreLabelData", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String generateScoreLabelData(String salesId, String custId, String dataDate, HttpServletRequest request) {

		if (dataDate == null) {
			dataDate = DateUtils.getDate3(new Date());
		} else {
			dataDate = dataDate.replaceAll("-", SysConstants.CONSTANT_NULL_STRING).replaceAll("/", SysConstants.CONSTANT_NULL_STRING);
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = lpScoreIndexMidService.generateScoreLabelData(salesId, custId, dataDate);
		return JsonUtils.parseFromObject(resultMap);
	}
	*/

	@RequestMapping(value = "/generateScoreResultLabelData", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String generateScoreResultLabelData(String salesId, String custId, String dataDate,
			HttpServletRequest request) {

		if (dataDate == null) {
			dataDate = DateUtils.getDate3(new Date());
		} else {
			dataDate = dataDate.replaceAll("-", SysConstants.CONSTANT_NULL_STRING).replaceAll("/", SysConstants.CONSTANT_NULL_STRING);
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = lpScoreIndexMidService.generateScoreResultLabelData(salesId, custId, dataDate);
		return JsonUtils.parseFromObject(resultMap);
	}
}
