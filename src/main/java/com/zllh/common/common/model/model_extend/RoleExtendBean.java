package com.zllh.common.common.model.model_extend;

import java.util.List;

import com.zllh.common.common.model.PubResource;
import com.zllh.common.common.model.PubRole;

public class RoleExtendBean extends PubRole{
	
    private List<PubResource> resources;
    
    public List<PubResource> getResources() {
		return resources;
	}

	public void setResources(List<PubResource> resources) {
		this.resources = resources;
	}
}