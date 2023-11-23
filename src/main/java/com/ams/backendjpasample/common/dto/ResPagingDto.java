package com.ams.backendjpasample.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//@ApiModel(value = "ResPagingDto", description = "Paging Items")
public class ResPagingDto<T> {

//    @ApiModelProperty(value = "Items", required = true, position = 0)
    private List<T> items;
//    @ApiModelProperty(value = "Current Page", required = true, position = 1)
    private int page;
//    @ApiModelProperty(value = "Total Pages", required = true, position = 2)
    private int totalPages;
//    @ApiModelProperty(value = "Page Size", required = true, position = 3)
    private int pageSize;
//    @ApiModelProperty(value = "Total Count", required = true, position = 4)
    private long totalCount;

    public ResPagingDto( Page<T> pages ) {
        this.items = pages.getContent();
        this.page = pages.getNumber() + 1;
        this.totalPages = pages.getTotalPages();
        this.pageSize = pages.getSize();
        this.totalCount = pages.getTotalElements();
    }
}
