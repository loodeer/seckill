package com.loodeer.service;

import com.loodeer.error.BussinessException;
import com.loodeer.service.model.ItemModel;

import java.util.List;

public interface ItemService {

        ItemModel createItem(ItemModel itemModel) throws BussinessException;

        List<ItemModel> listItem();

        ItemModel getItemById(Integer id);
}
