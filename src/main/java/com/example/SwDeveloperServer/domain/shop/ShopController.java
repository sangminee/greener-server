package com.example.SwDeveloperServer.domain.shop;

import com.example.SwDeveloperServer.domain.shop.dto.GetShopRes;
import com.example.SwDeveloperServer.domain.shop.dto.PostItemReq;
import com.example.SwDeveloperServer.domain.shop.service.ShopServiceImpl;
import com.example.SwDeveloperServer.utils.jwt.JwtService;
import com.example.SwDeveloperServer.utils.response.BaseException;
import com.example.SwDeveloperServer.utils.response.ResponseService;
import com.example.SwDeveloperServer.utils.response.SuccessStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags ="4. 상점 API ")
public class ShopController {

    private final ShopServiceImpl shopService;
    private final ResponseService responseService;
    private final JwtService jwtService;

    public ShopController(ShopServiceImpl shopService, ResponseService responseService, JwtService jwtService) {
        this.shopService = shopService;
        this.responseService = responseService;
        this.jwtService = jwtService;
    }

    /**
     * 상점 API
     */
    @ApiOperation(value = "상점 API")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = GetShopRes.class)
    })
    @GetMapping("/shop")
    public ResponseEntity<?> getShop() throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            GetShopRes getShopRes = shopService.getShop(userId);
            return responseService.successResult(getShopRes, SuccessStatus.SUCCESS);

        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }

    /**
     * 아이템 구매 API
     */
    @ApiOperation(value = "아이템 구매 API")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = GetShopRes.class)
    })
    @PostMapping("/shop")
    public ResponseEntity<?> purchaseItem(@RequestParam(required = false) Long itemId) throws BaseException {
        try {
            Long userId = jwtService.getUserIdx();
            GetShopRes getShopRes = shopService.purchaseItem(userId);
            return responseService.successResult(getShopRes, SuccessStatus.SUCCESS);

        } catch (BaseException exception) {
            return responseService.errorResult(exception.getErrorStatus());
        }
    }


    /**
     * 상점 Item 추가 API
     */
    @ApiOperation(value = "상점 Item 추가 API")
    @ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK", response = GetShopRes.class)
    })
    @PostMapping("/shop/addItem")
    public ResponseEntity<?> setItem(@RequestBody PostItemReq postItemReq) throws BaseException {
        String message = shopService.setItem(postItemReq);
        return responseService.successResult(message, SuccessStatus.SUCCESS);
    }
}
