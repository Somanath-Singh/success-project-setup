package com.aashdit.prod.heads.hims.ipms.dto;

import java.util.List;

import com.aashdit.prod.heads.hims.ipms.model.*;

import lombok.Data;

@Data
public class WebsiteChildContentVO {

	private List<WebsiteChildContent> childContentList;

	private List<WebsiteContentType> webContentList;

	private List<WebMenu> menuList;

	private WebsiteChildContent webChildContent;

	private String checkCall;
}
