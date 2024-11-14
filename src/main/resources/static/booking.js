function todayDate(){

    // Add one day to today's date
    const today = new Date(Date());

    // Format date as YYYY-MM-DD
    const yearToday = today.getFullYear();
    const monthToday = String(today.getMonth() + 1).padStart(2, '0'); // Months are 0-based
    const dayToday = String(today.getDate()).padStart(2, '0');

    document.getElementById('date').value = `${yearToday}-${monthToday}-${dayToday}`;
    document.getElementById('date').setAttribute('min', `${yearToday}-${monthToday}-${dayToday}`);
}

function populateTimeOptions() {
    const now = new Date();
    const nowDate = now.getFullYear() + '-' + String(now.getMonth() + 1).padStart(2, '0') + '-' + String(now.getDate()).padStart(2, '0');
    const selectedDate = document.getElementById('date').value;

    const currentHour = now.getHours();
    const currentMinute = now.getMinutes();

    const timeSelect = document.getElementById('time');

    // Clear any existing options
    timeSelect.innerHTML = '';

    // Create optgroups for Morning, Afternoon, and Evening
    const morningGroup = document.createElement('optgroup');
    morningGroup.label = 'Morning';
    const afternoonGroup = document.createElement('optgroup');
    afternoonGroup.label = 'Afternoon';
    const eveningGroup = document.createElement('optgroup');
    eveningGroup.label = 'Evening';

    // Function to decide the optgroup based on the hour
    function getOptgroup(hour) {
        if (hour < 12) return morningGroup;
        if (hour < 18) return afternoonGroup;
        return eveningGroup;
    }

    // Populate time slots
    for (let hour = 7; hour <= 20; hour++) {
        for (let minute = 0; minute < 60; minute += 60) { // Increment by 60 minutes

            let isPast = false;
            if (nowDate === selectedDate) {
                isPast = hour < currentHour || (hour === currentHour && minute <= currentMinute);
            }


            // Skip past times
            if (isPast) continue;

            // Format time as HH:MM
            const formattedHour = String(hour).padStart(2, '0');
            const formattedMinute = String(minute).padStart(2, '0');
            const timeOption = `${formattedHour}:${formattedMinute}`;

            // Create option element
            const option = document.createElement('option');
            option.value = timeOption;
            option.textContent = timeOption;

            // Add option to the appropriate optgroup
            const optgroup = getOptgroup(hour);
            optgroup.appendChild(option);
        }
    }

    // Append optgroups to the select element
    if (morningGroup.children.length > 0) timeSelect.appendChild(morningGroup);
    if (afternoonGroup.children.length > 0) timeSelect.appendChild(afternoonGroup);
    if (eveningGroup.children.length > 0) timeSelect.appendChild(eveningGroup);

    // Get the availability list passed from the backend
    const availability = document.getElementById("availability").value;

    // Hide or show each optgroup based on availability
    if (!availability.includes("MORNING")) {
        morningGroup.style.display = "none";
    }

    if (!availability.includes("AFTERNOON")) {
        afternoonGroup.style.display = "none";
    }

    if (!availability.includes("EVENING")) {
        eveningGroup.style.display = "none";
    }
}


// Run above functions when the page loads
todayDate();
populateTimeOptions();