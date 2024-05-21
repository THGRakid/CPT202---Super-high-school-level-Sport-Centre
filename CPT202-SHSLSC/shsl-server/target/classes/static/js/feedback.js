function showFeedbackPopup() {
    var feedbackPopup = document.getElementById('feedback-popup');
    feedbackPopup.style.display = 'block';
}

function hideFeedbackPopup() {
    var feedbackPopup = document.getElementById('feedback-popup');
    feedbackPopup.style.display = 'none';
}

function submitFeedback() {
    var feedbackInput = document.getElementById('feedback-input').value;
    // Here you can add logic to submit feedback
    // For demonstration purpose, I'm just logging the input value
    console.log("Feedback submitted: " + feedbackInput);
    // Optionally, you can hide the popup after submitting feedback
    hideFeedbackPopup();
}
