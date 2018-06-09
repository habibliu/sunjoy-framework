package com.sunjoy.framework.service.controller;

import com.sunjoy.common.constants.CommonConstant;
import com.sunjoy.framework.client.dto.Response;
/**
 * API调用的基础控制类
 * @author liuganchao<840033486@>
 *
 */
public class ApiController extends BaseController {

	public Response getDefaultResponse() {
		return new Response(CommonConstant.SUCCESS_CODE, null, "");
	}
}
