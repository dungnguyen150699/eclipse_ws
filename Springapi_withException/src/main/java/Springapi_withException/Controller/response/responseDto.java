package Springapi_withException.Controller.response;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain=true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class responseDto<E> {
	private String code,message;
	private int page,size;
	private E item;
    @JsonProperty("total_items")
    private long totalItems;
    private List<E> items;
	
	public responseDto(Pageable pageable, Page<E> page, String message) {
        this.code = code;
        this.message = message;
        this.totalItems = page.getTotalElements();
        this.page = pageable.getPageNumber();
        this.size = pageable.getPageSize();
        this.items = page.getContent();
    }
	
    public responseDto(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
