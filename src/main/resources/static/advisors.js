let debounceTimeout;

function debounceFilter() {
    // Cancela o timeout anterior se o usuário continuar digitando
    clearTimeout(debounceTimeout);

    // Define um novo timeout para chamar `filter` depois de 500ms (ajuste conforme necessário)
    debounceTimeout = setTimeout(() => {
        filter();
    }, 3000); // 500ms de atraso
}

// Function to handle change and trigger form submission
function filter() {
    const price = document.getElementById("price");

    // Capture the selected price range and extract min and max values
    let minCost = 0.00;
    let maxCost = 0.00;

    const priceValue = price.value;

    if (priceValue === "-- ALL --") {
        minCost = null;
        maxCost = null;
    } else if (priceValue === "price00_10") {
        minCost = -0.01;
        maxCost = 10.00;
    } else if (priceValue === "price10_20") {
        minCost = 10.01;
        maxCost = 20.00;
    } else if (priceValue === "price20_30") {
        minCost = 20.01;
        maxCost = 30.00;
    } else if (priceValue === "price_gt_30") {
        minCost = 30.01;
        maxCost = 999999.99; // or some large value
    }

    document.getElementById("minCost").value = minCost || '';
    document.getElementById("maxCost").value = maxCost || '';

    document.getElementById('frmAdvisors').submit();
}

// Listen for form changes and trigger form submission
document.addEventListener("DOMContentLoaded", function() {
    const category = document.getElementById("category");
    const availability = document.getElementById("availability");
    const price = document.getElementById("price");
    const name = document.getElementById("name");

    category.addEventListener("change", filter);
    availability.addEventListener("change", filter);
    price.addEventListener("change", filter);
    name.addEventListener("input", debounceFilter);
});

