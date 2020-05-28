/*
 * Copyright (c) 2020 ForgeRock. All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package org.forgerock.android.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Action can be used for {@link FRRequestInterceptor} to easily identify what type of outgoing
 * event is being made from the SDK and for developers to customize the given {@link Request}
 * object in {@link FRRequestInterceptor}`. See {@link RequestInterceptor} for more detail.
 *
 * Currently, ForgeRock SDK makes following Requests with corresponding Actions:
 * <p>
 * <ul>
 * <li>START_AUTHENTICATE - Initial Request made to the Authentication Tree: '/json/realms/{realm}/authenticate'</li>
 * <li>AUTHENTICATE - Any subsequent Requests made to the Authentication Tree: '/json/realms/{realm}/authenticate'</li>
 * <li>AUTHORIZE - Request for exchanging SSO Token to Authorization code: '/oauth2/realms/{realm}/authorize' </li>
 * <li>EXCHANGE_TOKEN - OAuth2 token exchange request with Authorization Code: '/oauth2/realms/{realm}/access_token'</li>
 * <li>REFRESH_TOKEN - OAuth2 token refresh request with given 'refresh_token': '/oauth2/realms/{realm}/access_token'</li>
 * <li>REVOKE_TOKEN - OAuth2 token revocation with given 'access_token' or 'refresh_token': '/oauth2/realms/{realm}/token/revoke'</li>
 * <li>LOGOUT - AM Session logout request to revoke SSO Token: '/json/realms/{realm}/sessions?_action=logout'</li>
 * <li>USER_INFO - Retrieving user info: `/oauth2/realms/{realm}/userinfo`</li>
 * </ul>
 */
@Getter
@AllArgsConstructor
public class Action {

    public static final String START_AUTHENTICATE = "START_AUTHENTICATE";
    public static final String AUTHENTICATE = "AUTHENTICATE";
    public static final String AUTHORIZE = "AUTHORIZE";
    public static final String EXCHANGE_TOKEN = "EXCHANGE_TOKEN";
    public static final String REFRESH_TOKEN = "REFRESH_TOKEN";
    public static final String REVOKE_TOKEN = "REVOKE_TOKEN";
    public static final String LOGOUT = "LOGOUT";
    public static final String USER_INFO = "USER_INFO";

    private String type;
}