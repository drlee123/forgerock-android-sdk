/*
 * Copyright (c) 2020 ForgeRock. All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package org.forgerock.android.auth;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.util.Consumer;

import net.openid.appauth.AppAuthConfiguration;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationServiceConfiguration;

import java.net.MalformedURLException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * AppAuth Integration, https://github.com/openid/AppAuth-Android
 * this class provides AppAuth integration and customization
 */
@RequiredArgsConstructor
public class AppAuthConfigurer {

    private final FRUser.Browser parent;

    @Getter(AccessLevel.PACKAGE)
    private Consumer<AuthorizationRequest.Builder> authorizationRequestBuilder = builder -> {
    };
    @Getter(AccessLevel.PACKAGE)
    private androidx.core.util.Consumer<AppAuthConfiguration.Builder> appAuthConfigurationBuilder = builder -> {
    };
    @Getter(AccessLevel.PACKAGE)
    private Consumer<CustomTabsIntent.Builder> customTabsIntentBuilder = builder -> {
    };
    @Getter(AccessLevel.PACKAGE)
    private Supplier<AuthorizationServiceConfiguration> authorizationServiceConfigurationSupplier = () -> {
        OAuth2Client oAuth2Client = Config.getInstance().getOAuth2Client();
        try {
            return new AuthorizationServiceConfiguration(
                    Uri.parse(oAuth2Client.getAuthorizeUrl().toString()),
                    Uri.parse(oAuth2Client.getTokenUrl().toString()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    };

    /**
     * Override the default OAuth2 endpoint defined under the String.xml
     * forgerock_url
     */
    public AppAuthConfigurer authorizationServiceConfiguration(@NonNull Supplier<AuthorizationServiceConfiguration> authorizationServiceConfiguration) {
        this.authorizationServiceConfigurationSupplier = authorizationServiceConfiguration;
        return this;
    }

    /**
     * Override the {@link AuthorizationRequest} that prepared by the SDK.
     * The client_id, response type (code), redirect uri, scope are populated by the configuration defined under
     * string.xml forgerock_oauth_client_id, forgerock_oauth_redirect_uri, forgerock_oauth_scope. Developer can provide more
     * customization on the {@link AuthorizationRequest} object, for example {@link AuthorizationRequest.Builder#setPrompt(String)}
     */
    public AppAuthConfigurer authorizationRequest(@NonNull Consumer<AuthorizationRequest.Builder> authorizationRequest) {
        this.authorizationRequestBuilder = authorizationRequest;
        return this;
    }

    /**
     * Override the {@link AppAuthConfiguration} that prepared by the SDK.
     */
    public AppAuthConfigurer appAuthConfiguration(@NonNull Consumer<AppAuthConfiguration.Builder> authorizationService) {
        this.appAuthConfigurationBuilder = authorizationService;
        return this;
    }

    /**
     * Override the {@link CustomTabsIntent} that prepared by the SDK.
     */
    public AppAuthConfigurer customTabsIntent(@NonNull Consumer<CustomTabsIntent.Builder> customTabsIntent) {
        this.customTabsIntentBuilder = customTabsIntent;
        return this;
    }

    /**
     * Finish up the AppAuth customization.
     */
    public FRUser.Browser done() {
        return parent;
    }


}
