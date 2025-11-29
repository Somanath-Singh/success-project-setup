package com.aashdit.prod.heads.hims.ipms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.aashdit.prod.heads.framework.core.ServiceOutcome;
//import com.aashdit.prod.heads.framework.core.ServiceOutcome;
import com.aashdit.prod.heads.hims.ipms.dto.SliderImageVO;
import com.aashdit.prod.heads.hims.ipms.dto.UserVO;
import com.aashdit.prod.heads.hims.ipms.dto.WebsiteChildContentVO;
import com.aashdit.prod.heads.hims.ipms.model.WebMenu;
import com.aashdit.prod.heads.hims.ipms.model.WebsiteChildContent;
import com.aashdit.prod.heads.hims.ipms.model.WebsiteSliderImage;
import com.aashdit.prod.heads.hims.ipms.repository.WebMenuRepository;
import com.aashdit.prod.heads.hims.ipms.repository.WebsiteChildContentRepository;
import com.aashdit.prod.heads.hims.ipms.repository.WebsiteContentTypeRepository;
import com.aashdit.prod.heads.hims.ipms.repository.WebsiteSliderImageRepository;
import com.aashdit.prod.heads.hims.ipms.utils.ApplicationConstants;
import com.aashdit.prod.heads.hims.ipms.utils.CommonUploadFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebsiteServiceImpl implements WebsiteService{
	private final WebsiteChildContentRepository websiteChildContentRepository;
	private final WebsiteContentTypeRepository websiteContentTypeRepository;
	private final MessageSource messageSource ;
	private final WebMenuRepository webMenuRepository;
	private final WebsiteSliderImageRepository websiteSliderImageRepository;
	final ResourceBundle rb = ResourceBundle.getBundle("application");
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public ServiceOutcome<WebsiteChildContentVO> getAllChildContentData(String loginFrom) {
		ServiceOutcome<WebsiteChildContentVO> srvc = new ServiceOutcome<>();
		log.info("method call");
		try {
			List<WebsiteChildContent> content = new ArrayList<>();
			Long unvId = SecurityHelper.getCurrentUser().getEntityId();
			WebsiteChildContentVO cntVo = new WebsiteChildContentVO();
			cntVo.setWebContentList(websiteContentTypeRepository.findAllByIsActiveTrue());
			cntVo.setMenuList(webMenuRepository.findAllByIsActiveTrue());
			if(loginFrom!= null && loginFrom.equals(ApplicationConstants.LOGIN_FROM_CANDIDATE)){
				 content = websiteChildContentRepository.findAllByIsActiveTrueAndObjectIdPublishDateAndExpiryDateLessThanCurrentDate(0L);
			}else if (loginFrom!= null && loginFrom.equals(ApplicationConstants.LOGIN_FROM_UNIVERSITY)){
				 content = websiteChildContentRepository.findAllByIsActiveTrueAndObjectId1(unvId != null? unvId : 0L);
			}
			cntVo.setChildContentList(content);
			srvc.setData(cntVo);
		} catch (Exception e) {
			log.error("Getting error at getAllChildContentData",e.getMessage());
		}
		return srvc;
	}

	@Override
	public ServiceOutcome<WebsiteChildContentVO> getContentDataForWebsite(Long unvId) {
		ServiceOutcome<WebsiteChildContentVO> srvc = new ServiceOutcome<>();
		log.info("method call");
		try {
			WebsiteChildContentVO cntVo = new WebsiteChildContentVO();

			cntVo.setWebContentList(websiteContentTypeRepository.findAllByIsActiveTrue());
			cntVo.setMenuList(webMenuRepository.findAllByIsActiveTrue());
			List<WebsiteChildContent> content = websiteChildContentRepository.findAllByIsActiveTrueAndObjectIdPublishDateAndExpiryDateLessThanCurrentDate(unvId != null? unvId : 0L);
			cntVo.setChildContentList(content);

			srvc.setData(cntVo);
		} catch (Exception e) {
			log.error("Getting error at getAllChildContentData",e.getMessage());
		}
		return srvc;
	}



	@Transactional
	@Override
	public ServiceOutcome<WebsiteChildContent> saveChildContent(WebsiteChildContentVO contentVo) {
		ServiceOutcome<WebsiteChildContent>  srvc = new ServiceOutcome<>();
		try {
		UserVO user =SecurityHelper.getCurrentUser();
			WebsiteChildContent content  = Optional.ofNullable(contentVo.getWebChildContent().getContentId()).isPresent() ? websiteChildContentRepository.findByContentId(contentVo.getWebChildContent().getContentId()) : new WebsiteChildContent();

			content.setContentNameEn(contentVo.getWebChildContent().getContentNameEn().trim());
			content.setContentNameOd(contentVo.getWebChildContent().getContentNameEn().trim());
			content.setChildContentNameEn(contentVo.getWebChildContent().getChildContentNameEn().trim());
			content.setChildContentNameOd(contentVo.getWebChildContent().getChildContentNameEn().trim());
			content.setMenuId(webMenuRepository.findByMenuId(contentVo.getWebChildContent().getMenuId().getMenuId()));
			 if (contentVo.getWebChildContent().getPublishDate() != null) {
		            content.setPublishDate(contentVo.getWebChildContent().getPublishDate());
		            String pub = sdf.format(contentVo.getWebChildContent().getPublishDate());
		            content.setPublishDate(sdf.parse(pub));  
		        } else {
		            content.setPublishDate(null); 
		        }
			 if(contentVo.getWebChildContent().getExpireDate()!= null) {
				 content.setExpireDate(contentVo.getWebChildContent().getExpireDate());
				 String exp =sdf.format(contentVo.getWebChildContent().getExpireDate());
				 content.setExpireDate(sdf.parse(exp));
			 }else {
		            content.setExpireDate(null);  
		        }
			content.setDocName(contentVo.getWebChildContent().getDocName());
			content.setContenTypeId(websiteContentTypeRepository.findByContentTypeId(contentVo.getWebChildContent().getContenTypeId().getContentTypeId()));

			//content.setCreatedBy(user.getUserId());
			content.setCreatedOn(new Date());
			if(contentVo.getWebChildContent().getContentId() != null) {
				content.setUpdatedOn(new Date());
				//content.setUpdatedBy(user.getUserId());
			}
			content.setObjectId(user.getEntityId());
			 if (contentVo.getWebChildContent().getDocWeb()!= null && !contentVo.getWebChildContent().getDocWeb().isEmpty())
			 {
					String imageName = FilenameUtils.getBaseName(contentVo.getWebChildContent().getDocWeb().getOriginalFilename());
					content.setDocName(imageName);
					content.setDocPath(CommonUploadFile.uploadDocumentCommon(contentVo.getWebChildContent().getDocWeb(),rb.getString("UPLOAD.FILE.PATH"), "CHILD_CONTENT", imageName));
			 }
			 content = websiteChildContentRepository.save(content);
		srvc.setData(content);
		srvc.setMessage(messageSource.getMessage(contentVo.getWebChildContent().getContentId() != null ? "msg.success.cntUpdt" : "msg.success.cntAdd",null, LocaleContextHolder.getLocale()));

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Getting error at saveChildContent",e.getMessage());
		}
		return srvc;
	}
@Override
public ServiceOutcome<WebsiteChildContentVO> fetchnUpdateFarmerDtls(Long contentId,String checkCall) {
	ServiceOutcome<WebsiteChildContentVO>  svc= new ServiceOutcome<>();
	try {
		if(Optional.ofNullable(contentId).isPresent()) {
			WebsiteChildContentVO webVo = new WebsiteChildContentVO();
			webVo.setCheckCall(checkCall);
			webVo.setWebChildContent(websiteChildContentRepository.findByContentId(contentId));
			svc.setData(webVo);
	}

	} catch (Exception e) {
		e.printStackTrace();
		log.error("Getting error at fetchnUpdateFarmerDtls",e.getMessage());
	}
	return svc;
}
@Override
public ServiceOutcome<SliderImageVO> getAllSliderImgData() {
	ServiceOutcome<SliderImageVO> svc= new ServiceOutcome<>();
	try {
		SliderImageVO imgVo = new SliderImageVO();
		List<WebsiteSliderImage> sliderImg = websiteSliderImageRepository.findAllByIsActiveTrue();
		imgVo.setSliderImgList(sliderImg);
		svc.setData(imgVo);
	} catch (Exception e) {
		log.error("Getting error at getAllSliderImgData",e.getMessage());
		}
	return svc;
}
@Override
public ServiceOutcome<WebsiteSliderImage> saveSliderImgData(SliderImageVO imgVo) {
	ServiceOutcome<WebsiteSliderImage> srvc = new ServiceOutcome<>();
	try {
		UserVO user  = SecurityHelper.getCurrentUser();
		WebsiteSliderImage sliderImg  = Optional.ofNullable(imgVo.getSliderImg().getWebSliderId()).isPresent()  ? websiteSliderImageRepository.findByWebSliderId(imgVo.getSliderImg().getWebSliderId()) : new WebsiteSliderImage();
		sliderImg.setTitleEn(imgVo.getSliderImg().getTitleEn());
		sliderImg.setTitleInOdia(imgVo.getSliderImg().getTitleEn());
		sliderImg.setHyperLink(imgVo.getSliderImg().getHyperLink());
		sliderImg.setImageName(imgVo.getSliderImg().getImageName());
		 if (imgVo.getSliderImg().getImageWeb()!= null && !imgVo.getSliderImg().getImageWeb().isEmpty())
		 {
			String imageName = FilenameUtils.getBaseName(imgVo.getSliderImg().getImageWeb().getOriginalFilename());
			sliderImg.setImageName(imageName);
			sliderImg.setImagePath(CommonUploadFile.uploadDocumentCommon(imgVo.getSliderImg().getImageWeb(),rb.getString("UPLOAD.FILE.PATH"), "SLIDER-IMG", imageName));
		 }
		 //sliderImg.setCreatedBy(user.getUserId());
		 sliderImg.setCreatedOn(new Date());
		// sliderImg.setLastUpdatedBy(user.getUserId());
		 websiteSliderImageRepository.save(sliderImg);
		srvc.setMessage(messageSource.getMessage(imgVo.getSliderImg().getWebSliderId() != null ? "msg.success.sldImgUpdt" : "msg.success.sldImgAdd",null, LocaleContextHolder.getLocale()));

	} catch (Exception e) {
		e.printStackTrace();
		log.error("Fetch error at saveSliderImgData - serviceImpl",e.getMessage());
	}
	return srvc;
}
@Override
public ServiceOutcome<SliderImageVO> fetchSliderImage(Long webSliderId, String string) {
	 ServiceOutcome<SliderImageVO> svc = new ServiceOutcome<>();
	 try {
		 if(Optional.ofNullable(webSliderId).isPresent()) {
			 SliderImageVO imgVo = new SliderImageVO();
			 imgVo.setSliderImg(websiteSliderImageRepository.findByWebSliderId(webSliderId));
			svc.setData(imgVo);
		 }

	} catch (Exception e) {
		log.error("Fetching error at fetchSliderImage -> controller",e.getMessage());
	}
	return svc;
}

@Override
@Cacheable(value = "mapList", key = "'homePage'")
public Map<String, List<WebsiteChildContent>> getHomePageData(Long unvId) {
	try {
		log.info("Fetching data from the service...");
		ServiceOutcome<WebsiteChildContentVO> content = getContentDataForWebsite(unvId);
		if (content.getData() != null) {
			content.getData().getChildContentList().forEach(a -> {
				if (a.getContentId() != null) {
					System.out.println(a.getContentId() + " " + a.getMenuId().getMenuTextEN());
				}
			});
			return content.getData().getChildContentList()
					.stream()
//					.filter(a ->a.getObjectId().equals(unvId))
					.collect(Collectors.groupingBy(a -> a.getMenuId().getMenuTextEN()
							.concat("#")
							.concat(a.getMenuId().getMenuTextEN().toUpperCase().replaceAll(" ", ""))));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

	

@Override
@Cacheable(value = "sliderImgList", key = "'sliderImage'")
public List<WebsiteSliderImage> getAllSliderImglist() {
	log.info("Fetching data from the service...");
	try {
	List<WebsiteSliderImage> sliderImages= this.getAllSliderImgData().getData().getSliderImgList();
	return sliderImages;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}

//	@Override
//	public Map<String, List<WebsiteChildContent>> getAllChildContentDataByObjectIdandMenuId(Long unvId) {
//		Map<String, List<WebsiteChildContent>> mapList = new HashMap<>();
//		try{
//			List<WebsiteChildContent> content = websiteChildContentRepository.findAllByIsActiveTrueAndObjectIdPublishDateGreaterThanCurrentDate(unvId != null? unvId : 0L);
//			List<WebMenu> menuList = webMenuRepository.findAllByIsActiveTrue();
//
//			//group by menu id and filter by unvid
//			mapList = content
//					.stream()
//					.collect(Collectors.groupingBy(a -> a.getMenuId().getMenuTextEN()
//							.concat("#")
//							.concat(a.getMenuId().getMenuTextEN().toUpperCase().replaceAll(" ", ""))));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return mapList;
//	}

	@Override
	public Map<String, List<WebsiteChildContent>> getAllChildContentDataByObjectIdandMenuId(Long unvId) {
		Map<String, List<WebsiteChildContent>> mapList = new HashMap<>();
		try {
			// Fetch all menus
			List<WebMenu> menuList = webMenuRepository.findAllByIsActiveTrue();

			// Fetch all website child content for given unvId
			List<WebsiteChildContent> content = websiteChildContentRepository.findAllByIsActiveTrueAndObjectIdPublishDateAndExpiryDateLessThanCurrentDate(
					unvId != null ? unvId : 0L);

			// Group content by menu name
			Map<String, List<WebsiteChildContent>> groupedContent = content
					.stream()
					.collect(Collectors.groupingBy(a -> a.getMenuId().getMenuTextEN()
							.concat("#")
							.concat(a.getMenuId().getMenuTextEN().toUpperCase().replaceAll(" ", ""))));

			// Ensure all menus exist in the result, even if there's no content
			for (WebMenu menu : menuList) {
				String key = menu.getMenuTextEN() + "#" + menu.getMenuTextEN().toUpperCase().replaceAll(" ", "");
				mapList.put(key, groupedContent.getOrDefault(key, new ArrayList<>()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapList;
	}

}
