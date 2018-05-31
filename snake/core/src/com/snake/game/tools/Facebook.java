package com.snake.game.tools;

import com.badlogic.gdx.Gdx;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;


public class Facebook {

    private String appId = "611409215906032";
    private String appSecret = "adde3a263fc61f995ab94080ff807ab1";
    private String redirectUri = "https://www.facebook.com/connect/login_success.html";

    private FacebookClient facebookClient;
    private ScopeBuilder scopeBuilder;

    public Facebook() {
        scopeBuilder = new ScopeBuilder();
        facebookClient = new DefaultFacebookClient(Version.VERSION_2_9);
    }

    public void login() {
        String loginDialogUrl = facebookClient.getLoginDialogUrl(appId, redirectUri, scopeBuilder);
        Gdx.net.openURI(loginDialogUrl);
    }





}
