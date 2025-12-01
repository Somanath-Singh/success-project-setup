package com.aashdit.prod.heads.hims.ipms.service;
import java.util.List;
import java.util.Map;

import com.aashdit.prod.heads.framework.core.ServiceOutcome;
import com.aashdit.prod.heads.hims.ipms.dto.*;
import com.aashdit.prod.heads.hims.ipms.model.*;


public interface WebsiteService  {

	ServiceOutcome<WebsiteChildContentVO> getAllChildContentData(String loginFrom);

	ServiceOutcome<WebsiteChildContent> saveChildContent(WebsiteChildContentVO contentVo);

	ServiceOutcome<WebsiteChildContentVO> fetchnUpdateFarmerDtls(Long contentId,String checkCall);

	ServiceOutcome<SliderImageVO> getAllSliderImgData();

	ServiceOutcome<WebsiteSliderImage> saveSliderImgData(SliderImageVO imgVo);

	ServiceOutcome<SliderImageVO> fetchSliderImage(Long webSliderId, String string);

	Map<String, List<WebsiteChildContent>> getHomePageData(Long unvId);

	List<WebsiteSliderImage> getAllSliderImglist();

	ServiceOutcome<WebsiteChildContentVO> getContentDataForWebsite(Long unvId);

	Map<String, List<WebsiteChildContent>> getAllChildContentDataByObjectIdandMenuId(Long unvId);


}
