package com.bubble.lint.config.bean

/**
 * 资源命名规范
 * User: batteria
 * Date: 2020/6/19
 * Time: 5:06 PM
 */
data class ResourceName(
    val drawable: BaseConfigProperty = BaseConfigProperty(),
    val layout: BaseConfigProperty = BaseConfigProperty()
)