/*
 * Copyright © 2019 Beijing XUNTING Network Technology Co., Ltd. All rights reserved.
 */

package com.lm.flowlablesearchhistory.cache;

import android.text.TextUtils;


import androidx.annotation.NonNull;

import com.lm.flowlablesearchhistory.configuration.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

/**
 * 缓存搜索历史数据类
 */
public class SearchHistoryDataCache {

    private static volatile SearchHistoryDataCache instance = null;
    private int cache_size = Constants.SEARCH_HISTORY_CONTENT_COUNT;

    /**
     * 构造方法
     */
    private SearchHistoryDataCache() {}


    public static SearchHistoryDataCache getInstance() {
        synchronized (SearchHistoryDataCache.class) {
            SearchHistoryDataCache _cache = instance;
            if (_cache == null) {
                _cache = new SearchHistoryDataCache();
                instance = _cache;
            }
            return instance;
        }
    }

    /**
     * 获取搜索历史缓存数据
     *
     * @return
     */
    public List<String> getHistoryCacheData() {
        List<String> history_cache_datas = new ArrayList<>();
        //获得缓存信息
        String _history_content = PreferencesConfiguration.INSTANCE.getSValues(Constants.KEY_SEARCH_HISTORY_CONTENT);
        //判断缓存是否为空字符串
        if (!TextUtils.isEmpty(_history_content)) {
            try {
                String[] _arrays = _history_content.split(Constants.SEPARATOR);
                for (int i = 0; i < _arrays.length; i++) {
                    history_cache_datas.add(_arrays[i]);
                }
            } catch (PatternSyntaxException e) {}
        }
        return history_cache_datas;
    }

    /**
     * 添加数据
     *
     * @param parame
     */
    public void addSearchHistoryContent(@NonNull String parame) {
        if (!TextUtils.isEmpty(parame) && cache_size > 0) {
            //获得数据
            List<String> _history_content = getHistoryCacheData();
            //插入标记
            boolean _is_insert = true;
            //循环数据
            TAG:
            for (int i = 0; i < _history_content.size(); i++) {
                //判断内容是否相同
                if (_history_content.get(i).contentEquals(parame)) {
                    //移除该数据
                    String _str = _history_content.remove(i);
                    //添加新数据
                    _history_content.add(0, _str);
                    //设置标记
                    _is_insert = false;
                    //退出循环
                    break TAG;
                }
            }
            if (_is_insert) {
                _history_content.add(0, parame);
            }
            StringBuffer _string_buffer = new StringBuffer();
            //循环次数
            int _count = _history_content.size() > cache_size ? cache_size : _history_content.size();
            //保存数据
            for (int i = 0; i < _count; i++) {
                _string_buffer.append(_history_content.get(i));
                if (i != (_count - 1)) {
                    _string_buffer.append(Constants.SEPARATOR);
                }
            }
            //保存数据
            PreferencesConfiguration.INSTANCE.setSValues(Constants.KEY_SEARCH_HISTORY_CONTENT, _string_buffer.toString());
        }
    }

    /**
     * 清除所有历史数据方法
     *
     * @return
     */
    public void removeAllHistoryContent() {
        PreferencesConfiguration.INSTANCE.setSValues(Constants.KEY_SEARCH_HISTORY_CONTENT, "");
    }
}
