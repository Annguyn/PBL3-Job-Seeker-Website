$(document).ready(function() {
    $('.button42').click(function() {
        var result = confirm("Do you want to delete this experience?");
        if (result) {
            // Assuming the experience id is stored in a data attribute
            var experienceId = $(this).data('experience-id');
            $.ajax({
                url: '/deleteExperience/' + experienceId,
                type: 'DELETE',
                success: function(result) {
                    // Remove the experience from the UI
                    $('#experience-' + experienceId).remove();
                }
            });
        }
    });
});
$(document).ready(function() {
    $('.button44').click(function() {
        var result = confirm("Do you want to delete this education?");
        if (result) {
            // Assuming the education id is stored in a data attribute
            var educationId = $(this).data('education-id');
            $.ajax({
                url: '/deleteEducation/' + educationId,
                type: 'DELETE',
                success: function(result) {
                    // Remove the education from the UI
                    $('#education-' + educationId).remove();
                }
            });
        }
    });
});