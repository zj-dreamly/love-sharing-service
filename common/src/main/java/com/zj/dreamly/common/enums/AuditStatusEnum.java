package com.zj.dreamly.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 苍海之南
 */

@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    /**
     * 待审核
     */
    NOT_YET,
    /**
     * 审核通过
     */
    PASS,
    /**
     * 审核不通过
     */
    REJECT
}
