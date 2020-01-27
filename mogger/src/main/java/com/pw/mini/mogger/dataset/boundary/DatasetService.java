package com.pw.mini.mogger.dataset.boundary;

import com.pw.mini.mogger.dataset.boundary.command.CreateNewDatasetCommand;
import com.pw.mini.mogger.dataset.boundary.vo.DatasetVO;
import com.pw.mini.mogger.dataset.control.mapper.DatasetMapper;
import com.pw.mini.mogger.dataset.control.repository.DatasetRepository;
import com.pw.mini.mogger.dataset.entity.DatasetEntity;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DatasetService {

    @PersistenceContext
    private EntityManager entityManager;
    private final DatasetRepository datasetRepository;

    @Transactional
    public Integer createDataset(CreateNewDatasetCommand command) {
        DatasetEntity datasetEntity = DatasetEntity.builder()
                .datasetName(command.getDatasetName())
                .userName(command.getUserName())
                .taskName(command.getTaskName())
                .timestamp(command.getTimestamp())
                .datasetDescription(command.getDatasetDescription())
                .url(command.getUrl())
                .build();
        entityManager.persist(datasetEntity);
        entityManager.flush();

        return datasetEntity.getDatasetId();
    }

    public List<DatasetVO> getAllDatasets(){
        return datasetRepository.findAll()
                .stream()
                .map(e-> DatasetMapper.INSTANCE.mapEntity(e))
                .collect(Collectors.toList());
    }

    public String findDatasetsByTaskName(String taskName) {
        List<DatasetEntity> datasetEntities = datasetRepository.findAllByTaskName(taskName);

        JSONArray jsonArray = new JSONArray();

        for (DatasetEntity dataset : datasetEntities) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("datasetId", dataset.getDatasetId());
            jsonObject.put("datasetName", dataset.getDatasetName());

            jsonArray.put(jsonObject);
        }

        return jsonArray.toString();
    }
}
