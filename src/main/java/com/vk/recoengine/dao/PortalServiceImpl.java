package com.vk.recoengine.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vk.recoengine.entities.Portals;
import com.vk.recoengine.entities.SkuSettings;
import com.vk.recoengine.service.PortalService;

@Service("portalService")
@Transactional
public class PortalServiceImpl extends BaseServiceImpl implements PortalService{

	public Portals getPortal() {
		Portals portal=entityManager.find(Portals.class, 1);
		return portal;
	}

	public SkuSettings getSKU() {
		SkuSettings sku=entityManager.find(SkuSettings.class, 1);
		return sku;
	}

}
