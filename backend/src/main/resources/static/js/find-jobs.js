$(document).ready(function() {
    $('#showMoreButton').click(function() {
        var moreContent = $('#moreContent');
        if (moreContent.css('display') === 'none') {
            moreContent.show();
        } else {
            moreContent.hide();
        }
    });
});
$(document).ready(function() {
    $('#toggleButton').click(function() {
        var levelsContainer = $('#levelsContainer');
        if (levelsContainer.css('display') === 'none') {
            levelsContainer.css('display', 'block');
        } else {
            levelsContainer.css('display', 'none');
        }
    });
});
window.onload = function() {
    document.getElementById('toggleButtonSalary').addEventListener('click', function() {
        var salaryContainer = document.getElementById('salaryContainer');
        if (salaryContainer.style.display === 'none') {
            salaryContainer.style.display = 'block';
        } else {
            salaryContainer.style.display = 'none';
        }
    });
};
function toggleOptions() {
    var otherOptions = document.getElementById("otherOptions");
    if (otherOptions.style.display === "none") {
        otherOptions.style.display = "block";
        otherOptions.classList.add("show");
    } else {
        otherOptions.style.display = "none";
        otherOptions.classList.remove("show");
    }
}
