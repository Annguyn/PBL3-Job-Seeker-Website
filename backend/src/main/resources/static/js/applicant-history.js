document.addEventListener('DOMContentLoaded', (event) => {
    var modal = document.getElementById("myModal");
    var span = document.getElementsByClassName("close")[0];
    var time = new Date(document.getElementById('interviewTime').value);
    var formattedTime = time.toLocaleDateString('en-GB', { day: '2-digit', month: '2-digit', year: 'numeric' }) + ' : ' + time.toLocaleTimeString();
    var labelWrappers = document.querySelectorAll('.label-wrapper');
    labelWrappers.forEach(function(labelWrapper) {
        labelWrapper.addEventListener('click', function() {
            var status = this.querySelector('.caption294').innerText;
            if (status === 'Interview') {
                document.getElementById('displayInterviewTime').innerText = 'Interview Time: ' + formattedTime;
                modal.style.display = "block";
            }
        });
    });

    if(span) {
        span.onclick = function() {
            modal.style.display = "none";
        }
    }

    window.onclick = function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
});