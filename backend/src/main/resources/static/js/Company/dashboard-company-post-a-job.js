function addLanguageTag(event) {
    event.preventDefault();

    var dropdown = document.getElementById('languageDropdown');
    var selectedOption = dropdown.options[dropdown.selectedIndex];
    var tagsDiv = document.getElementById('languageTags');

    var newTag = document.createElement('span');
    newTag.textContent = selectedOption.text;
    newTag.classList.add('tag');

    tagsDiv.appendChild(newTag);
}

function addCategoryTag(event) {
    event.preventDefault();

    var dropdown = document.getElementById('categoryDropdown');
    var selectedOption = dropdown.options[dropdown.selectedIndex];
    var tagsDiv = document.getElementById('categoryTags');

    var newTag = document.createElement('span');
    newTag.textContent = selectedOption.text;
    newTag.classList.add('tag');

    tagsDiv.appendChild(newTag);
}