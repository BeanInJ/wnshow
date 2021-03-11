package com.woniu.show.service;

import com.woniu.show.dao.DanmuCrud;
import com.woniu.show.pojo.Danmu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanmuServiceImpl implements DanmuService{
    @Autowired
    private DanmuCrud danmuCrud;
    @Override
    public void saveDanmu(Danmu danmu) {
        danmuCrud.save(danmu);
    }

    @Override
    public List<Danmu> find20() {
        return danmuCrud.find20();
    }
}
