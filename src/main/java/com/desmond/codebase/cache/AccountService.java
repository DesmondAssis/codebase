package com.desmond.codebase.cache;

import com.desmond.codebase.cache.bean.Account;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by Li.Xiaochuan on 15/9/5.
 */
public class AccountService {

    @Cacheable("accountCache")
    public Account getByName(String username) {
        System.out.println("real query account."+ username);

        return getByNameFromDB(username);
    }

    private Account getByNameFromDB(String acctName) {
        System.out.println("========== get from db");
        return new Account(1, "desmond");
    }
}
