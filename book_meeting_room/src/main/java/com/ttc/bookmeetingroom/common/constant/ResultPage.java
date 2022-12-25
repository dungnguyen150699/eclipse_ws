package com.ttc.bookmeetingroom.common.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultPage<T> {
    private long totalItems;
    private List<T> pageList;
}