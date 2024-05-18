package com.quyennv.datn.courseservice.core.domain.entities;

import com.quyennv.datn.courseservice.core.utils.FunctionHelper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Section {
    private Identity id;
    private String name;
    private String description;
    private Course course;
    private List<Lesson> lessons;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Section delete() {
        this.setDeletedAt(LocalDateTime.now());
        return this;
    }

    public Section update(Section s) {
       BeanUtils.copyProperties(s, this, FunctionHelper.getNullPropertyNames(s));
      return this;
    }
}
