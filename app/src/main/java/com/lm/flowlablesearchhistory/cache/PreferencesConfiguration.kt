/*
 * Copyright © 2019 Beijing XUNTING Network Technology Co., Ltd. All rights reserved.
 */

package com.lm.flowlablesearchhistory.cache

import androidx.preference.PreferenceManager
import com.lm.flowlablesearchhistory.MyApplication
import com.lm.flowlablesearchhistory.cache.utils.PreferencesUtils

/**
 * 偏好设置缓存
 * Created by think on 2016/12/21.
 * @author liming
 */
object PreferencesConfiguration {
//    internal var sp = PreferenceManager.getDefaultSharedPreferences(TTApplication.getAppContext())
    internal var sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.context)
    /**
     * [Integer]根据给定的Key值得到对应的Values
     *
     * @param key 给定的Key
     * @return 对应的Values
     */
    fun getIValues(key: String): Int {

        return PreferencesUtils.getInt(sp, key, -1)
    }

    /**
     * 设定一组key-values
     *
     * @param key   指定的key
     * @param value 指定的values
     */
    fun setIValues(key: String, value: Int) {
        PreferencesUtils.putInt(sp, key, value)
    }

    /**
     * [String]根据给定的Key值得到对应的Values
     *
     * @param key 给定的Key
     * @return 对应的Values
     */
    fun getSValues(key: String): String {
        return PreferencesUtils.getString(sp, key)
    }

    /**
     * 设定一组key-values
     *
     * @param key   指定的key
     * @param value 指定的values
     */
    fun setSValues(key: String, value: String) {
        PreferencesUtils.putString(sp, key, value)
    }

    /**
     * [Boolean]根据给定的Key值得到对应的Values
     *
     * @param key 给定的Key
     * @return 对应的Values
     */
    fun getBValues(key: String): Boolean {
        return PreferencesUtils.getBoolean(sp, key, false)
    }

    /**
     * [Boolean]根据给定的Key值得到对应的Values
     *
     * @param key 给定的Key
     * @return 对应的Values
     */
    fun getBValues(key: String, defaultValue: Boolean): Boolean {
        return PreferencesUtils.getBoolean(sp, key, defaultValue)
    }

    /**
     * 设定一组key-values
     *
     * @param key   指定的key
     * @param value 指定的values
     */
    fun setBValues(key: String, value: Boolean) {
        PreferencesUtils.putBoolean(sp, key, value)
    }

    /**
     * [Long]根据给定的Key值得到对应的Values
     *
     * @param key 给定的Key
     * @return 对应的Values
     */
    fun getLValues(key: String): Long {
        return PreferencesUtils.getLong(sp, key, -1)
    }

    /**
     * 设定一组key-values
     *
     * @param key   指定的key
     * @param value 指定的values
     */
    fun setLValues(key: String, value: Long) {
        PreferencesUtils.putLong(sp, key, value)
    }
}
