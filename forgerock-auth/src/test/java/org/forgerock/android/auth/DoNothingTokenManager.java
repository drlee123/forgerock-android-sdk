/*
 * Copyright (c) 2019 ForgeRock. All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package org.forgerock.android.auth;

import org.forgerock.android.auth.exception.AuthenticationRequiredException;

public class DoNothingTokenManager implements TokenManager {

    @Override
    public void persist(AccessToken token) {
    }

    @Override
    public void exchangeToken(SSOToken token, FRListener<AccessToken> listener) {

    }

    @Override
    public void refresh(AccessToken accessToken, FRListener<AccessToken> listener) throws AuthenticationRequiredException {

    }

    @Override
    public void getAccessToken(AccessTokenVerifier accessTokenVerifier, FRListener<AccessToken> tokenListener) {
        tokenListener.onException(new AuthenticationRequiredException("No Access Token!"));
    }

    @Override
    public boolean hasToken() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void revoke(FRListener<Void> listener) {
        Listener.onException(listener, new UnsupportedOperationException());
    }
}
