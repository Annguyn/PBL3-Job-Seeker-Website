$(document).ready(function() {
    $('.button42').click(function() {
        var result = confirm("Do you want to delete this experience?");
        if (result) {
            var experienceId = $(this).data('experience-id');
            $.ajax({
                url: '/deleteExperience/' + experienceId,
                type: 'DELETE',
                success: function(result) {
                    $('#experience-' + experienceId).remove();
                }
            });
        }
    });
});
$(document).ready(function() {
    $(document).on('click', '.button44', function() { // Event delegation
        var result = confirm("Do you want to delete this education?");
        if (result) {
            var educationId = $(this).data('education-id');

            $.ajax({
                url: '/deleteEducation/' + educationId,
                type: 'DELETE',
                success: function(result) {
                    $(this).closest('.button44').remove();
                }
            });
        }
    });
});