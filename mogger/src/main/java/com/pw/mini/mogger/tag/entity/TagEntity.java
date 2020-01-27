package com.pw.mini.mogger.tag.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tags")
@IdClass(TagId.class)
public class TagEntity {

    @Id
    @Column(name = "model_id", nullable = false)
    private Integer modelId;

    @Id
    @Column(name = "tag_name", nullable = false)
    private String tagName;
}

@AllArgsConstructor
@NoArgsConstructor
class TagId implements Serializable {

    private Integer modelId;
    private String tagName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagId)) return false;
        TagId tagId = (TagId) o;
        return modelId.equals(tagId.modelId) &&
                tagName.equals(tagId.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, tagName);
    }
}