document.addEventListener('DOMContentLoaded', (event) => { // Wait for the DOM to load
    const statusSelect = document.getElementById("statusSelect");
    const interviewModal = document.getElementById("interviewModal");
    const saveButton = document.getElementById("saveButton");

    if (statusSelect && interviewModal && saveButton) {
        statusSelect.addEventListener("change", (event) => {
            if (event.target.value === "Interview") {
                interviewModal.classList.add("show");
            } else {
                interviewModal.classList.remove("show");
            }
        });

        saveButton.addEventListener("click", () => {
            const interviewDate = document.getElementById("interviewDate").value;
            const interviewTime = document.getElementById("interviewTime").value;

            // 2. Validate (Ensure date and time are selected)
            if (!interviewDate || !interviewTime) {
                alert("Please select both date and time.");
                return;
            }

            // 3. Store values (send to server via AJAX)
            fetch('/updateStatus', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams({
                    'id' : parseInt(document.getElementsByName("id")[0].value),                    'status': 'Interview', // Set the status to 'interviewed'
                    'interviewDate': interviewDate,
                    'interviewTime': interviewTime
                })
            })
                .then(response => response.text())
                .then(data => console.log(data))
                .catch((error) => {
                    console.error('Error:', error);
                });

            // 4. Close the modal (and optionally, submit the form)
            interviewModal.classList.remove("show");
        });
    } else {
        if (!saveButton) {
            console.error('saveButton element could not be found.');
        }
        console.error('One or more elements could not be found.');
    }
});