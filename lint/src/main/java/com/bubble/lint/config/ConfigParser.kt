package com.bubble.lint.config

import com.bubble.lint.config.bean.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

/**
 * lint配置解析器
 * User: batteria
 * Date: 2020/6/10
 * Time: 7:33 PM
 */
class ConfigParser {

    private var configJson = JsonObject()

    companion object {
        const val KEY_AVOID_USAGE_API = "avoid_usage_api"
        const val KEY_HANDLE_EXCEPTION_METHOD = "handle_exception_method"
        const val KEY_DEPENDENCY_API = "dependency_api"
        const val KEY_RESOURCE_NAME = "resource_name"
        const val KEY_SERIALIZABLE_CONFIG = "serializable_config"
        const val KEY_DATA_STORE_WRITE = "data_store_write"
    }

    init {
        val input = javaClass.getResourceAsStream("/config/custom_lint_config.json")
        configJson = Gson().fromJson(input.bufferedReader(), JsonObject::class.java)
    }

    fun getAvoidUsageApi(): AvoidUsageApi {
        return Gson().fromJson(
            configJson.getAsJsonObject(KEY_AVOID_USAGE_API),
            AvoidUsageApi::class.java
        ) ?: AvoidUsageApi()
    }

    fun getHandleExceptionMethod(): List<HandleExceptionMethod> {
        return Gson().fromJson(
            configJson.getAsJsonArray(KEY_HANDLE_EXCEPTION_METHOD),
            object : TypeToken<List<HandleExceptionMethod>>() {}.type
        ) ?: listOf()
    }

    fun getDependencyApiList(): List<DependencyApi> {
        return Gson().fromJson(
            configJson.getAsJsonArray(KEY_DEPENDENCY_API),
            object : TypeToken<List<DependencyApi>>() {}.type
        ) ?: listOf()
    }

    fun getResourceName(): ResourceName {
        return Gson().fromJson(
            configJson.getAsJsonObject(KEY_RESOURCE_NAME),
            object : TypeToken<ResourceName>() {}.type
        ) ?: ResourceName()
    }

    fun getSerializableConfig(): BaseConfigProperty {
        return Gson().fromJson(
            configJson.getAsJsonObject(KEY_SERIALIZABLE_CONFIG),
            object : TypeToken<BaseConfigProperty>() {}.type
        ) ?: BaseConfigProperty()
    }

    fun getDataStoreWriteConfig(): DataStoreMemberClasses {
        return Gson().fromJson(
            configJson.getAsJsonObject(KEY_DATA_STORE_WRITE),
            object : TypeToken<DataStoreMemberClasses>() {}.type
        ) ?: DataStoreMemberClasses()
    }
}