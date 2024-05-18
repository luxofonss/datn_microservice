package com.quyennv.datn.courseservice.core.domain.valueobject;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Resource {
    private Identity id;
    private String name;
    private String type;
    private String url;
    private String videoDuration;
    private String storeProvider;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
