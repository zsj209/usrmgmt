/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.hrocloud.usrmgmt.service;

import com.hrocloud.apigw.client.dubboext.DubboExtProperty;
import com.hrocloud.common.api.CommParamInfoService;
import com.hrocloud.common.exception.BusinessException;
import com.hrocloud.common.page.PageParameter;
import com.hrocloud.company.api.PostService;
import com.hrocloud.company.model.Post;
import com.hrocloud.usrmgmt.api.NodeService;
import com.hrocloud.usrmgmt.api.RoleService;
import com.hrocloud.usrmgmt.api.UserService;
import com.hrocloud.usrmgmt.constant.ValueListDefine;
import com.hrocloud.usrmgmt.dao.UserInfoMapper;
import com.hrocloud.usrmgmt.dto.Menu;
import com.hrocloud.usrmgmt.dto.ParamValueDTO;
import com.hrocloud.usrmgmt.dto.UserPermissionDTO;
import com.hrocloud.usrmgmt.dto.UserPermissionDetailDTO;
import com.hrocloud.usrmgmt.exception.UserServiceHttpCode;
import com.hrocloud.usrmgmt.model.RoleInfo;
import com.hrocloud.usrmgmt.model.UserInfo;
import com.hrocloud.usrmgmt.utils.Utils;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;

