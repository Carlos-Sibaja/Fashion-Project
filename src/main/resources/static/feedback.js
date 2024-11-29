function toggleStar(star) {
    const selectedSrc = "/images/star_selected.png";
    const unselectedSrc = "/images/star_unselected.png";

    let numStars = parseInt(document.getElementById("booking_ratingStars").value);

    if (star.src.includes("star_unselected.png")) {
        star.src = selectedSrc;
        numStars +=1;
    } else {
        star.src = unselectedSrc;
        numStars -=1;
    }
    document.getElementById("booking_ratingStars").value = numStars;

}

function toggleAdjectiveColor(cell) {
    const selectedColor = "rgb(230, 199, 189)";
    const defaultColor = "white"; // Default background color (e.g., no color)

    // Get the value from the input field
    let inputText = document.getElementById("booking_review").value;

    // Process the text into an array
    let listAdj = []
    if (inputText !== ""){
        listAdj = inputText
            .replace(/[();']/g, '') // Remove parentheses, semicolon, and quotes
            .split(',');            // Split by dot notation
    }

    // Toggle the background color
    if (cell.style.backgroundColor === selectedColor) {
        cell.style.backgroundColor = defaultColor;
        listAdj = listAdj.filter(item => item !== cell.getAttribute('data-value'));
    } else {
        cell.style.backgroundColor = selectedColor;
        listAdj[listAdj.length] = cell.getAttribute('data-value');
    }

    console.log(cell.getAttribute('data-value'));
    console.log(listAdj);
    // Convert the array back into a string
    let stringContent = listAdj.join(','); // Join array elements with a comma

    // Write the string into the input field
    document.getElementById("booking_review").value = stringContent;
}