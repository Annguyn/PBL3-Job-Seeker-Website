function confirmDelete(imgElement) {
    var postId = imgElement.getAttribute('data-id');
    var result = confirm("Do you want to delete this recruitment?");
    if (result) {
        fetch('/post/' + postId, {
            method: 'DELETE',
        }).then(response => {
            if (response.ok) {

                imgElement.parentElement.remove();
            } else {
                alert('An error occurred while deleting the post.');
            }
        });
    }
}