package com.cat.grabclass.common.dto.req;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Mr.xin
 */
@Data
@ApiModel
public class GoodsReqDto {

    private String title;

    private Double price;

    private String description;

    private Integer sales;

    private String imgUrl;


}
