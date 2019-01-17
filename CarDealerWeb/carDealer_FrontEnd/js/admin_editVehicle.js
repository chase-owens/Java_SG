$(document).ready(function() {
  getMakes();
  let id = window.location.hash.substring(1);
  getVehicle(id);
});

let id = window.location.hash.substring(1);

// Set up Form

function getMakes() {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/make/readAll`,
    success: function(makes) {
      getModelsByMake(loadMakes(makes));
    },
    error: function(err) {
      alert("error with makes");
    }
  });
}

function loadMakes(makes) {
  let makesDropdown = $("#make");
  makesDropdown.empty();
  makes.forEach(make => {
    makesDropdown.append(`<option value="${make.makeId}">
            ${make.makeName}
          </option>`);
  });
  return makes[0].makeId;
}

function getModelsByMake(makeId) {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/model/readAllByMakeId?makeId=${makeId}`,
    success: function(models) {
      loadModels(models);
    },
    failure: function(err) {
      console.log(err);
    }
  });
}

function getModelsByMakeId() {
  let makeId = $("#make option:selected").val();
  console.log(makeId);

  $.ajax({
    type: "GET",
    url: `http://localhost:8080/model/readAllByMakeId?makeId=${makeId}`,
    success: function(models) {
      loadModels(models);
    },
    failure: function(err) {
      console.log(err);
    }
  });
}

function loadModels(models) {
  let modelDropdown = $("#model");
  modelDropdown.empty();
  models.forEach(model => {
    modelDropdown.append(`<option value="${model.id}">
      ${model.modelName}
    </option>`);
  });
}

$("#make").on("select", getModelsByMakeId());

// Populate form with vehicle data

function getVehicle(vehicleId) {
  console.log(vehicleId);
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/vehicles/readOne?vehicleId=${vehicleId}`,
    success: function(vehicle) {
      loadVehicle(vehicle);
    },
    error: function(e) {
      console.log(e);
    }
  });
}

function loadVehicle(vehicle) {
  console.log(vehicle);
  // let makes = ;
  // let models = ;

  $("#make").selectedIndex = vehicle.make.makeName;
  $("#model").selectedIndex = vehicle.model.modelName;
  $("#type").selectedIndex = vehicle.vehicleType;
  $("#body-style").selectedIndex = vehicle.bodyStyle;
  $("#year").val(vehicle.vehicleYear);
  $("#transmission").val(vehicle.transmission);
  $("#exterior-color").val(vehicle.exteriorColor);
  $("#interior-color").val(vehicle.interiorColor);
  $("#mileage").val(vehicle.mileage);
  $("#vin").val(vehicle.vin);
  $("#sale-price").val(vehicle.listPrice);
  $("#msrp").val(vehicle.msrp);
  $("#description").text(vehicle.vehicleDescription);
  $("#picture").val(vehicle.image);
}

// Edit vehicle

function updateVehicle() {
  let data = {
    make: { makeId: $("#make option:selected").val() },
    model: {
      modelId: $("#model option:selected").val(),
      modelName: $("#model option:selected")
        .text()
        .trim()
    },
    vehicleType: $("#type option:selected").val(),
    bodyStyle: $("#body-style").val(),
    vehicleYear: $("#year").val(),
    transmission: $("#transmission option:selected").val(),
    exteriorColor: $("#exterior-color option:selected").val(),
    interiorColor: $("#interior-color option:selected").val(),
    mileage: $("#mileage").val(),
    vin: $("#vin").val(),
    msrp: $("#msrp").val(),
    listPrice: $("#sale-price").val(),
    vehicleDescription: $("#description").val(),
    image: $("#picture")
      .val()
      .substring(12),
    createdBy: { userId: "1" },
    vehicleId: id
  };

  let url = `http://localhost:8080/vehicles/edit`;

  console.log(data);

  $.ajax({
    type: "PUT",
    url: url,
    data: JSON.stringify(data),
    dataType: "json",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json; charset-utf-8"
    },
    success: function(success) {
      alert("vehicle updated");
    },
    error: function(err) {
      alert("vehicle updaetd");
    }
  });
}

function logOutUser() {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/user/logOut`,
    success: function(user) {
      hideDropdownAndShowLoginButton();
    },
    error: function(err) {
      hideLoginButtonAndShowDropdown();
    }
  });
}

function hideLoginButtonAndShowDropdown() {
  $("#login-btn").hide();
}

function hideDropdownAndShowLoginButton() {
  $("#user-dropdown").hide();
}

function showDropdownItems() {
  $("#user-dropdown-items").toggle();
}

function showAdminAndUserNavTabs() {
  $("#admin-nav").show();
  $("#sales-nav").show();
}

function showSalesNavTab() {
  $("#sales-nav").show();
}
