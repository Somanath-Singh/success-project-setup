package com.aashdit.setup.dto;

import java.util.List;
import java.util.Locale;

import javax.persistence.Transient;

import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class MenuDto {
	
	private Long menuId;

	private String menuTextEN;

	private String menuTextHI;

	@Transient
	private String menuText;

	private String menuIcon;

	private String menuURL;

	private MenuDto parent;

	private List<MenuDto> children;

	private Integer displayOrder;

	private Boolean isActive;

	@Transient
	public Boolean isParent;

	private Boolean isDisplay;

	private Boolean isSystemConfigEntry;

	private Boolean isModule;

	/* V 1.0.1 */

	private String appCode;

	public String getMenuText() {
		Locale locale = LocaleContextHolder.getLocale();
		switch (locale.getLanguage()) {
		case "en":
			return this.getMenuTextEN();
		case "hi":
			return this.getMenuTextHI();
		default:
			return this.getMenuTextEN();
		}
	}

	public Boolean getIsParent() {
		// Any Menu that has children or is a module or is a submodule. Submodule URLs
		// are always #
		return ((this.children != null && this.children.size() > 0) || this.isModule || this.menuURL.equals("#"));
	}

}
