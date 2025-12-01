package com.aashdit.prod.heads.hims.umt.misc;

import com.aashdit.prod.heads.hims.umt.model.Menu;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MenuSpecs {

	public static Specification<Menu> isNotModuleOrSystemEntry() {
		return new Specification<Menu>() {
			/**
			* 
			*/
			private static final long serialVersionUID = 7036916678419234664L;

			@Override
			public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate pr = cb.and(cb.notEqual(root.get("isModule"), true),
						cb.notEqual(root.get("isSystemConfigEntry"), true));
				return pr;
			}
		};
	}
}
