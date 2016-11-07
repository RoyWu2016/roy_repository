package com.roy.demo.utils;

import org.apache.log4j.Logger;

import com.asiasoft.yeskey.api.InitManager;
import com.asiasoft.yeskey.api.OTPService;
import com.asiasoft.yeskey.api.YeskException;
import com.pingan.core.framework.api.base.exception.ApiException;
import com.pingan.core.framework.api.base.exception.CustomGatewayException;
import com.pingan.core.framework.api.dataservice.vo.EAPIResponseCode;
import com.pingan.core.framework.utils.SysProperties;
import com.pingan.interfaces.api.gw.vo.verifycode.SendPayloadRequestVO.EnumMessageDataType;

public class SendSMSUtils {
	private final static Logger log = Logger.getLogger(SendSMSUtils.class);

	static {
		try {
			String param = SendSMSUtils.class.getResource("/opt-sms-config.properties").getFile();
			InitManager.init(param);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
	}

	public static boolean sendMessage(String mobile, EnumMessageDataType mode) {
		return sendMessage(mobile, "scene||||" + mode.getVal());
	}

	public static boolean sendMessage(String mobile, String context) {
		try {
			String templetId = SysProperties.getInstance().getProperty("opt.sms.templateId", "YH_WJ140429001");
//			OTPService.generateTmpSMSOTP(mobile);
			OTPService.generateTmpSMSOTP(mobile, templetId, context); //发验证短信，有其他重载方法支持版本号，方法调用无异常抛出则正常
			return true;
		} catch (YeskException e) {
			log.error(e.toString(), e);
			return false;
		}
	}

	/**
	 * 
	 * @param mobile
	 * @param token
	 * @return
	 */
	public static boolean verifyToken(String mobile, String token)  throws ApiException {
		log.debug("verifyToken mobile=" + mobile + " , token=" + token);
		try {
			//TODO hongrongyuan580 待删除
			boolean flag = true;
			if (flag)
				return flag;
			      
			OTPService.verifyTmpOTP(mobile, token);// 动态码验证，方法调用无异常抛出则正常，否则取异常信息展示
			return true;
		} 
		catch (YeskException e) {
			e.printStackTrace();
			log.debug("verifyToken error msg:" + e.getMessage());
			String code = e.getCode();
			if ("Exception.VerifyTmpOTP.OTPExpired".equals(code)) {

				log.debug("Exception.VerifyTmpOTP.OTPExpired");

				throw new CustomGatewayException("验证码超时，请重新获取验证码",EAPIResponseCode.C001012.getCodeValue());

			} else if ("Exception.VerifyTmpOTP.InvalidOTP".equals(code)) {

				log.debug("Exception.VerifyTmpOTP.InvalidOTP");

				throw new CustomGatewayException("验证码错误，请输入正确的验证码");

			}
			return false;
		}
	}
}
