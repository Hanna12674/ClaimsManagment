// src/static/js/scripts.js

// Fetch data dynamically:
fetch('/api/claims')
    .then(response => response.json())
    .then(data => {
        const table = document.querySelector('#claimsTable');
        data.forEach(claim => {
            const row = document.createElement('tr');
            row.innerHTML = `<td>${claim.id}</td><td>${claim.customerName}</td><td>${claim.status}</td>`;
            table.appendChild(row);
        });
    });


// Form validation script for claim submission
document.addEventListener("DOMContentLoaded", function() {
    // Example: Check form submission
    const claimForm = document.querySelector("form");
    if (claimForm) {
        claimForm.addEventListener("submit", function(event) {
            const claimAmount = document.querySelector("#amount").value;
            const description = document.querySelector("#description").value;
            if (!claimAmount || !description) {
                alert("Please fill out all required fields.");
                event.preventDefault();
            }
        });
    }
});


// Using jQuery for form validation claim amount
$(document).ready(function() {
    $("form").on("submit", function(event) {
        var claimAmount = $("#amount").val();
        if (!claimAmount) {
            alert("Claim amount is required!");
            event.preventDefault();
        }
    });
});