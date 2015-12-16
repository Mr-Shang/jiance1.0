package com.ccu.dao.business;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccu.dao.DaoSupport;
import com.ccu.model.business.VideoInfo;


@Repository("videoDao")
@Transactional
public class VideoDaoImpl extends DaoSupport<VideoInfo> implements VideoDao {

}
