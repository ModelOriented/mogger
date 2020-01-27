//:// init

var margin = {l: 40, r: 40, b: 40, t: 40, pad: 4};
var legend = {x: 1, xanchor: 'right', y: 1, traceorder: 'reversed',
              font: {size: 16}, yref: 'paper'};
var font = {family: 'Roboto, sans-serif'};
var DATASET_DICT = {};

addTasks();
addTags();

//:// add events

$('#select-task').on('change', function(e) {
  addUsers();
  addMeasures();
  addDatasets();
  setTimeout(function() {
    updatePlots();
  }, 1500)
})

$('#select-users').on('change', function(e) {
  updatePlots();
});

$('#select-measure').on('change', function(e) {
  updatePlots()
})

$('#control-left #select-dataset').on('change', function(e) {
  updatePlotModelAudit()
})

$('#control-middle #select-dataset').on('change', function(e) {
  updatePlotModelAuditDouble()
})

$('#control-right #select-dataset').on('change', function(e) {
  updatePlotModelAuditTime()
})

//:// pick first tag

setTimeout(function () {
  $('#select-task').trigger("change");
}, 1500);

//:// rest functions

function addTasks() {
  // fetch GET tasks from api
  // for all tasks
    // append task to select list

  $.get("/mogger/api/v1/dataset/tasks",
        function (data) {
          let temp = $("#select-task");

          data.forEach((item) => {
            temp.append( `<option value="${item}">${item}</option>` );
          });
          temp.selectpicker('refresh');
        });
}

function addUsers() {
  // fetch GET users from api
  // for all users
    // append user to select list

  $.get("/mogger/api/v1/model/uniqueUsers/" + getHeaderInputValues("task"),
        function (data) {
          let temp = $("#select-users");
          temp.children().remove();

          data.forEach((item) => {
            temp.append( `<option value="${item}">${item}</option>` );
          });
          temp.prop('disabled', false).selectpicker('refresh').selectpicker('selectAll');
        });
}

function addMeasures() {
  // fetch GET measures from api
  // for all measure
    // append measure to select list

  $.get("/mogger/api/v1/audit/uniqueMeasures/" + getHeaderInputValues("task"),
        function (data) {
          let temp = $("#select-measure");
          temp.children().remove();

          data.forEach((item) => {
            temp.append( `<option value="${item}">${item}</option>` );
          });
          temp.prop('disabled', false).selectpicker('refresh');
        });
}

function addTags() {
  // getch GET tags from api
  // for all tags
    // append tag to bootstrap-tagsinput
    // https://bootstrap-tagsinput.github.io/bootstrap-tagsinput/examples/

  $.get("/mogger/api/v1/tag",
        function (data) {
          let temp = $('#select-tags');

          data.forEach((item) => {
            temp.tagsinput('add', item.tagName);
          });
        });
}

function addDatasets() {
  // fetch GET datasets from api for a given task
  // for all datasets
    // append dataset to each select list

  $.get("/mogger/api/v1/dataset/uniqueDatasets/" + getHeaderInputValues("task"),
        function (data) {
          data_parsed = JSON.parse(data);

          // populate the dict to retrieve the datasetId by datasetName in POST querries
          DATASET_DICT = {};
          data_parsed.forEach((item) => {
            DATASET_DICT[item.datasetName] = item.datasetId
          });

          $('[id=select-dataset]').each(function() {
            // remove old datasets
            $(this).children().remove();
            // add new datasets and refresh
            data_parsed.forEach((item) => {
              $(this).append( `<option value="${item.datasetName}">${item.datasetName}</option>` );
            });
            $(this).prop('disabled', false).selectpicker('refresh');
          })
        });
}

//:// plot functions

function updatePlots() {
  updatePlotModelAudit();
  updatePlotModelAuditDouble();
  updatePlotModelAuditTime();
}

function updatePlotModelAudit() {
  $.ajax({
    url: "/mogger/api/v1/plot/modelAudit",
    type: "post",
    dataType: 'json',
    contentType: 'application/json; charset=utf-8',
    data: JSON.stringify({
      datasetId: DATASET_DICT[getFooterInputValues("dataset", "left")],
      userNames: getHeaderInputValues("users"),
      measure: getHeaderInputValues("measure")
    }),
    success: function(data) {
      var layout = {
        margin: margin,
        legend: legend,
        font: font,
        yaxis: {
          zeroline: false,
          gridwidth: 2
        },
        bargap :0.05
      };

      Plotly.react('plot-left', data, layout);
    }
  });
}

function updatePlotModelAuditDouble() {
  $.ajax({
    url: "/mogger/api/v1/plot/modelAuditDouble",
    type: "post",
    dataType: 'json',
    contentType: 'application/json; charset=utf-8',
    data: JSON.stringify({
      datasetIdX: DATASET_DICT[$(`#control-middle #select-dataset[which="x"]`).val()],
      datasetIdY: DATASET_DICT[$(`#control-middle #select-dataset[which="y"]`).val()],
      userNames: getHeaderInputValues("users"),
      measure: getHeaderInputValues("measure")
    }),
    success: function(data) {
      var layout = {
        margin: margin,
        legend: legend,
        font: font
      };

      Plotly.react('plot-middle', data, layout);
    }
  });
}

function updatePlotModelAuditTime() {
  $.ajax({
    url: "/mogger/api/v1/plot/modelAuditTime",
    type: "post",
    dataType: 'json',
    contentType: 'application/json; charset=utf-8',
    data: JSON.stringify({
      datasetId: DATASET_DICT[getFooterInputValues("dataset", "right")],
      userNames: getHeaderInputValues("users"),
      measure: getHeaderInputValues("measure")
    }),
    success: function(data) {
      var layout = {
        margin: margin,
        legend: legend,
        font: font
      };

      Plotly.react('plot-right', data, layout);
    }
  });
}

//:// helper functions

function getHeaderInputValues(inputName) {
  return $(`#select-${inputName}`).val();
}

function getFooterInputValues(inputName, where) {
  return $(`#control-${where} #select-${inputName}`).val();
}
