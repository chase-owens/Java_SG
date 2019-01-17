$(document).ready(function() {
  getAllSpecials();
});

function getAllSpecials() {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/specials/readAll",
    success: function(specials) {
      loadSpecials(specials);
      console.log(specials);
    },
    error: function() {
      alert("Error With Specials");
    }
  });
}

function loadSpecials(specials) {
  let specialsContainer = $("#specials-container");
  specialsContainer.empty();
  specials.forEach(special => {
    specialsContainer.append(`<div class="special">
    <div class="special-icon">
      <i class="fas fa-cash-register fa-7x"></i>
    </div>
    <div class="special-details">
      <div class="special-top">
        <h3 class="special-title">${special.title}</h3>
        <button class="delete-special-btn btn btn-danger" onclick="deleteThis(${
          special.specialId
        })">Delete</button>
      </div>
      <P class="special-description"
        >${special.specialDescription}</P
      >
    </div>
  </div>`);
  });
}

function createSpecial() {
  let title = $("#title").val(),
    description = $("#descripton").val();

  $.ajax({
    type: "POST",
    url: `http://localhost:8080/specials/create?title=${title}&description=${description}&vehicleId=2&userId=1`,
    success: function(special) {
      getAllSpecials();
    },
    error: function(err) {
      console.log("ERROR" + err);
    }
  });
}

function deleteThis(specialId) {
  $.ajax({
    type: "POST",
    url: `http://localhost:8080/specials/delete?id=${specialId}`,
    success: function(special) {
      getAllSpecials();
    },
    error: function(err) {
      console.log("ERROR" + err);
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
