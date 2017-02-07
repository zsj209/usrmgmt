package com.hrocloud.usrmgmt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hrocloud.common.api.CommParamInfoService;
import com.hrocloud.common.api.model.ParamValue;
import com.hrocloud.usrmgmt.api.UserAgwUtils;
import com.hrocloud.usrmgmt.constant.ValueListDefine;
import com.hrocloud.usrmgmt.dto.ParamValueDTO;
import com.hrocloud.util.CommonUtil;

@Service("userAgwUtilsImpl")
public class UserAgwUtilsImpl implements UserAgwUtils {

	@Resource
	private CommParamInfoService commParamInfoService;

	public List<ParamValueDTO> getNodeType(String userType) {
		List<ParamValueDTO> list = new ArrayList<ParamValueDTO>();
		List<ParamValue> valueList = CommonUtil.getValueList(commParamInfoService, "bustype", null, "00", true);

		if (userType != null && "0root".equals(userType)) {
			// new List<ParamValue>();
			for (int i = 0; i < 3; i++) {
				list.add(new ParamValueDTO(valueList.get(i)));
			}
		} else if (userType != null && "2hroadmin".equals(userType)) {
			list.add(new ParamValueDTO(valueList.get(3)));
		} else {
			for (int i = 0; i < valueList.size(); i++) {
				list.add(new ParamValueDTO(valueList.get(i)));
			}
		}
		return list;
	}

/*	public List<List<ParamValueDTO>> getSelect(String typeCodes) {
		
		ArrayList<String> listCode = new ArrayList<String>();
		ArrayList<List<ParamValueDTO>> listSelect = new ArrayList<List<ParamValueDTO>>();
		String idstr[] = typeCodes.split(",");
		Collections.addAll(listCode, idstr);
		for(int i=0;i<listCode.size();i++){
			List<ParamValueDTO> list = new ArrayList<ParamValueDTO>();
			List<ParamValue> valueList = CommonUtil.getValueList(commParamInfoService, listCode.get(i), ValueListDefine.class, "00", true);
			for (int ii = 0; ii < valueList.size(); ii++) {
				list.add(new ParamValueDTO(valueList.get(ii)));
			}
			listSelect.add(list);
		}
		return listSelect;
	}*/

}
