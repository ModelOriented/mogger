package com.pw.mini.mogger.plot.model_audit_double;

import com.pw.mini.mogger.model.boundary.ModelService;
import com.pw.mini.mogger.plot.model_audit.ModelAuditDataPoint;
import lombok.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelAuditDoubleGenerator {

    private ModelAuditDoubleCommand command;
    private ModelService modelService;

    public String generateData() {
        // for each user get his model+audit on dataset1+audit on dataset2 data

        JSONArray jsonArray = new JSONArray();

        for (String userName : command.getUserNames()) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", userName);
            jsonObject.put("type", "scatter");
            jsonObject.put("mode", "markers");

            List<ModelAuditDataPoint> dataPointsX =
                    modelService.findModelAuditDataPoints(command.getDatasetIdX(), userName, command.getMeasure());
            List<ModelAuditDataPoint> dataPointsY =
                    modelService.findModelAuditDataPoints(command.getDatasetIdY(), userName, command.getMeasure());

            Map<Integer, ModelAuditDataPoint> ModelAuditDataPointMapX =
                    dataPointsX.stream().collect(Collectors.toMap(ModelAuditDataPoint::getModelId, Function.identity()));
            Map<Integer, ModelAuditDataPoint> ModelAuditDataPointMapY =
                    dataPointsY.stream().collect(Collectors.toMap(ModelAuditDataPoint::getModelId, Function.identity()));

            // get the intersection of modelIds
            Set<Integer> modelIds = ModelAuditDataPointMapX.keySet();
            modelIds.retainAll(ModelAuditDataPointMapY.keySet());

            List<Double> x = new ArrayList<>();
            List<Double> y = new ArrayList<>();

            List<String> text = new ArrayList<>();

            for (Integer modelId : modelIds) {
                ModelAuditDataPoint pointX = ModelAuditDataPointMapX.get(modelId);
                ModelAuditDataPoint pointY = ModelAuditDataPointMapX.get(modelId);

                x.add(pointX.getValue());
                y.add(pointY.getValue());
                text.add(String.format("%s </br>AuditY dataset id: %d",
                        pointX.generateTooltipTextX(), pointY.getAuditsDatasetId()));
            }

            jsonObject.put("x", x);
            jsonObject.put("y", y);
            jsonObject.put("text", text);

            // add color and other stuff to plot

            jsonArray.put(jsonObject);
        }

        return jsonArray.toString();
    }
}
