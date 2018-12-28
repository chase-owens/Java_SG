$(document).ready(function() {
  loadItems();
});

let amountDeposited = 0.0,
  amountDepositedDisplay = $("#deposit"),
  itemSelected = $("#itemSelected"),
  itemPrice = $("#itemPrice");

function loadItems() {
  let vendingMachine = $("#itemsContainer");
  vendingMachine.empty();
  amountDepositedDisplay.text(amountDeposited);

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/api/items",
    success: function(items) {
      items.forEach(item => {
        let name = item.name.replace(/\ /g, "+");
        let itemDiv = $('<div class="itemDiv"/>'),
          nameDiv = $(`<div class='nameDiv'><p>${item.name}</p></div>`),
          priceDiv = $(
            `<div class='priceDiv'>
            <p class="itemPrice">${item.price}</p>
            <button id="${name}" value=${item.price} price=${
              item.price
            } class="btn btn-info select">Select</button></div>`
          );
        itemDiv.append(nameDiv);
        itemDiv.append(priceDiv);
        vendingMachine.append(itemDiv);
      });
      listenForSelection();
    },
    error: function() {
      alert("Something went awry");
    }
  });
}

function processTransaction() {
  let deposit = amountDepositedDisplay.text();
  let selection = itemSelected.text();
  let url = `http://localhost:8080/api/purchase?money=${deposit}&selection=${selection}`;

  $.ajax({
    type: "POST",
    url: url,
    success: function(change) {
      giveChange(change);
    },
    error: function(err) {
      alert(
        JSON.stringify(err.responseJSON.message).replace(
          new RegExp('"', "g"),
          ""
        )
      );
    }
  });
}

function addMoney(amount) {
  amountDeposited = amountDeposited + parseFloat(amount);
  amountDepositedDisplay.text(amountDeposited.toFixed(2));
}

function zeroDeposit() {
  amountDeposited = 0;
  amountDepositedDisplay.text(amountDeposited.toFixed(2));
}

function selectItem(item) {
  let name = item.id.replace(/\+/g, " ");
  itemSelected.text(name);
  itemPrice.text(item.value);
}

function listenForSelection() {
  let items = $("#itemsContainer");
  items.on("click", ".select", function() {
    selectItem(this);
    $(".selectedItemDisplay").show();
  });
}

function giveChange(amountOwed) {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/api/change?money=${amountOwed}`,
    success: function(change) {
      giveRefund(amountOwed, change, true);
    },
    error: function(err) {
      console.log(err);
    }
  });
}

function getChangeToGive() {
  let amountOwed = amountDepositedDisplay.text();
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/api/change?money=${amountOwed}`,
    success: function(change) {
      giveRefund(amountOwed, change, false);
    },
    error: function(err) {
      console.log(err);
    }
  });
}

function giveRefund(total, changeMaker, wasATransaction) {
  let ones = changeMaker.one,
    quarters = changeMaker.quarter,
    dimes = changeMaker.dime,
    nickels = changeMaker.nickel,
    pennies = changeMaker.penny;

  if (wasATransaction) {
    alert(
      `Thank you! Your change = ${total} : Ones = ${ones}, Quarters = ${quarters}, Dimes = ${dimes}, Nickels = ${nickels}, Pennies = ${pennies}`
    );
  } else {
    alert(
      `Refund = ${total} : Ones = ${ones}, Quarters = ${quarters}, Dimes = ${dimes}, Nickels = ${nickels}, Pennies = ${pennies}`
    );
  }

  $(".selectedItemDisplay").hide();
  zeroDeposit();
}
