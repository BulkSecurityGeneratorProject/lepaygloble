package com.jifenke.lepluslive.order.controller;

import com.jifenke.lepluslive.global.util.LejiaResult;
import com.jifenke.lepluslive.merchant.domain.entities.MerchantUser;
import com.jifenke.lepluslive.merchant.service.MerchantService;
import com.jifenke.lepluslive.merchant.service.MerchantUserResourceService;
import com.jifenke.lepluslive.merchant.service.MerchantUserService;
import com.jifenke.lepluslive.order.service.OffLineOrderShareService;
import com.jifenke.lepluslive.security.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanjun on 2016/12/15.
 */
@RestController
@RequestMapping("/api")
public class OffLineOrderShareController {

    @Inject
    private OffLineOrderShareService offLineOrderShareService;
    @Inject
    private MerchantUserResourceService merchantUserResourceService;
    @Inject
    private MerchantService merchantService;

    @RequestMapping(value = "/offLineOrder/todayCommissionAndTodayNumber", method = RequestMethod.GET)
    @ResponseBody
    public LejiaResult findTodayCommissionAndTodayNumber(){
        MerchantUser merchantUser = merchantService.findMerchantUserBySid(SecurityUtils.getCurrentUserLogin());
        List<Object []> list = merchantUserResourceService.findMerchantsByMerchantUserSql(merchantUser.getName());
        List<Object> mlist = new ArrayList<Object>();
        for (int i =0;i<list.size();i++){
            mlist.add(list.get(i)[0]);
        }
        Object obj = offLineOrderShareService.findTodayCommissionAndTodayNumber(mlist);
        return LejiaResult.ok(obj);
    }
}
