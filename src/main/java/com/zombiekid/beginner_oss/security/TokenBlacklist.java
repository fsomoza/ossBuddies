package com.zombiekid.beginner_oss.security;

import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenBlacklist {
    private Set<String> blacklist = ConcurrentHashMap.newKeySet();

    public void add(String tokenId) {
        blacklist.add(tokenId);
    }

    public boolean contains(String tokenId) {
        return blacklist.contains(tokenId);
    }
}
