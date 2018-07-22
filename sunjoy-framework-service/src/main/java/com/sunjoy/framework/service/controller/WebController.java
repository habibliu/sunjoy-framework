package com.sunjoy.framework.service.controller;

import java.util.Date;

import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sunjoy.framework.service.binder.DateEditor;

public class WebController extends BaseController {
	@InitBinder
	protected void initBaseBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor("true", "false", true));
	}
}
