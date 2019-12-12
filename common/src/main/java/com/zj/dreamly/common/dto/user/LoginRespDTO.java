package com.zj.dreamly.common.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 苍海之南
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRespDTO {
    /**
     * token
     */
    private JwtTokenRespDTO token;

    /**
     * 用户信息
     */
    private UserRespDTO user;
}
