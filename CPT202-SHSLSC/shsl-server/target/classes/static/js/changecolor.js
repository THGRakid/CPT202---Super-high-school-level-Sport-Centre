document.addEventListener("DOMContentLoaded", function() {
  const buttons = document.querySelectorAll('.sport-button');
  
  buttons.forEach(button => {
    button.addEventListener('click', function() {
      // Reset background color for all buttons
      buttons.forEach(btn => btn.classList.remove('active'));
      // Change background color for clicked button
      this.classList.add('active');
    });
  });
});
