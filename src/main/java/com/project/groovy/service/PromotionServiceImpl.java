package com.project.groovy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.groovy.dao.PromotionDao;
import com.project.groovy.model.Promotion;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	PromotionDao promotionDao;
	
	@Override
	public int remove(Integer num) throws Exception {
		return promotionDao.delete(num);
	}
	
	@Override
	public List<Promotion> selectAll() throws Exception {
		return promotionDao.selectAll();
	}
	
	@Override
	public Promotion select(Integer num) throws Exception {
		return promotionDao.select(num);
	}
	
	@Override
	public Promotion selectRandom() throws Exception {
		return promotionDao.selectRandom();
	}
	
	@Override
	public int update(Promotion promotion) throws Exception {
		return promotionDao.update(promotion);
	}

	@Override
	public int insert(Promotion promotion) throws Exception {
		return promotionDao.insert(promotion);
	}
}
