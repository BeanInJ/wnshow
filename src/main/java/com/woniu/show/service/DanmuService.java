package com.woniu.show.service;

import com.woniu.show.pojo.Danmu;

import java.util.List;

public interface DanmuService {
    void saveDanmu(Danmu danmu);
    List<Danmu> find20();
}
