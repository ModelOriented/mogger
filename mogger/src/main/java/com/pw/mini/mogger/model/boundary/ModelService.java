package com.pw.mini.mogger.model.boundary;

import com.pw.mini.mogger.model.boundary.command.CreateNewModelCommand;
import com.pw.mini.mogger.model.control.repository.ModelRepository;
import com.pw.mini.mogger.model.entity.ModelEntity;
import com.pw.mini.mogger.plot.model_audit.ModelAuditDataPoint;
import com.pw.mini.mogger.tag.entity.TagEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelService {

    @PersistenceContext
    private EntityManager entityManager;
    private final ModelRepository modelRepository;

    @Transactional
    public Integer createModel(CreateNewModelCommand command) {
        ModelEntity modelEntity = ModelEntity.builder()
                .datasetId(command.getDatasetId())
                .userName(command.getUserName())
                .modelName(command.getModelName())
                .hash(command.getHash())
                .timestamp(command.getTimestamp())
                .language(command.getLanguage())
                .target(command.getTarget())
                .modelDescription(command.getModelDescription())
                .build();
        entityManager.persist(modelEntity);
        entityManager.flush();

        if (command.getTags() != null) {
            Integer modelId = modelEntity.getModelId();

            for (String tag : command.getTags()) {
                TagEntity tagEntity = TagEntity.builder()
                        .modelId(modelId)
                        .tagName(tag)
                        .build();
                entityManager.persist(tagEntity);
            }
        }

        return modelEntity.getModelId();
    }

    public boolean checkUser(String userName, Integer modelId) {
        try {
            ModelEntity modelEntity = modelRepository.findByModelId(modelId);
            return modelEntity.getUserName().equals(userName);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<ModelAuditDataPoint> findModelAuditDataPoints(Integer datasetId, String userName, String measure) {
        return modelRepository.findModelAuditByDatasetIdAndUserNameAndMeasure(datasetId, userName, measure);
    }

    public List<Double> findAuditsByModelId(Integer modelId) {
        return modelRepository.findAuditsByModelId(modelId);
    }

    public List<String> findUniqueUsersByTaskName(String taskName) {
        return modelRepository.findUniqueUsersByTaskName(taskName);
    }
}
