package com.ams.backendjpasample.common.dto;

import com.ams.backendjpasample.common.enums.EnumError;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
//@ApiModel(value = "ResMsgDto", description = "API Response")
@Slf4j
public class ResMsgDto<T> {

//    @ApiModelProperty(value = "Header", required = true, position = 0)
    private ResHeaderDto header;
//    @ApiModelProperty(value = "Body", required = false, position = 1)
    private T body;

    public ResMsgDto( EnumError error ) {
        this.header = new ResHeaderDto( error );
    }

    public ResMsgDto( EnumError error, String errorMessage ) {
        this.header = new ResHeaderDto( error, errorMessage );
    }

    public void setError( EnumError error ) {
        this.header = new ResHeaderDto( error );
    }

    public void setError( EnumError error, String errorMessage ) {
        this.header = new ResHeaderDto( error );
        this.header.setResultMsg( errorMessage );
    }

    /**
     * Page<Entity>를 ResPagingDto<ModelDTO> 로 변환 및 저장
     * @param <S1>
     * @param <S2>
     * @param pages
     * @param entityClass
     * @param targetModelClass
     */
    @SuppressWarnings("unchecked")
    public <S1,S2> void setPageableBody( Page<S1> pages, Class<S1> entityClass, Class<S2> targetModelClass ) {
        ResPagingDto<S2> paging = new ResPagingDto<>();

        List<S1> items = pages.getContent();
        List<S2> converted = items.stream().map( s -> {
            try {
                return targetModelClass.getDeclaredConstructor( entityClass ).newInstance( s );
            } catch (Exception e) {
                log.error( e.getMessage(), e );
            }
            return null;
        }).filter(Objects::nonNull).collect( Collectors.toList() );

        paging.setItems( converted );
        paging.setPage( pages.getNumber() + 1 );
        paging.setTotalPages( pages.getTotalPages() );
        paging.setPageSize( pages.getSize() );
        paging.setTotalCount( pages.getTotalElements() );
        this.body = (T) paging;
    }

    public <S1> void setPageableBody( Page<S1> pages) {
        ResPagingDto<S1> paging = new ResPagingDto<>();
        paging.setItems( pages.getContent() );
        paging.setPage( pages.getNumber() + 1 );
        paging.setTotalPages( pages.getTotalPages() );
        paging.setPageSize( pages.getSize() );
        paging.setTotalCount( pages.getTotalElements() );
        this.body = (T) paging;
    }
}

