package com.dmall.inventoryservice.apis;

import com.dmall.inventoryservice.apis.dtos.InventoryCreateRequest;
import com.dmall.inventoryservice.apis.dtos.InventoryLockRequest;
import com.dmall.inventoryservice.applications.InventoryFactory;
import com.dmall.inventoryservice.applications.InventoryLockFactory;
import com.dmall.inventoryservice.applications.InventoryService;
import com.dmall.inventoryservice.domain.Inventory;
import com.dmall.inventoryservice.domain.InventoryLock;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @ApiOperation(value = "创建库存")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createInventory(@RequestBody InventoryCreateRequest inventoryCreateRequest) {
        Inventory inventory = InventoryFactory.ToDomain(inventoryCreateRequest.getProductId(), inventoryCreateRequest.getQuantity());
        inventoryService.createInventory(inventory);
    }

    @ApiOperation(value = "锁定库存")
    @PostMapping("/lock")
    @ResponseStatus(HttpStatus.CREATED)
    public long createInventory(@RequestBody InventoryLockRequest inventoryLockRequest) {
        InventoryLock inventoryLock = InventoryLockFactory.ToDomain(inventoryLockRequest.getProductId(), inventoryLockRequest.getQuantity());
        return inventoryService.lockInventory(inventoryLock);
    }

    @ApiOperation(value = "扣减库存")
    @PutMapping("/lock/{id}")
    public void deductInventory(@PathVariable("id") long inventoryLockId) {
        inventoryService.deductInventory(inventoryLockId);
    }


}
