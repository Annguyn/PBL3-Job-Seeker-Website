document.addEventListener('DOMContentLoaded', (event) => {
    var modal = document.getElementById("myModal");

    var btn = document.getElementById("applyButton");

    btn.onclick = function() {
        modal.style.display = "block";
    }
    var closeBtn = document.getElementsByClassName("icon56")[0];
    closeBtn.onclick = function() {
        modal.style.display = "none";
    }
    window.onclick = function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
});
document.addEventListener('DOMContentLoaded', (event) => {
    const modal = document.getElementById("predict-modal");
    const closeModal = document.getElementsByClassName('close')[0];
    const modalContent = document.getElementById('modal-content');

    // Hide the modal when the close button is clicked
    closeModal.onclick = function () {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }

    $(document).ready(function () {
        $('#predict-icon').on('click', function () {
            var postId = $(this).data('post-id');

            // Show the modal immediately with "Loading..." message
            modalContent.innerText = 'Loading...';
            modal.style.display = 'block';

            $.ajax({
                url: '/predict',  // replace with the URL of your prediction endpoint
                type: 'POST',
                data: {
                    postId: postId
                },
                success: function (predictedSalary) {
                    modalContent.innerText = 'Predicted salary: ' + predictedSalary + ' VND';
                },
                error: function () {
                    modalContent.innerText = 'An error occurred while predicting the salary.';
                }
            });
        });
    });
});
