package com.pw.mini.mogger.plot.model_audit;

import com.pw.mini.mogger.model.boundary.ModelService;
import lombok.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelAuditGenerator {

    private ModelAuditCommand command;
    private ModelService modelService;

    public String generateData() {
        // for each user get his model+audit data

        JSONArray jsonArray = new JSONArray();

        for (String userName : command.getUserNames()) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", userName);
            jsonObject.put("type", "bar");

            List<ModelAuditDataPoint> dataPoints =
                    modelService.findModelAuditDataPoints(command.getDatasetId(), userName, command.getMeasure());

            jsonObject.put("x", dataPoints.stream().map((item) -> item.getModelName()).collect(Collectors.toList()));
            jsonObject.put("y", dataPoints.stream().map((item) -> item.getValue()).collect(Collectors.toList()));
            jsonObject.put("text", dataPoints.stream().map((item) -> item.generateTooltipText()).collect(Collectors.toList()));
            // add color and other stuff to plot

            jsonArray.put(jsonObject);
        }

        return jsonArray.toString();
    }

    public String generateDataTime() {
        // for each user get his model+audit data

        JSONArray jsonArray = new JSONArray();

        for (String userName : command.getUserNames()) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", userName);
            jsonObject.put("type", "scatter");
            jsonObject.put("mode", "lines+markers");
            jsonObject.put("line", "{'shape': 'linear'}");

            List<ModelAuditDataPoint> dataPoints =
                    modelService.findModelAuditDataPoints(command.getDatasetId(), userName, command.getMeasure());

            jsonObject.put("x", dataPoints.stream().map((item) -> item.getModelsTimestamp()).collect(Collectors.toList()));
            jsonObject.put("y", dataPoints.stream().map((item) -> item.getValue()).collect(Collectors.toList()));
            jsonObject.put("text", dataPoints.stream().map((item) -> item.generateTooltipText()).collect(Collectors.toList()));
            // add color and other stuff to plot

            jsonArray.put(jsonObject);
        }

        return jsonArray.toString();
    }
}
