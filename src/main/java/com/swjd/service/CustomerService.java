package com.swjd.service;

import com.swjd.bean.Customer;

import java.util.List;

public interface CustomerService {
    //查询全部
    public abstract List<Customer> showSelectAll();
}
