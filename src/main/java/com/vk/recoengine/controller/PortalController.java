package com.vk.recoengine.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vk.recoengine.entities.Portals;
import com.vk.recoengine.entities.SkuSettings;
import com.vk.recoengine.service.PortalService;

@Path("/portal")
@Component
@Scope("request")
public class PortalController {

	@Autowired
	private PortalService portalSerive;
	
	@GET()
	@Produces(MediaType.APPLICATION_JSON)
	public SkuSettings getDevice(){
		Portals portals=portalSerive.getPortal();
		SkuSettings sku=portalSerive.getSKU();
		System.out.println("##################"+sku.getCategory().getCommissions().size());
		//System.out.println("#########################"+portals.getSdPortal().getProductCategories().iterator().next().getSkus().iterator().next().getSku());
		//System.out.println("#########################"+portals.getSdPortal().getProductCategories().iterator().next().getCommissions().size());
	
		
		return sku;
	}
	
}
