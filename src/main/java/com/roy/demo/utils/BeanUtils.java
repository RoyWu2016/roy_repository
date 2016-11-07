package com.roy.demo.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.pingan.interfaces.api.dataservice.vo.shopinfo.ShopInfoVO;
import com.pingan.interfaces.api.dataservice.vo.systemmenu.SystemMenuVO;
import com.pingan.interfaces.api.dataservice.vo.systemrole.SystemRoleVO;
import com.pingan.interfaces.api.dataservice.vo.systemuser.SystemUserVO;
import com.pingan.service.server.domain.SystemRole;
import com.pingan.service.server.domain.SystemUser;
import com.pingan.service.server.service.SystemMenuService;

/**
 * ClassName: BeanUtils
 * 
 * @description
 * @author init_code
 * @Date Mar 31, 2014
 * 
 */
public class BeanUtils {
	private final static Logger log = Logger.getLogger(BeanUtils.class);

	public static void copyProperties(Object source, Object target) {
		copyProperties(source, target, null);
	}

	public static void copyProperties(Object source, Object target, String[] ignoreProperties) {
		if (ignoreProperties == null) {
			ignoreProperties = new String[0];
		}
		if(target != null && target instanceof ShopInfoVO && ignoreProperties.length == 0) {
			ignoreProperties = new String[] { "provinceCode", "cityCode", "districtCode", "businessCode", "marketCode" };
		}
		String[] ignore = new String[ignoreProperties.length + 2];
		for (int i = 0; i < ignoreProperties.length; i++) {
			ignore[i] = ignoreProperties[i];
		}
		ignore[ignore.length - 2] = "createUser";
		ignore[ignore.length - 1] = "updateUser";
		org.springframework.beans.BeanUtils.copyProperties(source, target, ignore);
		try {
			Method method = null;
			try {
				method = source.getClass().getMethod("getCreateUser", new Class[0]);
			} catch (NoSuchMethodException e1) {
			}
			if (method != null) {
				Object userObject = method.invoke(source, new Object[0]);
				if (userObject != null) {
					method = userObject.getClass().getMethod("getId", new Class[0]);
					Object userId = method.invoke(userObject, new Object[0]);

					method = target.getClass().getMethod("setCreateUser", new Class[] { Long.class });
					method.invoke(target, new Object[] { userId });
				}
			}

			try {
				method = source.getClass().getMethod("getUpdateUser", new Class[0]);
			} catch (NoSuchMethodException e1) {
			}
			if (method != null) {
				Object userObject = method.invoke(source, new Object[0]);
				if (userObject != null) {
					method = userObject.getClass().getMethod("getId", new Class[0]);
					Object userId = method.invoke(userObject, new Object[0]);

					method = target.getClass().getMethod("setUpdateUser", new Class[] { Long.class });
					method.invoke(target, new Object[] { userId });
				}
			}

		} catch (Exception e) {
			log.error("Copy data error, check log.", e);
		}
	}

	public static void copySaveProperties(Object source, Object target) {
		copySaveProperties(source, target, null);
	}

	public static void copySaveProperties(Object source, Object target, String[] ignoreProperties) {

		String[] ignore = new String[2];
		if (ignoreProperties != null) {
			ignore = new String[ignoreProperties.length + 2];
			for (int i = 0; i < ignoreProperties.length; i++) {
				String string = ignoreProperties[i];
				ignore[i] = string;
			}
			ignore[ignoreProperties.length] = "createUser";
			ignore[ignoreProperties.length + 1] = "updateUser";
		} else {
			ignore[0] = "createUser";
			ignore[1] = "updateUser";
		}
		org.springframework.beans.BeanUtils.copyProperties(source, target, ignore);
	}

	public static void copySystemUser(SystemUser po, SystemUserVO itemVO, SystemMenuService systemMenuService) {
		copyProperties(po, itemVO, new String[]{"systemRole"});
		SystemRole role = po.getSystemRole();
		if(role != null) {
			SystemRoleVO roleVO = new SystemRoleVO();
			copySystemRole(role, roleVO, systemMenuService);
			itemVO.setRoleId(roleVO.getId());
			itemVO.setRoleName(roleVO.getRoleName());
			itemVO.setSystemRole(roleVO);
		}
	}

	public static void copySystemRole(SystemRole role, SystemRoleVO roleVO, SystemMenuService systemMenuService) {
		copyProperties(role, roleVO);
		List<SystemMenuVO> dbVOList = systemMenuService.findAllSystemMenusByRoleId(role.getId());

		roleVO.setMenuList(indexMenu(dbVOList));
	}

	public static List<SystemMenuVO> indexMenu(List<SystemMenuVO> dbVOList) {
		List<SystemMenuVO> menuList = new ArrayList<SystemMenuVO>();
		for (SystemMenuVO menuLevel1 : dbVOList) {
			// sub menu has been add by copyMenuInfo
			if (menuLevel1.getMenuLevel() == 1) {
				for (SystemMenuVO menuLevel2 : dbVOList) {
					if (menuLevel2.getMenuLevel() == 2) {
						// log.debug(String.format("l2.parent=[%s], l1.id=[%s] => [%s], [%s]",
						// menuLevel2.getParentId(),
						// menuLevel1.getId(), menuLevel2.getParentId() ==
						// menuLevel1.getId(), menuLevel2
						// .getParentId().equals(menuLevel1.getId())));

						if (menuLevel2.getParentId().equals(menuLevel1.getId())) {
							menuLevel1.getChildren().add(menuLevel2);
							for (SystemMenuVO menuLevel3 : dbVOList) {
								// just support level 3
								if (menuLevel3.getMenuLevel() == 3) {
									if (menuLevel3.getParentId().equals(menuLevel2.getId())) {
										menuLevel2.getChildren().add(menuLevel3);
									}
								}
							}
						}
					}
				}
				menuList.add(menuLevel1);
			}
		}
		return menuList;
	}

	// public static void copySystemMenu(SystemMenu po, SystemMenuVO menuVO) {
	// copyProperties(po, menuVO, new String[] { "children" });
	// // default just 2 level menu
	// if (po.getMenuLevel() <= 2) {
	// if (po.getChildren() != null && po.getChildren().size() > 0) {
	// for (SystemMenu subMenu : po.getChildren()) {
	// SystemMenuVO subMenuVO = new SystemMenuVO();
	// copySystemMenu(subMenu, subMenuVO);
	// menuVO.getChildren().add(subMenuVO);
	// }
	// }
	// }
	// }
}
