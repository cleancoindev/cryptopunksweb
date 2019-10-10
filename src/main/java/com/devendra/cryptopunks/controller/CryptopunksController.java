package com.devendra.cryptopunks.controller;

import com.devendra.cryptopunks.service.CryptopunksService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class CryptopunksController {

    private static final Logger log = LoggerFactory.getLogger(CryptopunksController.class);

    @Autowired
    private CryptopunksService cryptopunksService;

    @RequestMapping("/cryptopunks/sale")
    public List<BigInteger> getPunksForSale() throws ExecutionException, InterruptedException {
        return cryptopunksService.getPunksForSale();
    }

    @RequestMapping("/cryptopunks")
    public String getPunkDetailsForSale(@RequestParam(value = "id") BigInteger id)
    throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Map <String, Object> map = cryptopunksService.getAllPunkDetails().get(id.toString());
        map.put("sale", cryptopunksService.getPunkOfferedPrice(id));
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
    }
}
