package com.loodeer.controller;

import com.loodeer.controller.viewObject.ItemVO;
import com.loodeer.error.BussinessException;
import com.loodeer.response.CommonResult;
import com.loodeer.service.ItemService;
import com.loodeer.service.impl.ItemServiceImpl;
import com.loodeer.service.impl.PromoServiceImpl;
import com.loodeer.service.model.ItemModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Controller("/item")
@RequestMapping("/item")
@CrossOrigin(origins = { "*" }, allowCredentials = "true")
public class ItemController extends BaseController {

    Logger logger = LogManager.getLogger();

    @Resource
    private ItemServiceImpl itemService;

    @RequestMapping(value = "/create", method = { RequestMethod.POST }, consumes = { CONTENT_TYPE_FORMED })
    @ResponseBody
    public CommonResult createItem(@RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") Integer price,
            @RequestParam("stock") Integer stock,
            @RequestParam("imgUrl") String imgUrl) throws BussinessException {

        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setDescription(description);
        itemModel.setImgUrl(imgUrl);
        ItemModel itemModelOfReturn = itemService.createItem(itemModel);
        ItemVO itemVO = itemService.convertVOFromModel(itemModelOfReturn);

        return CommonResult.create(itemVO);
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public CommonResult getItem(@RequestParam("id") Integer id) {
        ItemModel itemModel = itemService.getItemById(id);
        ItemVO itemVO = itemService.convertVOFromModel(itemModel);
        return CommonResult.create(itemVO);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public CommonResult itemList() {
        System.out.println("println: 访问了一次商品列表页");
        logger.info("log4j: 访问了一次商品列表页");
        logger.debug("debug: 访问了一次商品列表页");
        List<ItemModel> itemModelList = itemService.listItem();

        List<ItemVO> itemVOList = new ArrayList<>();

        for (int i = 0; i < itemModelList.size(); i++) {
            ItemModel itemModel = itemModelList.get(i);
            ItemVO itemVO = itemService.convertVOFromModel(itemModel);
            itemVOList.add(itemVO);
        }
        return CommonResult.create(itemVOList);
    }

}
