package com.project.groovy.service;

import java.util.List;

import com.project.groovy.model.Promotion;

public interface PromotionService {

	int remove(Integer num) throws Exception;

	List<Promotion> selectAll() throws Exception;

	Promotion select(Integer num) throws Exception;

	Promotion selectRandom() throws Exception;

	int update(Promotion promotion) throws Exception;

	int insert(Promotion promotion) throws Exception;
}