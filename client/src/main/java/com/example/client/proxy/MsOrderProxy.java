package com.example.client.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ms-cart", url = "localhost:8093")
public interface MsOrderProxy {


}