/**
 * Created by zou_s on 2016/12/28.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	 private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserInfoMapper userDao;
    
    @Resource
	private PostService postservice;
    
    @Resource
	private RoleService roleservice;
    
    @Resource
	private NodeService nodeservice;
    
    @Resource
    private CommParamInfoService commParamInfoService;

    public UserInfo getUser(String loginCode) {

        return userDao.getUser(loginCode);
    }

    public UserInfo getUserById(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    public int update(UserInfo userInfo) {
        return userDao.updateByPrimaryKeySelective(userInfo);
    }

    public int insert(UserInfo userInfo) {
        return userDao.insertSelective(userInfo);
    }

    public UserInfo getById(Integer i) {
        return userDao.selectByPrimaryKey(i);
    }

    public int deleteById(Integer id) {
        return userDao.deleteByPrimaryKey(id);
    }

    public List<UserInfo> getUserList(HashMap<String, String> dataMap, PageParameter pageInfo) {
        //return userDao.getAllUserPage(dataMap.get("nameOrMobile"),dataMap.get("status"), pageInfo);
        return userDao.getAllUserPage(dataMap, pageInfo);
    }


    public boolean addOrModifyUser(String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
        UserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(data, UserInfo.class);
            userInfo.setLoginName(userInfo.getMobileNo());
            //设置默认密码
            userInfo.setPassword("c33367701511b4f6020ec61ded352059");
        } catch (IOException e) {
            e.printStackTrace();
            DubboExtProperty.setErrorCode(UserServiceHttpCode.PARAMETER_ERROR);
        }
        if (userInfo != null) {
            if (userInfo.getId() != null) {// 节点id不为空,修改
                userInfo.setUpdateTime(new Date());
                int modifySelective = userDao.updateByPrimaryKeySelective(userInfo);
                if (modifySelective == 1) {
                    return true;
                } else {
                    DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_MODIFYINFO_ERROR);
                }
            } else {// 节点id为空，新增
                userInfo.setCreateTime(new Date());
                userInfo.setUpdateTime(new Date());
                int insertSelective = userDao.insertSelective(userInfo);
                if (insertSelective == 1) {
                    return true;
                } else {
//					 DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_ADDINFO_ERROR);
                    throw new BusinessException(UserServiceHttpCode.USER_ADDINFO_ERROR.getCode(), UserServiceHttpCode.USER_ADDINFO_ERROR.getDesc());
                }
            }
        }

        return false;
    }

    public List<UserInfo> queryUser(String expr) {
        return userDao.queryUser(expr);
    }

    public boolean deleteUser(String ids) {
        try {
            ArrayList<String> list = new ArrayList<String>();
            String idstr[] = ids.split(",");
            Collections.addAll(list, idstr);
            int deleteUser = userDao.deleteUser(list);
            return true;
        } catch (Exception e) {
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_DELINFO_ERROR);
        }
        return false;
    }

    public boolean modifyStatus(String ids, String status) {
        try {
            ArrayList<String> list = new ArrayList<String>();
            String idstr[] = ids.split(",");
            Collections.addAll(list, idstr);
            int modifyStatus = userDao.modifyStatus(list, status);
            if (modifyStatus == list.size()) {
                return true;
            }
        } catch (Exception e) {
            DubboExtProperty.setErrorCode(UserServiceHttpCode.USER_MODIFYINFO_ERROR);
        }
        return false;
    }

	public UserPermissionDTO getUserPerm(int userId, String roletype)
			throws BusinessException {
		try {
			logger.info("------------------>getUserPerm");
			List<Post> list_posits = postservice.getUserPositions(userId);
			if (list_posits == null) {
				return null;
			} else {
				if (list_posits.size() == 0)
					return null;
				UserPermissionDTO uspmdto = new UserPermissionDTO();
				uspmdto.userId = userId;

				List<ParamValueDTO> roletypeList = Utils.buildAgwParamList(
						"bustype", ValueListDefine.class, commParamInfoService,
						"", true);

				for (Post pos : list_posits) {
					UserPermissionDetailDTO uspmdt = new UserPermissionDetailDTO();
					uspmdt.positionId = pos.getId();
					uspmdt.companyId = pos.getCompanyId();
					uspmdt.roleId = pos.getPostRole();
					boolean addrole = true;
					if (uspmdt.roleId > 0) {
						logger.info("position role id:" + uspmdt.roleId);
						RoleInfo role = roleservice.getRoleById(uspmdt.roleId);
						if (role != null
								&& role.getEnabled()
										.equalsIgnoreCase("02valid")) {
							uspmdt.roleType = role.getType();
							if (uspmdt.roleType.equalsIgnoreCase("0root")
									|| uspmdt.roleType
											.equalsIgnoreCase("1admin")) {
								uspmdt.companyId = 1;
							}
							if (roletype != null
									&& !StringUtils.isBlank(roletype)) {
								if (!uspmdt.roleType.equalsIgnoreCase(roletype)) {
									addrole = false;
								}
							}
						} else {
							addrole = false;
						}
					} else {
						addrole = false;
					}
					if (addrole) {
						// 有效的角色
						for (ParamValueDTO pvdto : roletypeList) {
							if (pvdto.code.equalsIgnoreCase(uspmdt.roleType)) {
								uspmdt.roleTypeDesc = pvdto.desc;
								break;
							}
						}
						uspmdto.permDlist.add(uspmdt);
					}
				}
				if (uspmdto.permDlist.size() > 0) {
					// 排序
					Collections.sort(uspmdto.permDlist,
							new Comparator<UserPermissionDetailDTO>() {
								public int compare(
										UserPermissionDetailDTO arg0,
										UserPermissionDetailDTO arg1) {
									return arg0.roleType
											.compareToIgnoreCase(arg1.roleType);
								}
							});

					return uspmdto;
				} else
					return null;
			}
		} catch (BusinessException e) {
			throw e;
		}
	}

	public int modifyFinalRoleType(int userId, String roleType) {
		return userDao.modifyFinalRoleType(userId, roleType);
	}
	
	public List<Menu> getUserMenu(int userId, String roleType)
			throws BusinessException {
		logger.info("------------------>getUserMenu");
		UserPermissionDTO uspmdto = getUserPerm(userId, roleType);
		if (uspmdto != null) {
			List<Integer> roleids = new ArrayList<Integer>();
			for (UserPermissionDetailDTO uspd : uspmdto.permDlist) {
				roleids.add(uspd.roleId);
			}
			List<Menu> nodelist = nodeservice.getMenuByRoleIds(roleids);
			if (nodelist.size() == 0)
				return null;
			else
				return nodelist;
		} else
			return null;
	}
}
