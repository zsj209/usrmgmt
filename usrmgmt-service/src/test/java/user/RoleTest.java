/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016All Rights Reserved.
 */
package user;


import java.util.ArrayList;
import java.util.HashMap;

import com.hrocloud.usrmgmt.api.RoleAgwService;
import com.hrocloud.usrmgmt.dao.RoleInfoMapper;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.dto.RoleDTO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/*@ContextConfiguration(locations = { "/com/spring-mvc.xml",
        "/com/dubbo-demo-consumer.xml" })*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/java/user/spring-mybatis.xml"})
public class RoleTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private RoleAgwService roleService;
    @Resource
    private RoleInfoMapper rif;
   
    @Test
    public void testAddOrModifyRole() {
    	boolean role = roleService.addOrModifyRole(2, "{\"roleName\":\"测试节点\",\"companyId\":100,\"type\":\"0root\",\"enabled\":1}");
    	System.out.println(role);
    }
    @Test
    public void testGetRoleList() {
    	/*PageDTO list = roleService.getRoleList("管理员", "02valid", "0root,1admin,2hroadmin", 20, 1);*/
    	/*ArrayList<String> list = new ArrayList<String>();
    	list.add("0root");
    	HashMap<String,String> hashMap = new HashMap<String, String>();
    	hashMap.put("roleName", "管理");
    	hashMap.put("enabled", "02valid");
    	rif.getAllRole(hashMap, list);*/
    	roleService.getRoleList("管理员", "02valid","1", "true", 20, 1);
    }
    @Test
    public void testDeleteRole() {
    	
    	boolean role = roleService.deleteRole("1009");
    	System.out.println(role);
    	
    }
    @Test
    public void testGetRoleById() {
    	
    	RoleDTO roleDTO = roleService.getRoleById(1010);
    	System.out.println(roleDTO);
    }

   
}
