package com.sunjoy.framework.dao.datasource.druid;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.druid.stat.DruidStatService;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.util.IPRange;

/**
 * Druid监控servlet
 * 
 */

@WebServlet(urlPatterns = "/druid/*")
public class DruidStatServlet extends StatViewServlet {
	private static final long serialVersionUID = -3541263495851948853L;
	protected final Logger logger = LoggerFactory.getLogger(DruidStatServlet.class);

	@Value("${egsc.config.druid.loginUsername:}")
	private String loginUsername;
	@Value("${egsc.config.druid.loginPassword:}")
	private String loginPassword;
	@Value("${egsc.config.druid.allow:}")
	private String allow; // IP白名单 (没有配置或者为空，则允许所有访问),多个ip用","分隔，如 192.168.1.72,127.0.0.1
	@Value("${egsc.config.druid.deny:}")
	private String deny; // IP黑名单 (存在共同时，deny优先于allow),多个ip用","分隔，如 192.168.1.73,192.168.1.74
	@Value("${egsc.config.druid.resetEnable:false}")
	private String resetEnable; // 禁用HTML页面上的“Reset All”功能,默认为ture禁用

	public void init() throws ServletException {
		username = loginUsername;
		password = loginPassword;
		initAllow();
		initDeny();
		initResetEnable();
		super.init();
	}

	/**
	 * 白名单
	 */
	private void initAllow() {
		try {
			if (allow != null && allow.trim().length() != 0) {
				allow = allow.trim();
				String[] items = allow.split(",");

				for (String item : items) {
					if (item == null || item.length() == 0) {
						continue;
					}

					IPRange ipRange = new IPRange(item);
					allowList.add(ipRange);
				}
			}
		} catch (Exception e) {
			String msg = "initParameter config error, allow : " + allow;
			logger.error(msg, e);
		}
	}

	/**
	 * 黑名单
	 */
	private void initDeny() {
		try {
			if (deny != null && deny.trim().length() != 0) {
				deny = deny.trim();
				String[] items = deny.split(",");

				for (String item : items) {
					if (item == null || item.length() == 0) {
						continue;
					}

					IPRange ipRange = new IPRange(item);
					denyList.add(ipRange);
				}
			}
		} catch (Exception e) {
			String msg = "initParameter config error, deny : " + deny;
			logger.error(msg, e);
		}
	}

	/**
	 * 重置按钮 void
	 */
	private void initResetEnable() {
		try {
			if (resetEnable != null && resetEnable.trim().length() != 0) {
				DruidStatService.getInstance().setResetEnable(Boolean.parseBoolean(resetEnable.trim()));
			}
		} catch (Exception e) {
			String msg = "initParameter config error, resetEnable : " + resetEnable;
			logger.error(msg, e);
		}
	}
}
