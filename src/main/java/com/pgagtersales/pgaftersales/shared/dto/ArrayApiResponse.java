package com.pgagtersales.pgaftersales.shared.dto;
import lombok.Data;

import javax.annotation.Nullable;
import java.util.List;

@Data
public class ArrayApiResponse<T> {
    private String id;
    private List<T> item;

}
