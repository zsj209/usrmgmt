/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016All Rights Reserved.
 */
package user;

import com.hrocloud.common.api.CommParamInfoService;
import com.hrocloud.common.api.model.CaptchaTemplate;
import com.hrocloud.common.api.model.ParamValue;
import com.hrocloud.usrmgmt.api.CaptchaService;
import com.hrocloud.usrmgmt.api.NodeAgwService;
import com.hrocloud.usrmgmt.api.UserAgwService;
import com.hrocloud.usrmgmt.constant.ValueListDefine;
import com.hrocloud.usrmgmt.dto.ListUserDTO;
import com.hrocloud.usrmgmt.dto.Menu;
import com.hrocloud.usrmgmt.dto.ParamValueDTO;
import com.hrocloud.usrmgmt.dto.UserDTO;
import com.hrocloud.usrmgmt.utils.Utils;
import com.hrocloud.util.CommonUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

/*@ContextConfiguration(locations = { "/com/spring-mvc.xml",
        "/com/dubbo-demo-consumer.xml" })*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/java/user/spring-mybatis.xml"})
public class LoginTest extends AbstractJUnit4SpringContextTests {
    @Resource
    private CaptchaService captchaService;

    @Resource
    private UserAgwService userAgwService;
    @Resource
    private NodeAgwService nas;

    @Resource
    private CommParamInfoService commParamInfoService;

	private List<ParamValue> valueList;

	private ParamValue paramValue;


    @Test
    public void testCaptcha() {
        CaptchaTemplate captchaTemplate = new CaptchaTemplate();
        captchaTemplate.setBgColor("#111111");
        captchaTemplate.setBorderColor("#ffffff");
        captchaTemplate.setFont("Californian FB");

        captchaTemplate.setFontHeight(28);

        captchaTemplate.setInterferingMax(6);
        captchaTemplate.setInterferingMin(0);
        captchaTemplate.setWidth(120);
        captchaTemplate.setHeight(30);

        captchaService.genCaptchaImg(4, captchaTemplate, 50);

        System.out.println("OK");

    }

    @Test
    public void testListUser() {

//        userAgwService.login("192.168.1.1", 0, "18121008989", "123456");

        ListUserDTO listUserDTO = userAgwService.getAllUser(1001,1);

        System.out.println("OK");

    }


    @Test
    public void testLogin() {

//        userAgwService.login("192.168.1.1", 0, "18121008989", "123456");

        System.out.println("OK");

    }

    @Test
    public void testUser() {

//        userAgwService.login("192.168.1.1", 0, "18121008989", "123456");

        UserDTO userDto = userAgwService.getLoginUser(1001, 1);

        System.out.println("OK");

    }

    @Test
    public void testValue() {

//        userAgwService.login("192.168.1.1", 0, "18121008989", "123456");

        valueList = CommonUtil.getValueList(commParamInfoService, "bustype", ValueListDefine.class, "05", true);
        

        paramValue = CommonUtil.getParamValue(commParamInfoService, ValueListDefine.class, "p_fmttype", "p_fmttype_01");
        List<ParamValueDTO> vlList = userAgwService.getParamList("gender");

        vlList = Utils.buildAgwParamList("bustype", ValueListDefine.class, commParamInfoService, 1, true);

        System.out.println("OK");

    }

    @Test
    public void testNAS() {

        userAgwService.getParamList("datetype");
        System.out.println("d");
    }
}
