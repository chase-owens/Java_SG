$(document).ready(function() {
  loadMovies();
});
const movieList = $(".movieList"),
  movieTitle = $("#moviesTitle");

function loadMovies() {
  movieList.empty();
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/api/movies",
    success: function(movies) {
      if (!movies.length) {
        console.log("0");
        movieTitle.text("No movies in library");
      } else {
        makeMovies(movies);
      }
    },
    error: function() {
      movieList
        .append($("<div />"))
        .append($('"<p />'))
        .text("Sorry there was an error");
    }
  });
}

function makeMovies(movies) {
  movieTitle.text("Movies");
  movies.forEach(movie => {
    let movieName = movie.title;
    formattedName = movieName.replace(/\ /g, "+");

    let movieDiv = $(`<div class='movieDiv'/>`);
    let btnDiv = $("<div class='btnDiv'/>");
    let nameDiv = $("<div class='nameDiv'/>");

    let detailsButton = $(
      `<button class='btn btn-info detailsBtn' value=${formattedName}>Details</button>`
    );

    let editButton = $(
      `<button class='btn btn-secondary editBtn' value=${formattedName}>Edit</button>`
    );
    let deleteButton = $(
      `<button class='btn btn-danger deleteBtn' value=${formattedName}>Delete</button>`
    );

    movieDiv.append(nameDiv);
    movieDiv.append(btnDiv);
    nameDiv.append($("<p class='movieName' />")).text(movieName);
    btnDiv
      .append(detailsButton)
      .append(editButton)
      .append(deleteButton);
    movieList.append(movieDiv);
  });
  checkButton();
}

function searchMovies(query) {
  const movieList = $(".movieList");
  movieList.empty();
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/api/search?query=${query}`,
    success: function(movies) {
      console.log(movies);
      if (!movies.length) {
        movieTitle.text("No matching movies");
      } else {
        makeMovies(movies);
      }
    },
    error: function() {
      movieList
        .append($("<div />"))
        .append($('"<p />'))
        .text("Sorry there was an error");
    }
  });
}

function addDVD() {
  let title = $("#toAddTitle").val(),
    releaseDate = $("#toAddReleaseDate").val(),
    MPAArating = $("#toAddMPAA").val(),
    director = $("#toAddDirector").val(),
    studio = $("#toAddStudio").val(),
    userRating = $("#toAddUserRating").val();
  console.log(title, releaseDate, MPAArating, director, studio, userRating);

  $.ajax({
    type: "POST",
    url: `http://localhost:8080/api/add?title=${title}&releaseDate=${releaseDate}&MPAArating=${MPAArating}&directorsName=${director}&studio=${studio}&userRating=${userRating}`,
    success: function(dvd) {
      displayNewDVD(dvd);
    },
    error: function() {
      console.log("Error");
    }
  });
  loadMovies();
}

function checkButton() {
  let movieList = $(".movieList");

  movieList.on("click", ".movieDiv .editBtn", function() {
    editDVD(this.value);
  });

  movieList.on("click", ".movieDiv .deleteBtn", function() {
    deleteDVD(this.value);
  });

  movieList.on("click", ".movieDiv .detailsBtn", function() {
    displayDetails(this.value);
  });
}

function editDVD(title) {
  let name = title.replace(/\+/g, " ");
  $(".movieToBeEdited").text(name);
  $(".editMovie").toggle();
}

function updateDVD() {
  let movieToBeEdited = $(".movieToBeEdited").text();
  let updatedRating = $("#newRating").val();

  $.ajax({
    type: "POST",
    url: `http://localhost:8080/api/edit?title=${movieToBeEdited}&newRating=${updatedRating}`,
    success: function(dvd) {
      displayUpdatedDVD(dvd);
    },
    error: function() {
      alert("ERROR");
    }
  });

  $(".editMovie").toggle();
  $("#newRating").val("");
}

function deleteDVD(title) {
  $.ajax({
    type: "DELETE",
    url: `http://localhost:8080/api/remove?title=${title}`,
    success: function() {
      loadMovies();
    },
    error: function() {
      alert("ERROR");
    }
  });
}

function search(query) {
  searchMovies(query);
}

function displayNewDVD(dvd) {
  $("#movieContainerTitle").text("New DVD");
  displayDVD(dvd);
}

function displayUpdatedDVD(dvd) {
  $("#movieContainerTitle").text("Updated DVD");
  displayDVD(dvd);
}

function displayDetails(dvd) {
  $("#movieContainerTitle").text("Details");
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/api/movie?title=${dvd}`,
    success: function(movie) {
      displayDVD(movie);
    },
    error: function() {
      alert("ERROR");
    }
  });
}

function closeMe() {
  $(".movieContainer").hide();
}

function displayDVD(dvd) {
  $("#dvdName").text(dvd.title);
  $("#dvdReleaseDate").text(dvd.date);
  $("#dvdMPAA").text(dvd.mpaarating);
  $("#dvdDirector").text(dvd.directorsName);
  $("#dvdStudio").text(dvd.studio);
  $("#dvdCustomRating").text(dvd.rating);

  $(".movieContainer").toggle();
}
