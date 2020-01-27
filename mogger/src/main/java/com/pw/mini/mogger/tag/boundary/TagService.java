package com.pw.mini.mogger.tag.boundary;

import com.pw.mini.mogger.tag.boundary.command.CreateNewTagCommand;
import com.pw.mini.mogger.tag.boundary.vo.TagVO;
import com.pw.mini.mogger.tag.control.mapper.TagMapper;
import com.pw.mini.mogger.tag.control.repository.TagRepository;
import com.pw.mini.mogger.tag.entity.TagEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    @PersistenceContext
    private EntityManager entityManager;
    private final TagRepository tagRepository;

    @Transactional
    public void createTag(CreateNewTagCommand command) {
        TagEntity tagEntity = TagEntity.builder()
                .modelId(command.getModelId())
                .tagName(command.getTagName())
                .build();
        entityManager.persist(tagEntity);
    }

    public List<TagVO> getAllTags(){
        return tagRepository.findAll()
                .stream()
                .map(e-> TagMapper.INSTANCE.mapEntity(e))
                .collect(Collectors.toList());
    }
}
